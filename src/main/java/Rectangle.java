import Geometry.Shape;

public class Rectangle implements Shape {
    private Point bl;
    private Point tr;
    private Point br;
    private Point tl;
    private double height;
    private double width;

    public Rectangle(Point bl, double b, double c) {
        this.bl = bl;
        height = b;
        width = c;
    }

    public Point getTopRight(){
        return tr;
    }

    public Point getBottomLeft(){
        return bl;
    }

    public Point getBottomRight(){
        return br;
    }

    public Point getTopLeft(){
        return tl;
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public double perimeter() {
        return 0;
    }
}
