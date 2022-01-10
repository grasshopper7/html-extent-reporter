package tech.grasshopper.reporter;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class HtmlV3Report {

	public static void main(String[] args) {
		ExtentReports extent = new ExtentReports();
		// extent.setAnalysisStrategy(AnalysisStrategy.SUITE);

		ExtentHtmlReporter html = new ExtentHtmlReporter("reports/htmlV3.html");
		extent.attachReporter(html);
		html.config().setDocumentTitle("MOUNISH");
		html.config().setReportName("GRASSHOPPER");

		extent.addTestRunnerOutput("Hello Runner Logs");

		extent.setSystemInfo("SYS1", "system info one");
		extent.setSystemInfo("SYS2", "system info two");

		extent.createTest("Log Levels", "Specify all log levels").pass("pass").fail("fail").skip("skip").warning("warn")
				.info("info").assignCategory("Category").assignAuthor("Author").assignDevice("Device")
				.assignCategory("Category Two").assignAuthor("Author Two").assignDevice("Device Two");

		extent.createTest("Test Heirarchy").pass("passing test").assignCategory("Category Three")
				.assignAuthor("Author Three").assignDevice("Device Three")
				.createNode("Child Test", "Child Test description").pass("child pass test")
				.createNode("Grand Child Test").pass("grand child pass test");

		Exception ex = null;
		try {
			int x = 1 / 0;
		} catch (Exception e) {
			ex = e;
		}

		extent.createTest("Exception Test",
				"This is an Exception extent Test. This is an Exception extent Test. This is an Exception extent Test.")
				.fail(ex);

		extent.flush();
	}
}