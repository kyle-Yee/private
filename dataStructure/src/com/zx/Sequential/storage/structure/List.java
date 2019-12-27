package com.zx.Sequential.storage.structure;

public interface List {
    public Object getElement(int index) throws Exception;
    public void doInsert(Object obj,int index) throws Exception;
    public void doDelete(int index) throws Exception;
}