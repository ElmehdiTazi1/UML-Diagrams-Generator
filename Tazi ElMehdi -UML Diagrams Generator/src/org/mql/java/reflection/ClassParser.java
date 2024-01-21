package org.mql.java.reflection;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Vector;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;
import org.mql.java.models.Class;

public class ClassParser {

	private static java.lang.Class clazz;
	private static Logger logger;
    private static String path;
	public static java.lang.Class<?> loadClass(File file, Boolean drapeau) {
		java.lang.Class<?> cls = null;
		if (drapeau) {
			
			try {
				File root = new File(
						file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("\\bin\\") + 5));
				URL[] url = { root.toURI().toURL() };
				@SuppressWarnings("resource")
				URLClassLoader urlClassLoader = new URLClassLoader(url);
				cls = urlClassLoader.loadClass(getClassPackageName(file.getAbsolutePath()));
				log(LogLevel.Class, cls.descriptorString());
			} catch (Exception e) {
				System.out.println("Error : " + e.getMessage());
			}
		} else {
			try {
				cls = java.lang.Class.forName("" + file);
				log(LogLevel.Class, cls.descriptorString());
			} catch (Exception e) {
				System.out.println("Error " + e.getMessage());
			}
		}
		return cls;
	}

	public static String getClassPackageName(String absolutePath) {
		return absolutePath.substring(absolutePath.lastIndexOf("\\bin\\") + 5).replace(".class", "")
				.replace(File.separator, ".");
	}

	private static void setNom(Class c) {
		c.setNom(clazz.getName());
	}

	private static void setField(Class c) {
		Vector<Field> fields = new Vector<>();
		for (Field field : clazz.getDeclaredFields()) {
			String s = "    " + Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " "
					+ field.getName() + ";";
			log(LogLevel.field, s);
			fields.add(field);
		}
		c.setFields(fields);
	}

	private static void setConstructeur(Class c) {
		@SuppressWarnings({ "rawtypes" })
		Vector<Constructor> constructeur = new Vector<>();
		String s = null;
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			s = "    " + Modifier.toString(constructor.getModifiers()) + " " + clazz.getSimpleName() + "(";

			Parameter[] parameters = constructor.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				s += parameter.getType().getSimpleName() + " " + parameter.getName();
				if (i < parameters.length - 1) {
					s += ", ";
				}
			}

			s += ");";
			log(LogLevel.constructor, s);
			constructeur.add(constructor);

		}
		c.setConstructors(constructeur);
	}

	private static void setMethod(Class c) {
		Vector<Method> methods = new Vector<>();
		String s;
		for (Method method : clazz.getDeclaredMethods()) {

			s = "    " + Modifier.toString(method.getModifiers()) + " " + method.getReturnType().getSimpleName() + " "
					+ method.getName() + "(";

			Parameter[] parameters = method.getParameters();
			for (int i = 0; i < parameters.length; i++) {
				Parameter parameter = parameters[i];
				s += parameter.getType().getSimpleName() + " " + parameter.getName();
				if (i < parameters.length - 1) {
					s += ", ";
				}
			}

			s += ");";
			log(LogLevel.method, s);
			methods.add(method);

		}
		c.setMethods(methods);
	}

	private static void setSubClass(Class c) {
		String s="";
		if (clazz.getGenericSuperclass() != null) {
			if(clazz.getGenericSuperclass().getTypeName().contains("org")) s+=c.getPath();
			s += clazz.getGenericSuperclass().getTypeName();
			Class sup = new Class(s);
			log(LogLevel.subClass, s);
			c.setSupClass(sup);
		}
	}
	private static void setInterfaces(Class c) {
		List<Class>interfaces = new Vector<>();
		Type []t = c.getClazz().getGenericInterfaces();
		for (int i = 0; i < t.length; i++) {
			c.addInterface(new Class(t[i].getTypeName()));
			
		}
	}
	public static void init(String className, Class c) {
		Boolean drap = false;
		if (className.contains("C:/")) {
			drap = true;
		}else if (className.contains("org")) {
			String s = path+className;
			drap = true;
			className=s;
		}
		clazz = loadClass(new File(className), drap);
		if(clazz!=null) {
		if(className.contains("bin")) {
			c.setPath(className.substring(0,className.indexOf("org")));
			path=className.substring(0,className.indexOf("org"));
		}
		c.setClazz(clazz);
		setNom(c);
		setField(c);
		setConstructeur(c);
		setMethod(c);
		setSubClass(c);
		setInterfaces(c);
		}

	}



	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private static void log(LogLevel level, String msg) {
		if (logger != null) {
			logger.log(level, msg);
		}
	}

}
