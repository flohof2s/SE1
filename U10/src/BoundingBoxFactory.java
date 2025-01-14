package U10.src;

public class BoundingBoxFactory {
    public static MyPrettyRectangle calcBoundingBox(MyPrettyRectangle[] rectangles){
        if(rectangles == null){
            return null;
        }
        MyPoint lb = new MyPoint(0.0,0.0);
        MyPoint rt = new MyPoint(0.0,0.0);

        boolean initPoints = false;
        for(MyPrettyRectangle rect : rectangles){
            if(!initPoints){
                lb = rect.getLb();
                rt = rect.getRt();
                initPoints=true;
            }

            if(rect.getLb().getX()<lb.getX()){
                lb.setX(rect.getLb().getX());
            }
            if(rect.getLb().getY()<lb.getY()){
                lb.setY(rect.getLb().getY());
            }
            if(rect.getRt().getX()>rt.getX()){
                rt.setX(rect.getRt().getX());
            }
            if(rect.getRt().getY()>rt.getY()){
                rt.setY(rect.getRt().getY());
            }

        }

        return new MyPrettyRectangle(lb,rt);
    }
}
