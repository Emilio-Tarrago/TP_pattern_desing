import java.util.concurrent.ThreadLocalRandom;

public class Taxi implements TransportStrategy{
    private TransportSnapshot transportSnapshot;
    private double costMin = 6000;
    private double  costMax = 9000;

    public Taxi(double distance){ //distance>2km && distance<40km
        transportSnapshot = new TransportSnapshot();
        //En Taxi se hacen 1km en 1 minutos
        int ETA = (int)distance;
        // Valor aleatoreo de costo por hora [6000--9000]$
        double cost = ThreadLocalRandom.current().nextDouble(costMin, costMax+1)/3600;
        //se le podria agregar una bajada de bandera en
        // Valores por default
        transportSnapshot.setName("Taxi");
        transportSnapshot.setCost(cost); //cost en $/seg
        transportSnapshot.setDistance(distance);//distancia en km
        transportSnapshot.setETA(ETA); //ETA en minutos
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
