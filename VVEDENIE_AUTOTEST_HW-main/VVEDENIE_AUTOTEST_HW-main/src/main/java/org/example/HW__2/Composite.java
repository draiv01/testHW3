package org.example.HW__2;

import com.sun.source.doctree.SummaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Composite implements Products { // контейнер с товарами: отдельными продуктами и упаковками

    private List<Products> products = new ArrayList<>();

    void addProduct(Products product) {
        products.add(product);
    }

    void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public void writeDown() {
        for (Products product : products) {
            product.writeDown();
        }
    }

    @Override
    public Long getSum() { // используется для обеспечения атомарности операций
        AtomicReference<Long> result = new AtomicReference<>(0L);
        products.forEach(iProduct -> {
            result.set(result.get() + iProduct.getSum());
        });
        return result.get();
    }
}