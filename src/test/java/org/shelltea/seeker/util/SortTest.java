package org.shelltea.seeker.util;

import com.google.common.base.Stopwatch;

import java.util.Random;

/**
 * 八大排序算法.
 *
 * @author Xiong Shuhong(xiongsh@youyuan.com)
 */
public class SortTest {
    public static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                int temp = numbers[j];
                if (numbers[j + 1] < temp) {
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }

    public static int[] getRandomIntegerArray(int length) {
        int[] numbers = new int[length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = new Random().nextInt(length * 10);
        }
        return numbers;
    }

    public static void insertSort(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            int currentNumber = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > currentNumber) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = currentNumber;
        }
    }

    public static void main(String[] args) {
        final int size = 20;
        int[] numbers1 = getRandomIntegerArray(size);
        int[] numbers2 = numbers1.clone();
        int[] numbers3 = numbers1.clone();
        int[] numbers4 = numbers1.clone();

        for (int i : numbers1) {
            System.out.print(i + " ");
        }
        System.out.println();

        Stopwatch stopwatch = Stopwatch.createStarted();

        insertSort(numbers1);
        System.out.println(stopwatch);

        stopwatch.reset().start();

        shellSort(numbers2);
        System.out.println(stopwatch);

        stopwatch.reset().start();

        bubbleSort(numbers3);
        System.out.println(stopwatch);

        stopwatch.reset().start();

        quickSort(numbers4, 0, size - 1);
        System.out.println(stopwatch);

        for (int i : numbers1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : numbers2) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : numbers3) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : numbers4) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] numbers, int low, int high) {
        if (high > low) {
            int p = partition(numbers, low, high);

            quickSort(numbers, low, p - 1);
            quickSort(numbers, p + 1, high);
        }
    }

    public static void shellSort(int[] numbers) {
        int group = numbers.length / 2;

        while (group > 0) {
            for (int i = 0; i < group; i++) {
                int j = i + group;

                while (j < numbers.length) {
                    int k = j - group;
                    int key = numbers[j];
                    while (k >= 0 && numbers[k] > key) {
                        numbers[k + group] = numbers[k];
                        numbers[k] = key;
                        k -= group;
                    }
                    j += group;
                }
            }
            group /= 2;
        }
    }

    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[high];
        int i = low - 1;
        int j, temp;
        for (j = low; j < high; ++j)
            if (numbers[j] < pivot) {
                temp = numbers[++i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;
        return i + 1;
    }

}
