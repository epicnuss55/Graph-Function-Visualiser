import javax.swing.*;
import java.awt.*;


//All calculations are done here

public class application extends JPanel {

    //variables innit
    private final int type;
    private final int a;
    private final int b;
    private final int c;
    private final int size;
    public boolean W = false;
    public boolean A = false;
    public boolean S = false;
    public boolean D = false;
    private int xAxisDisplacement = 0;
    private int yAxisDisplacement = 0;

    public void paintComponent(Graphics g) {


        //Remove this for ugly art
        super.paintComponent(g);


        //Yes, this isn't the best way but im keeping it for now
        int maximum = 7500;


        //Draws the X and Y line
        g.setColor(Color.RED);
        g.drawLine(getWidth() / 2 + xAxisDisplacement, -maximum * size + yAxisDisplacement, getWidth() / 2 + xAxisDisplacement, maximum * size + yAxisDisplacement);
        g.drawLine(-maximum * size + xAxisDisplacement, getHeight() / 2 + yAxisDisplacement, maximum * size + xAxisDisplacement, getHeight() / 2 + yAxisDisplacement);

        //Draws the rest of the grid
        g.setColor(Color.LIGHT_GRAY);
        //X lines
        for (int x = -maximum; x != maximum; x++) {
            if (x == 0) {
                continue;
            }
            g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, -maximum * size + yAxisDisplacement, getWidth() / 2 + x * size + xAxisDisplacement, maximum * size + yAxisDisplacement);
        }
        //Y lines
        for (int y = -maximum; y != maximum; y++) {
            if (y == 0) {
                continue;
            }
            g.drawLine(-maximum * size + xAxisDisplacement, getHeight() / 2 + y * size + yAxisDisplacement,
                    maximum * size + xAxisDisplacement, getHeight() / 2 + y * size + yAxisDisplacement);
        }


        //sets grid line numbers
        g.setColor(Color.BLACK);
        for (int y = -1; y != -maximum; y--) {
            g.drawString(String.valueOf(-y), getWidth() / 2 + xAxisDisplacement, getHeight() / 2 + y * size + yAxisDisplacement);
        }
        for (int x = 1; x != maximum; x++) {
            g.drawString(String.valueOf(x), getWidth() / 2 + x * size - 10 + xAxisDisplacement, getHeight() / 2 + yAxisDisplacement);
        }
        for (int negY = 1; negY != maximum; negY++) {
            g.drawString(String.valueOf(-negY), getWidth() / 2 + xAxisDisplacement, getHeight() / 2 + negY * size + yAxisDisplacement);
        }
        for (int negX = -1; negX != -maximum; negX--) {
            g.drawString(String.valueOf(negX), getWidth() / 2 + negX * size + xAxisDisplacement, getHeight() / 2 + yAxisDisplacement);
        }


        //The actual graph calculator
        g.setColor(Color.BLUE);

        //checks the type
        switch (type) {

            //Turning point (parabola)
            case 1 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow(x + b, 2) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow((x + 1) + b, 2) + c) * size + yAxisDisplacement);
                }
            }

            //X-intercept (parabola)
            case 2 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + -((x + a) * (x + b) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + -(((x + 1) + a) * ((x + 1) + b) + c) * size + yAxisDisplacement);
                }
            }

            //Circle graph
            case 3 -> g.drawOval(getWidth() / 2 + a * size - c * size + xAxisDisplacement, getHeight() / 2 + -(b * size) - (c * size) + yAxisDisplacement, c * 40, c * 40);

            //exponential graph
            case 4 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + (int) -(Math.pow(a, x + b) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + (int) -(Math.pow(a, (x + 1) + b) + c) * size + yAxisDisplacement);
                }
            }

            //power graph (exponential graph with less steps)
            case 5 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow(x, b) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow((x + 1), b) + c) * size + yAxisDisplacement);
                }
            }

            //linear graph
            case 6 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + -(a * (x + b) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + -(a * ((x + 1) + b) + c) * size + yAxisDisplacement);
                }
            }

            //Cube
            case 7 -> {
                for (int x = -maximum; x != maximum; x++) {
                    g.drawLine(getWidth() / 2 + x * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow((x + b),3) + c) * size + yAxisDisplacement,
                            getWidth() / 2 + (x + 1) * size + xAxisDisplacement, getHeight() / 2 + (int) -(a * Math.pow(((x + 1) + b),3) + c) * size + yAxisDisplacement);
                }
            }
        }
    }


    //Gives all the variables their respective values
    public application(int type, int a, int b, int c, int size, int speed) {
        this.type = type;
        this.a = a;
        this.b = b;
        this.c = c;
        this.size = size;

        //repaint loop
        Thread paint = new Thread(() -> {
            while (true){
                repaint();

                try {Thread.sleep(15);} catch (InterruptedException ignored) {}

                if(W){
                    yAxisDisplacement+=speed;
                }
                if(S){
                    yAxisDisplacement-=speed;
                }
                if(A){
                    xAxisDisplacement+=speed;
                }
                if(D){
                    xAxisDisplacement-=speed;
                }
            }
        });

        paint.start();
    }
}