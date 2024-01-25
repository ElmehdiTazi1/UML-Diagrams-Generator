package org.mql.java.xml;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.mql.java.models.Package ;
import org.mql.java.log.ConsoleLogger;
import org.mql.java.log.LogLevel;
import org.mql.java.log.Logger;
import org.mql.java.models.Class;
import org.mql.java.models.ProjetJava;
import org.mql.java.uml.RelationshipDetector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler {
	private String projetJava, packages, c, text;
    private ProjetJava project;
	private Logger logger= new ConsoleLogger();
    private List<Class> classes;

    public SaxParser() {
    }

    public ProjetJava parse(String source) {
        SAXParserFactory factory = SAXParserFactory.newDefaultInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(source, this);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        return project;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if ("projetjava".equals(qName)) {
            projetJava = attributes.getValue(0);
            project = new ProjetJava();
        } else if ("package".equals(qName)) {
            packages = attributes.getValue(0);
            classes = new Vector<>();
        } else if ("class".equals(qName)) {
            c = attributes.getValue(0);
            c = packages + "/" + c.split("\\.")[c.split("\\.").length - 1];
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("class".equals(qName)) {
            Class clazz = new Class(c);
            classes.add(clazz);
        }
        if ("package".equals(qName)) {
            Package pack = new Package();
            pack.setNom(packages);
            pack.setClasses(classes);
            project.addPackage(pack);
        }
        if ("projetjava".equals(qName)) {
            project.setName(projetJava);
            RelationshipDetector.detectRelationships(project);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
    	log(LogLevel.INFO,"project :"+project.getName());
        for (Package p : project.getPackages()) {
            log(LogLevel.INFO,"  Package => " + p.getNom());
            for (Class c : p.getClasses()) {
                	log(LogLevel.INFO,"    Class => " + c.getNom());
                for (Field f : c.getFields()) {
                    log(LogLevel.INFO,"        Field=> " + f.getName());
                }
                if (c.getSupClass() != null)
                    log(LogLevel.INFO,"        SubClass=> " + c.getSupClass());
            }
        }
    }
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private void log(LogLevel level, String msg) {
		if (logger != null) {
			logger.log(level, msg);
		}
	}
}
