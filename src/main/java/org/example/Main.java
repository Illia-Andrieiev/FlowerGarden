package org.example;

import Atributes.Decoration;
import Flowers.Flower;
import Flowers.LivingFlower;
import Flowers.MultipleFlowers;

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
    }
}