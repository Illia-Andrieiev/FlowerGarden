package Atributes;

import java.sql.ResultSet;
import java.sql.Statement;

public class Decoration {
    /* Decoration name */
    protected String name;
    /* Decoration cost */
    protected double cost;
    /* Constructor */
    public Decoration(double cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public double getCost(){
        return cost;
    }

    public String getName() {
        return name;
    }
    public int saveIntoDB(Statement stmt) {
        String sql = "INSERT INTO flowergarden.decoration (name, cost) VALUES ('"
                + this.name + "', " + this.cost + ", " + ")";
        try {
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                System.out.println("Flower saved into database successfully with ID: " + generatedId);
                return generatedId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
