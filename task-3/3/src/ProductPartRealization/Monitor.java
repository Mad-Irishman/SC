package ProductPartRealization;

import Product.IProductPart;

public class Monitor implements IProductPart {
    private String resolution;
    private double size;

    public Monitor(String resolution, double size) {
        this.resolution = resolution;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Монитор [разрешение=" + resolution + ", размер=" + size + " дюймов]";
    }
}
