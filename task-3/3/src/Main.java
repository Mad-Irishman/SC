import AssemblyLine.IAssemblyLine;
import AssemblyLine.impl.LaptopAssemblyLine;
import LineStep.impl.CaseStep;
import LineStep.ILineStep;
import LineStep.impl.MonitorStep;
import LineStep.impl.MotherboardStep;
import Product.IProduct;
import Product.impl.Laptop;

public class Main {
    public static void main(String[] args) {
        ILineStep caseStep = new CaseStep();
        ILineStep motherboardStep = new MotherboardStep();
        ILineStep monitorStep = new MonitorStep();

        IAssemblyLine assemblyLine = new LaptopAssemblyLine(caseStep, motherboardStep, monitorStep);

        IProduct laptop = new Laptop(
                caseStep.buildProductPart(),
                motherboardStep.buildProductPart(),
                monitorStep.buildProductPart()
        );

        IProduct assembledLaptop = assemblyLine.assembleProduct(laptop);
        System.out.println(assembledLaptop.getDescription());
    }
}
