package me.jinsui.java8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java 流式（函数式）编程
 * 接口：Consumer，Supplier，Function，Predicate
 * 操作：
 * filter、map、collect、count；
 * sample:filter age < 20 and sort by age+name
 */
public class StreamProgramming {
    @AllArgsConstructor
    @Getter
    @ToString
    private static class Person {
        int age;
        String name;
    }

    public static void main(String[] args) {
        filterAndSortArray();

    }

    private static void filterAndSortArray() {
        Person[] personArray = new Person[]{
                new Person(51, "a"),
                new Person(2, "b"),
                new Person(3, "c"),
                new Person(10, "d"),
                new Person(21, "e"),
        };
        Arrays.stream(personArray).filter(person -> person.age > 20)
                .sorted((pA, pB) -> {
                    if (pA.age == pB.age) {
                        return pA.name.compareTo(pB.name);
                    }
                    return pA.age > pB.age ? 1 : -1;
                })
                .forEach(System.out::println);
    }

    private static void initList() {
        List<Person> personList = new ArrayList<Person>() {{
            add(new Person(51, "a"));
            add(new Person(2, "b"));
            add(new Person(3, "c"));
            add(new Person(10, "d"));
            add(new Person(21, "e"));
        }};
        personList.stream().collect(Collectors.toList());
    }
}
