package com.bittch.Exception;

/**二分查找
 * Auther:CHAOQIWEN
 */
public class Test2 {
    public static void main(String[] args) {
        int array[] = new int[]{1,2,3,4,5,6,7,8,9,10};
       binarySearch(array, 7);
        System.out.println( binarySearch(array, 7));

    }

    public static int binarySearch(int[] array, int value) {
        int a = 0;
        int b = array.length - 1;

        while (a <= b) {
            int mid = (a + b) / 2;
            if (array[mid] > value) {
                b = mid - 1;
            } else if (value > array[mid]) {
                a = mid + 1;

            } else if (value == array[mid]) {
                return mid;
            }

        }
        return -1;

    }

}



