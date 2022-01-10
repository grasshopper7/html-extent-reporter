package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.config.external.JsonConfigLoader;
import com.aventstack.extentreports.config.external.XmlConfigLoader;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.observer.ReportObserver;
import com.aventstack.extentreports.observer.entity.ReportEntity;
import com.aventstack.extentreports.reporter.configuration.EntityFilters;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfig;
import com.aventstack.extentreports.reporter.configuration.ViewConfigurer;
import com.aventstack.extentreports.reporter.configuration.ViewsConfigurable;
import com.aventstack.extentreports.viewdefs.HtmlReportUtils;
import com.aventstack.extentreports.viewdefs.Icon;
import com.aventstack.extentreports.viewdefs.MaterialIcon;
import com.aventstack.extentreports.viewdefs.TWBSColor;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ExtentHtmlReporter extends AbstractFileReporter implements ReportObserver<ReportEntity>,
		ReporterConfigurable, ViewsConfigurable<ExtentHtmlReporter>, ReporterFilterable<ExtentHtmlReporter> {
	private static final Logger logger = Logger.getLogger(ExtentSparkReporter.class.getName());
	private static final String TEMPLATE_LOCATION = "templates/";
	private static final String ENCODING = "UTF-8";
	private static final String REPORTER_NAME = "html";
	private static final String SPA_TEMPLATE_NAME = REPORTER_NAME + "/v3-html-index.ftl";
	private static final String FILE_NAME = "Index.html";

	private final AtomicBoolean executed = new AtomicBoolean();
	@Getter(value = AccessLevel.NONE)
	private final ViewConfigurer<ExtentHtmlReporter> viewConfigurer = new ViewConfigurer<>(this);
	@Getter(value = AccessLevel.NONE)
	private final EntityFilters<ExtentHtmlReporter> filter = new EntityFilters<>(this);

	private ExtentHtmlReporterConfig conf = ExtentHtmlReporterConfig.builder().reporter(this).build();
	private Disposable disposable;
	private Report report;

	public ExtentHtmlReporter(String path) {
		super(new File(path));
	}

	public ExtentHtmlReporter(File f) {
		super(f);
	}

	@Override
	public EntityFilters<ExtentHtmlReporter> filter() {
		return filter;
	}

	@Override
	public ViewConfigurer<ExtentHtmlReporter> viewConfigurer() {
		return viewConfigurer;
	}

	public ExtentHtmlReporterConfig config() {
		return conf;
	}

	public ExtentHtmlReporter config(ExtentHtmlReporterConfig conf) {
		conf.setReporter(this);
		this.conf = conf;
		return this;
	}

	@Override
	public void loadJSONConfig(File jsonFile) throws IOException {
		final JsonConfigLoader<ExtentHtmlReporterConfig> loader = new JsonConfigLoader<ExtentHtmlReporterConfig>(conf,
				jsonFile);
		loader.apply();
	}

	@Override
	public void loadJSONConfig(String jsonString) throws IOException {
		final JsonConfigLoader<ExtentHtmlReporterConfig> loader = new JsonConfigLoader<ExtentHtmlReporterConfig>(conf,
				jsonString);
		loader.apply();
	}

	@Override
	public void loadXMLConfig(File xmlFile) throws IOException {
		final XmlConfigLoader<ExtentHtmlReporterConfig> loader = new XmlConfigLoader<ExtentHtmlReporterConfig>(conf,
				xmlFile);
		loader.apply();
	}

	@Override
	public void loadXMLConfig(String xmlFile) throws IOException {
		loadXMLConfig(new File(xmlFile));
	}

	private void executeActions() {
		if (!executed.get()) {
			conf.enableOfflineMode(conf.getOfflineMode());
			executed.compareAndSet(false, true);
		}
	}

	@Override
	public Observer<ReportEntity> getReportObserver() {
		return new Observer<ReportEntity>() {
			@Override
			public void onSubscribe(Disposable d) {
				start(d);
			}

			@Override
			public void onNext(ReportEntity value) {
				flush(value);
			}

			@Override
			public void onError(Throwable e) {
			}

			@Override
			public void onComplete() {
			}
		};
	}

	private void start(Disposable d) {
		disposable = d;
		loadTemplateModel();
	}

	private void flush(ReportEntity value) {
		executeActions();
		report = filterAndGet(value.getReport(), filter.statusFilter().getStatus());
		try {
			getTemplateModel().put("this", this);
			getTemplateModel().put("viewOrder", viewConfigurer.viewOrder().getViewOrder());
			getTemplateModel().put("report", report);
			
			// Added for html report. Check which are needed.
			getTemplateModel().put("MaterialIcon", new MaterialIcon());
			getTemplateModel().put("Icon", new Icon());
			getTemplateModel().put("TWBSColor", new TWBSColor());
			getTemplateModel().put("HtmlReportUtils", new HtmlReportUtils());
			
			createFreemarkerConfig(TEMPLATE_LOCATION, ENCODING);
			final String filePath = getFileNameAsExt(FILE_NAME, new String[] { ".html", ".htm" });
			final Template template = getFreemarkerConfig().getTemplate(SPA_TEMPLATE_NAME);
			processTemplate(template, new File(filePath));
			return;
		} catch (IOException | TemplateException e) {
			disposable.dispose();
			logger.log(Level.SEVERE, "An exception occurred", e);
		}
	}
}