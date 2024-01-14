package org.mql.java.models;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import static org.mql.java.reflection.ClassParser.*;


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
	public Class(String className) {
		super();
		loadClass(new File(className+".class"));

	}
}
