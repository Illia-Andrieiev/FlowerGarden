package Flowers;

import java.sql.Statement;
import java.time.Duration;

public class Rose extends MultipleFlowers{
    /* Is rose has spikes */
    boolean isSpiked;

    /* Constructor */
    public Rose(double cost, double stemLength,
                String name, Duration lifeTime,
                int flowersOnStemAmount, boolean isSpiked){
        super(cost, stemLength, name, lifeTime, flowersOnStemAmount);
        this.isSpiked = isSpiked;
    }

    @Override
    public double getCost(){
        if(isSpiked)
            return cost;
        else
            return cost * 1.2;
    }
    @Override
    public void print(){
        super.print();
        System.out.println("Spikes: " + isSpiked);
    }
    @Override
    public int saveIntoDB(Statement stmt) {
        int id = super.saveIntoDB(stmt);
        if (id == -1) {
            return -1;
        }
        String sql = "INSERT INTO flowergarden.rose (id, isSpiked) VALUES ('"
                + id + "', " + this.isSpiked + ")";
        try {
            stmt.executeUpdate(sql);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
