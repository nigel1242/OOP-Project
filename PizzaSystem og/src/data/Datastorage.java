package data;

import java.util.Vector;
import javax.swing.DefaultListModel;

public class Datastorage {

    private Vector<Food> foodStorage = new Vector<>();
    private Vector<User> users = new Vector<>();
    private DefaultListModel<String> orderListModel = new DefaultListModel<>();

    public DefaultListModel<String> getOrderListModel() {
        return orderListModel;
    }

    public Food[] getAllFood() {
        Food[] foodArray = new Food[this.foodStorage.size()];
        this.foodStorage.toArray(foodArray);
        return foodArray;
    }

    public void storeFood(Food food) {
        this.foodStorage.add(food);
    }

    public void removeFood(Food food) {
        this.foodStorage.remove(food);
    }

    public void addItemToOrder(String item) {
        orderListModel.addElement(item);
    }

    public void removeItemFromOrder(int index) {
        orderListModel.removeElementAt(index);
    }

    public void clearOrderList() {
        orderListModel.clear();
    }

    public void storeUser(User user) {
        this.users.add(user);
    }

    public User[] getAllUsers() {
        User[] userArray = new User[this.users.size()];
        this.users.toArray(userArray);
        return userArray;
    }
}
