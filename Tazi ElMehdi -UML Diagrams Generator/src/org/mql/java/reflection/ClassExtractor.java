package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.FileLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;

public class ClassExtractor {
	private static Logger logger;

	public ClassExtractor() {
	}

	public static List<String> explorePackage(String packageName) {
		String test = packageName;
		File dir = new File(test.split(";")[0]);
		// System.out.println(dir);
		File classes[] = dir.listFiles();
		List<String> res = new Vector<>();
		for (File f : classes) {
			if (!f.isDirectory()) {
				log(LogLevel.Class, "" + packageName + "/" + f.getName().replace(".class", ""));
				res.add(packageName + "/" + f.getName().replace(".class", ""));
			}
		}

		return res;
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
