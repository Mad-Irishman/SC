package AssemblyLine.impl;

import AssemblyLine.IAssemblyLine;
import LineStep.ILineStep;
import Product.IProduct;

public class LaptopAssemblyLine implements IAssemblyLine {
    private ILineStep caseStep;
    private ILineStep motherboardStep;
    private ILineStep monitorStep;

    public LaptopAssemblyLine(ILineStep caseStep, ILineStep motherboardStep, ILineStep monitorStep) {
        this.caseStep = caseStep;
        this.motherboardStep = motherboardStep;
        this.monitorStep = monitorStep;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installFirstPart(caseStep.buildProductPart());
        product.installSecondPart(motherboardStep.buildProductPart());
        product.installThirdPart(monitorStep.buildProductPart());
        return product;
    }
}
