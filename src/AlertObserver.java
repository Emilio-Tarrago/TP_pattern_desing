public class AlertObserver implements TransportObserver{
    private Logger logger;
    private double umbralCost;
    private int umbralETA;
    public AlertObserver(){
        this.logger = Logger.getInstance();
        //valores por default
        umbralCost = 500; //$
        umbralETA = 35; //min
    }
    public void setUmbralCost(double umbralCost) {
        System.out.println("-----Se modifico el Umbral de Cost"+ umbralCost + "-----");
        this.umbralCost = umbralCost;
    }
    public void setUmbralETA(int umbralETA) {
        System.out.println("-----Se modifico el Umbral de ETA a " + umbralETA + "-----");
        this.umbralETA = umbralETA;
    }
    @Override
    public void onUpdate(TransportSnapshot transportSnapshot) {
        if(transportSnapshot.getCost() > umbralCost){
            String msgWarning ="Se está superando el presupuesto $$$";
            logger.logWarning(msgWarning);
        }
        if (transportSnapshot.getETA() > umbralETA){
            String msgError ="Se está superando el tiempo estimado "+ transportSnapshot.getETA() + " minutos";
            logger.logError(msgError);
        }
    }
}
