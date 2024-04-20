public class Music {
    private String name;
    private double price;

    public Music(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getTitle() {
        return name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " - $" + price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}