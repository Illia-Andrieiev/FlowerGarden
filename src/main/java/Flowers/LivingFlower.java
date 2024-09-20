package Flowers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LivingFlower extends Flower {
    /* Flower cutting date */
    protected LocalDateTime cuttingDate;
    /* Represents how much time flower will live */
    protected Duration lifeTime;

    /* Constructor */
    public LivingFlower(double cost, double stemLength,
                        String name, Duration lifeTime) {
        super(cost, stemLength, name);
        cuttingDate = LocalDateTime.now();
        this.lifeTime = lifeTime;
    }
    public void setCuttingDate(LocalDateTime cuttingDate){
        this.cuttingDate = cuttingDate;
    }
    public LocalDateTime getCuttingDate() {
        return cuttingDate;
    }
    /* Count and return freshness */
    @Override
    public double getFreshness(){
        Duration passedTime = Duration.between(cuttingDate, LocalDateTime.now());
        double newFreshness = (double)passedTime.toMillis() /
                lifeTime.toMillis() * 100;
        double roundedFreshness = Math.round(newFreshness* 100.0) / 100.0;
        return roundedFreshness > 0 ? roundedFreshness : 0;
    }
    @Override
    public double getCost() {
        return cost * getFreshness() / 100;
    }

    @Override
    public void print(){
        super.print();
        System.out.println("Freshness: " + getFreshness() + "%");
        System.out.println("Flower life time: " + lifeTime.toHours() + " hours");
    }

    public Duration getLifeTime() {
        return lifeTime;
    }
}