package com.zx.singleList;
/*
实现静态链表
结点分为两部分:游标（类似于动态链表中的指针）、数据域
分为了两个链表，一个用于储数据的链表，一个是空的备用链表
数组最小下标（0）的结点作为备用链表的头结点，数组最大下标的结点作为用于储数据链表的头结点
备用链表头结点的游标指向第一个没有数据的结点的数组下标
用于储数据的链表的游标指向第一个存放数据的结点的数组下标
*/
public class  StaticLinkList
{
    private final int MAX_SIZE = 10;
    private int length = 0;//静态链表结点个数（头结点除外）
    /* 初始化块总在构造器前执行，执行初始化块的时候MAX_SIZE并未赋值
    public StaticLinkList(int MAX_SIZE){
        this.MAX_SIZE = MAX_SIZE;
    }
    */

    Structure[] sll = new StaticLinkList.Structure[MAX_SIZE];//引用类型数组

    {
        //System.out.println(MAX_SIZE);
        for (int i = 0 ; i < MAX_SIZE - 1 ; i++ ){
            //引用类型数组的元素sll[i]还是引用，需要指向另一块内存
            sll[i] = new StaticLinkList.Structure();
            sll[i].setCur(i + 1);           
        }
        sll[MAX_SIZE - 1] = new StaticLinkList.Structure();
        sll[MAX_SIZE - 1].setCur(0);

    } 
    //插入数据于尾部，相当于插入索引为length+1
    public boolean add(Object element) {
        if (length + 1 > MAX_SIZE - 2){
            System.out.println("静态链表分配内存已用完！静态链表最大容量：" + (MAX_SIZE - 2));
            return false;
        }

        int cur = sll[0].getCur(); //获取sll数组/静态链表的头指针
        Object data = sll[0].getData();
        int preArray = this.searchPre(length + 1);

        sll[0].setCur(sll[cur].getCur());
        sll[cur].setData(element);
        sll[cur].setCur(sll[preArray].getCur());       
        sll[preArray].setCur(cur);  
        length++;
        return true;   
    }
    //在index处插入element
    public boolean add(int index ,Object element) {
        if (index < 1 || index > length + 1 || length + 1  > MAX_SIZE - 2){
            if (index < 1  || index > length + 1 ){
                System.out.println("索引超出可插入数据范围！范围为1-" + (length + 1));
            }
            if (length + 1  > MAX_SIZE - 2){
                System.out.println("静态链表分配内存已用完！静态链表最大容量：" + (MAX_SIZE - 2));
            }

            return false;
        }
        int cur = sll[0].getCur(); //插入结点的数组下标为
        int preArray = this.searchPre(index);//插入结点前一个结点的数组下标

        //插入时的操作，注意顺序不能颠倒
        //指向某个结点的游标值与此结点的数组下标相同
        //备用链表头结点的游标重新指向插入结点游标sll[cur].getCur()（重新指向之前）所指向的数组下标（插入数据位置原先为备用链表的第一个结点，其游标指向备用链表的下一个结点的数组下标）
        sll[0].setCur(sll[cur].getCur());
        sll[cur].setData(element);
        //插入结点的游标重新指向插入结点的后一个结点的数组下标，也就是插入结点前一个结点的游标sll[preArray].getCur()所指向的数组下标
        sll[cur].setCur(sll[preArray].getCur()); 
        //插入结点的前一个结点的游标指向插入结点的数组下标
        sll[preArray].setCur(cur);  
        length++;
        return true;   
    }
    //删除index处的element
    public boolean delete(int index){
        if ( index > length || index < 1){
            System.out.println("索引超出可删除数据范围！范围为1-" + length);
            return false;
        }
        int preArray = this.searchPre(index);//所需删除结点前一个结点的数组下标
        int cur = sll[preArray].getCur();//所需删除结点前一个结点的游标，即所需删除结点的数组下标
        //所需删除结点前一个结点的数据的游标指向所需删除结点的后一个结点的数组下标（即所需删除结点的游标sll[cur].getCur()）
        sll[preArray].setCur(sll[cur].getCur());
        sll[cur].setData(null);
        //所需删除结点的游标指向第一个没有数据的结点的数组下标（即备用链表头结点游标指向的结点的数组下标sll[0].getCur()）
        sll[cur].setCur(sll[0].getCur()); 
        //备用链表头结点的游标指向所需删除结点的数组下标
        sll[0].setCur(cur); 
        length--;
        return true; 
    }

    public int searchPre(int index){ //传入index为数组长度length+1
        int arrayPos = MAX_SIZE - 1; //在数组中的位置
        int cur = sll[MAX_SIZE - 1].getCur();
        //int cur1 = sll[MAX_SIZE - 2].getCur();
        int i = 1;
        //插入前一个结点的游标就是插入结点的数组下标
        while(index != i){
            arrayPos = cur;
            cur = sll[arrayPos].getCur();
            i++;
        }
        return arrayPos;
    }

    public static void main(String[] args) 
    {
        StaticLinkList s = new StaticLinkList();
        //System.out.println(s.add(0,"B"));
        //System.out.println(s.delete(0));
        System.out.println(s.add("A"));
        //System.out.println(s.delete(2));
        System.out.println(s.add("C"));
        System.out.println(s.add("D"));
        System.out.println(s.add("E"));
        System.out.println(s.add(2,"B"));
        System.out.println(s.add("F"));
        System.out.println(s.add(1,"start"));
        System.out.println(s.add(8,"end"));
        System.out.println(s.add("G"));
        //System.out.println(s.add("H"));
        //System.out.println(s.delete(2));
        //System.out.println(s.delete(3));
        //System.out.println(s.delete(4));
        System.out.printf("%-10s","Output");
        for (int i = 0 ; i < 10 ; i++ ){
             System.out.printf("%-10s",s.getData(i));
        }
        System.out.println("\n\n\n\n");
        System.out.printf("%-10s","Cur");
        for (int i = 0 ; i < 10 ; i++ ){
             System.out.printf("%-10s",s.getCur(i));
        }
        System.out.println();
        System.out.printf("%-10s","Data");
        System.out.printf("%-10s","null");
        for (int i = 1 ; i < 10 ; i++ ){
             System.out.printf("%-10s",s.getArrayData(i));
        }
        System.out.println();
        System.out.printf("%-10s","subscript");
        for (int i = 0 ; i < 10 ; i++ ){
             System.out.printf("%-10d",i);
        }
        System.out.println();
    }

    public Object getData(int index) {
        if(index < 1 || index > length) {
            //return new String("超出静态链表索引范围！索引范围为1-" + length );
            return null;
        }
        int cur = sll[this.searchPre(index)].getCur();
        return sll[cur].getData();
    }

    public Object getArrayData(int index) {
        return sll[index].getData();    
    }
    public int getCur(int index) {
        return sll[index].getCur();   
    }

    public int getLength(){
        return this.length;
    }
    public static class Structure{
        private Object data;
        private int cur;
        public Object getData(){
            return this.data;
        }
        public void setData(Object data){
            this.data = data ;
        }
        public int getCur(){
            return this.cur;
        }
        public void setCur(int cur){
            this.cur = cur;
        }
    }
}