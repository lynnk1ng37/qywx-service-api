package com.qq.qywx.externalcontact.customer;

import com.alibaba.fastjson.JSONObject;
import com.qq.qywx.api.BaseTest;
import com.qq.qywx.api.externalcontact.customer.ListCustomer;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.utils.AssertUtils;
import com.qq.qywx.common.utils.ProxyUtils;
import com.qq.qywx.common.utils.TestCaseUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by 72088532 on 2021/2/26.
 */

public class TestListCustomer extends BaseTest {

    ListCustomer listCustomer = ProxyUtils.create(ListCustomer.class);

    // 当前接口编号，用来取出数据库中对应的测试用例数据
    private static String api_id = "customer_list_1";
    @DataProvider(name = "datas")
    public Object[][] datas() {
        // 指定接口编号和要取数据的字段，获取指定接口编号和指定字段的数据并存储到二维数组中
        Object[][] datas = TestCaseUtils.getCaseDataByApiId(api_id, fieldNames);
        // 返回数组
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testListCustomer(String case_id, String api_id, String query_param, String query_body, Integer expected_code,
                            String expected_message, String expected_targeted, Integer flag, String description) throws Exception {
        // 用putAll将access_token加到请求参数集合中
        Map<String, Object> query_param2 = (Map<String, Object>) JSONObject.parse(query_param);
        query_param2.putAll(GlobalVar.COOKIES);
        query_param = JSONObject.toJSONString(query_param2);
        response = listCustomer.listCustomer(query_param);
        // 断言
        boolean result = AssertUtils.getAssertResult(response, expected_code, expected_message, expected_targeted);
        Assert.assertEquals(true, result);
    }
}
