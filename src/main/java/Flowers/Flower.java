package Flowers;

import java.sql.*;

public class Flower {
    /* Flower`s name */
    protected String name;
    /* Flower cost */
    protected double cost;
    /* Length of flower`s stem in sm */
    protected double stemLength;

    /* Constructor */
    public Flower(double cost, double stemLength, String name) {
        this.cost = cost;
        this.stemLength = stemLength;
        this.name = name;
    }

    public double getFreshness() {
        return 100;
    }
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getStemLength() {
        return stemLength;
    }

    public void print(){
        System.out.println("Flower Name: " + name);
        System.out.println("Cost: " + cost + " uah");
        System.out.println("Stem Length: " + stemLength + " cm");
    }

    public int saveIntoDB(Statement stmt) {
        String sql = "INSERT INTO flowergarden.flower (name, cost, stemLength) VALUES ('" + this.name + "', " + this.cost + ", " + this.stemLength + ")";
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

    public static Flower readFromDB(Connection connection, int id) {
        String query = "SELECT * FROM flower WHERE id = ?";
        Flower flower = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double cost = resultSet.getDouble("cost");
                double stemLength = resultSet.getDouble("stemLength");

                flower = new Flower(cost, stemLength, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flower;
    }
}
