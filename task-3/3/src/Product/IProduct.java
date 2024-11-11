package Product;

public interface IProduct {
    void installFirstPart(IProductPart part);
    void installSecondPart(IProductPart part);
    void installThirdPart(IProductPart part);
    String getDescription();
}
