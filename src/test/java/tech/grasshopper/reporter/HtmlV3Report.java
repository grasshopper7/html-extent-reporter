package tech.grasshopper.reporter;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
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

		extent.createTest("Test Heirarchy").addScreenCaptureFromPath("images/dashboard_bdd.png")
				.addScreenCaptureFromPath("images/dashboard_default.png").pass("passing test")
				.assignCategory("Category Three").assignAuthor("Author Three").assignDevice("Device Three")
				.pass("Hello", MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.createNode("Child Test", "Child Test description")
				.addScreenCaptureFromPath("images/dashboard_default.png").pass("child pass test")
				.pass("Hello Child", MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.createNode("Grand Child Test").pass("grand child pass test")
				.pass("Hello Grand Child",
						MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.pass("Hello Grand Child 2",
						MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build());

		Exception ex = null;
		try {
			int x = 1 / 0;
		} catch (Exception e) {
			ex = e;
		}

		extent.createTest("Exception Test",
				"This is an Exception extent Test. This is an Exception extent Test. This is an Exception extent Test.")
				.fail(ex);

		extent.createTest("Media Test").addScreenCaptureFromPath("images/dashboard_bdd.png")
				.addScreenCaptureFromPath("images/dashboard_default.png").pass("passing test")
				.pass("Hello", MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.createNode("Child Test", "Child Test description")
				.addScreenCaptureFromPath("images/dashboard_default.png").pass("child pass test")
				.pass("Hello Child", MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build())
				.createNode("Grand Child Test").pass("grand child pass test").pass("Hello Grand Child",
						MediaEntityBuilder.createScreenCaptureFromPath("images/dashboard_bdd.png").build());

		String base64Img = "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAMhWlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS42LWMxNDIgNzkuMTYwOTI0LCAyMDE3LzA3LzEzLTAxOjA2OjM5ICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1sbnM6ZGM9Imh0dHA6Ly9wdXJsLm9yZy9kYy9lbGVtZW50cy8xLjEvIiB4bWxuczpwaG90b3Nob3A9Imh0dHA6Ly9ucy5hZG9iZS5jb20vcGhvdG9zaG9wLzEuMC8iIHhtbG5zOnRpZmY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vdGlmZi8xLjAvIiB4bWxuczpleGlmPSJodHRwOi8vbnMuYWRvYmUuY29tL2V4aWYvMS4wLyIgeG1wOkNyZWF0b3JUb29sPSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxNyAoV2luZG93cykiIHhtcDpDcmVhdGVEYXRlPSIyMDE4LTAzLTIwVDAxOjU5OjM2KzEwOjMwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE4LTA4LTA3VDEzOjMyOjM4KzA5OjMwIiB4bXA6TW9kaWZ5RGF0ZT0iMjAxOC0wOC0wN1QxMzozMjozOCswOTozMCIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2NWIzYWYwYS0xYjA3LWNhNDQtYmJmZS00MGUyYTBhZjI5NTciIHhtcE1NOkRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDpjOTVmNjk4My0yYjFkLTc4NDUtYWRhNi00NzA1ZGZhMzUxZTAiIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDplYTRjNmE4NS04ODM0LTAxNDgtYmQxZS00Zjk4NWQ5YmY4MjAiIGRjOmZvcm1hdD0iaW1hZ2UvcG5nIiBwaG90b3Nob3A6TGVnYWN5SVBUQ0RpZ2VzdD0iMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDEiIHBob3Rvc2hvcDpDb2xvck1vZGU9IjMiIHBob3Rvc2hvcDpJQ0NQcm9maWxlPSJzUkdCIElFQzYxOTY2LTIuMSIgdGlmZjpJbWFnZVdpZHRoPSIzMiIgdGlmZjpJbWFnZUxlbmd0aD0iMzIiIHRpZmY6UGhvdG9tZXRyaWNJbnRlcnByZXRhdGlvbj0iMiIgdGlmZjpPcmllbnRhdGlvbj0iMSIgdGlmZjpTYW1wbGVzUGVyUGl4ZWw9IjMiIHRpZmY6WFJlc29sdXRpb249IjcyMDAwMC8xMDAwMCIgdGlmZjpZUmVzb2x1dGlvbj0iNzIwMDAwLzEwMDAwIiB0aWZmOlJlc29sdXRpb25Vbml0PSIyIiBleGlmOkV4aWZWZXJzaW9uPSIwMjIxIiBleGlmOkNvbG9yU3BhY2U9IjY1NTM1IiBleGlmOlBpeGVsWERpbWVuc2lvbj0iMzIiIGV4aWY6UGl4ZWxZRGltZW5zaW9uPSIzMiI+IDx4bXBNTTpIaXN0b3J5PiA8cmRmOlNlcT4gPHJkZjpsaSBzdEV2dDphY3Rpb249ImNyZWF0ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6ZWE0YzZhODUtODgzNC0wMTQ4LWJkMWUtNGY5ODVkOWJmODIwIiBzdEV2dDp3aGVuPSIyMDE4LTAzLTIwVDAxOjU5OjM2KzEwOjMwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxNyAoV2luZG93cykiLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOmM2NzIwNzI2LWFhZWItNTc0ZC1hNDJjLWJlMjQ0Zjc0ZDA5YiIgc3RFdnQ6d2hlbj0iMjAxOC0wMy0yMFQwMTo1OTozNisxMDozMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDpjMzVlYmM5ZS1mM2Q5LWE4NDAtYTI5ZS01NjU0NzA3MGNkNGMiIHN0RXZ0OndoZW49IjIwMTgtMDctMTdUMTA6MDY6MzcrMDk6MzAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDQyAyMDE4IChXaW5kb3dzKSIgc3RFdnQ6Y2hhbmdlZD0iLyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0iY29udmVydGVkIiBzdEV2dDpwYXJhbWV0ZXJzPSJmcm9tIGltYWdlL2pwZWcgdG8gaW1hZ2UvcG5nIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJkZXJpdmVkIiBzdEV2dDpwYXJhbWV0ZXJzPSJjb252ZXJ0ZWQgZnJvbSBpbWFnZS9qcGVnIHRvIGltYWdlL3BuZyIvPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6NDdiZGZmM2UtMDgzMC0zNzQ3LWEzYWYtYTUzODQ2MTZhYWI2IiBzdEV2dDp3aGVuPSIyMDE4LTA3LTE3VDEwOjA2OjM3KzA5OjMwIiBzdEV2dDpzb2Z0d2FyZUFnZW50PSJBZG9iZSBQaG90b3Nob3AgQ0MgMjAxOCAoV2luZG93cykiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPHJkZjpsaSBzdEV2dDphY3Rpb249InNhdmVkIiBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjY1YjNhZjBhLTFiMDctY2E0NC1iYmZlLTQwZTJhMGFmMjk1NyIgc3RFdnQ6d2hlbj0iMjAxOC0wOC0wN1QxMzozMjozOCswOTozMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTggKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDwvcmRmOlNlcT4gPC94bXBNTTpIaXN0b3J5PiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpjMzVlYmM5ZS1mM2Q5LWE4NDAtYTI5ZS01NjU0NzA3MGNkNGMiIHN0UmVmOmRvY3VtZW50SUQ9ImFkb2JlOmRvY2lkOnBob3Rvc2hvcDo0ZmUyNzBmYS0yYjhhLTExZTgtOGFlMi1hY2VlOTcwNTYyODkiIHN0UmVmOm9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDplYTRjNmE4NS04ODM0LTAxNDgtYmQxZS00Zjk4NWQ5YmY4MjAiLz4gPHRpZmY6Qml0c1BlclNhbXBsZT4gPHJkZjpTZXE+IDxyZGY6bGk+ODwvcmRmOmxpPiA8cmRmOmxpPjg8L3JkZjpsaT4gPHJkZjpsaT44PC9yZGY6bGk+IDwvcmRmOlNlcT4gPC90aWZmOkJpdHNQZXJTYW1wbGU+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+BH/xJwAAAhZJREFUWIXtl89r1EAcxT+TCW53F9sGf/TmIqyw4EWvtaBQ/Ada60EFpW69qF2vpXjUXl30phdFvQjSf6C3Cv4PCtYe7SFVMG23u4mHbNrZmcGkqRQPebfJvJn3MvPNvIyIoggVTz8FFaAN3AKGyIlOL+Lz+i7f/C5hxDbwFmh9mT8ZqDzXMvY5MJtXOMHqWofvm72kOQQ0AQe4q/Icy9gbhxXvhrC+L/7XuW0GjhQ2A+8OO6nrQM2Tti5jblsNzAMhcRGW85q4VDuGFLus+V16EVvERfhI5wn9KxBC5NXMBF3vv6yBwkBh4EhhnAPe5EIFeEZ8DjjAB+CBv7L0U+XV2xsjwAtgBiil6KjnwIHDKEnFGY33CriWIpygDMwBkgxhdNPybMq7+riaNOrtjQowlVFcRYYwEsK2nI4cPjGcQzAVhgGnbOo45eM41dGtpP21dSoAPubQe68/MGpAjp4GAWHwa09cjozZJmsC2xy8CAdghNHY7deRTurD+/HmzmaKUCqKMCoMFAZ0GOeA60ouj1+gUa/hugN/tj739Dhgh35YLU5UB8LKm1xQwyokaxhdGb/I+cbZrC9QIg6rEnBd63vJYIDNEa94UyUZW9A4dyaruIrpJ6u/K0mjH+nTFp4RdIYBKeVOHgd58a9uRsuLE9W9vfVXlgJg2cJLDyPgIfs3o7TreYe4CO9b+maJQ0gtwpZO+gMbhoFhUaf91wAAAABJRU5ErkJggg==";

		extent.createTest("Media Base64 Test").addScreenCaptureFromBase64String(base64Img)
				.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build())
				.createNode("Child Test", "Child Test description")
				.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());

		extent.flush();
	}
}