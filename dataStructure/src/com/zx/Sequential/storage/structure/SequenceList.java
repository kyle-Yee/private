package com.zx.Sequential.storage.structure;

public class SequenceList implements List{
    static final int DEFUALTLENTH=10;
    int maxSize;//最大数组长度
    int size;//当前长度
    Object[] list;//对象数组
 
    //顺序表初始化方法
    public void init(int size1){
        maxSize = size1;//这个表的长度为size1
        list = new Object[size1];
    }
    //无参构造方法
    public SequenceList() {
        init(DEFUALTLENTH);
    }
    //有参构造方法
    public SequenceList(int size2){
        init(size2);
    }
 
    @Override
    public Object getElement(int index) throws Exception {
        if(size==0 || index<0 || index>=size){
            System.out.println("参数不正确");
        }
        if(size == maxSize){
            System.out.println("");
        }
        return list[index];
    }
 
    @Override
    public void doInsert(Object obj,int index) throws Exception {
        if(index<0 || index>size+1){
            System.out.println("参数错误");
        }
        if(size == maxSize){
            System.out.println("线性表已满，无法插入");
        }
        for(int i=size-1;i>index-1;i--){
            list[i+1]=list[i];//指定位置之后的元素都后移一位
        }
        list[index] = obj;
        size++;
    }
 
    @Override
    public void doDelete(int index) throws Exception {
        if(index<0||index>=size){
            System.out.println("参数错误");
        }
        if(index<size){
            for(int i=size-1;i>index;i--){
                list[i-1] = list[i];
            }
        }
        size--;
 
    }
 
}
