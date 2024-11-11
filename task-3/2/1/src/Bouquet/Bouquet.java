package Bouquet;

import Flower.Flower;

import java.util.ArrayList;
import java.util.List;

public class Bouquet {
    private List<Flower> flowers;

    public Bouquet() {
        this.flowers = new ArrayList<>();
    }

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public int sumTotalBouquet() {
        int sum = 0;
        for (Flower flower : flowers) {
            sum += flower.getPrice();
        }
        return sum;
    }

    public void showBouquet() {
        for (Flower flower : flowers) {
            System.out.printf("Flower name - %s: $ %d. Flower color is %s%n",
                    flower.getName(), flower.getPrice(), flower.getColor());

        }
        System.out.println("Sum bouquet: " + sumTotalBouquet());
    }
}
