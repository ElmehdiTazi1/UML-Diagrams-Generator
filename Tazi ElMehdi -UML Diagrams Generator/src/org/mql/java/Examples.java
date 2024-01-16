package org.mql.java;



import org.mql.java.models.ProjetJava;

import java.lang.reflect.Field;

import org.mql.java.dom.XMLGenerator;
import org.mql.java.models.Class;
import org.mql.java.models.Package;

public class Examples {

	public Examples() {
		exp02();
	}

	void exp01() {
		   String projectName = "C:/Users/Mehdi/MQL/p05-MultiThreading";
	       var project = new ProjetJava(projectName);
	       for (Package p: project.getPackages()) {
	    	   System.out.println("Package => "+p.getNom());
	    	   for(Class c : p.getClasses()) {
	    		   System.out.println("	Class => "+c.getNom());
	    		   for(Field f : c.getFields()) {
	    			   System.out.println("		Field=> "+f.getName());
	    		   }
	    		   if(c.getSupClass()!=null)
	    		   System.out.println("		SubClass=> "+c.getSupClass().getNom());
	    	   }
			
		}
	}
	void exp02() {
		   String projectName = "C:/Users/Mehdi/MQL/Tazi ElMehdi - StringMapper";
	       var project = new ProjetJava(projectName);
	        String outputPath = "resources/xml/Tazi ElMehdi - StringMapper.xml";
	       XMLGenerator.generateXML(project, outputPath);
	}
		
	

	public static void main(String[] args) {
		new Examples();

	}

}

