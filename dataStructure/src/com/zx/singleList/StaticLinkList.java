package com.zx.singleList;
/*
ʵ�־�̬����
����Ϊ������:�α꣨�����ڶ�̬�����е�ָ�룩��������
��Ϊ����������һ�����ڴ����ݵ�����һ���ǿյı�������
������С�±꣨0���Ľ����Ϊ���������ͷ��㣬��������±�Ľ����Ϊ���ڴ����������ͷ���
��������ͷ�����α�ָ���һ��û�����ݵĽ��������±�
���ڴ����ݵ�������α�ָ���һ��������ݵĽ��������±�
*/
public class  StaticLinkList
{
    private final int MAX_SIZE = 10;
    private int length = 0;//��̬�����������ͷ�����⣩
    /* ��ʼ�������ڹ�����ǰִ�У�ִ�г�ʼ�����ʱ��MAX_SIZE��δ��ֵ
    public StaticLinkList(int MAX_SIZE){
        this.MAX_SIZE = MAX_SIZE;
    }
    */

    Structure[] sll = new StaticLinkList.Structure[MAX_SIZE];//������������

    {
        //System.out.println(MAX_SIZE);
        for (int i = 0 ; i < MAX_SIZE - 1 ; i++ ){
            //�������������Ԫ��sll[i]�������ã���Ҫָ����һ���ڴ�
            sll[i] = new StaticLinkList.Structure();
            sll[i].setCur(i + 1);           
        }
        sll[MAX_SIZE - 1] = new StaticLinkList.Structure();
        sll[MAX_SIZE - 1].setCur(0);

    } 
    //����������β�����൱�ڲ�������Ϊlength+1
    public boolean add(Object element) {
        if (length + 1 > MAX_SIZE - 2){
            System.out.println("��̬��������ڴ������꣡��̬�������������" + (MAX_SIZE - 2));
            return false;
        }

        int cur = sll[0].getCur(); //��ȡsll����/��̬�����ͷָ��
        Object data = sll[0].getData();
        int preArray = this.searchPre(length + 1);

        sll[0].setCur(sll[cur].getCur());
        sll[cur].setData(element);
        sll[cur].setCur(sll[preArray].getCur());       
        sll[preArray].setCur(cur);  
        length++;
        return true;   
    }
    //��index������element
    public boolean add(int index ,Object element) {
        if (index < 1 || index > length + 1 || length + 1  > MAX_SIZE - 2){
            if (index < 1  || index > length + 1 ){
                System.out.println("���������ɲ������ݷ�Χ����ΧΪ1-" + (length + 1));
            }
            if (length + 1  > MAX_SIZE - 2){
                System.out.println("��̬��������ڴ������꣡��̬�������������" + (MAX_SIZE - 2));
            }

            return false;
        }
        int cur = sll[0].getCur(); //������������±�Ϊ
        int preArray = this.searchPre(index);//������ǰһ�����������±�

        //����ʱ�Ĳ�����ע��˳���ܵߵ�
        //ָ��ĳ�������α�ֵ��˽��������±���ͬ
        //��������ͷ�����α�����ָ��������α�sll[cur].getCur()������ָ��֮ǰ����ָ��������±꣨��������λ��ԭ��Ϊ��������ĵ�һ����㣬���α�ָ�����������һ�����������±꣩
        sll[0].setCur(sll[cur].getCur());
        sll[cur].setData(element);
        //��������α�����ָ�������ĺ�һ�����������±꣬Ҳ���ǲ�����ǰһ�������α�sll[preArray].getCur()��ָ��������±�
        sll[cur].setCur(sll[preArray].getCur()); 
        //�������ǰһ�������α�ָ�������������±�
        sll[preArray].setCur(cur);  
        length++;
        return true;   
    }
    //ɾ��index����element
    public boolean delete(int index){
        if ( index > length || index < 1){
            System.out.println("����������ɾ�����ݷ�Χ����ΧΪ1-" + length);
            return false;
        }
        int preArray = this.searchPre(index);//����ɾ�����ǰһ�����������±�
        int cur = sll[preArray].getCur();//����ɾ�����ǰһ�������α꣬������ɾ�����������±�
        //����ɾ�����ǰһ���������ݵ��α�ָ������ɾ�����ĺ�һ�����������±꣨������ɾ�������α�sll[cur].getCur()��
        sll[preArray].setCur(sll[cur].getCur());
        sll[cur].setData(null);
        //����ɾ�������α�ָ���һ��û�����ݵĽ��������±꣨����������ͷ����α�ָ��Ľ��������±�sll[0].getCur()��
        sll[cur].setCur(sll[0].getCur()); 
        //��������ͷ�����α�ָ������ɾ�����������±�
        sll[0].setCur(cur); 
        length--;
        return true; 
    }

    public int searchPre(int index){ //����indexΪ���鳤��length+1
        int arrayPos = MAX_SIZE - 1; //�������е�λ��
        int cur = sll[MAX_SIZE - 1].getCur();
        //int cur1 = sll[MAX_SIZE - 2].getCur();
        int i = 1;
        //����ǰһ�������α���ǲ�����������±�
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
            //return new String("������̬����������Χ��������ΧΪ1-" + length );
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