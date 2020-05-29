import java.util.Random;

public class Point {
    private double x;
    private double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double distance(Point a, Point b){

        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    public Point of(double a, double b){
        return new Point(a,b);
    }

    public Point random (int bound){
        double x1 = new Random().nextDouble() * bound;
        double y1 = new Random().nextDouble() * bound;
        return of(x1, y1);    }
}
