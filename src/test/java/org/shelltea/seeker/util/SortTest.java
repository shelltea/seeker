package org.shelltea.seeker.util;

import com.google.common.base.Stopwatch;

import java.util.Random;


/**
 * 八大排序算法.
 *
 * @author Xiong Shuhong(xiongsh@youyuan.com)
 */
public class SortTest {
    /**
     * 冒泡排序.
     *
     * @param numbers 待排序数组
     */
    public static void bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j + 1] < numbers[j]) {
                    int temp = numbers[j];
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

    /**
     * 堆排序.
     *
     * @param numbers 待排序数组
     */
    public static void heapSort(int[] numbers) {
        buildHeap(numbers);
    }

    /**
     * 直接插入排序.
     *
     * @param numbers 待排序数组
     */
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
        final int size = 10;
        int[] numbers1 = getRandomIntegerArray(size);
        int[] numbers2 = numbers1.clone();
        int[] numbers3 = numbers1.clone();
        int[] numbers4 = numbers1.clone();
        int[] numbers5 = numbers1.clone();
        int[] numbers6 = numbers1.clone();

        System.out.print("原数组大小:" + size + ",元素:");
        for (int i : numbers1) {
            System.out.print(i + " ");
        }
        System.out.println();

        Stopwatch stopwatch = Stopwatch.createStarted();
        insertSort(numbers1);
        System.out.println("插入排序:" + stopwatch);
        for (int i : numbers1) {
            System.out.print(i + " ");
        }
        System.out.println();

        stopwatch.reset().start();
        shellSort(numbers2);
        System.out.println("希尔排序:" + stopwatch);
        for (int i : numbers2) {
            System.out.print(i + " ");
        }
        System.out.println();

        stopwatch.reset().start();
        bubbleSort(numbers3);
        System.out.println("冒泡排序:" + stopwatch);
        for (int i : numbers3) {
            System.out.print(i + " ");
        }
        System.out.println();

        stopwatch.reset().start();
        quickSort(numbers4, 0, size - 1);
        System.out.println("快速排序:" + stopwatch);
        for (int i : numbers4) {
            System.out.print(i + " ");
        }
        System.out.println();

        stopwatch.reset().start();
        selectSort(numbers5);
        System.out.println("直接选择排序:" + stopwatch);
        for (int i : numbers5) {
            System.out.print(i + " ");
        }
        System.out.println();

        stopwatch.reset().start();
        heapSort(numbers6);
        System.out.println("堆排序:" + stopwatch);
        for (int i : numbers6) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 快速排序.
     *
     * @param numbers 待排序数组
     * @param low     低位
     * @param high    高位
     */
    public static void quickSort(int[] numbers, int low, int high) {
        if (high > low) {
            int p = partition(numbers, low, high);

            quickSort(numbers, low, p - 1);
            quickSort(numbers, p + 1, high);
        }
    }

    /**
     * 直接选择排序.
     *
     * @param numbers 待排序数组
     */
    public static void selectSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            int min = i;

            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[min]) {
                    min = j;
                }
            }

            int temp = numbers[min];
            numbers[min] = numbers[i];
            numbers[i] = temp;
        }
    }

    /**
     * 希尔排序.
     *
     * @param numbers 待排序数组
     */
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

    private static void buildHeap(int[] numbers) {
    }

    private static int partition(int[] numbers, int low, int high) {
        int pivot = numbers[high];
        int i = low - 1;
        int j, temp;
        for (j = low; j < high; ++j) {
            if (numbers[j] < pivot) {
                temp = numbers[++i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }
        temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;
        return i + 1;
    }
}
