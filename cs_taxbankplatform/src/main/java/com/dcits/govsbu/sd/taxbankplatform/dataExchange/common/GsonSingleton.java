package com.dcits.govsbu.sd.taxbankplatform.dataExchange.common;

import com.google.gson.Gson;

public class GsonSingleton {
	//持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
	private static Gson instance = null;
	public GsonSingleton() {
		//私有构造方法，防止被实例化
	}
	
	public static Gson getInstance(){
		if(instance == null){
			instance = new Gson();;
		}
		return instance;
	}
}