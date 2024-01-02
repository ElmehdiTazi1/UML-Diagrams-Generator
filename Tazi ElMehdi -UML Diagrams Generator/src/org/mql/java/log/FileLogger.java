package org.mql.java.log;

import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class FileLogger implements Logger{
    private String source="resources/logs/";
    private List<String>messages;
	public FileLogger() {
		messages = new Vector<>();
	}

	@Override
	public void log(LogLevel level, String msg) {
		 switch (level) {
         case DEBUG:
             source="resources/logs/debug.log";
             break;
         case INFO:
        	 source="resources/logs/info.log";
        	 break;
         case WARNING:
        	 source="resources/logs/warning.log";
             break;
         case ERROR:
        	 source="resources/logs/error.log";
        	 break;
         case Package:
        	 source="resources/logs/package.log";
        	 break;
         case Class:
             source="resources/logs/class.log";
             break;	 
         default:
             log(LogLevel.ERROR,"Niveau de log inconnu.");
     }
		messages.add(msg);
		try {
			PrintWriter out = new PrintWriter(source);
			for (String message : messages) {
				out.println(message);
			}
			out.close();
		}catch(Exception e) {
			log(LogLevel.ERROR,e.getMessage());
			//System.out.println("Erreur : " +e.getMessage());
		}
	}

}