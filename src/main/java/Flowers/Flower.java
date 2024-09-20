package Flowers;

public class Flower {
    /* Flower`s name */
    protected String name;
    /* Determine how old flower is in percents */
    protected double freshness;
    /* Flower cost */
    protected double cost;
    /* Length of flower`s stem in sm */
    protected double stemLength;

    /* Constructor */
    public Flower(double cost, double stemLength, String name) {
        this.cost = cost;
        this.stemLength = stemLength;
        this.name = name;
        freshness = 100;
    }

    public double getFreshness() {
        return freshness;
    }
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getStemLength() {
        return stemLength;
    }

    public void setFreshness(double freshness) {
        if(freshness > 100){
            this.freshness = 100;
        } else if (freshness <0) {
            this.freshness = 0;
        } else{
            this.freshness = freshness;
        }
    }

    public void print(){
        System.out.println("Flower Name: " + name);
        System.out.println("Freshness: " + freshness + "%");
        System.out.println("Cost: " + cost + " uah");
        System.out.println("Stem Length: " + stemLength + " cm");
    }
}
