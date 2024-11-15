import Bouquet.Bouquet;
import Flower.*;

public class Main {
    public static void main(String[] args) {
        Bouquet bouquet = new Bouquet();

        bouquet.addFlower(new Rose("Red"));
        bouquet.addFlower(new Tulip("Orange"));
        bouquet.addFlower(new Lily("Pink"));
        bouquet.addFlower(new Peonies("White"));
        bouquet.addFlower(new Lily("Green"));
        bouquet.addFlower(new Peonies("Burgundy"));
        bouquet.addFlower(new Rose("White"));

        bouquet.showBouquet();
    }
}









