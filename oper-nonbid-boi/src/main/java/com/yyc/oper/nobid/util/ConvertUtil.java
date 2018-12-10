package com.yyc.oper.nobid.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;



public class ConvertUtil {

	 
	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	     public  void transMap2Bean(Map<Integer, Object> map, Object obj) {
	  
	          try {
	              BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	             PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	  
	              for (PropertyDescriptor property : propertyDescriptors) {
	                  String key = property.getName();
	  
	                 if (map.containsKey(key)) {
	                      Object value = map.get(key);
	                      // 得到property对应的setter方法
	                      Method setter = property.getWriteMethod();
	                      setter.invoke(obj, value);
	                  }
	  
	              }
	  
	         } catch (Exception e) {
	              System.out.println("transMap2Bean Error " + e);
	         }
	  
	         return;
	 
	     }
}
