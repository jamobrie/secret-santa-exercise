package shapes;

import java.util.Objects;

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

    //TODO Jimmy Revisit toString and hashcode here
    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.length, length) == 0 && Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }
}
