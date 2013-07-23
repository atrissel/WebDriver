package com.solutionset.webdriver.impl;

import java.io.FileReader;
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

public class JiraProjectCreateUnitTestCase {
	
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
	
	/*
	 * Read the project list file and create an array of useable project definitions
	 */
	
	private List<ProjectBean> provisionList() throws Exception{
		
		List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		
		//List<String> nameList = new ArrayList<String>();
		
		CSVReader reader = new CSVReader(new FileReader("/Users/adamtrissel/git/JiraMigration/WebDriver/listProjects.csv"), '\t');
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			
			if (!nextLine[0].equals("//")){
			
				ProjectBean projectBean = new ProjectBean(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
				projectList.add(projectBean);
		
			}
			
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

//Code to shorten projectCode, not necessary for 6.0.1			
//			if (projectCode.length() > 10){
//				projectCode = projectCode.substring(0, 9);
//			}
			
			System.out.println(projectCode + " : " + projectName);
			
			driver.get(baseUrl + "secure/admin/AddProject!default.jspa");
			driver.findElement(By.id("add-project-name")).clear();
			driver.findElement(By.id("add-project-name")).sendKeys(projectName);
			driver.findElement(By.id("add-project-key")).clear();
			driver.findElement(By.id("add-project-key")).sendKeys(projectCode);
			driver.findElement(By.id("add-project-submit")).click();
			
			String projectString = "plugins/servlet/project-config/" + projectCode + "/workflows";
			
			driver.get(baseUrl + projectString);
			
			Thread.sleep(1000);
			
			if (!projectWorkFlow.equals("0")) {
				
				driver.findElement(By.id("project-config-workflows-scheme-change")).click();
				new Select(driver.findElement(By.id("schemeId_select"))).selectByValue(projectWorkFlow);
				driver.findElement(By.id("associate_submit")).click();
				
				Thread.sleep(500);
				
				driver.findElement(By.id("workflow-mapping-submit")).click();

				Thread.sleep(1000);
				
			}
			
			//additional code to add field scheme (this fixs scrum board problem)
			
			driver.findElement(By.id("project-admin-link")).click();
			//driver.findElement(By.linkText("System Default Field Configuration")).click();
			String fieldConfigurationPage = baseUrl + "plugins/servlet/project-config/" + projectCode + "/fields";
			driver.get(fieldConfigurationPage);
			
			Thread.sleep(2000);
			
			driver.findElement(By.id("project-config-tab-actions")).click();
			driver.findElement(By.id("project-config-fields-scheme-change")).click();
			new Select(driver.findElement(By.id("schemeId_select"))).selectByValue(fieldConfig);
			driver.findElement(By.id("associate_submit")).click();
			driver.findElement(By.cssSelector("#project-admin-link > span")).click();			
			
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
