import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.55));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_SIZE = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final int POINTS_TO_WIN = 2;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    ball ball;
    Score score;
    boolean singlePlayerMode;


    Panel(boolean singlePlayerMode) {
        this.singlePlayerMode = singlePlayerMode;
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new LISTENER());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }


    public void newBall() {
        random = new Random();
        ball = new ball((GAME_WIDTH/2)-(BALL_SIZE/2), random.nextInt(GAME_HEIGHT-BALL_SIZE), BALL_SIZE, BALL_SIZE);
    }


    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }


    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }


    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }


    public void move() {
        paddle1.move();
        if (singlePlayerMode) {
            aiMovePaddle2();
        } else{
            paddle2.move();
        }
        ball.move();
    }


    private void aiMovePaddle2() {
        if (ball.y < paddle2.y) {
            paddle2.setYdirection(-paddle2.speed);
        } else if (ball.y > paddle2.y + PADDLE_HEIGHT) {
            paddle2.setYdirection(paddle2.speed);
        } else {
            paddle2.setYdirection(0);
        }
        paddle2.move();
    }


    public void checkCollision() {

        // pilka odbija sie od gory i dolu ekranu
        if(ball.y <=0) {
            ball.setYdirection(-ball.yVelocity);
        }

        if(ball.y >= GAME_HEIGHT-BALL_SIZE) {
            ball.setYdirection(-ball.yVelocity);
        }

        //pilka odbija sie od paletek
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXdirection(ball.xVelocity);
            ball.setYdirection(ball.yVelocity);
        }

        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXdirection(-ball.xVelocity);
            ball.setYdirection(ball.yVelocity);
        }

        // sprawdza czy paletki nie wyjechaly za ekran

        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        //give a player 1 point and creates new paddles & ball

        if(ball.x <=0) {
            score.player2score++;
            score.checkWinner();
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2score);
        }

        if(ball.x >= GAME_WIDTH-BALL_SIZE) {
            score.player1score++;
            score.checkWinner();
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1score);
        }
    }


    public void run() {
        // petla gry
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (!score.gameEnded) {  // Gra nie będzie kontynuowana po zakończeniu
            long now = System.nanoTime();
            delta = delta + (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta = delta - 1;
            }
        }
    }



    public class LISTENER extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

}
