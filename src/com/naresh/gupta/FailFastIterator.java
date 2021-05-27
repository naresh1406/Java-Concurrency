package com.naresh.gupta;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Naresh Gupta on 13/3/2021 AD.
 */
public class FailFastIterator {
    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = numList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            numList.remove(1);
        }
    }
}
