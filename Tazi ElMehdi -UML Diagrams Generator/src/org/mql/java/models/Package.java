package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.reflection.ClassExtractor;

public class Package {
    private String nom;
    private List<String> className;
	private List<Class> classes= new Vector<>();
    public List<String> getClassName() {
		return className;
	}
	public void setClassName(List<String> className) {
		this.className = className;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Class> getClasses() {
		return classes;
	}
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	
	public Package(String nom) {
		super();
		this.nom = nom;
		className=ClassExtractor.explorePackage(nom);
		for (String string : className) {
			classes.add(new Class(string+".class"));
		}		
	}
	public Package() {
		
	}

}
