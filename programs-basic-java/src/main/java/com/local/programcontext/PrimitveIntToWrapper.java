package com.local.programcontext;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrimitveIntToWrapper {
    public static void main(String[] args) {
        List<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        Integer[] arr = new Integer[al.size()];
        arr = al.toArray(arr); // -- This is compilation issue (basic data types are not allowed)
        log.info("{}", arr);
        int[] org = new int[al.size()];
        log.info("{}", org);
    }

}
