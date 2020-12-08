package com.local.programcontext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinaryClass {

    public static void main(String[] args) {
        new BinaryClass().solution(1041);
    }

    public int solution(int number) {
        var input = number;
        var binaryNumber = toBinary(input);
        List<Integer> indexes = IntStream
                .iterate(binaryNumber.indexOf(1), index -> index >= 0, index -> binaryNumber.indexOf(1, index + 1))
                .boxed().collect(Collectors.toList());
        if (indexes.isEmpty()) {
            return 0;
        }
        var inc = 0;
        var maxZeroLength = 0;
        for ( ; input > 0; input /= 2 ) {
            var value = input % 2;
            if (value == 0) {
                inc++;
            } else {
                if (inc > maxZeroLength) {
                    maxZeroLength = inc;
                }
                inc = 0;
            }
        }
        return maxZeroLength;
    }

    String toBinary(long input) {
        var binaryNumber = new StringBuilder();
        for ( ; input > 0; input /= 2 ) {
            binaryNumber.append(input % 2);
        }
        return binaryNumber.toString();
    }
}
