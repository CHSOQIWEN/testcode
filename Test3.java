package com.bittch.Exception;

/**
 * Auther:CHAOQIWEN
 */
public class Test3 {
    private int array[] ;
    private int size;
    private int j;
    public Test3(int capacity){
        this.array=new int[capacity];
        this.size=0;
    }

        public void pushFront(int item){
            if(this.size==array.length){
                int capacity=this.array.length*2;
                int[] newArray = new int[capacity];
                for(int i=0;i<this.size;i++){
                    newArray[i]=this.array[i];
                }
                this.array=newArray;
            }

            for(int i=this.size;i>0;i--){
                this.array[i]=this.array[i-1];
            }
            this.array[0]= item;
            this.size++;

        }

        public void pushBack(int item) {
            this.array[this.size] = item;
            this.size++;
        }
        public void addFirst(int data){
             for(int i=this.size;i>0;i--){
                 this.array[i]=this.array[i-1];
             }
             this.array[0]=data;
             this.size++;
        }



        public  void  add(int index, int item){
            for (int i = this.size; i >index;i--) {
                this.array[i] = this.array[i - 1];
            }
            this.array[index] = item;
            this.size++;
    }

        public  void remove(int index){
            for(int i = index+1;i<this.size;i++){
                this.array[i-1] = this.array[i];
            }
            this.size--;
        }
        public void popBack(int size){
            if(this.size==0){
                throw new Error();
            }
            this.size--;
        }

        public int findkey(int key) {
            int i=0;
                for( i = 0;i<this.size;i++){
                    if(array[i]==key){
                        return i;
                    }
                }
                remove(i);

            return -1;
        }
        public void display(){
             for(int i=0;i<array.length;i++){
                 System.out.println(array[i]);

             }
            System.out.println("此时顺序表的长度为："+array.length);

        }
        public void clear(){
             for(int i=0;i<this.size;i++){
                 this.array[i]=0;
             }
        }




    public static void main(String[] args) {
            Test3 test3 = new Test3(5);
            test3.pushBack(5);
            test3.pushBack(1);

            //test3.remove(0);
            //test3.pushFront(10);
            test3.addFirst(20);
            test3.display();


    }


        //找新房子，找原来的2倍大
        //


}
