import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model m = new Model();
        OrderPizza op = new OrderPizza();
        Controller c = new Controller(m, op);

        JFrame frame = new JFrame("Order Pizza");
        frame.setContentPane(op);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
