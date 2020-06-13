package com.edu.cedesistemas.oop.arrays;

import static com.edu.cedesistemas.oop.arrays.ArrayUtils.bubbleSort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayUtilsTest {
    @Test
    public void testMaxNumber() {
        Integer[] numbers = {5, 8, 4, 9, 2};
        int max = ArrayUtils.max(numbers);
        assertThat(max, equalTo(9));
    }

    @Test
    public void testSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = ArrayUtils.sum(numbers);
        assertThat(sum, equalTo(15));
    }

    @Test
    public void testBubbleSort() {
        Integer[] array = {5, 9, 6, 1, 10, 4};
        bubbleSort(array);
        assertThat(array, arrayContaining(1, 4, 5, 6, 9, 10));
    }

    @Test
    public void testPrimeNumbers() {
        int limit = 20;
        List<Integer> numbers = ArrayUtils.getPrimeNumbers(limit);
        assertThat(numbers, contains(2, 3, 5, 7, 11, 13, 17, 19));
    }
}
