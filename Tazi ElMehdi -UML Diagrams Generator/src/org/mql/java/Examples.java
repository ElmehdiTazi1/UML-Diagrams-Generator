package org.mql.java;



import org.mql.java.reflection.PackageExtractor;

public class Examples {

	public Examples() {
		exp01();
	}

	void exp01() {
		 String projectName = "p03-Annotations and Reflection";
	        //PackageExtractor.extractPackages(projectName);
	       //projectName = "Tazi ElMehdi -UML and Project Parser";
	       // PackageExtractor.extractPackages(projectName);
	       projectName = "p04-XML Parsers";
	        PackageExtractor.extractPackages(projectName);
	    }
		
	

	public static void main(String[] args) {
		new Examples();

	}

}
