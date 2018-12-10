package com.yyc.oper.nobid;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.yyc.oper.nobid.expert.ExpertExamEvalBean;
import com.yyc.oper.nobid.expert.ExtractSupplier;

/**
 * Description:  对象敏感信息脱敏赋值工具类
 * @author hlg
 * @date 2018年10月16日
 */
public class SetDesensitizationUtil {

/*	public static void main(String[] args) {
		List<ExpertExamEvalBean> beans = new ArrayList<>();
		List<ExtractSupplier> beans2 = new ArrayList<>();
		ExtractSupplier s = new ExtractSupplier();
		s.setExtractMethod("13018121416");
		beans2.add(s);
		ExpertExamEvalBean evalBean1 = new ExpertExamEvalBean();
		evalBean1.setExpertName("q605336747@163.com");
		evalBean1.setDel("13018121416");
		evalBean1.setExamPlace("511304199303225816");
//		evalBean1.setBean(beans2);
		evalBean1.setExpertName("q605336747@163.com");
		ExpertExamEvalBean evalBean2 = new ExpertExamEvalBean();
		evalBean2.setExpertName("605336747@qq.com");
		evalBean2.setDel("13018128415");
		evalBean2.setExamPlace("511304199303225816");
//		evalBean2.setBean(beans2);
		beans.add(evalBean1);
		beans.add(evalBean2);
		
		
		
		objectDesensitization(beans);
	}*/


/**
 * 对象脱敏
 * @param object 需要脱敏的对象
 */
	public static Object objectDesensitization(Object object){
		List<Object> list = new ArrayList<Object>();
		if(object instanceof List && new Gson().toJsonTree(object) instanceof JsonElement) {
			list = (List<Object>) object;
			for (Object object2 : list) {
				getFiledName(object2);
			}
			System.out.println("------转换List bean--------");
		}else if(new Gson().toJsonTree(object).isJsonObject()) {
			getFiledName(object);
			System.out.println("------转换单个bean--------");
			return object;
		}else {
			System.out.println("------不能转化------");
			return object;
		}
			
		return object;
	}
	
	
	 
    /**  
     * 获取属性名数组  
     * */  
    private static String[] getFiledName(Object o){  
        Field[] fields=o.getClass().getDeclaredFields();  
        String[] fieldNames=new String[fields.length];  
        for(int i=0;i<fields.length;i++){  
            fieldNames[i]=fields[i].getName(); 
            getFieldValueByName(fields[i].getName(),o);
        }  
        return fieldNames;  
    }  
    
    /**
     *  根据属性名获取属性值  
     * */  
    private static Object getFieldValueByName(String fieldName, Object o) {  
        try {   
        	//Map对象脱敏
        	if(o.getClass() == HashMap.class) {
        		setMapValue((HashMap)o);
        		return null;
        	}
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            
            //日志ID没有get   set方法 直接返回
            if("SerialVersionUID".equals(firstLetter + fieldName.substring(1))) {
            	return null; 
            }
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(o, new Object[] {});
            //如果对象中还有对象 返回调用
            if(value instanceof List) {
            	objectDesensitization(value);
            }
            //如果是时间类型,直接返回
            if(null != value && value.getClass() == java.util.Date.class) {
            	return null; 
            }
            setValue(o,fieldName,DesensitizeUtil.chineseValue((String) value));
            return value;    
        } catch (Exception e) {    
          e.printStackTrace();
            return null;    
        }    
    }   


    /**
         *
     通过反射给对象的指定字段赋值
         *
     @param target 目标对象
         *
     @param fieldName 字段的名称
         *
     @param value 值
         */
    public static void setValue(Object target, String fieldName, Object value) {
       Class<?> clazz = target.getClass();
       String[] fs = fieldName.split("\\.");
       try{
	      for(int i = 0; i < fs.length - 1; i++) {
	        Field f = clazz.getDeclaredField(fs[i]);
	        f.setAccessible(true);
	        Object val = f.get(target);
	       if(val == null){
	        Constructor<?> c = f.getType().getDeclaredConstructor();
	        c.setAccessible(true);
	        val = c.newInstance();
	        f.set(target, val);
	        }
	        target = val;
	        clazz = target.getClass();
	        }
	        Field f = clazz.getDeclaredField(fs[fs.length - 1]);
	        f.setAccessible(true);
	        f.set(target, value);
       	}catch(Exception e) {
       		e.printStackTrace();
       	}
        
    }

    /**
     * map对象脱敏
     * @param map
     */
    public  static void setMapValue(Map<String,Object> map) {
		for (Map.Entry<String, Object> m : map.entrySet()) {
			if(null !=  m.getValue() &&  m.getValue().getClass() != java.util.Date.class)
				m.setValue(DesensitizeUtil.chineseValue((String) m.getValue()));
		}
    }
}
