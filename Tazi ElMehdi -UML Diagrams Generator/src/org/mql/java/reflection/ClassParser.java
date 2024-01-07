package org.mql.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

public class ClassParser {
    private static List<String>squelette= new Vector<String>();
    private static List<String>res= new Vector<String>();
	public ClassParser() {
	  	
	}
	public static Vector<String> squelette(String source){
		squelette.clear();
		parser(source);
		affiche();
		return (Vector<String>) squelette;
	}
	private static  void parser(String source) {
        try {
            Class<?> c = Class.forName(source);
    			getClassSignature(c);
                getClassField(c);
                getClassMethod(c);
                getClassConstructor(c);
            for(Class cc : c.getDeclaredClasses()) 
				parser(cc.getName().toString());
            squelette.add("}");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe non trouvée : " + source);
        }
	}
	public static Vector<String> parse(String source){
		try {
			res.clear();
			Class<?> c = Class.forName(source);
			String s ="Nombre de propriétés du "+source+" ===> "+getClassField(c);
			res.add(s);
			s="Nombre de méthodes du "+source+" ===> "+getClassMethod(c);
			res.add(s);
			s="Chaîne d’héritage du "+source+" : ";
			res.add(s);
			s=c.getSimpleName();
			while(!"java.lang.Object".equals(c.getName())){
				c=(Class<?>) c.getGenericSuperclass();
				s+=" extends ==> "+c.getSimpleName()+" ";
			}
			res.add(s);
			
		} catch (Exception e) {
            System.out.println("Classe non trouvée : " + source);
		}
		return (Vector<String>) res;
	}
	private static  void getClassSignature(Class c) {
		String s="";
		s="public class " + c.getSimpleName();
		if(!"java.lang.Object".equals(c.getName())) 

	    s+=" extends "+c.getGenericSuperclass().getTypeName()+" ";
		
			Type []implement = c.getGenericInterfaces();
			if(implement.length!=0) s+=" implements ";
			for(Type type :implement) {
				s+=type.toString()+" ,";
			}
			s+=" {";
			squelette.add(s);
	}
	private static  int getClassField(Class c) {
		for (Field field : c.getDeclaredFields()) {
			String s ="    " + Modifier.toString(field.getModifiers()) + " " +
                    field.getType().getSimpleName() + " " + field.getName() + ";";
			squelette.add(s);
        }
		return c.getDeclaredFields().length;
	}
	private static  int getClassMethod(Class c) {
		String s;
        for (Method method : c.getDeclaredMethods()) {

            s="    " + Modifier.toString(method.getModifiers()) + " " +
                    method.getReturnType().getSimpleName() + " " + method.getName() + "(";

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                s+=parameter.getType().getSimpleName() + " " + parameter.getName();
                if (i < parameters.length - 1) {
                    s+=", ";
                }
            }

            s+=");";
            squelette.add(s);
        }
        return c.getDeclaredMethods().length; 
	}
	private static  void getClassConstructor(Class c) {
		String s;
		for (Constructor<?> constructor : c.getDeclaredConstructors()) {
            s="    " + Modifier.toString(constructor.getModifiers()) + " " +
                    c.getSimpleName() + "(";

            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                s+=parameter.getType().getSimpleName() + " " + parameter.getName();
                if (i < parameters.length - 1) {
                    s+=", ";
                }
            }

            s+=");";
            squelette.add(s);
        }
        
	}
	private static  void affiche() {
		for (String string : squelette) {
			System.out.println(string);
		}
	}
		

	

}
