package com.solutionset.webdriver.impl;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JiraProjectCreateUnitTestCase {
	
	private WebDriver driver = new ChromeDriver();
	private String baseUrl = "http://tracker.d.solutionset.com/";
	private List<ProjectBean> projectList = new ArrayList();

	

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
	
	private List<ProjectBean> provisionList(){
		
		List<ProjectBean> projectList = new ArrayList<ProjectBean>();
		
		projectList.add(new ProjectBean("AMEXCOMPARE", "American Express SOW21 - Compare Card", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("AMEXGCP", "American Express SOW39 - GCP Maintenance ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("AMEXMISC", "American Express - Misc Small Projects", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("AMEXMROE", "American Express SOW28 - MR Online Enrollment", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("AMEXR", "American Express SOW27 - Russia GCP Website ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("AMEXUKSAW", "American Express SOW29 - UK Savings at Work ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("BERKELEY", "Berkeley Review", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("CALOTBB", "CALottery : Client & GTECH : Tech Testing & On-going Development", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("CALOTDD", "CALottery : 2nd Chance internal task queue", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("CALOTT", "CALottery - Dev, Systems Engineering, and defects - Internal", "SolutionSet Default FixedÊ"));
		projectList.add(new ProjectBean("CLOUDERA", "Cloudera - SOW 1", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("CLUBFOUR", "ClubOne - SOW4 ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("CLUBTHREE", "ClubOne - SOW 3", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("DATAS", "DataSift TACT MVP ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("DELLB", "Dell Social Net Advocacy (SNA, SNAp, SNApi)", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("DELLSNASHAREVOICE", "Dell SNA Competitor Data & Maintenance", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("ECHOSIGN", "EchoSign - SOW 1  Site Redesign ", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("GOOGLECA", "Google SOW46 CA Repo & Calendar System", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("GOOGLEM", "Google SOW45 Email Production Tools", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("GORESOURCE", "Launchpad Central", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("MRCB", "MRC SOW2", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("PROCURIAN", "Procurian SOW 2 - Website Updates", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("REMITDATAWD", " RemitDATA: SOW2", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("SITECOREKIT", "Sitecore Starter Kit", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("SOCIALR", "SocialRep", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("SOLSET", "solutionset.com: redesign 2011", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("SPECKB", "Speck Products SOW3 - Partner Portal", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("UPSIPAD", "UPS iPad Project", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("VALSPAR", "Valspar: SOW1", "SolutionSet Scrum"));
		projectList.add(new ProjectBean("YOUTUBE", "YouTube SOW 1 Course Builder", "SolutionSet Scrum"));
		
		return projectList;
		
	}
	
	private void addProjects() throws Exception{
		
		
		for (ProjectBean project : projectList){
			
			
			String projectCode = project.getProjectCode();

//Code to shorten projectCode, not necessary for 6.0.1			
//			if (projectCode.length() > 10){
//				projectCode = projectCode.substring(0, 9);
//			}
			
			String projectName = project.getProjectName();
			String projectWorkFlow = project.getProjectWorkFlow();
			
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
			
			driver.findElement(By.id("project-config-workflows-scheme-change")).click();
			new Select(driver.findElement(By.id("schemeId_select"))).selectByValue("10061");
			driver.findElement(By.id("associate_submit")).click();
			
			driver.findElement(By.id("workflow-mapping-submit")).click();
			//driver.findElement(By.id("leave_admin")).click();
			
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
