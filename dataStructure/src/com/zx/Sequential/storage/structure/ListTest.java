package com.zx.Sequential.storage.structure;

public class ListTest {
    public static void main(String args[]){
        SequenceList seq = new SequenceList(20);
        try {
            seq.doInsert("aa", 1);
            seq.doInsert("bb", 2);
            seq.doInsert("cc", 3);
            for(int j=0;j<seq.size;j++){
            System.out.println(seq.getElement(j));}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}
