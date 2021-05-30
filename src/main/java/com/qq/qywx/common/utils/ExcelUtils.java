package com.qq.qywx.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lynnking.
 */

public class ExcelUtils {
	//excel表信息
    public static final String EXCEL_PATH = "src/test/resources/testcase_data.xlsx";//用例表格excel文件路径
    public static final String CASE_SHEET_NAME = "api_case";//用例页的页名
    public static final String REST_SHEET_NAME = "api_rest";//接口信息页的页名
    public static final String VARIABLE_SHEET_NAME = "api_variable";//参数化变量页的页名
    //调用的数据列
	public static final String EXCEL_DATA_CASE_ID = "CaseId";//用例页中的用例编号列
	public static final String EXCEL_DATA_APi_ID = "ApiId";//用例页中的接口编号列
	public static final String EXCEL_DATA_REQUEST_PARAM = "RequestParam";//用例页中的请求参数列
	public static final String EXCEL_DATA_EXPECTED = "Expected";//用例页中的期望结果列
	public static final String EXCEL_DATA_ACTUAL = "Actual";//用例页中的期望结果列	
	public static final String EXCEL_DATA_PRE_VALIDATE_SQL = "PreValidateSql";//用例页中的接口调用前的验证脚本列
	public static final String EXCEL_DATA_AFTER_VALIDATE_SQL = "AfterValidateSql";//用例页中的接口调用后的验证脚本列
	//回写的数据列
	public static final String EXCEL_DATA_PRE_VALIDATE_RESULT = "PreValidateResult";//用例页中的接口调用前的验证脚本列
	public static final String EXCEL_DATA_AFTER_VALIDATE_RESULT = "AfterValidateResult";//用例页中的接口调用后的验证脚本列
	public static final String EXCEL_DATA_REFLECT_VALUE = "ReflectValue";//参数化变量页中的反射得到的值
	//通用全局常量
	public static final String SPLIT_REGEX = "[（\\(]";//切割小括号去掉标题列的中文，兼容中英文左括号
    public static final String STRJOINT_SET = "set"; //用于拼接对象的set方法 
    public static final String STRJOINT_GET = "get"; //用于拼接对象的get方法
    
	//定义一个以行标识符和行号为映射关系Map集合用来定位行号
	public static Map<String, Integer> rowIdentifierRowNumMapping = new HashMap<String, Integer>();
	//定义一个以列名和列号为映射关系Map集合用来定位列号
	public static Map<String, Integer> cellNameCellNumMapping = new HashMap<String, Integer>();
	//因操作的是同一个excel表，定义静态workbook
	public static Workbook workbook;
	
	/**
     * 解析指定的excel数据
	 * @param <T> 传进来的类型不确定
     * @param excelPath excel文件相对路径
     * @param sheetName 要解析数据的sheet名
     */
	public static <T> List<T> load(String excelPath,String sheetName,Class<T> clazz) {
		//创建一个泛型集合，具体类型由调用者指定
		List<T> objectList = new ArrayList<T>();
		FileInputStream fis = null;
		try {
			//初始化workbook对象关联被读取文件
			fis = new FileInputStream(excelPath);
			workbook = WorkbookFactory.create(fis);
			//获取指定的sheet页
			Sheet sheet = workbook.getSheet(sheetName);
			//获取第一行，拿到所有的标题列
			Row titleRow = sheet.getRow(0);
			//获取最后一个列号，即总列数
			int lastCellNum = titleRow.getLastCellNum();
			//根据总列数创建存储列名字段的数组
			String[] fields = new String[lastCellNum];
			//遍历数组将字段存储进去
			for (int i = 0; i < lastCellNum; i++) {
				//根据列的索引获取对应的列
				Cell titleCell = titleRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//设置列属性为字符串类型
				titleCell.setCellType(CellType.STRING);
				//获取字段名
				String field = titleCell.getStringCellValue();
				//去掉标题列的中文，兼容中英文左括号
				field = field.split(SPLIT_REGEX)[0];
				//将字段名存到数组中
				fields[i] = field;
				//建立列名和列号的映射关系
				cellNameCellNumMapping.put(field, i);
			}
			//获取最后一行的角标，即总行数
			int lastRowIndex = sheet.getLastRowNum();
			//通过反射动态封装数据到对象中
			for (int i = 1; i <= lastRowIndex; i++) {
				//泛型创建对象封装数据
				T object = clazz.newInstance();
				//获取一行
				Row dataRow = sheet.getRow(i);
				//判断空行
				if(null==dataRow || isEmptyRow(dataRow))
					continue;				
				//遍历每一列并封装数据
				for (int j = 0; j < lastCellNum; j++) {
					//根据列的索引获取对应的列
					Cell cell = dataRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//设置列属性为字符串类型
					cell.setCellType(CellType.STRING);
					//获取列的数据
					String value = cell.getStringCellValue();
					//建立行标识符和行号的映射关系
					if(j==0)
						rowIdentifierRowNumMapping.put(value, dataRow.getRowNum());
					//根据字段名拼接set方法名
					String methodName = STRJOINT_SET+fields[j];
					//获取set方法并执行将数据封装到对象中					
					Method method = clazz.getMethod(methodName, String.class);
					method.invoke(object, value);
				}
				//添加保存数据的对象到集合中
				objectList.add(object);
			}
		} catch (Exception e) {	
			e.printStackTrace();
		} finally {
			//判空关流
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		//返回集合
		return objectList;
	}
	
	//判断是否是空行
    private static boolean isEmptyRow(Row dataRow) {
		int lastCellNum = dataRow.getLastCellNum();
		for (int i = 0; i < lastCellNum; i++) {
			Cell cell = dataRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value = cell.getStringCellValue();
			//只要有一列不为空就不是空行
			if(value!=null && value.trim().length()!=0){
				return false;
			}
		}
		return true;
	}  
}
