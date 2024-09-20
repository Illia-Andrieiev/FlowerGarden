package org.example;

import Atributes.Decoration;
import Flowers.Flower;

import java.util.ArrayList;
import java.util.Comparator;

public class Bouquet {
    /* Flowers in bouquet */
    ArrayList<Flower> flowers;
    /* Decorations for bouquet */
    ArrayList<Decoration> decorations;

    /* Constructor */
    public Bouquet() {
        flowers = new ArrayList<>();
        decorations = new ArrayList<>();
    }

    /* Add flower to bouquet */
    public void addFlower(Flower flower){
        flowers.add(flower);
    }

    /* Print bouquet */
    public void print(){
        System.out.println("Flowers in bouquet: " + flowers.size());
        System.out.println();
        for (Flower flower: flowers){
            flower.print();
            System.out.println();
        }
    }

    /* Sort flowers in bouquet by freshness */
    public void sortByFreshness(){
        Comparator<Flower> compareByFreshness = Comparator
                .comparing(Flower::getFreshness);
        flowers.sort(compareByFreshness);
    }

    /* Find all flowers with stem length in interval */
    public ArrayList<Flower> findInStemDiapason(double minLen, double maxLen){
        ArrayList<Flower> res = new ArrayList<Flower>();
        for (Flower flower: flowers){
            double len = flower.getStemLength();
            if(len >= minLen && len <= maxLen)
                res.add(flower);
        }
        return res;
    }

    /* Remove all flowers with freshness lower then freshnessThreshold */
    public void removeWiltedFlowers(double freshnessThreshold) {
        flowers.removeIf(flower -> flower.getFreshness() < freshnessThreshold);
    }

    /* Count bouquet cost */
    public double countCost(){
        double res = 0;
        for(Flower flower: flowers) {
            res += flower.getCost();
        }
        for(Decoration deco: decorations){
            res += deco.getCost();
        }
        return res;
    }

    /* Add decoration */
    public void addDecoration(Decoration decoration){
        decorations.add(decoration);
    }

    public ArrayList<Flower> getFlowers() {
        return flowers;
    }
}
