package zt.qq.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
	public static final String gettime()
	{
		Calendar c = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = s.format(c.getTime());
		return date;
	}
}
