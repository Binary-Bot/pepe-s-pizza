public class Pizza implements LineItem{
    private String itemName;
    private double itemPrice;
    private String itemSize;

    public Pizza(String itemName, double itemPrice, String itemSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemSize = itemSize;
    }

    @Override
    public String getName() {
        return itemName;
    }

    @Override
    public double getPrice() {
        return itemPrice;
    }

    @Override
    public String getDescription() {
        return itemSize + " " + itemName + "   " + String.format("$%.2f", itemPrice);
    }
}
