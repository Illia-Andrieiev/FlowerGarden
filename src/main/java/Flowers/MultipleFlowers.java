package Flowers;

import java.sql.*;
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

    public static MultipleFlowers readFromDB(Connection connection, int id) {
        String query = "SELECT * FROM multipleflowers WHERE id = ?";
        MultipleFlowers flower = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int amount = resultSet.getInt("flowersOnStemAmount");
                LivingFlower f = LivingFlower.readFromDB(connection,id);
                flower = new MultipleFlowers(f.cost, f.stemLength, f.name, f.lifeTime, amount);
                flower.cuttingDate = f.cuttingDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flower;
    }
}
