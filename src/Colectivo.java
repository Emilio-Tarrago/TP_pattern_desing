import java.util.concurrent.ThreadLocalRandom;

public class Colectivo implements TransportStrategy{
    private TransportSnapshot transportSnapshot;
    private double costMin = 4000;
    private double  costMax = 6000;

    public Colectivo(double distance){ //distance>20km && distance<1000km
        transportSnapshot = new TransportSnapshot();
        //En colectivo se hacen 1km entre 1.5 y 3 minutos
        int ETA = (int)(distance*ThreadLocalRandom.current().nextDouble(1.5,3));
        // Valor aleatoreo de costo por hora [4000--6000]$
        double cost = ThreadLocalRandom.current().nextDouble(costMin, costMax+1)/3600;
        transportSnapshot.setName("Colectivo");
        transportSnapshot.setCost(cost); //cost en $/seg
        transportSnapshot.setDistance(distance);//distancia en km
        transportSnapshot.setETA(ETA); //ETA en minutos
    }

    public TransportSnapshot getTransportSnapshot() {
        return transportSnapshot;
    }

    @Override
    public String getName() {
        return transportSnapshot.getName();
    }
    @Override
    public double getCost() {
        return transportSnapshot.getCost();
    }
    @Override
    public double getDistance() {
        return transportSnapshot.getDistance();
    }
    @Override
    public int getETA() {
        return transportSnapshot.getETA();
    }
}
