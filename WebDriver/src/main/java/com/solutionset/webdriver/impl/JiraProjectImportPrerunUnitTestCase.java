package com.solutionset.webdriver.impl;

import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import au.com.bytecode.opencsv.CSVReader;

public class JiraProjectImportPrerunUnitTestCase {
	
	private final String projectImportFile = "JIRA-merge-20130628-modified.zip";
	
	private WebDriver driver = new ChromeDriver();
	private String baseUrl = "http://tracker.d.solutionset.com/";
	private List<String> projectList = new ArrayList<String>();
	

	@Before
	public void setUp() throws Exception {
		
		projectList = provisionList();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void writeToFile(String filename, String contents) throws Exception, UnsupportedEncodingException{
		
		PrintWriter pw = new PrintWriter(filename + ".html", "UTF-8");
		
		pw.write(contents);
		
		pw.close();
		
	}
	
	private List<String> provisionList() throws Exception{
		
		List<String> projectList = new ArrayList<String>();
		
		CSVReader reader = new CSVReader(new FileReader("/Users/adamtrissel/git/JiraMigration/WebDriver/listProjects.csv"), '\t');
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			
			if (!nextLine[0].equals("//")){
				
				ProjectBean projectBean = new ProjectBean(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);

				projectList.add(projectBean.getProjectCode());

			}
			
		
		}
		
		reader.close();
		
		return projectList;
		
	}
	
	

	@Test
	public void test() throws InterruptedException {

		driver.get(baseUrl + "/login.jsp");
		driver.findElement(By.id("login-form-username")).clear();
		driver.findElement(By.id("login-form-username")).sendKeys("adam.trissel");
		driver.findElement(By.id("login-form-password")).clear();
		driver.findElement(By.id("login-form-password")).sendKeys("m@ke1hill");
		driver.findElement(By.id("login-form-submit")).click();
		//driver.findElement(By.id("admin_link")).click();
		driver.get(baseUrl + "/secure/admin/ProjectImportSelectBackup!default.jspa");
		driver.findElement(By.name("backupXmlPath")).clear();
		driver.findElement(By.name("backupXmlPath")).sendKeys(projectImportFile);
		driver.findElement(By.id("project-import-submit")).click();
		
		
		// Warning: waitForTextPresent may require manual changes
		//this is just looping until the body text is found.  
		//This particular loop is waiting for the process bar to finish.  
		//I have given this 2 minutes to complete.
		
		for (int second = 0;; second++) {
			if (second >= 120) fail("timeout");
			try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The list of projects contains all projects present in the XML backup provided\\.[\\s\\S]*$")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}
		
		
		//this loop has to process each project
		//There are three steps:
		
		//1) find a project in the dropdown
		//2) proceed to the import page
		//3) migrate back to this page and repeat for each project.
		
		for (String project : projectList){
			
			System.out.println(project);
			
			new Select(driver.findElement(By.id("project_select"))).selectByValue(project);
			driver.findElement(By.id("next_submit")).click();
			
			for (int second = 0;; second++) {
				if (second >= 1000) fail("timeout");
				try { if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The results of automatic mapping are displayed below\\.[\\s\\S]*$")) break; } catch (Exception e) {}
				Thread.sleep(1000);
			}
			
			try {
				writeToFile(project, driver.getPageSource());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("bad encoding type??");;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("general exception writing to file");
			}
			
			driver.get(baseUrl + "/secure/admin/ProjectImportSelectProject!default.jspa");
			//driver.findElement(By.cssSelector("[value*=Previous]")); 
			//driver.findElement(By.id("prevButton")).click();
			
		}

		//driver.findElement(By.id("log_out")).click();
		driver.quit();
		//driver.close();
	
	}

}
