package Flowers;

import java.sql.*;
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

    public static Tulip readFromDB(Connection connection, int id) {
        String query = "SELECT * FROM tulip WHERE id = ?";
        Tulip flower = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double coef = resultSet.getInt("rarityCoef");
                LivingFlower f = LivingFlower.readFromDB(connection,id);
                flower = new Tulip(f.cost, f.stemLength, f.name, f.lifeTime, coef);
                flower.cuttingDate = f.cuttingDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flower;
    }
}
