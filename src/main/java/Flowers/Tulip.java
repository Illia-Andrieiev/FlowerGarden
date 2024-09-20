package Flowers;

import java.time.Duration;

public class Tulip extends LivingFlower{
    /* multiplies on cost */
    double rarityCoef;
    public Tulip(double cost, double stemLength, String name,
                 Duration lifeTime, double rarityCoef) {
        super(cost, stemLength, name, lifeTime);
        this.rarityCoef = rarityCoef;
    }
    @Override
    public double getCost(){
        return cost * rarityCoef;
    }
    @Override
    public void print(){
        super.print();
        System.out.println("rarity: " + rarityCoef);
    }
}
