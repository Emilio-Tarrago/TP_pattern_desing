import java.util.concurrent.ThreadLocalRandom;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Logger logger = Logger.getInstance();
        TransportMonitor transportMonitor = new TransportMonitor();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        AlertObserver alertObserver = new AlertObserver();
        transportMonitor.subcribe(consolePrinter);
        transportMonitor.subcribe(alertObserver);
        for (int i = 0; i<15; i++){
            //intervalo random de tiempo entre 5 y 60 segundos
            int intervalMs = ThreadLocalRandom.current().nextInt(5000,60000+1);
            transportMonitor.start(intervalMs);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //pruebas
            //cambio de strategy
            if(i==5) transportMonitor.setStrategy(new Bicicleta(20));
            if(i==10) transportMonitor.setStrategy(new Taxi(32));
            //cambio de umbrales
            if(i==8){
                alertObserver.setUmbralCost(150);
                alertObserver.setUmbralETA(20);
            }
        }
    }
}