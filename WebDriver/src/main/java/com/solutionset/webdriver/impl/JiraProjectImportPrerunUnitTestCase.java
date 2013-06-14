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

public class JiraProjectImportPrerunUnitTestCase {
	
	private final String projectImportFile = "JIRA-merge-20130604-1348.zip";
	
	private WebDriver driver = new ChromeDriver();
	private String baseUrl = "http://tracker.d.solutionset.com/";
	private List<String> projectList = new ArrayList();
	

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
	
	private List<String> provisionList(){
		
		List<String> projectList = new ArrayList();
		
//		projectList.add("DELLSNASHAREVOICE");
//		projectList.add("EMEA");
//		projectList.add("GOOGAEC");
//		projectList.add("GOOGENGAGE");
//		projectList.add("GOOGGDNNL");
//		projectList.add("GOOGNX");
//		projectList.add("REMITDATAWD");
//		projectList.add("DOTNETTOOL");
//		projectList.add("AMEXMISC");
//		projectList.add("AMEXCOMPARE");
//		projectList.add("AMEXR");
//		projectList.add("AMEXMROE");
//		projectList.add("AMEXUKSAW");
//		projectList.add("AMEXGCP");
//		projectList.add("APOLLO");
//		projectList.add("BERKELEY");
//		projectList.add("BLACKARROW");
//		projectList.add("BREAD");
//		projectList.add("BREADM");
//		projectList.add("CALOTCLIENT");
//		projectList.add("CALOTT");
//		projectList.add("CALOTINT");
//		projectList.add("CALOTDD");
//		projectList.add("CALOTBB");
//		projectList.add("CALOTCC");
//		projectList.add("CHEVRONG");
//		projectList.add("CLOUDERA");
//		projectList.add("CLUBTHREE");
//		projectList.add("CLUB");
//		projectList.add("CLUBFOUR");
//		projectList.add("CROWNA");
//		projectList.add("DATAS");
//		projectList.add("DELLA");
//		projectList.add("DELLBO");
//		projectList.add("DELLE");
//		projectList.add("DELLD");
//		projectList.add("DELLSNAP");
//		projectList.add("DELLB");
//		projectList.add("ECHOSIGN");
//		projectList.add("GOOGADMOB");
//		projectList.add("GOOGLEN");
//		projectList.add("GOOGLEP");
//		projectList.add("GOOGLEWSHOP");
//		projectList.add("GOOGLEABC");
//		projectList.add("GOOGLEM");
//		projectList.add("GOOGLECA");
//		projectList.add("GOOGADS");
//		projectList.add("GOOGLEAG");
//		projectList.add("GOOGCM");
//		projectList.add("GOOGALLSTARS");
//		projectList.add("GOOGLEENTAPPS");
//		projectList.add("GOOGLCS");
//		projectList.add("GOOGLEGA");
//		projectList.add("GOOGLEGW");
//		projectList.add("GOOGLEH");
//		projectList.add("GOOGLEO");
//		projectList.add("GOOGDC");
//		projectList.add("GOOGEAV");
//		projectList.add("GOOGLESHOW");
//		projectList.add("GOOGHTCTRANS");
//		projectList.add("GOOGRHINO");
//		projectList.add("GOOGLEA");
//		projectList.add("NEWOPS");
//		projectList.add("GORESOURCE");
//		projectList.add("LEAP");
//		projectList.add("MRCB");
//		projectList.add("NVPA");
//		projectList.add("PACUNION");
//		projectList.add("POLITEAR");
//		projectList.add("PROCURIAN");
//		projectList.add("PROSIGHT");
//		projectList.add("PROSIGHTB");
//		projectList.add("PROSIGHTC");
//		projectList.add("RSACA");
//		projectList.add("SSNB");
//		projectList.add("SITECOREKIT");
//		projectList.add("SOCIALR");
//		projectList.add("SOLSETCREAT");
//		projectList.add("SOLJIRA");
//		projectList.add("SOLSETWEBOPS");
//		projectList.add("SOLSET");
//		projectList.add("SPECKB");
//		projectList.add("STELLRA");
//		projectList.add("TEACHA");
//		projectList.add("TXUD");
//		projectList.add("UPSIPAD");
//		projectList.add("VALSPAR");
//		projectList.add("VMWARE");
//		projectList.add("VMWAREB");
//		projectList.add("WFSWEEP");
//		projectList.add("YOUTUBE");			
		
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
