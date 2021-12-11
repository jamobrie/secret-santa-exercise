package shapes;

public class Rectangle implements Shape {

    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        double area = length * width;
        System.out.println("Area of rectangle is: " + area);
        return area;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = (length + width) * 2;
        System.out.println("Area of rectangle is:"  + perimeter);
        return perimeter;
    }

}
