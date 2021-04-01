package com.qq.qywx.common.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.qq.qywx.common.GlobalVar;

/**
 * Created by 72088532 on 2021/1/8.
 */

public class RetryAnalyzer implements IRetryAnalyzer{
    private int retry_count = 1; // 重试次数

    private int max_retry_count = GlobalVar.RETRY_COUNTS; // 最大重试次数

    @Override
    public boolean retry(ITestResult iTestResult) { // 是否需要重试
        if (retry_count < max_retry_count) {
            retry_count++;
            return true;
        }
        return false;
    }

    public void reset() {
        retry_count = 0;
    }

}
