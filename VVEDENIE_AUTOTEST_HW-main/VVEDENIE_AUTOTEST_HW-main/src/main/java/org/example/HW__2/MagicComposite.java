package org.example.HW__2;

// Компоновщик - паттерн проектирования, объединяющий объекты в древовидную структуру
// и позволяющий работать одинаково с родителями и детьми в дереве.

public class MagicComposite {
    public static void main(String[] args) {

        Products product1 = new Product(95L);

        Products product2 = new Product(88L);

        Products package1 = new Package();
        Products package2 = new Package();
        Products package3 = new Package();

        Composite products = new Composite();

        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        composite1.addProduct(product1);
        composite1.addProduct(package1);

        composite2.addProduct(package3);
        composite2.addProduct(product2);
        composite2.addProduct(package2);

        products.addProduct(composite1);
        products.addProduct(composite2);
        products.writeDown();
    }
}
