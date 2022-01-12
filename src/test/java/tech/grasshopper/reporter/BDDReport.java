package tech.grasshopper.reporter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BDDReport {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ExtentReports extent = new ExtentReports();
		// extent.setMediaResolverPath(new String[] { "images" });

		ExtentHtmlReporter html = new ExtentHtmlReporter("reports/htmlV3BDD.html");
		extent.attachReporter(html);
		html.config().setDocumentTitle("MOUNISH");
		html.config().setReportName("GRASSHOPPER");
		html.config().enableOfflineMode(true);

		extent.addTestRunnerOutput("Hello Runner Logs");

		ExtentSparkReporter spark = new ExtentSparkReporter("reports/BDDSparkReport.html");
		extent.attachReporter(spark);
		spark.config().enableOfflineMode(true);

		extent.setSystemInfo("SYS1", "system info one");
		extent.setSystemInfo("SYS2", "system info two");

		ExtentTest feature = extent.createTest(Feature.class, "Feature", "FD");
		feature.assignCategory("simple").assignAuthor("dev").assignDevice("all");
		ExtentTest scenario = feature.createNode(Scenario.class, "Scenario", "scenario description");
		scenario.createNode(Given.class, "Given step.").pass("");
		scenario.createNode(When.class, "When step.").pass("");
		scenario.createNode(Then.class, "Then step.").pass("");

		ExtentTest scenarioOutline = feature.createNode(ScenarioOutline.class, "Scenario Outline");
		ExtentTest scenarioSO = scenarioOutline.createNode(Scenario.class, "SO Scenario Outline",
				"Scenario in a Scenario Outline");
		scenarioSO.createNode(Given.class, "Given SO step.").pass("");
		scenarioSO.createNode(When.class, "When SO step.").pass("");
		scenarioSO.createNode(Then.class, "Then SO step.").pass("");

		ExtentTest featureLogs = extent.createTest(Feature.class, "Feature Logs", "Text, Exception, Media and Markup");
		featureLogs.assignCategory("complex").assignAuthor("qa").assignDevice("all");

		ExtentTest scenarioTextLogs = featureLogs.createNode(Scenario.class, "Scenario Text Logs");

		scenarioTextLogs.createNode(Given.class, "Single Text Log Step").log(Status.PASS, "text log single");

		scenarioTextLogs.createNode(Given.class, "Multiple Text Log Step").log(Status.PASS, "text log first")
				.log(Status.PASS, "text log second").log(Status.PASS, "text log third");

		ExtentTest mediaLogs = featureLogs.createNode(Scenario.class, "Media Logs");

		mediaLogs.createNode(Given.class, "Single Media Log Step")
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.log(Status.INFO, "Hello");

		mediaLogs.createNode(Given.class, "Multiple Media Log Step")
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/logo.png").build())
				.log(Status.PASS, "Hello",
						MediaEntityBuilder.createScreenCaptureFromPath("images/test_details_bdd.png").build())
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/test_logs_bdd.png").build());

		ExtentTest mediaSOLogs = featureLogs.createNode(ScenarioOutline.class, "Media SO Logs");

		mediaSOLogs.createNode(Scenario.class, "Single Media Log Scenario")
				.createNode(Given.class, "Single Media Log Step")
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build());

		mediaSOLogs.createNode(Scenario.class, "Multiple Media Log Scenario")
				.createNode(Given.class, "Multiple Media Log Step")
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/logo.png").build())
				.log(Status.PASS, "Hello",
						MediaEntityBuilder.createScreenCaptureFromPath("images/test_details_bdd.png").build())
				.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath("images/test_logs_bdd.png").build());

		ExtentTest exceptionLogs = featureLogs.createNode(Scenario.class, "Exception Logs");
		Exception ex = null;
		try {
			int x = 1 / 0;
		} catch (Exception e) {
			ex = e;
		}

		exceptionLogs.createNode(Given.class, "Exception Log Step").log(Status.FAIL, ex);

		ExtentTest markupLogs = featureLogs.createNode(Scenario.class, "Markup Logs");
		String[][] table = { { "Heading One", "Heading Two", "Heading Three", "Heading Four" },
				{ "Row 1 Column 1", "Row 1 Column 2", "Row 1 Column 3", "Row 1 Column 4" },
				{ "Row 2 Column 1", "Row 2 Column 2", "Row 2 Column 3", "Row 2 Column 4" },
				{ "Row 3 Column 1", "Row 3 Column 2", "Row 3 Column 3", "Row 3 Column 4" } };

		markupLogs.createNode(Given.class, "Single Markup Log Step").log(Status.SKIP, MarkupHelper.createTable(table));

		String JSON_CODE = "{ name: { first: \"John\", last:\"Jane\" }, age: 31, city: \"New York\" }";

		Map<String, String> ullidata = new LinkedHashMap<>();
		ullidata.put("First", "List Item Number 1");
		ullidata.put("Second", "List Item Number 2");
		ullidata.put("Third", " List Item Number 3");

		markupLogs.createNode(Given.class, "Multiple Markup Log Step")
				.log(Status.SKIP, MarkupHelper.createLabel("This is a sample label text display.", ExtentColor.RED))
				.log(Status.SKIP, MarkupHelper.createCodeBlock(JSON_CODE, CodeLanguage.JSON))
				.log(Status.SKIP, MarkupHelper.createOrderedList(ullidata));

		extent.flush();
	}
}
