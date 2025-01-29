import java.awt.*;
import java.util.*;

public class ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 3;


    ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXdirection = random.nextInt(2);
        if (randomXdirection == 0)
            randomXdirection--;
        setXdirection(randomXdirection*initialSpeed);

        int randomYdirection = random.nextInt(2);
        if (randomYdirection == 0)
            randomYdirection--;
        setYdirection(randomYdirection*initialSpeed);
    }


    public void setXdirection(int randomXdirection) {
        xVelocity = randomXdirection;
    }


    public void setYdirection(int randomYdirection) {
        yVelocity = randomYdirection;
    }


    public void move() {
        x += xVelocity;
        y += yVelocity;
    }


    public void draw(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x, y, width, height);
    }


}
