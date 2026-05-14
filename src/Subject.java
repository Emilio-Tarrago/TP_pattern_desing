public interface Subject {
    public void subcribe(TransportObserver observer);
    public void unsubcribe(TransportObserver observer);
    public void start(int intervalMs);
}
