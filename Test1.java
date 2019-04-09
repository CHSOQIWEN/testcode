package com.bittch.Exception;

/**冒泡排序
 * Auther:CHAOQIWEN
 */
public class Test1 {
    public static void main(String[] args) {
        int i = 0;
        int array[] = new int[]{2,3,4,1,5,7,9,6,8};
        BubbleSort(array);
        for(i=0;i<array.length;i++){
            System.out.println(array[i]);
        }

    }
    public static void  BubbleSort(int [] array){
        int i=0;
        int j=0;
        for(i=0;i<array.length;i++){
            for(j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }

        }
    }
}






