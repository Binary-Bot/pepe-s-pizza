public class Controller {
    private Model m;
    private OrderPizza op;

    public Controller(Model m, OrderPizza op) {
        this.m = m;
        this.op = op;
        op.addController(this);
    }

    public void addItem(LineItem item) {
        m.addItem(item);
    }

    public void removeAllItems() {
        m.removeAllItems();
    }

    public double getTotal() {
        return m.getTotal();
    }
}
