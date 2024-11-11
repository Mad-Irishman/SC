package LineStep.impl;

import LineStep.ILineStep;
import Product.IProductPart;
import ProductPartRealization.Monitor;

public class MonitorStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new Monitor("1920x1080", 15.6);
    }
}