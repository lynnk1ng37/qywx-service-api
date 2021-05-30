package com.qq.qywx.api;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.entity.TestCase;
import com.qq.qywx.common.entity.Variable;
import com.qq.qywx.common.utils.DBUtils;
import com.qq.qywx.common.utils.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * 测试基类
 * Created by lynnking.
 */

public abstract class BaseTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    // 测试用例用到的字段，依次对应如下
    // 用例编号, 接口编号, 请求参数, 请求体, 期望状态码, 期望结果, 针对性断言, 用例标记, 用例描述
    protected static String[] fieldNames = {"case_id", "api_id", "query_param", "query_body",
            "expected_code", "expected_message", "expected_targeted", "flag", "description"};

    public Response response;

    // 全局测试用例数据集合
    public static List<TestCase> testCases = new ArrayList<>();
    // 可变参数集合
    public static List<Variable> variables = new ArrayList<>();
    // 业务测试数据
    public static Map<String, Object> processDatas = new HashMap<>();

    @BeforeSuite
    public void init() {
        //读取配置文件
        try {
            // 1.创建Properties集合类
            Properties properties = new Properties();
            // 获取src路径下的文件--->ClassLoader类加载器
            ClassLoader classLoader = DBUtils.class.getClassLoader();
            URL resource = classLoader.getResource("api_tables.properties");
            String path = resource.getPath();
            // 2.加载文件
            properties.load(new FileReader(path));
            // 3.获取数据
            String[] api_tables = properties.getProperty("api_tables").split(",");
            // 加载所有用例测试数据
            for (int i = 0; i < api_tables.length; i++) {
                JDBCUtils.loadTestData(api_tables[i].trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 加载所有参数化变量数据
        variables = JDBCUtils.loadVariable();
        // 加载业务流程测试数据
        processDatas = JDBCUtils.getProcessDatas(testCases);
    }

    @AfterSuite
    public void teardown() {
        // 执行结束后发送v消息或者邮件

    }

    @BeforeClass
    public static void beforeClass() {

    }

    @AfterClass
    public static void afterClass() {

    }

    @BeforeTest
    public static void beforeTest() {
        // 设置数据解析格式json
        GlobalVar.CONTENT_TYPE = "application/json;charset=utf-8";
        // 从数据库中取出Header
//        HeaderUtils.setHeaders();
    }

    @AfterTest
    public static void afterTest() {
        GlobalVar.COOKIES.clear();
    }

}
