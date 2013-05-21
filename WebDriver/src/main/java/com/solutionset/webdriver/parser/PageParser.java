package com.solutionset.webdriver.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageParser {
	
	public ArrayList listFilesForFolder(String directoryName) {
		
		ArrayList<String> fileNames = new ArrayList();
		
		File folder = new File(directoryName);
		
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            //
	        } else {
	            fileNames.add(fileEntry.getPath());
	        }
	    }
	    
	    return fileNames;
	}

	public boolean hasError(String html){
		
		boolean retVal = false;
		
		if (html.contains("The data mappings have produced errors")){
			retVal = true;
		}
		
		return retVal;
	}
	
	public void outputHeader(){
		
		System.out.println("<!DOCTYPE html>");
		System.out.println("<html>");
		System.out.println("<head>");
		System.out.println("<title>MyHtml.html</title>");    
		System.out.println(" ");	
		System.out.println("<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">");    
		System.out.println("<meta http-equiv=\"description\" content=\"this is my page\">");    
		System.out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");    
		System.out.println(" ");    
		System.out.println("<!--<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles.css\">-->");    
		System.out.println(" ");    
		System.out.println("</head>");  
		
	}
	
	public void evaluateFile(String fileName){
		
		
		try{

			File input = new File(fileName);
			Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
			
			Element contentBody = doc.select("div.content-body").first();
			
			//System.out.println(contentBody.html());
			Elements messages = contentBody.getElementsByClass("aui-message");
			
			for (Element element : messages){
				
				if (hasError(element.html())){
					
					Elements errors = contentBody.getElementsByClass("error");
					
					for (Element error : errors){
						
						if (!hasError(error.html())){
							
							System.out.println("<h3>" + fileName + "</h3><br>");
							System.out.println(error.html());
							System.out.println("<br>");
							
						}
						
					}
					
				}
				
			}
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		PageParser pp = new PageParser();
		
		ArrayList<String> fileNames = pp.listFilesForFolder("/Users/adamtrissel/Workspaces/m/WebDriver/StatusFiles");
		
		pp.outputHeader();
		
		System.out.println("<body>");
		
		for (String fileName : fileNames){
			
			pp.evaluateFile(fileName);
			
		}

		System.out.println("</body>\n</html>");
		

	}

}
