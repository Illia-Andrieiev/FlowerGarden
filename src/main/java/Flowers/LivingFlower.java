package Flowers;

import java.time.Duration;

public class LivingFlower extends Flower {
    /* Flower cutting date */
    protected Duration cuttingDate;
    /* Represents how much time flower will live */
    protected Duration lifeTime;

    /* Constructor */
    public LivingFlower(double cost, double stemLength,
                        String name, Duration lifeTime) {
        super(cost, stemLength, name);
        cuttingDate = Duration.ofNanos(System.nanoTime());
        this.lifeTime = lifeTime;
    }

    /* Update flower freshness */
    protected void updateFreshness(){
        Duration currentMoment = Duration.ofNanos(System.nanoTime());
//        System.out.println("current: " + currentMoment.toSeconds());
//        System.out.println("lifeTime: " + lifeTime.toSeconds());
//        System.out.println("cut date: " + cuttingDate.toSeconds());
        double newFreshness = (double)cuttingDate.plus(lifeTime).minus(currentMoment).toMillis() /
                lifeTime.toMillis() * 100;
        double roundedFreshness = Math.round(newFreshness* 100.0) / 100.0;
        freshness = roundedFreshness > 0 ? roundedFreshness : 0;
    }

    @Override
    public double getFreshness(){
        updateFreshness();
        return freshness;
    }


    @Override
    public double getCost() {
        updateFreshness();
        return cost * freshness / 100;
    }

    @Override
    public void print(){
        super.print();
        System.out.println("Flower life time: " + lifeTime.toHours() + " hours");
    }

    public Duration getLifeTime() {
        return lifeTime;
    }
}