package collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

/**
 * 泛型
 */
public class Generics {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();//参数化类型
        list.add(new String("Monday"));
        list.add(new String("Tuesday"));
        list.add(new String("Wensday"));
        for(int i = 0; i < list.size(); i++){
            String weekday = list.get(i);//隐式类型转换，编译器自动完成
            System.out.println(weekday.toUpperCase());
        }

        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add(String.valueOf(Calendar.JANUARY));
        stringLinkedList.add(String.valueOf(Calendar.FEBRUARY));
        stringLinkedList.add(String.valueOf(Calendar.MARCH));
        stringLinkedList.add(String.valueOf(Calendar.APRIL));
        System.out.println(stringLinkedList);

        ArrayDeque<String> stringArrayDeque = new ArrayDeque<>();
        stringArrayDeque.push("first");
        stringArrayDeque.push("second");
        stringArrayDeque.push("third");

    }
}
