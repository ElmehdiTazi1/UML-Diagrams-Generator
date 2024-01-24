package org.mql.java.uml;

import java.lang.reflect.Field;
import java.util.List;
import org.mql.java.models.ProjetJava;
import org.mql.java.models.Package;
import org.mql.java.models.Class;
public class RelationshipDetector {

    public static void detectRelationships(ProjetJava project) {
        for (Package aPackage : project.getPackages()) {
            for (Class aClass : aPackage.getClasses()) {
                // Détecter les relations avec d'autres classes
                detectRelatedClasses(aClass, aPackage.getClasses());


            }
        }
    }

    private static void detectRelatedClasses(Class sourceClass, List<Class> allClasses) {
        for (Field field : sourceClass.getFields()) {
            if (field.getType().toString().contains("class")) {
                try {
                	if(!field.getType().getName().startsWith("java") && !sourceClass.getNom().equals(field.getType().getName())) {
                		sourceClass.addRelatedClasses(new Class(field.getType().getCanonicalName()));
                	}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}