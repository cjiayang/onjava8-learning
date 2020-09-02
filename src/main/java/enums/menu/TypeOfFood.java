package enums.menu;

// enums/menu/TypeOfFood.java
// {java enums.menu.TypeOfFood}
import static enums.menu.Food.*;
public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Appetizer.SALAD;
        food = MainCourse.LASAGNE;
        food = Dessert.GELATO;
        food = Coffee.CAPPUCCINO;
    }
}