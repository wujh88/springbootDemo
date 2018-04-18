package com.sinotech.settle.utils.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.QuietWriter;

/**
 * 重写log4j类
 * 设置了日志缓存后，遇到服务器异常或其他原因导致服务关闭时，利用jvm钩子将缓存日志保存
 * @author Administrator
 *
 */
public class DailyRollingFileAppender extends
		MyDailyRollingFileAppender {
	
	public DailyRollingFileAppender() {
		super();
		Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
	}

	public DailyRollingFileAppender(Layout layout, String filename,
			String datePattern) throws IOException {
		super(layout, filename, datePattern);
		Runtime.getRuntime().addShutdownHook(new Log4jHockThread());
	}

	@Override
	public synchronized void setFile(String fileName, boolean append,
			boolean bufferedIO, int bufferSize) throws IOException {
		File logfile = new File(fileName);

		logfile.getParentFile().mkdirs();

		super.setFile(fileName, append, bufferedIO, bufferSize);
	}

	public QuietWriter getQw() {
		return super.qw;
	}

	private class Log4jHockThread extends Thread {
		@Override
		public void run() {
			QuietWriter qw = DailyRollingFileAppender.this.getQw();
			if (qw != null) {
				qw.flush();
			}
		}
	}
}
