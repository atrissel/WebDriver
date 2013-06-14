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
		
//		projectList.add(new ProjectBean("AMEXCOMPARE", "AmericanExpress SOW21 - Compare Card", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXGCP", "AmericanExpress SOW39 -GCP Maintenance", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXMISC", "AmericanExpress - Misc Small Projects", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXMROE", "AmericanExpress SOW28 - MR OnlineEnrollment", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXR", "AmericanExpress SOW27 - RussiaGCP Website", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXUKSAW", "AmericanExpress SOW29 - UK Savings at Work", "10061", "10020"));
//		projectList.add(new ProjectBean("BERKELEY", "Berkeley Review", "10061", "10020"));
//		projectList.add(new ProjectBean("BREADM", "Bre.ad SOW 2", "10061", "10000"));
//		projectList.add(new ProjectBean("CALOTBB", "CALottery : Client &GTECH : Tech Testing & On-goingDevelopment", "10061", "10060"));
//		projectList.add(new ProjectBean("CALOTT", "CALottery -Dev, SystemsEngineering, andDefects - Internal", "10061", "10050"));
//		projectList.add(new ProjectBean("CLOUDERA", "Cloudera - SOW 1", "10061", "10062"));
//		projectList.add(new ProjectBean("CLUBFOUR", "ClubOne - SOW4", "10061", "10000"));
//		projectList.add(new ProjectBean("CLUBTHREE", "ClubOne - SOW 3", "10061", "10000"));
//		projectList.add(new ProjectBean("DATAS", "DataSift TACT MVP", "10061", "10000"));
//		projectList.add(new ProjectBean("DELLA", "Dell - SOW1 - Social Net Advocacy", "10061", "10020"));
//		projectList.add(new ProjectBean("DELLSNASHAREVOICE", "Dell SNA CompetitorData & Maintenance", "10061", "10020"));
//		projectList.add(new ProjectBean("ECHOSIGN", "echoSign - SOW 1 Site Redesign", "10061", "10000"));
//		projectList.add(new ProjectBean("GOOGLECA", "Google SOW46 CA Repo & Calendar System", "10061", "10060"));
//		projectList.add(new ProjectBean("GOOGLEM", "Google SOW45Email Production Tools", "10061", "10060"));
//		projectList.add(new ProjectBean("GORESOURCE", "Launchpad Central", "10061", "10060"));
//		projectList.add(new ProjectBean("LEAP", "Leap Motion", "10061", "10000"));
//		projectList.add(new ProjectBean("PACUNION", "Pacific Union", "10061", "10060"));
//		projectList.add(new ProjectBean("POLITEAR", "PolitEar SOW1", "10061", "10060"));
//		projectList.add(new ProjectBean("PROCURIAN", "Procurian SOW 2 - Website Updates", "10061", "10000"));
//		projectList.add(new ProjectBean("SITECOREKIT", "Sitecore Starter Kit", "10061", "10020"));
//		projectList.add(new ProjectBean("SOCIALR", "SocialRep", "10061", "10000"));
//		projectList.add(new ProjectBean("SOLSET", "Solutionset.com: redesign 2011", "10061", "10000"));
//		projectList.add(new ProjectBean("SOLSETWEBOPS", "SolutionSet - SystemsEngineering Task Queue", "10061", "10020"));
//		projectList.add(new ProjectBean("SPECKB", "Speck Products SOW3 - Partner Portal", "10061", "10060"));
//		projectList.add(new ProjectBean("UPSIPAD", "UPS iPad Project", "10061", "10020"));
//		projectList.add(new ProjectBean("VALSPAR", "Valspar: SOW1", "10061", "10060"));
//		projectList.add(new ProjectBean("YOUTUBE", "YouTube SOW 1 Course Builder", "10061", "10020"));
//		projectList.add(new ProjectBean("AMEXUSSITE", "AmericanExpress SOW30 - US Site", "0", "10034"));
//		projectList.add(new ProjectBean("APOLLO", "ApolloGroup SOW 3", "0", "10020"));
//		projectList.add(new ProjectBean("BLACKARROW", "BlackArrow", "0", "10000"));
//		projectList.add(new ProjectBean("BREAD", "Bre.ad Maintenance - SOW1", "0", "10000"));
//		projectList.add(new ProjectBean("CALOTCC", "Calottery : imarcs 2nd chance TDD tech queue", "0", "10060"));
//		projectList.add(new ProjectBean("CALOTCLIENT", "CALottery - CLIENT related task queue", "0", "10042"));
//		projectList.add(new ProjectBean("CALOTDD", "CALottery : 2nd Chance internal task queue", "10061", "10060"));
//		projectList.add(new ProjectBean("CALOTINT", "CALottery - Internal Project To-dos", "0", "10042"));
//		projectList.add(new ProjectBean("CHEVRONG", "Chevron", "0", "10020"));
//		projectList.add(new ProjectBean("CISCOUAT", "Cisco - CLN4.5 Upgrade UAT", "0", "10020"));
//		projectList.add(new ProjectBean("CLUB", "ClubOne - SOW1", "0", "10000"));
//		projectList.add(new ProjectBean("CROWNA", "Crown Peak SOW7", "0", "10060"));
//		projectList.add(new ProjectBean("DELLB", "Dell Social Net Advocacy (SNA, SNAp, SNApi)", "10061", "10060"));
//		projectList.add(new ProjectBean("DELLBO", "DELL Back Office", "0", "10000"));
//		projectList.add(new ProjectBean("DELLD", "Dell SNA Beta Testing", "0", "10061"));
//		projectList.add(new ProjectBean("DELLE", "DELLEIT", "0", "10060"));
//		projectList.add(new ProjectBean("DELLSNAP", "Dell SNAP Productization", "0", "10000"));
		projectList.add(new ProjectBean("DOTNETTOOL", ".NET Team Tools", "0", "10020"));
//		projectList.add(new ProjectBean("EMEA", "Epsilon - MarriottEMEA", "0", "10060"));
//		projectList.add(new ProjectBean("GOOGADMOB", "Google AdWords Mobile App", "0", "10060"));
//		projectList.add(new ProjectBean("GOOGADS", "Google: ADS", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGAEC", "Google: AgencyEngage Central", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGALLSTARS", "Google:Engage All Stars Microsite", "0", "10060"));
//		projectList.add(new ProjectBean("GOOGCM", "Google: CM Site", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGDC", "Google: Incremental projects", "0", "10050"));
//		projectList.add(new ProjectBean("GOOGEAV", "Google: K2 MSA", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGENGAGE", "Google:Engage", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGGDNNL", "Google:GDN Nurture Lead", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGHTCTRANS", "Google:HTC - Translations", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLCS", "Google:Google + LCS", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEA", "Google-MPR", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEABC", "Google SOW24 - ABCDeployment", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEAG", "Google: Agency", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEENTAPPS", "Google:Enterprise Apps", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEGA", "Google:Google Analytics", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEGW", "Google:Google Wallet", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEH", "Google:GRM", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEN", "Google Incentives", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEO", "Google: HTCEmails", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEP", "Google Plus", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLESHOW", "Google: Showtime", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGLEWSHOP", "Google Shopping", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGNX", "Google: Newbie X", "0", "10000"));
//		projectList.add(new ProjectBean("GOOGRHINO", "Google:ShoppingExpress", "0", "10000"));
//		projectList.add(new ProjectBean("MRCB", "MRC SOW2", "10061", "10060"));
//		projectList.add(new ProjectBean("NEWOPS", "Incoming Opportunities", "0", "10000"));
//		projectList.add(new ProjectBean("NVPA", "NVP", "0", "10000"));
//		projectList.add(new ProjectBean("PROSIGHT", "ProSight SOW1", "0", "10000"));
//		projectList.add(new ProjectBean("PROSIGHTB", "ProSight SOW2 Maintenance", "0", "10000"));
//		projectList.add(new ProjectBean("PROSIGHTC", "ProSight SOW3", "0", "10000"));
//		projectList.add(new ProjectBean("REMITDATAWD", "RemitDATA: SOW2", "10061", "10060"));
//		projectList.add(new ProjectBean("RSACA", "RSAC 2013", "0", "10000"));
//		projectList.add(new ProjectBean("SOLJIRA", "SolutionSet - Jira Internal: New accounts, projects, support &Dev", "0", "10000"));
//		projectList.add(new ProjectBean("SOLSETCREAT", "SolutionSet - Creative", "0", "10000"));
		projectList.add(new ProjectBean("SSNB", "Silver Spring Networks SOW 2DRE 2011-12", "0", "10020"));
//		projectList.add(new ProjectBean("STELLRA", "Stellr Marketing and MobileDevelopment", "0", "10060"));
//		projectList.add(new ProjectBean("TEACHA", "Teachscape SOW 1", "0", "10060"));
//		projectList.add(new ProjectBean("TXUD", "TXU UI", "0", "10060"));
		projectList.add(new ProjectBean("VMWARE", "VMware", "0", "10020"));
//		projectList.add(new ProjectBean("VMWAREB", "VMware SOW8 - TheGame", "0", "10060"));
//		projectList.add(new ProjectBean("WFSWEEP", "Wells Fargo - Sweepstakes", "0", "10060"));
		
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
