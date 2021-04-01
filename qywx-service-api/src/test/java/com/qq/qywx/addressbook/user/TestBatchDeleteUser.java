package com.qq.qywx.addressbook.user;

import com.alibaba.fastjson.JSONObject;
import com.qq.qywx.api.BaseTest;
import com.qq.qywx.api.addressbook.user.BatchDeleteUser;
import com.qq.qywx.api.addressbook.user.CreateUser;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.utils.AssertUtils;
import com.qq.qywx.common.utils.ProxyUtils;
import com.qq.qywx.common.utils.TestCaseUtils;
import com.qq.qywx.common.utils.VariableUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 72088532 on 2021/2/25.
 */

public class TestBatchDeleteUser extends BaseTest {

    BatchDeleteUser batchDeleteUser = ProxyUtils.create(BatchDeleteUser.class);
    CreateUser createUser = ProxyUtils.create(CreateUser.class);

    // 当前接口编号，用来取出数据库中对应的测试用例数据
    private static String api_id = "user_batchdelete_1";
    @DataProvider(name = "datas")
    public Object[][] datas() {
        // 指定接口编号和要取数据的字段，获取指定接口编号和指定字段的数据并存储到二维数组中
        Object[][] datas = TestCaseUtils.getCaseDataByApiId(api_id, fieldNames);
        // 返回数组
        return datas;
    }

    @Test(dataProvider = "datas")
    public void testBatchDeleteUser(String case_id, String api_id, String query_param, String query_body, Integer expected_code,
                               String expected_message, String expected_targeted, Integer flag, String description) throws Exception {
        // 批量删除之前，要清空GlobalVar.useridlist，因为之前已经add了一个userid并且已经删除了
        GlobalVar.useridlist.clear();
        // 把access_token放到url上
        query_param = JSONObject.toJSONString(GlobalVar.COOKIES);
        // 批量删除之前，先创建一部分成员，并将userid存入到useridList中，最后替换${useridList}
        Map<String, Object> mBody1 = new HashMap<>();
        mBody1.put("userid", "${userid}");
        mBody1.put("name", "${userid}");
        mBody1.put("mobile", "${mobile}");
        mBody1.put("position", "测试");
        mBody1.put("department", 1);
        for (int i = 0; i < 2; i++) {
            String sBody = VariableUtils.handleVariable(JSONObject.toJSONString(mBody1));
            createUser.createUser(query_param, sBody);
        }
        if (!Objects.isNull(query_body)) {
            Map<String, Object> mBody2 = new HashMap<>();
            mBody2.put("useridlist", GlobalVar.useridlist);
            query_body = JSONObject.toJSONString(mBody2);
        }
        response = batchDeleteUser.batchDeleteUser(query_param, query_body);
        // 断言
        boolean result = AssertUtils.getAssertResult(response, expected_code, expected_message, expected_targeted);
        Assert.assertEquals(true, result);
    }
}
