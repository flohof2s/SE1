package U10.src;

import java.util.Objects;

public class MyPrettyRectangle {
    private MyPoint lb, rt; // lb: left-bottom ; rt: right-top

    public MyPrettyRectangle(double x1, double y1, double x2, double y2){
        this(new MyPoint(x1,y1),new MyPoint(x2,y2));
    }

    public MyPrettyRectangle(MyPoint lb, MyPoint rt){
        this.lb = lb;
        this.rt = rt;
    }

    public boolean contains(MyPrettyRectangle compare){
        return this.lb.comparelb(compare.getLb()) && this.rt.comparert(compare.getRt());
    }

    public MyPoint getCenter(){
        double x = (this.getLb().getX()+this.getRt().getX())/2;
        double y = (this.getLb().getY()+this.getRt().getY())/2;
        return new MyPoint(x,y);
    }

    public double getArea(){
        return Math.abs(this.lb.getX()-this.rt.getX()) * Math.abs(this.lb.getY()-this.rt.getY());
    }

    public double getPerimeter(){
        return (Math.abs(this.lb.getX()-this.rt.getX()) + Math.abs(this.lb.getY()-this.rt.getY()))*2;
    }

    public MyPoint getLb() {
        return lb;
    }
    public MyPoint getRt() {
        return rt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPrettyRectangle that = (MyPrettyRectangle) o;
        return getLb().equals(that.getLb()) && getRt().equals(that.getRt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLb(), getRt());
    }

    @Override
    public String toString() {
        return "MyPrettyRectangle{" +
                "lb=" + lb +
                ", rt=" + rt +
                '}';
    }
}
