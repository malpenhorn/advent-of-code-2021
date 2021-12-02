package com.github.malpenhorn.aoc.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
    public static List<Integer> readFileAsListOfIntegers(String fileName){
        InputStream inputStream = FileUtil.class.getResourceAsStream("/" + fileName);
        return getStreamOfStrings(inputStream)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Stream<String> getStreamOfStrings(InputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream))
                .lines();
    }

    public static List<String> readFileAsListOfStrings(String fileName){
        InputStream inputStream = FileUtil.class.getResourceAsStream("/" + fileName);
        return getStreamOfStrings(inputStream)
                .collect(Collectors.toList());
    }
}
