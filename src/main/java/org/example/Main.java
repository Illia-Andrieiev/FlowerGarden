package org.example;

import Atributes.Decoration;
import Flowers.Flower;
import Flowers.LivingFlower;
import Flowers.MultipleFlowers;

import java.sql.*;
import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Decoration ribbon = new Decoration(150, "red ribbon");
        LivingFlower f = new LivingFlower(100,100,"rose", Duration.ofMinutes(1));
        Flower f2 = new Flower(120,70,"georgine");
        Flower f3 = new Flower(150,90,"artificial rose");
        LivingFlower f4 = new MultipleFlowers(65,110,
                "rose", Duration.ofMinutes(3),5);
        Bouquet bouquet = new Bouquet();
        bouquet.addDecoration(ribbon);
        bouquet.addFlower(f);
        bouquet.addFlower(f2);
        bouquet.addFlower(f3);
        bouquet.addFlower(f4);
        bouquet.print();
        bouquet.sortByFreshness();
        bouquet.print();
        System.out.println("******************************");
        bouquet.removeWiltedFlowers(90);
        bouquet.print();


        String url = "jdbc:mysql://localhost:3306/flowergarden";
        String user = "root";
        String password = "a2324252627";
        Flower ff = new MultipleFlowers(5,12,
                "Rose", Duration.ofSeconds(10),5);
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            ff.saveIntoDB(stmt);
            String query = "SELECT * FROM multipleflowers";
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}