package shapes;

import java.util.Objects;

public class Circle implements Shape {

    private static final double pi = Math.PI;
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        double area = radius * radius * pi;

        System.out.println("Area is: " + area);

        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Shape)) return false;//add another layer on the object equality to sure their class types match
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 && this.radius == radius;//Assuming you want to check for other values between both objects match
        //Could also do a NULL check on the boolean like so and just add to the return immediately above:

//        boolean currencyCodeEquals = (this.currencyCode == null && other.currencyCode == null)
//                || (this.currencyCode != null && this.currencyCode.equals(other.currencyCode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    //TODO Jimmy Revisit toString and hashcode here
    @Override
    public double calculatePerimeter() {
        double perimeter = 2 * radius * pi;

        System.out.println("Area is: " + perimeter);

        return perimeter;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
    //e.g. of a more refined example of the toString()
    //Over-riding the toString() function as a class function
//    public String toString(){
//        return "The name of the pet is " + this.name + ". The age of the pet is " + this.age;
//    }


}
