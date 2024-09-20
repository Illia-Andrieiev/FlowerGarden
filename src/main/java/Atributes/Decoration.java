package Atributes;

public class Decoration {
    /* Decoration name */
    protected String name;
    /* Decoration cost */
    protected double cost;
    /* Constructor */
    public Decoration(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public double getCost(){
        return cost;
    }

    public String getName() {
        return name;
    }
}
