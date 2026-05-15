import java.sql.Timestamp;
import java.util.concurrent.Semaphore;

public class Logger {
    private static Logger instance = null;
    private static Semaphore semaphore = new Semaphore(1);
    public static final String RESET = "\u001B[0m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String VERDE = "\u001B[32m";
    public static final String GRIS = "\u001B[90m";
    public static final String ROJO = "\u001B[31m";
    private Logger(){}
    public static Logger getInstance(){
        try {
            semaphore.acquire();
            if (instance == null){
                instance = new Logger();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        semaphore.release();
        return instance;
    }
    public void logWarning(String msg){
        System.out.println(AMARILLO+"[WARN] "+ RESET + msg +" ["+ getTimestamp()+ "]" );
    }
    public void logDebug(String msg){
        System.out.println(VERDE+"[DEBUG] "+ RESET + msg +" ["+ getTimestamp()+ "]" );
    }
    public void logInfo(String msg){
        System.out.println(GRIS+"[INFO] "+ RESET + msg +" ["+  getTimestamp()+ "]" );
    }
    public void logError(String msg){
        System.out.println(ROJO+"[ERROR] "+ RESET + msg +" ["+  getTimestamp()+ "]" );
    }
    private String getTimestamp() {
        // solo hasta los segundos.
        return new Timestamp(System.currentTimeMillis()).toString().substring(0,19);
    }
}
