package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.List;

import static org.mql.java.reflection.ClassParser.*;


public class Class {
    private String nom;
    private List<Field> fields;
	@SuppressWarnings("rawtypes")
	private List<Constructor> constructors;
	private List <Method>  methods;
	private Class supClass;
	private List<Interface>  interfaces;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	@SuppressWarnings("rawtypes")
	public List<Constructor> getConstructors() {
		return constructors;
	}
	@SuppressWarnings("rawtypes")
	public void setConstructors(List<Constructor> constructors) {
		this.constructors = constructors;
	}
	public List<Method> getMethods() {
		return methods;
	}
	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}
	public Class getSupClass() {
		return supClass;
	}
	public void setSupClass(Class supClass) {
		this.supClass = supClass;
	}
	public List<Interface> getInterfaces() {
		return interfaces;
	}
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}
	public Class(String className) {
		super();
		init(className,this);
	}
}
