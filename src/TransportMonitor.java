import java.util.ArrayList;

public class TransportMonitor implements Subject{
    public ArrayList<TransportObserver> observers;
    public TransportStrategy transportStrategy;
    private TransportSnapshot transportSnapshot;
    public TransportMonitor(){
        observers = new ArrayList<>();
        transportSnapshot = new TransportSnapshot();
        setStrategy(new Colectivo(50)); //valor por defecto
    }
    public void setStrategy(TransportStrategy strategy){
        transportStrategy = strategy;
        // Armo el Snapshot de la strategy
        transportSnapshot.setName(strategy.getName());
        transportSnapshot.setCost(0);//costo inicial estimado empieza en 0
        transportSnapshot.setDistance(strategy.getDistance());
        transportSnapshot.setETA(strategy.getETA());
    }
    @Override
    public void subcribe(TransportObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unsubcribe(TransportObserver observer) {
        int i = observers.indexOf(observer);
        if (i >= 0){
            observers.remove(observer);
        }
    }
    public void recalcular(int intervalMs){
        int newETA = transportSnapshot.getETA()-(int)intervalMs/1000;// resto el tiempo que ya transcurrio
        //Controlar que no quede negativo el ETA
        transportSnapshot.setETA(newETA); //piso el valor actual
        double newCost = transportStrategy.getCost()*intervalMs/1000; //delta de aumento
        transportSnapshot.setCost(transportSnapshot.getCost()+newCost);//sumo el viejo con el nuevo
    }
    @Override
    public void start(int intervalMs) {
        // recalculo el TransportSnapshot
        recalcular(intervalMs);
        //updateo observers
        for (int i=0; i < observers.size(); i++){
            TransportObserver observer = observers.get(i);
            observer.onUpdate(transportSnapshot);
        }
    }
}
