import javax.sound.sampled.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class MenuPanel implements Observable {
    public JPanel menuPanel;
    private JLabel menuLabel;
    private JPanel menuItemsPanel;
    private JRadioButton pizzaRButton1;
    private JRadioButton pizzaRButton2;
    private JRadioButton pizzaRButton3;
    private JPanel toppingsPanel;
    private JLabel toppingsLabel;
    private JCheckBox checkBox1;
    private JCheckBox sausageCheckBox;
    private JCheckBox checkBox3;
    private JCheckBox salamiCheckBox;
    private JLabel cheeseLabel;
    private JComboBox cheeseBox;
    private JButton addToCartButton;
    private JButton clearChoicesButton;
    private JLabel sizeLabel;
    private JTabbedPane pizzaTab;
    private JPanel buildPanel;
    private JPanel specialityPanel;
    private JLabel crustLabel;
    private JRadioButton handTossedRadioButton;
    private JRadioButton brooklynStyleRadioButton;
    private JRadioButton crunchyThinCrustRadioButton;
    private JRadioButton chicagoDeepDishRadioButton;
    private JRadioButton newYorkStyleRadioButton;
    private JCheckBox olivesCheckBox;
    private JCheckBox onionsCheckBox;
    private JCheckBox mushroomsCheckBox;
    private JCheckBox spinachCheckBox;
    private JLabel sauceLabel;
    private JComboBox comboBox1;
    private JRadioButton selectedSize;
    private JRadioButton selectedPizza;

    private ButtonGroup sizeGroup;
    private ButtonGroup crustGroup;
    private ArrayList<Observer> observers;
    private ArrayList<JCheckBox> selectedToppings;


    public MenuPanel() {
        observers = new ArrayList<Observer>();
        selectedToppings = new ArrayList<JCheckBox>();
        sizeGroup = new ButtonGroup();
        sizeGroup.add(pizzaRButton1);
        sizeGroup.add(pizzaRButton2);
        sizeGroup.add(pizzaRButton3);

        crustGroup = new ButtonGroup();
        crustGroup.add(handTossedRadioButton);
        crustGroup.add(brooklynStyleRadioButton);
        crustGroup.add(crunchyThinCrustRadioButton);
        crustGroup.add(chicagoDeepDishRadioButton);
        crustGroup.add(newYorkStyleRadioButton);

        cheeseBox.addItem("Light Cheese");
        cheeseBox.addItem("Normal Cheese");
        cheeseBox.addItem("Extra Cheese");
        cheeseBox.setSelectedIndex(1);

        comboBox1.addItem("Robust Tomato Sauce");
        comboBox1.addItem("Marinara Sauce");
        comboBox1.addItem("Honey BBQ Sauce");
        comboBox1.addItem("Garlic Parmesan Sauce");
        comboBox1.addItem("Alfredo Sauce");
        comboBox1.addItem("Ranch");
        comboBox1.setSelectedIndex(0);

        addToCartButton.addActionListener(e -> {

            for (Enumeration<AbstractButton> buttons = crustGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    selectedPizza = (JRadioButton) button;
                }
            }

            for (Enumeration<AbstractButton> buttons = sizeGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    selectedSize = (JRadioButton) button;
                }
            }

            LineItem p = new Pizza(selectedPizza.getText(), 5.99, selectedSize.getText());
            p = new Toppings(p, comboBox1.getSelectedItem().toString(), 0.0);
            if (cheeseBox.getSelectedItem().equals("Extra Cheese")) {
                p = new Toppings(p, cheeseBox.getSelectedItem().toString(), 1.0);
            } else {
                p = new Toppings(p, cheeseBox.getSelectedItem().toString(), 0.0);
            }
            for( Component comp : toppingsPanel.getComponents() ) {
                if ( comp instanceof JCheckBox) {
                    JCheckBox c = (JCheckBox) comp;
                    if (c.isSelected()) {
                        p = new Toppings(p, c.getText(), 1.0);
                    }
                }
            }
            notifyListeners(p);
        });

        clearChoicesButton.addActionListener(e -> {
            for(Component c: toppingsPanel.getComponents()) {
                if (c instanceof JCheckBox) {
                    JCheckBox r = (JCheckBox) c;
                    r.setSelected(false);
                }
            }
            crustGroup.clearSelection();
            sizeGroup.clearSelection();
            cheeseBox.setSelectedIndex(1);
            comboBox1.setSelectedIndex(0);


        });
        menuPanel.setPreferredSize(new Dimension(600, 500));
    }

    @Override
    public void addCallListener(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeCallListener(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyListeners(LineItem lineItem) {
        for(Observer o: observers){
            o.update(lineItem);
        }
    }
}
