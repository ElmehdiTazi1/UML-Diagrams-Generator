package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Class {
    private String nom;
    private Field [] fields;
	private Constructor[] constructors;
	private Method [] methods;
	private Class supClass;
	private Interface [] interfaces;
	public Class() {
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Field[] getFields() {
		return fields;
	}
	public void setFields(Field[] fields) {
		this.fields = fields;
	}
	public Constructor[] getConstructors() {
		return constructors;
	}
	public void setConstructors(Constructor[] constructors) {
		this.constructors = constructors;
	}
	public Method[] getMethods() {
		return methods;
	}
	public void setMethods(Method[] methods) {
		this.methods = methods;
	}
	public Class getSupClass() {
		return supClass;
	}
	public void setSupClass(Class supClass) {
		this.supClass = supClass;
	}
	public Interface[] getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(Interface[] interfaces) {
		this.interfaces = interfaces;
	}
	public Class(String nom, Field[] fields, Constructor[] constructors, Method[] methods, Class supClass,
			Interface[] interfaces) {
		super();
		this.nom = nom;
		this.fields = fields;
		this.constructors = constructors;
		this.methods = methods;
		this.supClass = supClass;
		this.interfaces = interfaces;
	}

}
