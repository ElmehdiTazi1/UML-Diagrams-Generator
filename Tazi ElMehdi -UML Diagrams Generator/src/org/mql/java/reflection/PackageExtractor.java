package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;

public class PackageExtractor {
	private static Logger logger ;

	public static List<String> extractPackages(String projectName) {
		List<String> packages = new Vector<>();
		exploreProject(projectName, new File(projectName), "", packages);
		log(LogLevel.DEBUG, "Chemin du Projet => " + projectName);
		return packages;
	}

	private static void exploreProject(String projectName, File directory, String currentPackage,
			List<String> packages) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					String newPackage = currentPackage + file.getName() + "/";
					exploreProject(projectName, file, newPackage, packages);
				} else if (file.isFile() && file.getName().endsWith(".class")) {
					String packageName = currentPackage.substring(0, currentPackage.length() - 1); // Supprimez le
					if (!packages.contains(projectName + "/" + packageName)) {
						packages.add(projectName + "/" + packageName);
						log(LogLevel.Package, projectName + "/" + packageName);
					}

				}
			}
		}
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
