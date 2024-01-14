package org.mql.java.reflection;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;

public class ClassParser {
	private static Logger logger = new ConsoleLogger();
	public static java.lang.Class<?> loadClass(File file) {
		java.lang.Class<?> cls = null;
		try {
			File root = new File(
					file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\bin\\") + 5));
			URL[] url = { root.toURI().toURL() };
			@SuppressWarnings("resource")
			URLClassLoader urlClassLoader = new URLClassLoader(url);
			cls = urlClassLoader.loadClass(getClassPackageName(file.getAbsolutePath()));
			log(LogLevel.Class,cls.descriptorString());
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
		return cls;
	}
    public static String getClassPackageName(String absolutePath) {
		return absolutePath.substring(absolutePath.lastIndexOf("\\bin\\") + 5)
				                 .replace(".class", "").replace(File.separator,".");
	}
    
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private static void log(LogLevel level, String msg) {
		if (logger != null) {
			logger.log(level, msg);
		}
	}

}
