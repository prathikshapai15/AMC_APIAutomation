package com.apihttps.test;

import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class RLtoWOP {

	public static Logger logger=LoggerFactory.getLogger(RLtoWOP.class);

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=1)
	@Title("Execute RL API Test case for Getting Access Token")
	public void executeRLAPIforgettingAccessToken() throws ClientProtocolException, IOException {
		System.out.println("===========================================");
		APITestFunctions.testhttpclientforPost(1);
		APITestFunctions.readJson(1,"access_token"); 
		logger.info("Execute RL API Test case for Getting Access Token");


	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=2)
	@Title("Execute RL API Test case using Access Token as a Authorization bearer token")
	public void executeRLGetApiwithAccessToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.getapiExecute("2");
		logger.info("Execute RL API Test case using Access Token as a Authorization bearer token");
	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=3)
	@Title("Execute WOP API Test case using Access Token as a Authorization bearer token")
	public void executeWOPApiforAccessToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.testhttpclientforPost(3);
		APITestFunctions.readJson(3,"access_token");

		logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=4)
	@Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	public void executeWOPApiforToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.testhttpclientforPost(4);
		APITestFunctions.readJson(4,"token");

		logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=5)
	@Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	public void executeWopApiwithAccessTokenandToken() throws ClientProtocolException, IOException {
		System.out.println("WOPRESPONCESTART======================================================");
		APITestFunctions.getapiExecute("5");
		System.out.println("WOPRESPONCEend======================================================");
		//APITestFunctions.readjson("WOP", "Title");
		// APITest.readJson(9,"token");
		// APITestFunctions.readjsonRLWOP(5,"RL");

		logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
	}


	@Test(priority=6,enabled=false)
	@Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	public void executeFinalWopApiwithAccessTokenandToken() throws ClientProtocolException, IOException {
		System.out.println("======================================================");
		APITestFunctions.getapiExecute("8");
	}

	// @Test(priority=6)
	// @Title("Execute WOP API Test case using  Token as a Authorization bearer token")
	// public void executeWOPLatestAPI() throws ClientProtocolException, IOException {
	//	 System.out.println("======================================================");
	////	 APITestFunctions.getapiExecute("6");
	////	 APITestFunctions.testhttpclientforPost(7);
	//	 APITestFunctions.testhttpclientforPost(8);
	//	 
	//	 //APITestFunctions.readjson("WOP", "Title");
	//	 // APITest.readJson(9,"token");
	//	// APITestFunctions.readjsonRLWOP(5,"RL");
	// }


	@Test(priority=14,enabled=false)
	@Title("Comparing Definition in Zeus and WOP")
	public static void duration(){
		System.out.println("==================Compare Definition in Zeus and WOP===========================");
		APITestFunctions.clearlist();

		APITestFunctions.readjson("WOP", "VideoFormat");
		String zeus_definition = APITestFunctions.zeus_definition;

		System.out.println("Zeus Param values are-----:" + zeus_definition);

		if(zeus_definition.equalsIgnoreCase(APITestFunctions.paramText)) {

			System.out.println("comparison passed");
		}

		else {

			System.out.println("comparison failed");
		}

	}

	@Test(priority=15,enabled=false)
	@Title("Comparing ASPECT_RATIO in Zeus and WOP")
	public static void aspectRatio(){
		System.out.println("==================Compare ASPECT_RATIO in Zeus and WOP===========================");
		APITestFunctions.clearlist();

		APITestFunctions.readjson("WOP", "ASPECT_RATIO");
		String zeus_aspect_Ratio = APITestFunctions.zeus_aspect_Ratio;

		System.out.println("Zeus Param values are-----:" + zeus_aspect_Ratio);

		if(zeus_aspect_Ratio.contains("16:9")) {
			zeus_aspect_Ratio="1.78";
		}else {

		}
		if(zeus_aspect_Ratio.equalsIgnoreCase(APITestFunctions.paramText)) {

			System.out.println("comparison passed");
		}

		else {

			System.out.println("comparison failed");
		}

	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=6)
	@Title("Compare Title Values in RL and WOP")
	public static void compareTitle() {
		System.out.println("==================Compare Title Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "Title");
		APITestFunctions.readjson("WOP", "Title");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}

	@Features("RLToWOP Test--")	
	@Stories("RLToWOP Test--")
	@Test(priority=7,enabled=false)
	@Title("Compare ShowType Values in RL and WOP")
	public static void compareShowType() {
		System.out.println("==================Compare ShowType Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "AssetType");
		APITestFunctions.readjson("WOP", "ShowType");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=8)
	@Title("Compare Supplier Values in RL and WOP")
	public static void compareSupplier() {
		System.out.println("==================Compare Supplier Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "Vendor");
		APITestFunctions.readjson("WOP", "Supplier");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=9)
	@Title("Compare AMCN ID Values in RL and WOP")
	public static void compareAmcnid() {
		System.out.println("==================Compare AMCN ID Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "ID");
		APITestFunctions.readjson("WOP", "AMCN ID");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=10)
	@Title("Compare AltTitle Values in RL and WOP")
	public static void compareAltTitle() {
		System.out.println("==================Compare Alternate title Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "Alt Title");
		APITestFunctions.readjson("WOP", "Alt Title");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=11)
	@Title("Compare Release Year Values in RL and WOP")
	public static void compareReleaseyear() {
		System.out.println("==================Compare Alternate title Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "Release Year");
		APITestFunctions.readjson("WOP", "Release Year");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=12)
	@Title("Compare Alternative Title Type Values in RL and WOP")
	public static void compareAltTitleType() {
		System.out.println("==================Compare Alternate title Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "Alt Title Type");
		APITestFunctions.readjson("WOP", "Alt Title Type");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}
	@Test(priority=13)
	@Title("Compare First Name Values in RL and WOP")
	public static void compareFirstName() {
		System.out.println("==================Compare Alternate title Values in RL and WOP===========================");
		APITestFunctions.clearlist();
		APITestFunctions.readjson("RL", "First Name");
		APITestFunctions.readjson("WOP", "First Name");
		APITestFunctions.compareValues("RLtoWOPMappingRule");
	}

	@Test(priority=13,enabled=false)
	public static  void ConnectDB() {
		try {


			APITestFunctions.TestVerifyDB("");
			APITestFunctions.activeMQ();

			//APITestFunctions.launchBrowser();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}


	@BeforeTest
	public void start() {
		System.out.println("Test RL to WOP Started=====================================");
	}
	@AfterTest
	public void End() {
		System.out.println("Test RL to WOP Ended=====================================");
	}
}
