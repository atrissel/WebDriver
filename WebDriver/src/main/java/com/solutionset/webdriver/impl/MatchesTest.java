package com.solutionset.webdriver.impl;

public class MatchesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String testString = "asdlkfjas;ldfkjasdl;fkjasd;fklj\nasdfadsfaThe project import completed successfullyasdfadsfa\nasdfasdf";
		
		if (testString.contains("The project import completed successfully")){
			
			System.out.println("score!");
			
		}
		
	}

}
