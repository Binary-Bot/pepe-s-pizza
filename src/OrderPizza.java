import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderPizza extends JPanel implements Observer{
    public JPanel mainPanel;
    private JPanel cartPanel;
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JLabel yourCartLabel;
    private JButton addToCartButton;
    private JButton clearChoicesButton;
    private JButton orderButton;
    private JTextArea cartList;
    private JButton clearCartButton;
    private JPanel contactForm;
    private JPanel infoPanel;
    private JLabel nameLabel;
    private JLabel contactLabel;
    private JLabel addressLabel;
    private JTextArea textArea3;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel orderType;
    private JRadioButton deliveryRadioButton;
    private JRadioButton pickupRadioButton;
    private Controller controller;
    private ButtonGroup bg;
    private JLabel text;

    public OrderPizza() {
        createUIComponents();
        bg = new ButtonGroup();
        bg.add(deliveryRadioButton);
        bg.add(pickupRadioButton);
        pickupRadioButton.setSelected(true);

        addActionListeners();
        add(mainPanel);
        orderButton.addActionListener(e -> {
            JFrame f = new JFrame();
            JDialog dialog = new JDialog(f, "Order Placed!", true);
            String msg;
            if (deliveryRadioButton.isSelected()) {
                msg = String.format("Thank you for your order %s!\n " +
                        "We will deliver your pizza in less than 20 minutes!", textField1.getText());
                text = new JLabel(msg);
            } else {
                msg = String.format("Thank you for your order %s!\n You can pick your pizza up in 15 minutes.", textField1.getText());
                text = new JLabel(msg);
            }
            text.setPreferredSize(new Dimension(520, 100));
            text.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
            dialog.add(text);
            dialog.pack();
            dialog.setLocation(500, 300);
            dialog.setVisible(true);
        });
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }

    private void addActionListeners() {
        clearCartButton.addActionListener(e -> {
            cartList.setText("");
            controller.removeAllItems();
            yourCartLabel.setText("Your Cart: $" + controller.getTotal());
        });

        addToCartButton.addActionListener(e -> {
            MenuPanel mp = new MenuPanel();
            mp.addCallListener(this);
            JFrame f = new JFrame("Add Pizza");
            f.setContentPane(mp.menuPanel);
            f.setLocation(500, 100);
            f.pack();
            f.setVisible(true);
        });
    }
    private void createUIComponents() {

    }


    @Override
    public void update(LineItem lineItem) {
        cartList.setText(cartList.getText() + "\n" + lineItem.getDescription());
        controller.addItem(lineItem);
        yourCartLabel.setText("Your Cart: $" + controller.getTotal());
    }
}
