import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.logInfo("Iniciando aplicación de simulación de transporte...");
        TransportMonitor transportMonitor = new TransportMonitor();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        AlertObserver alertObserver = new AlertObserver();
        transportMonitor.subcribe(consolePrinter);
        transportMonitor.subcribe(alertObserver);
        Thread monitorThread = new Thread(() -> {
            try {
                while (true) {
                    int intervalMs = ThreadLocalRandom.current().nextInt(2000, 5000);
                    transportMonitor.start(intervalMs);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                logger.logInfo("Monitor detenido.");
            }
        });
        monitorThread.start();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        System.out.println("=== MENÚ DE CONTROL ===");
        System.out.println("1: Cambiar a Taxi");
        System.out.println("2: Cambiar a Colectivo");
        System.out.println("3: Cambiar a Bicicleta");
        System.out.println("4: Cambiar Umbral de Costo");
        System.out.println("5: Cambiar Umbral de ETA");
        System.out.println("0: Salir de la aplicación");

        while (!salir) {
            String input = scanner.nextLine();
            double distanciaAleatoria = ThreadLocalRandom.current().nextDouble(10, 30);

            switch (input) {
                case "1":
                    transportMonitor.setStrategy(new Taxi(distanciaAleatoria));
                    logger.logInfo(">>> El usuario cambió la estrategia a TAXI");
                    break;
                case "2":
                    transportMonitor.setStrategy(new Colectivo(distanciaAleatoria));
                    logger.logInfo(">>> El usuario cambió la estrategia a COLECTIVO");
                    break;
                case "3":
                    transportMonitor.setStrategy(new Bicicleta(distanciaAleatoria));
                    logger.logInfo(">>> El usuario cambió la estrategia a BICICLETA");
                    break;
                case "4":
                    double costAleatorio = ThreadLocalRandom.current().nextDouble(100, 200);//valores mas bajos para que se vean los logs
                    alertObserver.setUmbralCost(costAleatorio);
                    break;
                case "5":
                    int etaAleatorio = ThreadLocalRandom.current().nextInt(10, 30);//valores mas bajos para que se vean los logs
                    alertObserver.setUmbralETA(etaAleatorio);
                    break;
                case "0":
                    salir = true;
                    monitorThread.interrupt(); // Frena el bucle del monitor
                    logger.logInfo("Apagando el sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Ingrese 1, 2, 3 o 0 para salir.");
            }
        }
        scanner.close();
    }
}
