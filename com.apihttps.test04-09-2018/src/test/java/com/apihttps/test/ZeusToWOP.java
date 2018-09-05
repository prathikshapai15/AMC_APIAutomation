package com.apihttps.test;

import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class ZeusToWOP  {

	public static Logger logger=LoggerFactory.getLogger(RLtoWOP.class);	

@Features("ZeusToWOP Test--")	
@Stories("ZeusToWOP Test--")	
@Test(priority=1)
 @Title("Execute RL API Test case for Getting Access Token")
  public void executeRLAPIforgettingAccessToken() throws ClientProtocolException, IOException {
	  System.out.println("===========================================");
	  APITestFunctions.testhttpclientforPost(1);
	  APITestFunctions.readJson(1,"access_token");
	  
	  logger.info("Execute RL API Test case for Getting Access Token");
	  
	 
  }
 
@Features("ZeusToWOP Test--")	
@Stories("ZeusToWOP Test--")
 @Test(priority=2)
 @Title("Execute RL API Test case using Access Token as a Authorization bearer token")
 public void executeRLGetApiwithAccessToken() throws ClientProtocolException, IOException {
	 System.out.println("======================================================");
	  APITestFunctions.getapiExecute("2");
	  
	  logger.info("Execute RL API Test case using Access Token as a Authorization bearer token");
	  
 }

@Features("ZeusToWOP Test--")	
@Stories("ZeusToWOP Test--")
 @Test(priority=3)
 @Title("Execute WOP API Test case using Access Token as a Authorization bearer token")
 public void executeWOPApiforAccessToken() throws ClientProtocolException, IOException {
	 System.out.println("======================================================");
	 APITestFunctions.testhttpclientforPost(3);
	  APITestFunctions.readJson(3,"access_token");
	  
	  logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
 }
 
@Features("ZeusToWOP Test--")	
@Stories("ZeusToWOP Test--")
 @Test(priority=4)
 @Title("Execute WOP API Test case using  Token as a Authorization bearer token")
 public void executeWOPApiforToken() throws ClientProtocolException, IOException {
	 System.out.println("======================================================");
	 APITestFunctions.testhttpclientforPost(4);
	  APITestFunctions.readJson(4,"token");
	  
	  logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
 }
 

@Features("ZeusToWOP Test--")	
@Stories("ZeusToWOP Test--")
 @Test(priority=5)
 @Title("Execute WOP API Test case using  Token as a Authorization bearer token")
 public void executeFinalWopApiwithAccessTokenandToken() throws ClientProtocolException, IOException {
	 System.out.println("WOP RESPONCESTART======================================================");
	 APITestFunctions.getapiExecute("8");
	 System.out.println("WOP RESPONCEEND======================================================");
	 logger.info("Execute WOP API Test case using Access Token as a Authorization bearer token");
	 
 }
 
 @Features("ZeusToWOP Test--")	
 @Stories("ZeusToWOP Test--")
 @Test(priority=6)
 public static  void ConnectDB() {
	 System.out.println("DB testing");
	 try {
		
		APITestFunctions.TestVerifyDB("SELECT ASPECT_RATIO,DEFINITION,SEGMENT_COUNT,V.TV_RATING,V.TX_COMMENTS FROM EXT_ZEUS_VERSIONS V, EXT_ZEUS_EPISODES E, EXT_ZEUS_TITLES T WHERE((UPPER(V.NAME) NOT LIKE '%NFA%' AND UPPER(V.NAME) NOT LIKE '%VOD%') OR V.NAME IS NULL) AND V.VERSION < '50' AND V.EPISODE_ID = E.EPISODE_ID AND E.SOURCE_REF IS NOT NULL AND E.TITLE_ID = T.TITLE_ID AND VERSION_ID='555153'");
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
 
 
 @Features("ZeusToWOP Test--")	
 @Stories("ZeusToWOP Test--")
 @Test(priority=7)
 @Title("Comparing Definition in Zeus and WOP")
 public static void Definition(){
	 System.out.println("==================Compare Definition in Zeus and WOP===========================");
	  APITestFunctions.clearlist();
	  
	    APITestFunctions.readjson("WOP", "VideoFormat");
		String zeus_definition = APITestFunctions.zeus_definition;
		
		System.out.println("Zeus Param values are-----:" + zeus_definition);
	
		if(zeus_definition.equalsIgnoreCase(APITestFunctions.paramText)) {
			
			System.out.println("Parameter values are matched for-----:" +zeus_definition);
		}
		 
		else {
			
			System.out.println("comparison failed");
		}
		
 }
 
 @Features("ZeusToWOP Test--")	
 @Stories("ZeusToWOP Test--")
 @Test(priority=8)
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
			
			System.out.println("Parameter values are matched for-----:" +zeus_aspect_Ratio);
		}
		 
		else {
			
			System.out.println("comparison failed");
		}
		
 }
 

 
 
 @Features("ZeusToWOP Test--")	
 @Stories("ZeusToWOP Test--")
 @Test(priority=7)
 @Title("Comparing SEGMENT_COUNT in Zeus and WOP")
 public static void SEGMENT_COUNT(){
	 System.out.println("==================Compare SEGMENT_COUNT in Zeus and WOP===========================");
	  APITestFunctions.clearlist();
	  
	    APITestFunctions.readjson("WOP", "SEGMENT_COUNT");
		String Zeus_segment_count = APITestFunctions.Zeus_segment_count;
		
		System.out.println("Zeus Param values are-----:" + Zeus_segment_count);
	
		if(Zeus_segment_count.equalsIgnoreCase(APITestFunctions.paramText)) {
			
			System.out.println("Parameter values are matched for-----:" +Zeus_segment_count);
		}
		 
		else {
			
			System.out.println("comparison failed");
		}
		
 }
 
 
 

 	
  @BeforeTest
  public void start() {
	  System.out.println("Test ZEUS to WOP Started=====================================");
  }
  @AfterTest
  public void End() {
	  System.out.println("Test ZEUS to WOP Ended=====================================");
  }
}
