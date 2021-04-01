package com.qq.qywx.addressbook.user;

import com.qq.qywx.api.BaseTest;
import com.qq.qywx.api.get_access_token.GetAccessToken;
import com.qq.qywx.common.utils.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by 72088532 on 2021/2/20.
 */

public class TestAddressbookGetAccessToken extends BaseTest {

    GetAccessToken getAccessToken = ProxyUtils.create(GetAccessToken.class);

    // 当前接口编号，用来取出数据库中对应的测试用例数据
    private static String api_id = "addressbook_gettoken_1";
    @DataProvider(name = "datas")
    public Object[][] datas() {
        // 指定接口编号和要取数据的字段，获取指定接口编号和指定字段的数据并存储到二维数组中
        Object[][] datas = TestCaseUtils.getCaseDataByApiId(api_id, fieldNames);
        // 返回数组
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testAddressbookGetAccessToken(String case_id, String api_id, String query_param, String query_body, Integer expected_code,
                                   String expected_message, String expected_targeted, Integer flag, String description) throws Exception {
        response = getAccessToken.getAccessToken(query_param);
        // 如果是正向用例，保存登录后的鉴权信息
        if (flag == 1) {
            AccessTokenUtils.putAndStoreAccessToken(response, api_id);
        }
        // 断言
        boolean result = AssertUtils.getAssertResult(response, expected_code, expected_message, expected_targeted);
        Assert.assertEquals(true, result);
    }
}
