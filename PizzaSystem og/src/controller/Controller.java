package controller;

import data.Datastorage;
import data.Food;
import data.MasterCard;
import data.User;
import data.Visa;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Datastorage ds = new Datastorage();
    
    private List<Food> orderedFoodItems = new ArrayList<>();
    private double totalPrice = 0.0;	

    public Datastorage getDatastorage() {
        return ds;
    }

    public void setDatastorage(Datastorage datastorage) {
        this.ds = datastorage;
    }

    public void addPizza(String name, double price) {
        Food pizza = new Food();
        pizza.setPizzaName(name);
        pizza.setPizzaPrice(price);
        this.ds.storeFood(pizza);
    }

    public void addDrink(String name, double price) {
        Food drink = new Food();
        drink.setDrinkName(name);
        drink.setDrinkPrice(price);
        this.ds.storeFood(drink);
    }

    public Food[] getFood() {
        return this.ds.getAllFood();
    }

    public void registerUser(String name, String password, String userType) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUserType(userType);
        this.ds.storeUser(user);
    }

    public boolean authenticateUser(String name, String password, String userType) {
        User[] users = this.ds.getAllUsers();
        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password) && user.getUserType().equals(userType)) {
                return true;
            }
        }
        return false;
    }

    public void deleteFood(Food food) {
        this.ds.removeFood(food);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        DefaultListModel<String> orderListModel = ds.getOrderListModel();
        for (int i = 0; i < orderListModel.getSize(); i++) {
            String item = orderListModel.getElementAt(i);
            String[] itemDetails = item.split(", ");
            double price = 0.0;
            int quantity = 0;
            price = Double.parseDouble(itemDetails[1].split(": ")[1]);
            quantity = Integer.parseInt(itemDetails[2].split(": ")[1]);

            totalPrice += price * quantity;
        }
        return totalPrice;
    }
    
    public void addItemToOrder(String item) {
        ds.addItemToOrder(item);
    }

    public void removeItemFromOrder(int index) {
        ds.removeItemFromOrder(index);
    }
}
