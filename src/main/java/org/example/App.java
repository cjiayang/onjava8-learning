package org.example;

/**
 * Hello world!
 *
 */
public class App
{


    static void printArray(Object... args) {
        for (Object obj: args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(Spiciness.HOT);
        Spiciness.HOT.toString();
        Spiciness.values();

    }
}
