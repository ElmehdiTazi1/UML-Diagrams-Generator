package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.FileLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;


public class ClassExtractor {
	private static Logger logger = new ConsoleLogger();

	public ClassExtractor() {
		// TODO Auto-generated constructor stub
	}
	public static List<String> explorePackage(String classPath,String packageName,List<String>packages) {
		if("".equals(classPath))
		   classPath =System.getProperty("java.class.path");
		String packageFolder = packageName.replace('.', '\\');
		File dir = new File(classPath.split(";")[0]+"\\"+packageFolder);
		File classes[] =dir.listFiles();
		List<String> res = new Vector<>();
		for (File f : classes) {
			if(!packages.contains(packageName +"."+f.getName().replace(".class",""))) {
				log(LogLevel.Class,""+packageName +"."+f.getName().replace(".class",""));
			//System.out.println("     Class => "+packageName +"."+f.getName().replace(".class",""));//forname
			res.add(packageName +"."+f.getName().replace(".class",""));
			}
			//scanMethods(packageName +"."+f.getName().replace(".class","") );
		}
		
		return res;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	private static void log(LogLevel level,String msg) {
		if(logger!= null) {
			logger.log(level, msg);
		}
	}

}
