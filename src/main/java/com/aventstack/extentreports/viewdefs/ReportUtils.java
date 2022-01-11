package com.aventstack.extentreports.viewdefs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;

public class ReportUtils {

	public String getBehaviorDrivenTypeName(Class<? extends IGherkinFormatterModel> bddType)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method method = bddType.getMethod("getGherkinName");
		Object o = method.invoke(null, (Object[]) null);
		return o.toString();
	}

	public String getBehaviorDrivenTypeName(Test test) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getBehaviorDrivenTypeName(test.getBddType());
	}

	public String timeTakenPretty(long time) {
		Date date = new Date(time);
		DateFormat formatter = new SimpleDateFormat("HH mm ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formatted = formatter.format(date);
		return formatted;
	}

	public String getMediaSource(Media media) {
		if (((ScreenCapture) media).getBase64() != null && ((ScreenCapture) media).getBase64().startsWith("data:")) {
			return "<a href='" + ((ScreenCapture) media).getBase64()
					+ "' data-featherlight='image'><span class='label grey badge white-text text-white'>base64-img</span></a>";
		}
		return "<img width='80' height='40' onerror='this.style.display=\"none\"' data-featherlight='" + media.getPath()
				+ "' src='" + media.getPath() + "'>";
	}
}
