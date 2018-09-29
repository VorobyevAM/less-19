package shop.products;

public class Product {

    protected String name;
    protected double cost;
    protected double quantity;

    public Product(String name, double cost, double quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }


    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "\nProduct{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}';
    }
}
