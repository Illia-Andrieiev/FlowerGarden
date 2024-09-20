package Flowers;

import java.sql.ResultSet;
import java.sql.Statement;
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
    @Override
    public int saveIntoDB(Statement stmt) {
        int id = super.saveIntoDB(stmt);
        if (id == -1) {
            return -1;
        }
        String sql = "INSERT INTO flowergarden.multipleFlowers (id, flowersOnStemAmount) VALUES ('"
                + id + "', " + this.flowersOnStemAmount + ")";
        try {
            stmt.executeUpdate(sql);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
