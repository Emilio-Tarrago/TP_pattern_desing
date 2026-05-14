import java.util.concurrent.ThreadLocalRandom;

public class Bicicleta implements TransportStrategy{
    private TransportSnapshot transportSnapshot;
    private double costMin = 3000;
    private double  costMax = 4500;

    public Bicicleta(double distance){ //distance<=10km
        transportSnapshot = new TransportSnapshot();
        //En Bicilceta se hacen 1km entre 4 y 7 minutos
        int ETA = (int)(distance*ThreadLocalRandom.current().nextDouble(4,7));
        // Valor aleatoreo de costo por hora [3000--4500]$
        double cost = ThreadLocalRandom.current().nextDouble(costMin, costMax+1)/3600;
        // Valores por default
        transportSnapshot.setName("Bicicleta");
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
