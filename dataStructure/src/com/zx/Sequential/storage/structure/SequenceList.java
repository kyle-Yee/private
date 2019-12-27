package com.zx.Sequential.storage.structure;

public class SequenceList implements List{
    static final int DEFUALTLENTH=10;
    int maxSize;//������鳤��
    int size;//��ǰ����
    Object[] list;//��������
 
    //˳����ʼ������
    public void init(int size1){
        maxSize = size1;//�����ĳ���Ϊsize1
        list = new Object[size1];
    }
    //�޲ι��췽��
    public SequenceList() {
        init(DEFUALTLENTH);
    }
    //�вι��췽��
    public SequenceList(int size2){
        init(size2);
    }
 
    @Override
    public Object getElement(int index) throws Exception {
        if(size==0 || index<0 || index>=size){
            System.out.println("��������ȷ");
        }
        if(size == maxSize){
            System.out.println("");
        }
        return list[index];
    }
 
    @Override
    public void doInsert(Object obj,int index) throws Exception {
        if(index<0 || index>size+1){
            System.out.println("��������");
        }
        if(size == maxSize){
            System.out.println("���Ա��������޷�����");
        }
        for(int i=size-1;i>index-1;i--){
            list[i+1]=list[i];//ָ��λ��֮���Ԫ�ض�����һλ
        }
        list[index] = obj;
        size++;
    }
 
    @Override
    public void doDelete(int index) throws Exception {
        if(index<0||index>=size){
            System.out.println("��������");
        }
        if(index<size){
            for(int i=size-1;i>index;i--){
                list[i-1] = list[i];
            }
        }
        size--;
 
    }
 
}
