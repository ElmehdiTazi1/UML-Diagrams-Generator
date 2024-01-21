package org.mql.java.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mql.java.models.Class;
import org.mql.java.models.Package;
import org.mql.java.models.ProjetJava;

public class XMLGenerator {

	public static void generateXML(ProjetJava projet, String outputPath) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element projetJavaElement = document.createElement("projetjava");
			projetJavaElement.setAttribute("name", projet.getName());
			document.appendChild(projetJavaElement);

			for (Package aPackage : projet.getPackages()) {
				generatePackageXML(document, projetJavaElement, aPackage);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(outputPath);
			transformer.transform(domSource, streamResult);
		} catch (ParserConfigurationException | javax.xml.transform.TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void generatePackageXML(Document document, Element parentElement, Package aPackage) {
		Element packageElement = document.createElement("package");
		packageElement.setAttribute("name", aPackage.getNom());
		parentElement.appendChild(packageElement);

		for (Class aClass : aPackage.getClasses()) {
			generateClassXML(document, packageElement, aClass);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void generateClassXML(Document document, Element parentElement, Class aClass) {
		Element classElement = document.createElement("class");
		classElement.setAttribute("name", aClass.getNom());
		parentElement.appendChild(classElement);

		Element nomElement = document.createElement("nom");
		nomElement.appendChild(document.createTextNode(aClass.getNom()));
		classElement.appendChild(nomElement);

		Element fieldsElement = document.createElement("fields");
		for (Field field : aClass.getFields()) {
			Element fieldElement = document.createElement("field");
			fieldElement.setAttribute("name", field.getName());
			fieldsElement.appendChild(fieldElement);
		}
		classElement.appendChild(fieldsElement);

		Element constructorsElement = document.createElement("constructors");
		for (Constructor constructor : aClass.getConstructors()) {
			Element constructorElement = document.createElement("constructor");
			constructorElement.setAttribute("name", "" + constructor);

			constructorsElement.appendChild(constructorElement);
		}
		classElement.appendChild(constructorsElement);

		Element methodsElement = document.createElement("methods");
		for (Method method : aClass.getMethods()) {
			Element methodElement = document.createElement("method");
			methodElement.setAttribute("name", "" + method);

			methodsElement.appendChild(methodElement);
		}
		classElement.appendChild(methodsElement);

		if (aClass.getSupClass() != null) {
			Element superClassElement = document.createElement("superclass");
			superClassElement.appendChild(document.createTextNode(aClass.getSupClass().getNom()));
			classElement.appendChild(superClassElement);
		}

		Element interfacesElement = document.createElement("interfaces");
//        for (Interface anInterface : aClass.getInterfaces()) {
//            Element interfaceElement = document.createElement("interface");
//            // Ajouter d'autres attributs de l'interface si nécessaire
//
//            interfacesElement.appendChild(interfaceElement);
//        }
		classElement.appendChild(interfacesElement);
	}

}
