package Endterm.Question1_a;

public class Car {
    private String name;
    private double price;
    private double weight;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public Car(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void info(){
        System.out.println("My name is "+name+" and you have to pay "+price+" to take me home!!!");
    }
}
