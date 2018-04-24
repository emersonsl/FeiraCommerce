package model.bean;

public class SpatialObject {

    private Point point;
    private String type;
    private String name;

    public SpatialObject() {

    }
    
    public SpatialObject(Point point) {
        this.point = point;
    }

    public SpatialObject(String name, String type, Point point) {
        this.name = name;
        this.type = type;
        this.point = point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }

    public Point getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public boolean isValid(){
        return type!=null && point!=null;
    }
    
    public String toString(){
        return "{\n\"name\":\""+name+"\","+
                "\n\"type\":\""+type+"\","+
                "\n\"point\":"+point.toString()+"\n}";
                
    }
}
