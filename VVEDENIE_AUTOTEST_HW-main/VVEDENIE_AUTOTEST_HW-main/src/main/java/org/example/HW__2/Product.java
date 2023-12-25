package org.example.HW__2;

public class Product implements Products { // продукт, имеет стоимость
    Long sum;

    public Product(Long sum) {
        this.sum = sum;
    }

    @Override
    public void writeDown() {
        System.out.println("штучный товар = " + getSum());
    }

    @Override
    public Long getSum() {
        return sum;
    }

}
