package com.qq.qywx.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.testng.*;
import org.testng.xml.XmlSuite;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

public class ZTestReport implements IReporter {

	// 测试报告输出路径
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	private static final String FILE_NAME = "接口自动化测试_" + formatter.format(System.currentTimeMillis()) + ".html";
	public static String OUTPUT_FOLDER = "test-output/reports/";
	public static String report_path = System.getProperty("user.dir") + File.separator + OUTPUT_FOLDER +  FILE_NAME;

//	private String templatePath = System.getProperty("user.dir") + File.separator + "template.html";
	private String templatePath = this.getClass().getClassLoader().getResource("template.html").getPath();

	private int testsPass = 0;

	private int testsFail = 0;

	private int testsSkip = 0;

	private String beginTime;

	private long totalTime;

	private String name;

	public ZTestReport() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		name = "接口自动化测试_"+formatter.format(System.currentTimeMillis());
	}

	public ZTestReport(String name) {
		this.name = name;
		if (this.name == null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			this.name = "接口自动化测试_"+formatter.format(System.currentTimeMillis());
		}
	}

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		List<ITestResult> list = new ArrayList<ITestResult>();
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult suiteResult : suiteResults.values()) {
				ITestContext testContext = suiteResult.getTestContext();
				IResultMap passedTests = testContext.getPassedTests();
				testsPass = testsPass + passedTests.size();
				IResultMap failedTests = testContext.getFailedTests();
				testsFail = testsFail + failedTests.size();
				IResultMap skippedTests = testContext.getSkippedTests();
				testsSkip = testsSkip + skippedTests.size();
				IResultMap failedConfig = testContext.getFailedConfigurations();
				list.addAll(this.listTestResult(passedTests));
				list.addAll(this.listTestResult(failedTests));
				list.addAll(this.listTestResult(skippedTests));
				list.addAll(this.listTestResult(failedConfig));
			}
		}
		this.sort(list);
		this.outputResult(list);
	}

	private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
		Set<ITestResult> results = resultMap.getAllResults();
		return new ArrayList<ITestResult>(results);
	}

	private void sort(List<ITestResult> list) {
		Collections.sort(list, new Comparator<ITestResult>() {
			@Override
			public int compare(ITestResult r1, ITestResult r2) {
				if (r1.getStartMillis() > r2.getStartMillis()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}

	private void outputResult(List<ITestResult> list) {
		try {
			List<ReportInfo> listInfo = new ArrayList<ReportInfo>();
			int index = 0;
			for (ITestResult result : list) {
				String tn = result.getTestContext().getCurrentXmlTest().getParameter("testCase");
				if (index == 0) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
					beginTime = formatter.format(new Date(result.getStartMillis()));
					index++;
				}
				long spendTime = result.getEndMillis() - result.getStartMillis();
				totalTime += spendTime;
				String status = this.getStatus(result.getStatus());
				List<String> log = Reporter.getOutput(result);
				for (int i = 0; i < log.size(); i++) {
					log.set(i, this.toHtml(log.get(i)));
				}
				Throwable throwable = result.getThrowable();
				if (throwable != null) {
					log.add(this.toHtml(throwable.toString()));
					StackTraceElement[] st = throwable.getStackTrace();
					for (StackTraceElement stackTraceElement : st) {
						log.add(this.toHtml("    " + stackTraceElement));
					}
				}
				ReportInfo info = new ReportInfo();
				info.setName(tn);
				info.setSpendTime(spendTime + "ms");
				info.setStatus(status);
				info.setClassName(result.getInstanceName());
				info.setMethodName(result.getName());
				// 自定义用例编号
				String caseId = "";
				if (result.getParameters().length > 0) {
					caseId = (String)result.getParameters()[0];
				}
				info.setCaseId(caseId);

//				info.setDescription(result.getMethod().getDescription());
				// 自定义描述，拿到方法上的所有参数，description在最后一个
				String description;
				if (result.getParameters().length < 1) {
					description = result.getMethod().getDescription();
				} else {
					description = (String) result.getParameters()[result.getParameters().length - 1];
				}
				info.setDescription(description);
				info.setLog(log);
				listInfo.add(info);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("testName", name);
			result.put("testPass", testsPass);
			result.put("testFail", testsFail);
			result.put("testSkip", testsSkip);
			result.put("testAll", testsPass + testsFail + testsSkip);
			result.put("beginTime", beginTime);
			result.put("totalTime", totalTime + "ms");
			result.put("testResult", listInfo);
			String template = this.read(templatePath);
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(report_path)), "UTF-8"));
			template = template.replaceFirst("\\$\\{resultData\\}", Matcher.quoteReplacement(new JSONObject(result).toJSONString()));
			output.write(template);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getStatus(int status) {
		String statusString = null;
		switch (status) {
			case 1:
				statusString = "成功";
				break;
			case 2:
				statusString = "失败";
				break;
			case 3:
				statusString = "跳过";
				break;
			default:
				break;
		}
		return statusString;
	}

	private String toHtml(String str) {
		if (str == null) {
			return "";
		} else {
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll(" ", "&nbsp;");
			str = str.replaceAll("\n", "<br>");
			str = str.replaceAll("\"", "\\\\\"");
		}
		return str;
	}

	public static class ReportInfo {

		private String name;

		private String className;

		private String methodName;

		private String caseId;

		private String description;

		private String spendTime;

		private String status;

		private List<String> log;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public String getCaseId() {
			return caseId;
		}

		public void setCaseId(String caseId) {
			this.caseId = caseId;
		}

		public String getSpendTime() {
			return spendTime;
		}

		public void setSpendTime(String spendTime) {
			this.spendTime = spendTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<String> getLog() {
			return log;
		}

		public void setLog(List<String> log) {
			this.log = log;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	private String read(String path) {
		File file = new File(path);
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		try {
			is = new FileInputStream(file);
			int index = 0;
			byte[] b = new byte[1024];
			while ((index = is.read(b)) != -1) {
				sb.append(new String(b, 0, index));
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
