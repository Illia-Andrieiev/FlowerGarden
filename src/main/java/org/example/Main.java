package org.example;

import Flowers.Flower;
import Flowers.LivingFlower;
import Flowers.MultipleFlowers;

import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LivingFlower f = new LivingFlower(100,100,"rose", Duration.ofMinutes(1));
        f.setFreshness(70);
        Flower f2 = new Flower(120,70,"georgine");
        Flower f3 = new Flower(150,90,"artificial rose");
        f3.setFreshness(80);
        LivingFlower f4 = new MultipleFlowers(65,110,
                "rose", Duration.ofMinutes(3),5);
        Bouquet bouquet = new Bouquet();
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
    }
}