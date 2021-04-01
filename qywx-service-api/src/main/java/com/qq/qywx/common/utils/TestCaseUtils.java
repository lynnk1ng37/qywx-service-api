package com.qq.qywx.common.utils;

import com.qq.qywx.api.BaseTest;
import org.apache.log4j.Logger;
import com.qq.qywx.common.entity.TestCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestCaseUtils {
	//创建日志记录器logger
	public static Logger logger = Logger.getLogger(TestCaseUtils.class);
	//静态caseClass对象用来使用反射
	public static Class<TestCase> testCaseClass = TestCase.class;

	/**
	 * 根据接口编号获取对应接口的测试数据
	 * @param apiId
	 * @param fieldNames
	 * @return
	 */
	public static Object[][] getCaseDataByApiId(String apiId,String[] fieldNames){
		//遍历出符合指定接口编号的用例对象并将其存到集合中
		List<TestCase> csList = new ArrayList<TestCase>();
		for(TestCase tc: BaseTest.testCases){
			if (apiId.equals(tc.getApi_id())) {
				csList.add(tc);
			}
		}
		//根据集合的长度、列的长度指定二维数组的大小
		Object[][] datas = new Object[csList.size()][fieldNames.length];
		//遍历集合中用例对象
		for (int i = 0; i < csList.size(); i++) {
			TestCase tc = csList.get(i);
			//从对象中取出指定的列
			for (int j = 0; j < fieldNames.length; j++) {
				String cellName = fieldNames[j];
				//根据列名拼接get方法名
				String bString = cellName.substring(0,1).toUpperCase();
				cellName = bString+cellName.substring(1);
				String methodName = "get"+cellName;
				try {
					//通过反射获取get方法
					Method method = TestCaseUtils.testCaseClass.getMethod(methodName);
					//执行get方法获取属性值
					Object value = method.invoke(tc);
					//将属性值存储到数组中
					datas[i][j] = value;
				} catch (Exception e) {		
					e.printStackTrace();
				}
			}
		}
		//返回数组
		return datas;
	}
}
