package Flowers;

import java.time.Duration;

public class MultipleFlowers extends LivingFlower{
    /* How many flowers on stem */
    int flowersOnStemAmount;
    /* Constructor */
    public  MultipleFlowers(double cost, double stemLength,
                        String name, Duration lifeTime, int flowersOnStemAmount) {
        super(cost, stemLength, name, lifeTime);
        this.flowersOnStemAmount = Math.abs(flowersOnStemAmount);
    }
    @Override
    public double getCost(){
        return super.getCost() * flowersOnStemAmount;
    }

    @Override
    public void print(){
        super.print();
        System.out.println("Flowers on stem: " + flowersOnStemAmount);
    }
}
