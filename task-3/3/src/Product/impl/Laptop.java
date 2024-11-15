package Product.impl;

import Product.IProduct;
import Product.IProductPart;


public class Laptop implements IProduct {
    private final IProductPart casePart;
    private final IProductPart motherboardPart;
    private final IProductPart monitorPart;

    // Конструктор, который инициализирует все поля
    public Laptop(IProductPart casePart, IProductPart motherboardPart, IProductPart monitorPart) {
        this.casePart = casePart;
        this.motherboardPart = motherboardPart;
        this.monitorPart = monitorPart;
    }

    @Override
    public void installFirstPart(IProductPart part) {
    }

    @Override
    public void installSecondPart(IProductPart part) {
    }

    @Override
    public void installThirdPart(IProductPart part) {
    }

    @Override
    public String getDescription() {
        return "Ноутбук собран из следующих компонентов:\n" +
                casePart + "\n" +
                motherboardPart + "\n" +
                monitorPart;
    }
}