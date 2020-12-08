package com.local.programcontext;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SmallestLargestInString {
    /**
     * This program is to calculate the smallest and biggest by splitting the given
     * string to smaller substrings based on the length
     * 
     * @param args
     */
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            var input = scan.next();
            var subLength = scan.nextInt();
            log.info("{}", getSmallestAndLargest(input, subLength));
        }
    }

    public static String getSmallestAndLargest(String input, int subLength) {
        var largest = input.substring(0, subLength);
        var smallest = input.substring(0, subLength);
        for (int i = 0; i <= input.length() - subLength; i++) {
            var curr = input.substring(i, i + subLength);
            if (curr.compareTo(smallest) < 0)
                smallest = curr;
            if (curr.compareTo(largest) > 0)
                largest = curr;
        }
        return smallest + "\n" + largest;
    }

}
