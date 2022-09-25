public interface Observable {
    void addCallListener(Observer o);
    void removeCallListener(Observer o);
    void notifyListeners(LineItem lineItem);
}