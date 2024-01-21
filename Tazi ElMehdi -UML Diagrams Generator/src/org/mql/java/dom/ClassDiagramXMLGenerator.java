package org.mql.java.dom;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.mql.java.models.ProjetJava;
import org.mql.java.models.Package;
import org.mql.java.models.Class;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class ClassDiagramXMLGenerator {

    public static void generateXML(ProjetJava projetJava, String outputFile) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(new FileWriter(outputFile));

            writer.writeStartDocument();
            writer.writeStartElement("class_diagram");

            for (Package aPackage : projetJava.getPackages()) {
                for (Class aClass : aPackage.getClasses()) {
                    writeClassElement(writer, aClass);
                }
            }

            writer.writeEndElement();
            writer.writeEndDocument();

            writer.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeClassElement(XMLStreamWriter writer, Class aClass) throws XMLStreamException {
        writer.writeStartElement("class");
        writer.writeAttribute("name", aClass.getNom());
        writeFields(writer, aClass.getFields());
        writeMethods(writer, aClass.getMethods());
        writeRelations(writer, aClass.getSupClass(), aClass.getRelatedClasses(),aClass.getInterfaces());
        writer.writeEndElement();
    }

    private static void writeFields(XMLStreamWriter writer, List<Field> fields) throws XMLStreamException {
        writer.writeStartElement("fields");
        for (Field field : fields) {
            writer.writeStartElement("field");
            String s =field.accessFlags().toString().equals("[PRIVATE]")?"-":"+";
            writer.writeAttribute("name",s + field.getName()+":"+field.getType().getSimpleName());
            writer.writeEndElement();
        }

        writer.writeEndElement();
    }


    private static void writeMethods(XMLStreamWriter writer, List<Method> methods) throws XMLStreamException {
        writer.writeStartElement("methods");

        for (Method method : methods) {
            writer.writeStartElement("method");
            String s = method.getName() + "(";

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                Parameter parameter = parameters[i];
                s += parameter.getType().getSimpleName() + " " + parameter.getName();
                if (i < parameters.length - 1) {
                    s += ", ";
                }
            }
            s += "):" + method.getReturnType().getSimpleName();
            writer.writeAttribute("name", method.accessFlags().toString().equals("[PRIVATE]")?"-":"+"+s);
            writer.writeEndElement();
        }

        writer.writeEndElement();
    }

    private static void writeRelations(XMLStreamWriter writer, Class superClass, List<Class> relatedClasses,List<Class>interfaces) throws XMLStreamException {
        writer.writeStartElement("relations");

        if (superClass != null) {
            writer.writeStartElement("relation");
            writer.writeAttribute("type", "inheritance");
            writer.writeAttribute("target", superClass.getNom());
            writer.writeEndElement();
        }
        if(relatedClasses.size()>0) {
        for (Class relatedClass : relatedClasses) {
            writer.writeStartElement("relation");
            writer.writeAttribute("type", "association");
            if(relatedClass.getNom()!=null)
            writer.writeAttribute("target", relatedClass.getNom());
            writer.writeEndElement();
        }
        }
        if(interfaces.size()>0) {
        for (Class i : interfaces ) {
            writer.writeStartElement("relation");
            writer.writeAttribute("type", "implements");
            writer.writeAttribute("target", i.getNom());
            writer.writeEndElement();
        }
        }

        writer.writeEndElement();
    }


}
