package ProductPartRealization;

import Product.IProductPart;

public class LaptopCase implements IProductPart {
    private String material;
    private String color;

    public LaptopCase(String material, String color) {
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Корпус [материал=" + material + ", цвет=" + color + "]";
    }
}