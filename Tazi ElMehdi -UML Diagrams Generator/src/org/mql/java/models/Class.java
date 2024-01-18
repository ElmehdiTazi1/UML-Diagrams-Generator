package org.mql.java.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.List;
import java.util.Vector;

import static org.mql.java.reflection.ClassParser.*;


public class Class {
    private String nom;
    private java.lang.Class clazz;
    private List<Field> fields;
	@SuppressWarnings("rawtypes")
	private List<Constructor> constructors;
	private List <Method>  methods;
	private Class supClass;
	private List<Interface>  interfaces;
    private List<Class> relatedClasses = new Vector<Class>();
    private List<Class> aggregatedClasses;
    private List<Class> composedClasses;
    private String path;
	public List<Class> getRelatedClasses() {
		return relatedClasses;
	}
	public void setRelatedClasses(List<Class> relatedClasses) {
		this.relatedClasses = relatedClasses;
	}
	public void addRelatedClasses(Class c) {
		this.relatedClasses.add(c);
	}
	public List<Class> getAggregatedClasses() {
		return aggregatedClasses;
	}
	public void setAggregatedClasses(List<Class> aggregatedClasses) {
		this.aggregatedClasses = aggregatedClasses;
	}
	public List<Class> getComposedClasses() {
		return composedClasses;
	}
	public void setComposedClasses(List<Class> composedClasses) {
		this.composedClasses = composedClasses;
	}
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
	public void setClazz(java.lang.Class clazz) {
		this.clazz = clazz;
	}
	public java.lang.Class getClazz() {
		return clazz;
	}
	public Class(String className) {
		super();
		init(className,this);
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
