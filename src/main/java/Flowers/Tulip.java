package Flowers;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
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
    @Override
    public int saveIntoDB(Statement stmt) {
        int id = super.saveIntoDB(stmt);
        if (id == -1) {
            return -1;
        }
        String sql = "INSERT INTO flowergarden.tulip (id, rarityCoef) VALUES ('"
                + id + "', " + this.rarityCoef + ")";
        try {
            stmt.executeUpdate(sql);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
