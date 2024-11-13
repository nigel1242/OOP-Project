package data;

public class Food {

    private String pizzaName;
    private double pizzaPrice;
    private String drinkName;
    private double drinkPrice;

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String name) {
        this.pizzaName = name;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double price) {
        this.pizzaPrice = price;
    }
    
    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String name) {
        this.drinkName = name;
    }

    public double getDrinkPrice() {
        return drinkPrice;
    }

    public void setDrinkPrice(double price) {
        this.drinkPrice = price;
    }

    // Methods to check if the item is a pizza or drink
    public boolean isPizza() {
        return pizzaName != null && !pizzaName.isEmpty();
    }

    public boolean isDrink() {
        return drinkName != null && !drinkName.isEmpty();
    }

}
