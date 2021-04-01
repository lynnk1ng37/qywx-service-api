package com.qq.qywx.addressbook.user;

import com.alibaba.fastjson.JSONObject;
import com.qq.qywx.api.BaseTest;
import com.qq.qywx.api.addressbook.user.UpdateUser;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.utils.AssertUtils;
import com.qq.qywx.common.utils.ProxyUtils;
import com.qq.qywx.common.utils.TestCaseUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by 72088532 on 2021/2/25.
 */

public class TestUpdateUser extends BaseTest {

    UpdateUser updateUser = ProxyUtils.create(UpdateUser.class);

    // 当前接口编号，用来取出数据库中对应的测试用例数据
    private static String api_id = "user_update_1";
    @DataProvider(name = "datas")
    public Object[][] datas() {
        // 指定接口编号和要取数据的字段，获取指定接口编号和指定字段的数据并存储到二维数组中
        Object[][] datas = TestCaseUtils.getCaseDataByApiId(api_id, fieldNames);
        // 返回数组
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testUpdateUser(String case_id, String api_id, String query_param, String query_body, Integer expected_code,
                               String expected_message, String expected_targeted, Integer flag, String description) throws Exception {
        // 把access_token放到url上
        query_param = JSONObject.toJSONString(GlobalVar.COOKIES);
        // 替换可变参数userid为创建接口生成的实际userid
        query_body = query_body.replace("${userid}", GlobalVar.useridlist.get(0));
        // 发包
        response = updateUser.updateUser(query_param, query_body);

        // 断言
        boolean result = AssertUtils.getAssertResult(response, expected_code, expected_message, expected_targeted);
        Assert.assertEquals(true, result);
    }
}
