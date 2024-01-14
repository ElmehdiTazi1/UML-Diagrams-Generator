package org.mql.java.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.reflection.PackageExtractor;

public class ProjetJava {
    private String name;
    private List<Package> packages= new Vector<>();
    private List<String> test;
	public ProjetJava(String projectName) {
		this.name=projectName;
		test=PackageExtractor.extractPackages(name);
		for (String p : test) {
			packages.add(new Package(p));
		}
		}
}