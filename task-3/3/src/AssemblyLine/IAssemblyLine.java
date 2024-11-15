package AssemblyLine;

import Product.IProduct;

public interface IAssemblyLine {
    IProduct assembleProduct(IProduct product);
}
