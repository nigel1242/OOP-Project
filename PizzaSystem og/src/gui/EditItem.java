package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import data.Food;
import controller.Controller;
import controller.MainFrame;

public class EditItem extends JPanel {

    private MainFrame main;
    private Controller cont;
    
    private JList<String> itemList;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private JTextField textFieldName;
    private JTextField textFieldPrice;
    private JTextField textFieldDelete;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnBack;
    private JButton btnUpload;
    private JRadioButton rdbtnDrink;
    private JRadioButton rdbtnPizza;
    private ButtonGroup radioGroup;

    public EditItem(MainFrame main, Controller cont) {
    	setBackground(new Color(255, 240, 245));
        this.main = main;
        this.cont = cont;
        
        setLayout(null);
        initializeComponents();
    }

    private void initializeComponents() {
        listModel = new DefaultListModel<>();

        scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 16, 930, 188);
        add(scrollPane);

        itemList = new JList<>(listModel);
        itemList.setFont(new Font("Tahoma", Font.PLAIN, 20));
        scrollPane.setViewportView(itemList);

        JLabel lblEnterNameToAdd = new JLabel("Enter item name to add: ");
        lblEnterNameToAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEnterNameToAdd.setBounds(26, 251, 225, 32);
        add(lblEnterNameToAdd);

        JLabel lblEnterPrice = new JLabel("Enter the price: ");
        lblEnterPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEnterPrice.setBounds(26, 220, 147, 32);
        add(lblEnterPrice);

        JLabel lblEnterItemNameToDelete = new JLabel("Enter item name to delete: ");
        lblEnterItemNameToDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEnterItemNameToDelete.setBounds(26, 285, 248, 32);
        add(lblEnterItemNameToDelete);

        textFieldName = new JTextField();
        textFieldName.setBounds(293, 227, 204, 25);
        add(textFieldName);

        textFieldPrice = new JTextField();
        textFieldPrice.setBounds(293, 256, 204, 25);
        add(textFieldPrice);

        textFieldDelete = new JTextField();
        textFieldDelete.setBounds(293, 290, 204, 25);
        add(textFieldDelete);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAdd.setBounds(541, 238, 140, 32);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAddButton();
            }
        });
        add(btnAdd);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDelete.setBounds(541, 277, 140, 38);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeleteButton();
            }
        });
        add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBack.setBounds(25, 365, 89, 34);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.showStaffScreen();
            }
        });
        updateItemList();
        add(btnBack);

        rdbtnDrink = new JRadioButton("Drink");
        rdbtnDrink.setBackground(new Color(255, 240, 245));
        rdbtnDrink.setBounds(420, 327, 79, 29);
        add(rdbtnDrink);

        rdbtnPizza = new JRadioButton("Pizza");
        rdbtnPizza.setBackground(new Color(255, 240, 245));
        rdbtnPizza.setBounds(293, 323, 90, 29);
        rdbtnPizza.setSelected(true); // default is pizza
        add(rdbtnPizza);

        radioGroup = new ButtonGroup();
        radioGroup.add(rdbtnDrink);
        radioGroup.add(rdbtnPizza);
        
        btnUpload = new JButton("Upload menu file");
        btnUpload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadMenuFile();
            }
        });
        btnUpload.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnUpload.setBounds(26, 324, 204, 37);
        add(btnUpload);
    }

    private void handleAddButton() {
        String name = textFieldName.getText();
        String priceStr = textFieldPrice.getText();

        if (!name.isEmpty() && !priceStr.isEmpty() && isNumeric(priceStr)) {
            double price = Double.parseDouble(priceStr);
            if (rdbtnPizza.isSelected()) {
                cont.addPizza(name, price);
            } else if (rdbtnDrink.isSelected()) {
                cont.addDrink(name, price);
            }
            updateItemList();
            textFieldName.setText("");
            textFieldPrice.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Please enter valid values for name and price.");
        }
    }

    private void handleDeleteButton() {
        String nameToDelete = textFieldDelete.getText();
        boolean itemFound = false;
        Food[] foods = cont.getFood();
        for (Food food : foods) {
            if ((food.getPizzaName() != null && food.getPizzaName().equals(nameToDelete)) ||
                (food.getDrinkName() != null && food.getDrinkName().equals(nameToDelete))) {
                cont.deleteFood(food);
                itemFound = true;
                break;
            }
        }

        if (itemFound) {
            JOptionPane.showMessageDialog(null, "Item '" + nameToDelete + "' has been deleted.");
            textFieldDelete.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Item '" + nameToDelete + "' not found.");
            textFieldDelete.setText("");
        }

        updateItemList();
    }

    private void updateItemList() {
        listModel.clear();
        Food[] foods = cont.getFood();
        for (Food food : foods) {
            if (food.isPizza()) {
                listModel.addElement("Pizza - Name: " + food.getPizzaName() + ", Price: " + food.getPizzaPrice());
            } else if (food.isDrink()) {
                listModel.addElement("Drink - Name: " + food.getDrinkName() + ", Price: " + food.getDrinkPrice());
            }
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void uploadMenuFile() {	// upload from textfile
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(","); // assuming format is "name,price" for both pizzas and drinks
                    if (parts.length == 2) { 
                        String name = parts[0].trim(); // take info from index 0, trim index 1
                        double price = Double.parseDouble(parts[1].trim()); // take info from index 1, trim index 2
                        if (name.toLowerCase().contains("pizza")) {
                            cont.addPizza(name, price);
                        } else {
                            cont.addDrink(name, price);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid format in line: " + line);
                    }
                }
                updateItemList();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
            }
        }
    }
}