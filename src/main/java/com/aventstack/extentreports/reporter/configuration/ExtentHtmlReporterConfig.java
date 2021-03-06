package com.aventstack.extentreports.reporter.configuration;

import java.io.File;
import java.util.stream.Stream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.util.ResourceHelper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Defines configuration settings for the Html reporter
 */
@Getter
@Setter
@SuperBuilder
public class ExtentHtmlReporterConfig extends InteractiveReporterConfig {
	private static final String REPORTER_NAME = "html";
	private static final String SEP = "/";
	// private static final String COMMONS = "commons" + SEP;
	private static final String CSS = "css" + SEP;
	private static final String JS = "js" + SEP;
	private static final String ICONS = "icons" + SEP;
	private static final String IMG = "img" + SEP;

	@Builder.Default
	private Boolean offlineMode = false;
	@Builder.Default
	private String resourceCDN = "github";
	@Builder.Default
	private String testViewChartLocation = "top";
	@Builder.Default
	private Boolean chartVisibleOnOpen = true;
	@Builder.Default
	private Boolean enableTimeline = true;
	@Builder.Default
	private Boolean disableToggleActionForPassedNode = false;
	@Builder.Default
	private Boolean enableCategoryView = true;
	@Builder.Default
	private Boolean enableAuthorView = true;
	@Builder.Default
	private Boolean enableExceptionView = true;
	@Builder.Default
	private Boolean enableTestRunnerLogsView = true;

	/**
	 * Creates the HTML report, saving all resources (css, js, fonts) in the same
	 * location, so the report can be viewed without an internet connection
	 * 
	 * @param offlineMode Setting to enable an offline accessible report
	 */
	public void enableOfflineMode(Boolean offlineMode) {
		this.offlineMode = offlineMode;
		if (offlineMode && reporter != null) {
			File f = Offline.getTargetDirectory(((ExtentHtmlReporter) reporter).getFile());
			String resPackage = ExtentReports.class.getPackage().getName().replace(".", SEP);
			resPackage += SEP + "offline" + SEP;
			String[] resx = Offline.combineAll();
			ResourceHelper.saveOfflineResources(resPackage, resx, f.getAbsolutePath());
		}
	}

	private static class Offline {
		private static File getTargetDirectory(File f) {
			String dir = f.getAbsolutePath().replace("\\", SEP);
			if (!f.isDirectory())
				dir = new File(dir).getParent();
			dir += "/" + REPORTER_NAME;
			return new File(dir);
		}

		private static String[] combineAll() {
			return combine(getJSFiles(), getCSSFiles(), getIconFiles(), getImgFiles());
		}

		private static String[] combine(String[]... array) {
			String[] result = new String[] {};
			for (String[] arr : array)
				result = Stream.of(result, arr).flatMap(Stream::of).toArray(String[]::new);
			return result;
		}

		private static String[] getJSFiles() {
			// final String commonsPath = COMMONS + JS;
			final String reporterPath = REPORTER_NAME + SEP + JS;
			final String[] files = { reporterPath + "v3html-script.js", reporterPath + "jsontree.js",
					reporterPath + "attr.js", reporterPath + "dashboard.js" };
			return files;
		}

		private static String[] getCSSFiles() {
			final String reporterPath = REPORTER_NAME + SEP + CSS;
			final String[] files = { reporterPath + "v3html-style.css" };
			return files;
		}

		private static String[] getIconFiles() {
			final String path = REPORTER_NAME + SEP + CSS + ICONS;
			final String iconDirPath = "material" + SEP;
			final String[] files = { path + "material-icons.css", path + iconDirPath + "MaterialIcons-Regular.eot",
					path + iconDirPath + "MaterialIcons-Regular.svg", path + iconDirPath + "MaterialIcons-Regular.ttf",
					path + iconDirPath + "MaterialIcons-Regular.woff",
					path + iconDirPath + "MaterialIcons-Regular.woff2",
					path + iconDirPath + "MaterialIcons-Regular.ijmap" };
			return files;
		}

		private static String[] getImgFiles() {
			final String path = REPORTER_NAME + SEP + IMG;
			final String[] files = { path + "logo.png" };
			return files;
		}
	}
}