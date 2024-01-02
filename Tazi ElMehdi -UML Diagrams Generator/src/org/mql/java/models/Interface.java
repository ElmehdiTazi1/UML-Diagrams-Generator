package org.mql.java.models;

import java.lang.reflect.Method;

public class Interface {
    private String nom;
    private Interface [] subClasses;
    private Method [] methodes;
	public Interface() {
		// TODO Auto-generated constructor stub
	}
	public Interface(String nom, Interface[] subClasses, Method[] methodes) {
		super();
		this.nom = nom;
		this.subClasses = subClasses;
		this.methodes = methodes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Interface[] getSubClasses() {
		return subClasses;
	}
	public void setSubClasses(Interface[] subClasses) {
		this.subClasses = subClasses;
	}
	public Method[] getMethodes() {
		return methodes;
	}
	public void setMethodes(Method[] methodes) {
		this.methodes = methodes;
	}

}
