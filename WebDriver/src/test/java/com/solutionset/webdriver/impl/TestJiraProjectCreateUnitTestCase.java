package com.solutionset.webdriver.impl;

//import static org.junit.Assert.*;

//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import au.com.bytecode.opencsv.CSVReader;

public class TestJiraProjectCreateUnitTestCase {
	
	private WebDriver driver = new ChromeDriver();
	private String baseUrl = "http://tracker.d.solutionset.com/";
	private List<ProjectBean> projectList = new ArrayList<ProjectBean>();

	

	@Before
	public void setUp() throws Exception {
		
		projectList = provisionList();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
//	private void writeToFile(String filename, String contents) throws Exception, UnsupportedEncodingException{
//		
//		PrintWriter pw = new PrintWriter(filename + ".html", "UTF-8");
//		
//		pw.write(contents);
//		
//		pw.close();
//		
//	}
	
	private List<ProjectBean> provisionList() throws Exception{
		
		List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		
		//List<String> nameList = new ArrayList<String>();
		CSVReader reader = new CSVReader(new FileReader("/Users/adamtrissel/git/JiraMigration/WebDriver/listProjects.csv"), '\t');
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
		    //nameList.add(nextLine[1]);
			
			ProjectBean projectBean = new ProjectBean(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
			projectList.add(projectBean);
		
		}
		
		reader.close();
		
		return projectList;
		
	}
	
	private void addProjects() throws Exception{
		
		
		for (ProjectBean project : projectList){
			
			
			String projectCode = project.getProjectCode();
			String fieldConfig = project.getProjectFieldScheme();
			String projectName = project.getProjectName();
			String projectWorkFlow = project.getProjectWorkFlow();

			System.out.println(projectCode + " : " + projectName+ " : " + fieldConfig + " : " + projectWorkFlow);
			
		}
		
	}

	@Test
	public void test() throws Exception {

		driver.get(baseUrl + "/login.jsp");
		driver.findElement(By.id("login-form-username")).clear();
		driver.findElement(By.id("login-form-username")).sendKeys("atrissel");
		driver.findElement(By.id("login-form-password")).clear();
		driver.findElement(By.id("login-form-password")).sendKeys("m@ke1hill");
		driver.findElement(By.id("login-form-submit")).click();
		
		addProjects();
		
		driver.quit();
		//driver.close();
	
	}

}
