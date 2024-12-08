package Flowers;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class LivingFlower extends Flower {
    /* Flower cutting date */
    protected LocalDateTime cuttingDate;
    /* Represents how much time flower will live */
    protected Duration lifeTime;
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;

        LivingFlower that = (LivingFlower) obj;
        return Objects.equals(cuttingDate, that.cuttingDate) &&
                Objects.equals(lifeTime, that.lifeTime);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuttingDate, lifeTime);
    }

    /* Constructor */
    public LivingFlower(double cost, double stemLength,
                        String name, Duration lifeTime) {
        super(cost, stemLength, name);
        cuttingDate = LocalDateTime.now();
        this.lifeTime = lifeTime;
    }
    public void setCuttingDate(LocalDateTime cuttingDate){
        this.cuttingDate = cuttingDate;
    }
    public LocalDateTime getCuttingDate() {
        return cuttingDate;
    }
    /* Count and return freshness */
    @Override
    public double getFreshness(){
        Duration passedTime = Duration.between(cuttingDate, LocalDateTime.now());
        double passedT = (double)passedTime.toMillis();
        double lifeT = (double)lifeTime.toMillis();
        double newFreshness =  (lifeT - passedT) / lifeT
                 * 100;
        double roundedFreshness = Math.round(newFreshness* 100.0) / 100.0;
        return roundedFreshness > 0 ? roundedFreshness : 0;
    }
    @Override
    public double getCost() {
        return cost * getFreshness() / 100;
    }
    @Override
    public String toString(){
        return super.toString() + " Freshness: " + getFreshness() + "%" +
        " Flower life time: " + lifeTime.toHours() + " hours";
    }
    @Override
    public void print(){
        super.print();
        System.out.println("Freshness: " + getFreshness() + "%");
        System.out.println("Flower life time: " + lifeTime.toHours() + " hours");
    }

    public Duration getLifeTime() {
        return lifeTime;
    }
    @Override
    public int saveIntoDB(Statement stmt) {
        int id = super.saveIntoDB(stmt);
        if (id == -1) {
            return -1;
        }
        Timestamp timestamp = Timestamp.valueOf(cuttingDate);
        String sql = "INSERT INTO flowergarden.livingflower (id, cuttingDate, lifeTime) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = stmt.getConnection().prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setTimestamp(2, timestamp);
            pstmt.setDouble(3, this.lifeTime.toSeconds());
            pstmt.executeUpdate();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static LivingFlower readFromDB(Connection connection, int id) {
        String query = "SELECT * FROM livingFlower WHERE id = ?";
        LivingFlower livingFlower = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                java.sql.Timestamp cuttingDate = resultSet.getTimestamp("cuttingDate");
                double lifeTime = resultSet.getDouble("lifeTime");
                Flower f = Flower.readFromDB(connection,id);
                livingFlower = new LivingFlower(f.cost, f.stemLength,
                        f.name, Duration.ofSeconds((long)lifeTime));
                livingFlower.cuttingDate = cuttingDate.toLocalDateTime();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livingFlower;
    }
}