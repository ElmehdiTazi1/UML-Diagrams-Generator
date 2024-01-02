package org.mql.java.models;

public class Package {
    private String nom;
    private Class[] classes;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Class[] getClasses() {
		return classes;
	}
	public void setClasses(Class[] classes) {
		this.classes = classes;
	}
	public Package(String nom, Class[] classes) {
		super();
		this.nom = nom;
		this.classes = classes;
	}
	public Package() {
		// TODO Auto-generated constructor stub
	}

}
