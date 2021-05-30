package com.qq.qywx.common.retry;

/**
 * Created by lynnking.
 */

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 使用dataProvider时，每个case运行结束都强制重置重试次数，如果失败，则重试结束后，重置次数
 */
public class TestngListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        // 对于dataProvider的用例，每次成功后，重置Retry次数
        RetryAnalyzer retry = (RetryAnalyzer) tr.getMethod().getRetryAnalyzer();
        retry.reset();
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        // 对于dataProvider的用例，每次失败后，重置Retry次数
        RetryAnalyzer retry = (RetryAnalyzer) tr.getMethod().getRetryAnalyzer();
        retry.reset();
    }

}
