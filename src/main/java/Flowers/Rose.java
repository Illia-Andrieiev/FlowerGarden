package Flowers;

import java.sql.*;
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
    public static Rose readFromDB(Connection connection, int id) {
        String query = "SELECT * FROM rose WHERE id = ?";
        Rose flower = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int isSpiked = resultSet.getInt("isSpiked");
                MultipleFlowers f = MultipleFlowers.readFromDB(connection,id);
                flower = new Rose(f.cost, f.stemLength, f.name, f.lifeTime,
                        f.flowersOnStemAmount, isSpiked != 0);
                flower.cuttingDate = f.cuttingDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flower;
    }
}
