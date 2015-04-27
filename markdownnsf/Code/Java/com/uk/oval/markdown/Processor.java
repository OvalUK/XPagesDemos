package com.uk.oval.markdown;


import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;


public class Processor {
	/*public static String processTest(String input) {
		String output = "";
		try {
			//HTMLSanitiser.
			//remove spans and font tags
			String removeFonts = "(g)<(FONT|font)([ ]([a-zA-Z]+)=(\\\"|\')[^\\\"\\\\\']+(\\\"|\'))*[^>]+>([^<]+)(<\\/FONT>|<\\/font>)";
			String removeSpans = "(g)<(SPAN|span)([ ]([a-zA-Z]+)=(\\\"|\')[^\\\"\\\\\']+(\\\"|\'))*[^>]+>([^<]+)(<\\/SPAN>|<\\/span>)";
			input = input.replaceAll("(g)<span", "$6");
			output =  new Markdown4jProcessor().process(input);
			//output = output.replace("data:image ","data:image");
			output = output.replace(" /","/");
			//data:image /png;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return output;
}*/
	public static String process(String input) {
		String output = "";
		try {
			//remove spans and fonts
			String removeFonts = "<(FONT|font)([ ]([a-zA-Z]+)=(\\\"|\')[^\\\"\\\\\']+(\\\"|\'))*[^>]+>([^<]+)(<\\/FONT>|<\\/font>)";
			String removeSpans = "<(SPAN|span)([ ]([a-zA-Z]+)=(\\\"|\')[^\\\"\\\\\']+(\\\"|\'))*[^>]+>([^<]+)(<\\/SPAN>|<\\/span>)";
			input = input.replaceAll(removeFonts, "$6").replaceAll(removeSpans, "$6");
			
			//look for image embedds
			//String pattern = "(?i)(\\%24\\%24OpenDominoDocument.xsp\\?documentId=.*?)(.+?)(\\&amp;action=editDocument)";
			String pattern = "(?i)\\[youtube\\]\\((.*?)\\)";
			//String pattern = "(?i)([youtube]\\(*?)(.+?)(\\))";
			String replace = "<iframe class=\"youtube-player\" type=\"text/html\" width=\"432\" height=\"270\" style=\"max-width:100%;\" src=\"http://www.youtube.com/embed/$1?wmode=opaque\" frameborder=\"0\"></iframe>";
			input = input.replaceAll(pattern, replace);
			//input = input.replaceAll(pattern, "0/$2\\?OpenDocument");
			//![:youtube 600x400](G-M7ECt3-zY)
			//[[embed url=http://www.youtube.com/watch?v=6YbBmqUnoQM]]
			/*
			 * 
			 */
			output = new Markdown4jProcessor().process(input);
			//output = new Markdown4jProcessor().registerPlugins(new CustomPlugin()).process(input);
			
			//fix to remove extra space that breaks IE
			//output = output.replace("data:image ","data:image");
			output = output.replace(" /","/");
			//Markdown4jProcessor mk = new Markdown4jProcessor();
			//mk.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
	
	
	
}
