package Flowers;

import java.time.Duration;

public class Tulip extends LivingFlower{
    public Tulip(double cost, double stemLength,
                            String name, Duration lifeTime) {
        super(cost, stemLength, name, lifeTime);
    }
}
