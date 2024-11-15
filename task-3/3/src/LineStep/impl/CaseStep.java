package LineStep.impl;

import LineStep.ILineStep;
import Product.IProductPart;
import ProductPartRealization.LaptopCase;

public class CaseStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new LaptopCase("Алюминий", "Серебристый");
    }
}