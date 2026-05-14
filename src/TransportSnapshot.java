public class TransportSnapshot {
    private String name;
    private double cost;
    private double distance;
    private int ETA;
    public TransportSnapshot(){
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public void setETA(int ETA) {
        this.ETA = ETA;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getCost() {
        return cost;
    }
    public double getDistance() {
        return distance;
    }
    public int getETA() {
        return ETA;
    }
    public String getName() {
        return name;
    }
}
