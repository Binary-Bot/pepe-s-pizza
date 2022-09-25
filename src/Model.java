import java.util.ArrayList;

public class Model {
    private ArrayList<LineItem> items;

    public Model(){
        items = new ArrayList<>();
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public void removeAllItems() {

        items.clear();
    }

    public double getTotal() {
        double total = 0;
        for(LineItem item : items){
            total += item.getPrice();
        }
        return total;
    }
}
