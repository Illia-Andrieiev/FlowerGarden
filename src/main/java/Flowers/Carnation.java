package Flowers;

import java.time.Duration;

public class Carnation extends MultipleFlowers{
    /* Constructor */
    public Carnation(double cost, double stemLength,
                String name, Duration lifeTime,
                int flowersOnStemAmount){
        super(cost, stemLength, name, lifeTime, flowersOnStemAmount);
    }
}
