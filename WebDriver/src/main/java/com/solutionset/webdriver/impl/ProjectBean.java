package com.solutionset.webdriver.impl;

public class ProjectBean {
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectWorkFlow() {
		return projectWorkFlow;
	}
	public void setProjectWorkFlow(String projectWorkFlow) {
		this.projectWorkFlow = projectWorkFlow;
	}

	
	private String projectCode;
	private String projectName;
	private String projectWorkFlow;
	
	public ProjectBean(String projectCode, String projectName,
			String projectWorkFlow) {
		super();
		this.projectCode = projectCode;
		this.projectName = projectName;
		this.projectWorkFlow = projectWorkFlow;
	}

}
