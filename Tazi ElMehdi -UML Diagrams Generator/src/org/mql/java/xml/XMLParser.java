package org.mql.java.xml;

import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;


public class XMLParser {
	private static Logger logger ;
    public static void parse(String src) {
        try {
            File xmlFile = new File(src);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList classList = doc.getElementsByTagName("class");

            for (int temp = 0; temp < classList.getLength(); temp++) {
                Node classNode = classList.item(temp);

                if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element classElement = (Element) classNode;

                    String className = classElement.getAttribute("name");
                    log(LogLevel.INFO,className);

                    NodeList fieldsList = classElement.getElementsByTagName("field");
                    for (int i = 0; i < fieldsList.getLength(); i++) {
                        Node fieldNode = fieldsList.item(i);
                        if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element fieldElement = (Element) fieldNode;
                            String fieldName = fieldElement.getAttribute("name");
                            log(LogLevel.INFO,fieldName);
                        }
                    }

                    NodeList methodsList = classElement.getElementsByTagName("method");
                    for (int i = 0; i < methodsList.getLength(); i++) {
                        Node methodNode = methodsList.item(i);
                        if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element methodElement = (Element) methodNode;
                            String methodName = methodElement.getAttribute("name");
                            log(LogLevel.INFO,methodName);
                        }
                    }

                    NodeList relationsList = classElement.getElementsByTagName("relation");
                    for (int i = 0; i < relationsList.getLength(); i++) {
                        Node relationNode = relationsList.item(i);
                        if (relationNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element relationElement = (Element) relationNode;
                            String relationType = relationElement.getAttribute("type");
                            String targetClass = relationElement.getAttribute("target");
                            log(LogLevel.INFO,relationType+" "+targetClass);
                        }
                    }

                }
            }

          

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@SuppressWarnings("static-access")
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private static void log(LogLevel level, String msg) {
		if (logger != null) {
			logger.log(level, msg);
		}
	}

}
