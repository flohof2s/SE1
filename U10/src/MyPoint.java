package U10.src;

import java.util.Objects;

public class MyPoint {
    private double x,y;
    public MyPoint(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Checks if this MyPoint is more at the bottom-left than "compare"
    public boolean comparelb(MyPoint compare){
        return this.getX()<=compare.getX() && this.getY()<=this.getY();
    }

    // Checks if this MyPoint is more at the right-top than "compare"
    public boolean comparert(MyPoint compare){
        return this.getX()>=compare.getX() && this.getY()>=this.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPoint myPoint = (MyPoint) o;
        return Double.compare(myPoint.x, x) == 0 && Double.compare(myPoint.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "MyPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
