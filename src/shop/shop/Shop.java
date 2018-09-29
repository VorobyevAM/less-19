package shop.shop;

import shop.products.Product;

import java.io.*;
import java.util.Arrays;

public class Shop {

    private Product[] products = new Product[0];

    @Override
    public String toString() {
        return "Shop{" +
                "products=" + Arrays.toString(products) +
                '}';
    }

    public void makePokupki() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader("pokupki.txt"))
        ) {
            do {
                addCheckList(new Product(reader.readLine(), Double.parseDouble(reader.readLine()), Double.parseDouble(reader.readLine())));
            } while (reader.ready());
            System.out.println("Преобразуем  файл в массив объектов:\n" + toString());
        } catch (IOException | NullPointerException e) {
            System.out.println("Ошибка, некоректно заполнен файл (неверная структура файла)");

        }
    }

    private void addCheckList(Product product) {//может бросить NPE
        Product[] productsNew = new Product[products.length + 1];
        System.arraycopy(products, 0, productsNew, 0, products.length);
        productsNew[productsNew.length - 1] = product;
        products = productsNew;
    }


    public double calculateSumma(int i) {
        double summa = products[i].getCost() * products[i].getQuantity();
        //System.out.println(summa);
        return summa;
    }

    public double calculateSumma() {
        double summa = 0;
        for (int j = 0; j < products.length; j++) {
            summa += products[j].getCost() * products[j].getQuantity();
        }
        return summa;
        //System.out.println(summa);
    }

    public void makeCheck() {
        System.out.print("Чек к оплате:");
        System.out.printf("\n%-1s", "=====================");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("\n%-20s: %-10.3f*  %-8.2f = %-10.2f", products[i].getName(), products[i].getCost(), products[i].getQuantity(), calculateSumma(i));
        }
        System.out.printf("\n%-1s", "=====================");
        System.out.printf("\n%20s %.2f", "К оплате:", calculateSumma());
    }

    public void savePokupki() {
        String nameCheckFile = "check.txt";
        try (
                PrintStream printStream = new PrintStream(new FileOutputStream(nameCheckFile))
        ) {
            System.setOut(printStream);
            makeCheck();
        } catch (FileNotFoundException e) {
            System.out.println("Невозможно сохранить файл");
        }
        System.out.println("Чек сохранен в файл: " + nameCheckFile);//никак не пояму как закрыть считывание с консоли, чтобы вывести данную фразу в конце метода.

    }

}
