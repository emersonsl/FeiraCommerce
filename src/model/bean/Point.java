package model.bean;

public class Point {
    private final Long id;
    private final double latitude;
    private final double longitude;
   
    public Point(long id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }   

    public Long getId() {
        return id;
    }
    
    @Override
    public String toString(){
        return "{\"lat\":"+latitude+",\"lgt\":"+longitude+"}";
    }
}
