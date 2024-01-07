package org.mql.java.reflection;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;

public class PackageExtractor {
	private static Logger logger;

    public static List<String> extractPackages(String projectName) {
        // Spécifiez le chemin de base du projet
        String basePath = "C:\\Users\\Mehdi\\MQL\\" + projectName;

        // Utilisez un ensemble pour stocker les packages uniques
        List<String> packages = new Vector<>();

        // Appel de la fonction récursive pour parcourir le répertoire du projet
        exploreProject(new File(basePath), "", packages);
		log(LogLevel.DEBUG,"Chemin du Projet => " +basePath);
        return packages;
    }

    private static void exploreProject(File directory, String currentPackage, List<String> packages) {
        // Obtenez la liste des fichiers dans le répertoire
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Si c'est un répertoire, récursion avec le nouveau package
                    String newPackage = currentPackage + file.getName() + ".";
                    exploreProject(file, newPackage, packages);
                } else if (file.isFile() && file.getName().endsWith(".java")) {
                    // Si c'est un fichier Java, ajoutez le package à l'ensemble
                    String packageName = currentPackage.substring(0, currentPackage.length() - 1); // Supprimez le dernier point
                    if(!packages.contains(packageName))
                    packages.add(packageName);
                  
                }
            }
        }
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
    