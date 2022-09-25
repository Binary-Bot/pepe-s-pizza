public class Toppings implements LineItem {

    private LineItem li;
    private String toppingName;
    private double toppingPrice;

    public Toppings(LineItem li, String name, double price) {
        this.li = li;
        this.toppingName = name;
        this.toppingPrice = price;
    }

    @Override
    public String getName() {
        return li.getDescription().substring(0, li.getDescription().length()-6) + "," + toppingName;
    }

    @Override
    public double getPrice() {
        return li.getPrice() + toppingPrice;
    }

    @Override
    public String getDescription() {
        return  getName()+ "  " + String.format("$%.2f", getPrice());

    }
}
