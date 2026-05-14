public class ConsolePrinter implements TransportObserver{
    private Logger logger;
    public ConsolePrinter(){
        this.logger = Logger.getInstance();
    }
    @Override
    public void onUpdate(TransportSnapshot transportSnapshot) {
        String msgInfo = String.format(
                "Se está ejecutando la estrategia de %s", transportSnapshot.getName());
        String msgDebug = String.format(
                "Distancia: %.2f km. Costo: %.2f",
                transportSnapshot.getDistance(),
                transportSnapshot.getCost());
        logger.logInfo(msgInfo);
        logger.logDebug(msgDebug);
    }
}
