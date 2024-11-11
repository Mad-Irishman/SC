package LineStep.impl;

import LineStep.ILineStep;
import Product.IProductPart;
import ProductPartRealization.Motherboard;


public class MotherboardStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new Motherboard("Intel", 4);
    }
}