package ProductPartRealization;

import Product.IProductPart;

public class Motherboard implements IProductPart {
    private String chipset;
    private int ramSlots;

    public Motherboard(String chipset, int ramSlots) {
        this.chipset = chipset;
        this.ramSlots = ramSlots;
    }

    @Override
    public String toString() {
        return "Материнская плата [чипсет=" + chipset + ", слоты для RAM=" + ramSlots + "]";
    }
}