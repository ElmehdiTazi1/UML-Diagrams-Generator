package org.mql.java.log;


public class ConsoleLogger implements Logger{

	public ConsoleLogger() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log(LogLevel level, String msg) {
        System.out.println(level+" : "+msg);
	}


}
