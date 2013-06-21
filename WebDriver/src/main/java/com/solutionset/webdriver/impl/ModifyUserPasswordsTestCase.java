package com.solutionset.webdriver.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
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

import au.com.bytecode.opencsv.CSVReader;

public class ModifyUserPasswordsTestCase {
	
	private WebDriver driver = new ChromeDriver();
	private String baseUrl = "http://tracker.d.solutionset.com/";
	private List<String> nameList = new ArrayList();
	
	private List<String> provisionList() throws Exception{
		
		List<String> nameList = new ArrayList();
		
		CSVReader reader = new CSVReader(new FileReader("/Users/adamtrissel/git/JiraMigration/WebDriver/listusers.csv"), '\t');
		String [] nextLine;
		while ((nextLine = reader.readNext()) != null) {
		    // nextLine[] is an array of values from the line
		    //System.out.println(nextLine[1]);
		    nameList.add(nextLine[1]);
		    }
		
		return nameList;
		
		
	}

	@Before
	public void setUp() throws Exception {
		
		nameList = provisionList();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		driver.get(baseUrl + "/login.jsp?os_destination=%2Fsecure%2FDashboard.jspa");
		driver.findElement(By.id("login-form-username")).clear();
		driver.findElement(By.id("login-form-username")).sendKeys("atrissel");
		driver.findElement(By.id("login-form-password")).clear();
		driver.findElement(By.id("login-form-password")).sendKeys("m@ke1hill");
		driver.findElement(By.id("login-form-submit")).click();
		
		Integer counter = 0;
		Integer length = nameList.size();
		
		for (String name : nameList){
			
			System.out.println((++counter).toString() + " of " + length.toString() + " : " + name);
			
			String pageString = baseUrl + "secure/admin/user/SetPassword!default.jspa?name=" + name;

			driver.get(pageString);
			driver.findElement(By.name("password")).clear();
			driver.findElement(By.name("password")).sendKeys("convertme");
			driver.findElement(By.name("confirm")).clear();
			driver.findElement(By.name("confirm")).sendKeys("convertme");
			driver.findElement(By.id("user-edit-password-submit")).click();

		}
		
		driver.quit();
		
	
	}

}
