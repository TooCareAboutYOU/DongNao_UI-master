package com.unit33.main;

/**
 *
 */
public class Sort {

    //直接插入排序
    private void insertSort(int[] list){

        //第一个元素是有序的，从第二个元素开始遍历，依次插入有序序列
        for (int i = 1; i < list.length; i++) {
            System.out.print("外层--->>>");
            int j=0;
            int temp=list[i]; // 取出第i个数，和前i-1个数比较后，插入合适位置
            // 因为前i-1个数都是从小到大的有序序列，所以只要当前比较的数(list[j])比temp大，就把这个数后移一位
            for (j=i-1; j>=0 && temp < list[j]; j--) {
                System.out.println("内层");

                list[j+1] =list[j];
            }
            list[j+1]=temp;

        }
        for (int i : list) {
            System.out.format("i = %d\n", i);
        }
    }

    public static void main(String[] args) {
        Sort sort=new Sort();
        int[] ints=new int[]{8,2,5,1,4,9};
        sort.insertSort(ints);
    }

}
