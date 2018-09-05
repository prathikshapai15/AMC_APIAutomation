package com.apihttps.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Title;

public class MPtoWOP {

	public static Logger logger=LoggerFactory.getLogger(RLtoWOP.class);

	@Test(priority=1)
	@Title("Execute RL API Test case for Getting Access Token")

	public void executeRLAPIforgettingAccessToken() throws ClientProtocolException, IOException {
		System.out.println("===========================================");
		APITestFunctions.testhttpclientforPost(1);
		APITestFunctions.readJson(1,"access_token");

		logger.info("Execute RL API Test case for Getting Access Token");

	}

	@Test(priority=2)
	@Title("Execute RL API Test case using Access Token as a Authorization bearer token")
	public void executeRLGetApiwithAccessToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.getapiExecute("2");

		logger.info("Execute RL API Test case using Access Token as a Authorization bearer token");

	}

	@Test(priority=3)
	@Title("Execute WOP API Test case using Access Token as a Authorization bearer token")
	public void executeWOPApiforAccessToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.testhttpclientforPost(3);
		APITestFunctions.readJson(3,"access_token");
		logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");

	}
	@Test(priority=4)
	@Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	public void executeWOPApiforToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.testhttpclientforPost(4);
		APITestFunctions.readJson(4,"token");

		logger.info("Execute WOP API Test case using  Token as a Authorization bearer token");

	}

	@Test(priority=5)
	@Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	public void executeFinalWopApiwithAccessTokenandToken() throws ClientProtocolException, IOException {
		System.out.println("WOPRESPONCESTART======================================================");
		APITestFunctions.getapiExecute("5");
		System.out.println("WOPRESPONCEend======================================================");
		logger.info("Execute WOP API Test case using  Token as a Authorization bearer token");
	}



	@Test(priority=6)
	@Title("Execute MP API Test case using  Token as a Authorization bearer token")
	public void executeMPApiwithAccessTokenandToken() throws ClientProtocolException, IOException {
		System.out.println("MPRESPONCESTART======================================================");
		APITestFunctions.testhttpclientforPost(9); 
		System.out.println("MPRESPONCEEND======================================================");
		logger.info("Execute MP API Test case using  Token as a Authorization bearer token");


	}


	@Test(priority=7)
	@Title("Compare Title Values in MP and WOP")
	public static void compareTitle() {
		System.out.println("==================Compare Title Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "Title");
		APITestFunctions.readjson("WOP", "Title");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare Title Values in MP and WOP");
	}

	@Test(priority=8)
	@Title("Compare AMCN ID Values in MP and WOP")
	public static void compareAMCNID() {
		System.out.println("==================Compare AMCN ID Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "AMCN ID");
		APITestFunctions.readjson("WOP", "AMCN ID");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare AMCN ID Values in MP and WOP");
	}

	
	@Test(priority=9)
	@Title("Compare showtype Values in MP and WOP")
	public static void compareshowtype() {
		System.out.println("==================Compare ShowType Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "ShowType");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("WOP", "ShowType");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare ShowType Values in MP and WOP");
	}
	
	@Test(priority=10)
	@Title("Compare Release Year Values in MP and WOP")
	public static void compareReleaseYear() {
		System.out.println("==================Compare Release Year Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "Release Year");
		APITestFunctions.readjson("WOP", "Release Year");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare Release Year Values in MP and WOP");
	}
	
	@Test(priority=11)
	@Title("Compare Asset Source Values in MP and WOP")
	public static void CompareAssetSource() {
		System.out.println("==================Compare AssetSource Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "Asset Source");
		APITestFunctions.readjson("WOP", "Asset Source");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare AssetSource Values in MP and WOP");
	}
	
	@Test(priority=11)
	@Title("Compare Owner Network Values in MP and WOP")
	public static void OwnerNetwork() {
		System.out.println("==================Compare Owner Network Values in MP and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("MP", "Owner Network");
		APITestFunctions.readjson("WOP", "Owner Network");
		APITestFunctions.compareValues("MappingRuleWOPtoMP");
		logger.info("Compare Owner Network Values in MP and WOP");
	}
	
	
	
	
	@BeforeTest
	public void start() {
		System.out.println("Test MP to WOP Started=====================================");
	}
	@AfterTest
	public void End() {
		System.out.println("Test MP to WOP Ended=====================================");
	}




}
