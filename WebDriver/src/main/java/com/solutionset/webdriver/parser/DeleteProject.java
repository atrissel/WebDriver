package com.solutionset.webdriver.parser;


import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DeleteProject {

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		final String inputFile = "/Users/adamtrissel/git/JiraMigration/WebDriver/src/test/resources/testDeletePage.html";

		
		File input = new File(inputFile);
		Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
		
		Elements projectTable = doc.select("table.aui");
		
		if (projectTable.size() >= 1) {
			
			Element project = projectTable.first();

			Elements deleteNodes = project.select("a");
			
			for (Element node : deleteNodes){
				
				//System.out.println(node.html());
				
				if (node.toString().contains("delete_project_")){
					
					System.out.println(node.toString()	);
					
				}
				
			}
			
			//System.out.println(deleteNodes);
			
			
			
		}
			
			
			
		
//		System.out.println(projectTable.html());
//		System.out.println(projectTable.size());
//		
	}

}
