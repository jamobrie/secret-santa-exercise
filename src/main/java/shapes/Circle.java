package shapes;

public class Circle implements Shape {

    private static final double pie = Math.PI;
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        double area = radius * radius * pie;

        System.out.println("Area is: " + area);

        return area;
    }

    @Override
    public double calculatePerimeter() {
        double perimeter = 2 * radius * pie;

        System.out.println("Area is: " + perimeter);

        return perimeter;
    }

}
