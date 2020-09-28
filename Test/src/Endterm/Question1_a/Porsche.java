package Endterm.Question1_a;

public class Porsche extends Car{
    private String owner;

    public Porsche(String name, String owner) {
        super(name);
        this.owner = owner;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("By the way, my owner is "+owner);
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
