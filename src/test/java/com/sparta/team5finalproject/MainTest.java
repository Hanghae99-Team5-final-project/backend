package com.sparta.team5finalproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainTest {

    public static void main(String[] args) {
        Set<Integer> integerSet = new HashSet<>(); // Collection의 자료형에는 primitive 타입은 올 수 없습니다. primitive 타입에 해당하는 class 가 존재하니 그것을 사용하세요.
        integerSet.add(1);
        integerSet.add(3);
        integerSet.add(2);
        integerSet.add(9);// 하나씩 값을 삽입합니다.
        System.out.println(integerSet); // 출력을 해보면 순서가 지켜지지 않는 것을 알 수 있습니다.

        Set<String> stringSet = new HashSet<>();
        stringSet.add("LA");
        stringSet.add("New York");
        stringSet.add("LasVegas");
        stringSet.add("San Francisco");
        stringSet.add("Seoul");
        System.out.println(stringSet);

        stringSet.remove("Seoul"); //Seoul을 HashSet에서 제거해보겠습니다.
        System.out.println(stringSet);

        ArrayList<String> target = new ArrayList<String>();
        target.add("New York");
        target.add("LasVegas");//제거할 항목을 ArrayList에 삽입하겠습니다.
        stringSet.removeAll(target);//제거항목에 삽입된 도시들을 삭제하겠습니다.
        System.out.println(stringSet);

        System.out.println("LA가 포함되어있나요? " + stringSet.contains("LA"));
        System.out.println("LA가 포함되어있나요? " + stringSet.contains("LasVegas"));
        //LA가 HashSet에 포함되어있으면 true를, 그렇지 않으면 false를 반환합니다.

        System.out.println("현재 HashSet의 크기는 : " + stringSet.size() + "입니다.");
        //HashSet의 크기를 반환합니다.

        stringSet.clear();//HashSet의 모든 아이템들을 삭제합니다.
        System.out.println(stringSet);
    }

}
