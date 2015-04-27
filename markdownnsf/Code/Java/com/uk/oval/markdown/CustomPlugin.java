package com.uk.oval.markdown;

import java.util.List;
import java.util.Map;

import org.markdown4j.Plugin;

public class CustomPlugin extends Plugin{

	public CustomPlugin() {
		  //bind your plugin with id
	    super("mypluginid");
	  }
	  @Override
	  public void emit(StringBuilder out, List<String> lines, Map<String, String> params) {
	    //read params and manage default value
	    String type = params.get("type");
	    if(type == null) {
	      type = "defaultType";                     
	    }
	    String clazz = params.get("class");
	    if(clazz == null) {
	      clazz = "defaultClass";                   
	    }

	    //process your plugin and return your text
	    out.append("...Custom Code");
	  }
	
}
