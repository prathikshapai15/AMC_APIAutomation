package com.apihttps.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.testng.Assert;
import javax.jms.ConnectionFactory;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class APITestFunctions extends Utilities {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	public static String[][] excelData = null;
	static int colCount=0;
	static int rowCount=0;
	static StringBuffer textView = new StringBuffer();
	static StringBuffer textView_1 = new StringBuffer();
	static StringBuffer textView_2 = new StringBuffer();
	public static String jsonPrettyPrintString = null;
	public static int source;
	public static String text2=null;
	public static String text1=null;
	public static String text=null;
	public static int lastRow ;
	static String[][] excelData2;
	public static String path = System.getProperty("user.dir");
	public static String accessToken="no value";
	public static String token="no value";
	public static JSONObject jsonObj = new JSONObject();
	public static JSONObject jsonObj_1 = new JSONObject();
	public static JSONObject jsonObj_2 = new JSONObject();
	public static JSONObject jsonObj_normal = new JSONObject();
	public static JSONObject jsonObj_matser = new JSONObject();
	public static JSONObject jsonObj_Compare = new JSONObject();
	public static String apirequest="no request";
	public static ArrayList<String> obj1_tagname = new ArrayList<String>();
	public static ArrayList<String> obj2_tagname = new ArrayList<String>();
	public static ArrayList<String> rlParamvalues = new ArrayList<String>();
	public static ArrayList<String> wopParamvalues = new ArrayList<String>();
	public static ArrayList<String> mpParamvalues = new ArrayList<String>();
	public static ArrayList<NameValuePair> postParameters;
	public static List <NameValuePair> nameValuePairs;
	public static String[] apirequests;
	public static String jSonTags;
	public static String jSonObj;
	public static int testline;
	public static String paramText;
	public static String paramName;
	public static String pName;
    public static String query;
    public static String zeus_aspect_Ratio;
    public static String zeus_definition;
    public static String Zeus_segment_count;
    public static String Zeus_TV_Rating;
   
	//Execute Get Request
	public static void getapiExecute(String lineNumbers) throws ClientProtocolException, IOException {
		textView.delete(0, textView.length());
		String[] cTypes;
		String[] hTypes;
		logStep("Started executing API Testing");
		String[] lines;
		if(lineNumbers.contains(",")) {
			lines=lineNumbers.split(",");
		}else {
			lines=lineNumbers.split(" ");
		}

		for(int lin=1;lin<=lines.length;lin++) {
			int lineNumber =Integer.parseInt(lines[lin-1]);
			testline=lineNumber;
			readingExcel("TestInputs");
			source=Integer.parseInt(excelData2[lineNumber][7].toString());
			logStep("Verify Api Type is Get  or Post");
			if(excelData2[lineNumber][1].toString().equalsIgnoreCase("GET")) {
				logStep("Given url is Get API continue execution");
				HttpClient client = HttpClients.createDefault();
				logStep("Given API is : "+excelData2[lineNumber][0]);
				HttpGet request = new HttpGet(excelData2[lineNumber][0]);
				String cType = excelData2[lineNumber][2].toString();
				String hType = excelData2[lineNumber][3].toString();

				if(cType.contains(",")) {
					cTypes = cType.split(",");
					hTypes=hType.split(",");
				}else {
					cTypes = cType.split(" ");
					hTypes=hType.split(" ");
				}
				for(int i=0;i<=cTypes.length-1;i++) {
					if(cTypes[i].trim().contains("Authorization")) {
						logStep("Using Access tokena as Bearer : "+accessToken);
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]+" "+accessToken);
						request.addHeader(cTypes[i],hTypes[i]+" "+accessToken);
					}else if(cTypes[i].trim().contains("auth2")) {
						logStep("Using Access tokena as Bearer : "+token);
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]+" "+token);
						request.addHeader(cTypes[i],hTypes[i]+" "+token);
					}else {
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]);
						request.addHeader(cTypes[i],hTypes[i]);
					}
					System.out.println("Headers are: : "+cTypes[i]+"------"+hTypes[i]);
				}

				HttpResponse response = client.execute(request);
				logStep("Execution Completed and reading response now");
				// Get the response
				BufferedReader rd = new BufferedReader
						(new InputStreamReader(
								response.getEntity().getContent()));
				if(lineNumber>1) {
					textView_2.delete(0, textView_2.length());

				}
				logStep("Verify Status code in response");
				if(response.getStatusLine().getStatusCode()!=200) {

					RuntimeException error =	new RuntimeException("Failed : HTTP error code : "
							+ response.getStatusLine().getStatusCode());
					System.out.println(error);
					logStep(error.toString());
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatusLine().getStatusCode());
				}else {

					String line = "";
					textView_2.delete(0, textView_2.length());
					while ((line = rd.readLine()) != null) {

						if(source==1) {
							textView_1.append(line);}

						else if(source>1) {

							textView_2.append(line);
						}else {
							textView.append(line);
						}
					}

					if(source==1) {
						if(textView_1.toString().contains("xml")) {
							convertxmltoJson(textView_1.toString());
						}else if(!textView_1.toString().contains("xml")) {
							jsonObj_1 = new JSONObject();
							text1=textView_1.toString();
							if(text1.startsWith("[")) {
								textView_1.deleteCharAt(0);
								textView_1.deleteCharAt(textView_1.length()-1);
								text1=textView_1.toString();
							}
							System.out.println("response  is : ========="+textView_1.toString());
						}
						jsonObj_1 = new JSONObject(textView_1.toString());
						jsonObj_matser=jsonObj_1;
						System.out.println(jsonObj_1.toString());
					}else if(source>1) {
						if(textView_2.toString().contains("xml")) {
							//convertxmltoJson(textView_2.toString());
							text2=convertxmltoJson(textView_2.toString());
							System.out.println("XML response  is : ========="+textView_2.toString());
						}else if(!textView_2.toString().contains("xml")) {
							text2=textView_2.toString();
							if(text2.startsWith("[")) {
								textView_2.deleteCharAt(0);
								textView_2.deleteCharAt(textView_2.length()-1);
								text2=textView_2.toString();
							}
							System.out.println("response  is : ========="+text2);
						}
						jsonObj_2 = new JSONObject(text2);
						jsonObj_Compare=jsonObj_2;
						System.out.println(jsonObj_2.toString());
					}else {
						System.out.println(textView.toString());
					}

					//System.out.println(textView.toString());
					logStep("API Esecuted Successfully");
					System.out.println("Api Executed successfully");
					logStep(jsonObj_2.toString());

				}
			}
		}
	}
	public static void launchBrowser() throws Exception {
		readingExcel("ZeusXML");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://admin:admin@dev-adam02.amcnetworks.com:8161/admin/queues.jsp");
		driver.findElement(By.name("QueueFilter")).sendKeys("AMCN.ESB.ALL.ALL.ALL.XML.QUEUE");
		driver.findElement(By.xpath("//input[@value='Filter']")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Send To")).click();
		System.out.println("Zeus XML is :--- "+excelData2[0][0].toString());
		WebElement body = driver.findElement(By.name("JMSText"));
		body.clear();
		body.sendKeys(excelData2[0][0].toString());
		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

	public static void clearTokens(){
		textView.delete(0, textView.length());
		textView_1.delete(0, textView_1.length());
		textView_2.delete(0, textView_2.length());
	}
	
	
	public static void activeMQ() {
		readingExcel("ZeusXML");
		String subject = "AMCN.ESB.ALL.ALL.ALL.XML.QUEUE";
		try {
		    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://dev-adam02.amcnetworks.com:61616/");

		    ActiveMQConnection connection = (ActiveMQConnection) connectionFactory.createConnection();
		    DestinationSource ds = connection.getDestinationSource();

		    connection.start();
		    Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE); 
		    Destination destination = session.createQueue(subject);
			// MessageProducer is used for sending messages to the queue.
			MessageProducer producer = session.createProducer(destination);
		    //list of Queues
			Set<ActiveMQQueue> queues = ds.getQueues();

		    for (ActiveMQQueue activeMQQueue : queues) {
		        try {
		            System.out.println(activeMQQueue.getQueueName());
		        } catch (JMSException e) {
		            e.printStackTrace();
		        }
		    }
		 
		    // We will send a small text message saying 'Hello World!!!'
			TextMessage message = session
					.createTextMessage(excelData2[0][0].toString());
			// Here we are sending our message!
			producer.send(message);

			System.out.println("Text updated in :--"+subject+" queue");
			System.out.println("JCG printing@@ '" + message.getText() + "'");
			connection.close();
		   
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}


	//Connecting DB
	public static void TestVerifyDB(String query) throws SQLException, ClassNotFoundException, Exception
	{
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//"jdbc:oracle:thin:@localhost:1521:xe","system","oracle"); 
			/*------for local connection*/
			//String databaseURL = "jdbc:oracle:thin:@localhost:1521:xe";
//			String user = "System";
//			String password = "Oracle_1";
			String databaseURL = "jdbc:oracle:thin:@ORA02-AWSNP.AMCNETWORKS.COM:1528/ADAMSIT.amcnetworks.com";
			String user = "ZEUS";
			String password = "ZEUS";
			// Class.forName("oracle.jdbc.driver.OracleDriver");  

			System.out.println("Driver Loaded");
			con= DriverManager.getConnection(databaseURL, user, password);

			if (con != null) {
				System.out.println("Connected to the Database...");
			}
			Statement stmt=con.createStatement();  
			System.out.println("Connection successfull"+stmt.toString());  
		}

		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e1)
		{
			e1.getMessage();
		}
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		//step4 execute query  
		ResultSet rs=stmt.executeQuery(query);
		//stmt.executeQuery(query2);
		
		while(rs.next())  {
		ArrayList<String> ar = new ArrayList<String>();
		
		ar.add(rs.getString(1));
		ar.add(rs.getString(2));
		ar.add(rs.getString(3));
		ar.add(rs.getString(4));
		
		ar.add(rs.toString());
		
		System.out.println(ar.get(0));
		System.out.println(ar.get(1));
		System.out.println(ar.get(2));
		
		zeus_aspect_Ratio= ar.get(0);
		zeus_definition=ar.get(1);
	    Zeus_segment_count=ar.get(2);
	    Zeus_TV_Rating=ar.get(3);
		
		}
		  
		con.close();
		
		

	}
	//Convert xml response to JSON
	public static String convertxmltoJson(String xmlString) {
		
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
			jsonPrettyPrintString= xmlJSONObj.toString();
			System.out.println(jsonPrettyPrintString);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		return jsonPrettyPrintString;
	}
	//Compare Json Reult
	public static void comparejsonResult() {
		obj2_tagname.clear();
		obj1_tagname.clear();
		String Objecttype;
		JSONObject obj1 =jsonObj_1; 
		JSONObject obj2 =jsonObj_2;
		//Base api response
		String[] obj1_tagNames=null;
		JSONObject obj1_maintag = (JSONObject) obj1.getJSONObject("RestResponse");
		Object obj1_result = obj1_maintag.get("result");
		String obj1_array = obj1_result.toString();
		JSONObject Obj1_resultTag = null;
		JSONArray Obj1_resultArray = null;

		if (obj1_result instanceof JSONObject) {
			Obj1_resultTag = (JSONObject) obj1_maintag.get("result");
			Objecttype="JsonObject";
		}
		else {
			Obj1_resultArray = (JSONArray) obj1_maintag.get("result");
			System.out.println("Size of the array :"+Obj1_resultArray.length());
			Objecttype="JsonArray";
		}
		if(Objecttype.equalsIgnoreCase("JsonObject")) {
			String tagName = Obj1_resultTag.getString("name");
			obj1_tagname.add(tagName);
		}else {
			for(int i=0;i<=Obj1_resultArray.length()-1;i++) {
				JSONObject obj1_tagName = (JSONObject) Obj1_resultArray.get(i);
				obj1_tagname.add(obj1_tagName.getString("name"));
			}
		}

		//Comparing API Responses
		JSONObject obj2_maintag = (JSONObject) obj2.getJSONObject("RestResponse");
		Object obj2_reult = obj2_maintag.get("result");
		JSONObject Obj2_resultTag = null;
		JSONArray Obj2_resultArray = null;
		if (obj2_reult instanceof JSONObject) {
			Obj2_resultTag = (JSONObject) obj2_reult;
			System.out.println("Obj2_resultTag : "+Obj2_resultTag.toString());
			Objecttype="JsonObject";
		}
		else  {
			Obj2_resultArray = (JSONArray) obj2_maintag.get("result");
			System.out.println("Size of the array :"+Obj2_resultArray.length());
			Objecttype="JsonArray";
		}


		if(Objecttype.equalsIgnoreCase("JsonObject")) {
			String tagName = Obj2_resultTag.getString("name");
			obj2_tagname.add(tagName);
		}else {
			for(int i=0;i<=Obj2_resultArray.length()-1;i++) {
				JSONObject obj2_tagName = (JSONObject) Obj2_resultArray.get(i);
				obj2_tagname.add(obj2_tagName.getString("name"));
			}
		}
		if(obj1_tagname.contains(obj2_tagname)) {
			System.out.println("values are matched with : "+obj2_tagname);
		}else {
			System.out.println("values are matched with : "+obj2_tagname);
		}

	}

	//get response of POST API 
	@SuppressWarnings("unlikely-arg-type")
	public static void testhttpclientforPost(int lineNumber) {
		clearTokens();
		readingExcel("TestInputs");
		source=Integer.parseInt(excelData2[lineNumber][7].toString());
		logStep("Get the details from Excel");
		addparamvaluesinRequest(lineNumber,"null");
		// HttpClient client = new DefaultHttpClient();
		logStep("Http Client service is invoked");
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(excelData2[lineNumber][0].toString());
		String[] cTypes;
		String[] hTypes;
		try {
			if(apirequest.equals("no request")) {
				apirequest=excelData2[lineNumber][4].toString();
				logStep("apirequest is  : --"+apirequest);
			}else if(apirequest.contains("&")) {
				logStep("apirequest is  : --"+apirequest);
				nameValuePairs = new ArrayList <NameValuePair> ();
				postParameters= new ArrayList<NameValuePair>();
				String[]params;
				apirequests=apirequest.split("&");
				for(String param:apirequests) {
					if(param.contains("=")) {
						params=param.split("=");

						nameValuePairs.add(new BasicNameValuePair(params[0], params[1]));
						postParameters.add(new BasicNameValuePair(params[0], params[1]));

					}
				}
				logStep("Executing request with http Post method");
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			}else {
				logStep("apirequest is  : --"+apirequest);
				StringEntity entity = new StringEntity(apirequest);
				post.setEntity(entity);
				//input.setContentType(excelData2[lineNumber][2]);
				String cType = excelData2[lineNumber][2].toString();
				String hType = excelData2[lineNumber][3].toString();

				if(cType.contains(",")) {
					cTypes = cType.split(",");
					hTypes=hType.split(",");
				}else {
					cTypes = cType.split(" ");
					hTypes=hType.split(" ");
				}
				for(int i=0;i<=cTypes.length-1;i++) {
					logStep("Using Access tokena as Bearer : "+token);
					if(cTypes[i].trim().contains("Authorization")) {
						logStep("Using Access tokena as Bearer : "+accessToken);
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]+" "+accessToken);
						post.addHeader(cTypes[i],hTypes[i]+" "+accessToken);
					}else if(cTypes[i].trim().contains("auth2")) {
						logStep("Using Access tokena as Bearer : "+token);
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]+" "+token);
						post.addHeader(cTypes[i],hTypes[i]+" "+token);
					}else {
						logStep("Headers are: : "+cTypes[i]+"------"+hTypes[i]);
						post.addHeader(cTypes[i],hTypes[i]);
					}
					//System.out.println(cTypes[i]+"===="+hTypes[i]);
				}
				logStep("Executing post API with Http Post method");
			}
			HttpResponse response = client.execute(post); 
			logStep("Response is : "+response);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			if(response.getStatusLine().getStatusCode()!=200) {
				RuntimeException error =	new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
				System.out.println(error);
				logStep(error.toString());
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}else {
				logStep("API Executed Successully : ");
				String line = "";
				while ((line = rd.readLine()) != null) {
					if(source==1) {
					textView_1.append(line);
					}else if(source==2) {
						textView_2.append(line);
					}else if(source==0) {
						textView.append(line);
					}
				}
				//System.out.println("Responce is : -------:\n"+textView);
				if(source==1) {
					if(textView_1.toString().contains("xml")) {
						text1=convertxmltoJson(textView_1.toString());
					}else if(!textView_1.toString().contains("xml")) {
						jsonObj_1 = new JSONObject();
						text1=textView_1.toString();
						if(text1.startsWith("[")) {
							textView_1.deleteCharAt(0);
							textView_1.deleteCharAt(textView_1.length()-1);
							text1=textView_1.toString();
						}
						System.out.println("response  is : ========="+textView_1.toString());
					}
					jsonObj_1 = new JSONObject(text1);
					jsonObj_matser=jsonObj_1;
					System.out.println(jsonObj_1.toString());
				}else if(source>1) {
					if(textView_2.toString().contains("xml")) {
						//convertxmltoJson(textView_2.toString());
						text2=convertxmltoJson(textView_2.toString());
						System.out.println("XML response  is : ========="+textView_2.toString());
					}else if(!textView_2.toString().contains("xml")) {
						text2=textView_2.toString();
						if(text2.startsWith("[")) {
							textView_2.deleteCharAt(0);
							textView_2.deleteCharAt(textView_2.length()-1);
							text2=textView_2.toString();
						}
						System.out.println("response  is : ========="+text2);
					}
					jsonObj_2 = new JSONObject(text2);
					jsonObj_Compare=jsonObj_2;
					System.out.println(jsonObj_2.toString());
				}else if(textView.toString().contains("xml")) {
					convertxmltoJson(textView.toString());
					jsonObj= new JSONObject(textView.toString());
					//jsonObj_matser=jsonObj;
					System.out.println(jsonObj.toString());
				}else if(!textView.toString().contains("xml")) {
					jsonObj = new JSONObject();
					text=textView.toString();
					if(text.startsWith("[")) {
						textView.deleteCharAt(0);
						textView.deleteCharAt(textView.length()-1);
						text=textView.toString();
					}
					System.out.println("response  is : ========="+textView.toString());
					jsonObj= new JSONObject(textView.toString());
					//jsonObj_matser=jsonObj;
					System.out.println(jsonObj.toString());
				}
				

				//System.out.println(textView.toString());
				logStep("API Esecuted Successfully");
				System.out.println("Api Executed successfully");
				logStep(jsonObj_2.toString());
				
				
				
//				if(textView.toString().contains("xml")) {
//					System.out.println("Responce is : -------:\n"+textView);
//					APITestFunctions.convertxmltoJson(textView.toString());
//				}else {
//					jsonObj = new JSONObject(textView.toString());
//					System.out.println("Responce in JSON is : -------:\n"+jsonObj);
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//add param values in request of next api 
	public static void addparamvaluesinRequest(int lineNumber,String jsonValue)  {
		readingExcel("TestInputs");
		apirequest=excelData2[lineNumber][4].toString();
		logStep("Check request details from Excel");
		try {
			JSONParser parser = new JSONParser();
			//Object obj = parser.parse(new String(request));
			JSONObject jObject  = new JSONObject(apirequest);
			logStep("Request in Json formate");
			JSONObject maintag = (JSONObject) jObject.getJSONObject("request");
			JSONObject metadata = null ;
			String [] metaValues;
			if(jsonValue.contains(",")) {
				metaValues = jsonValue.split(",");
			}else {
				metaValues = jsonValue.split(" ");
			}
			logStep("Try ing to add values to request");
			for(int i=0;i<=metaValues.length-1;i++) {	
				if(accessToken.equals("no value")||token.equals("no value")) {

				}else {
					if(jsonValue.contains(metaValues[i])) {
						metadata=maintag.getJSONObject("metadata").put(metaValues[i],accessToken);
					}else if(jsonValue.contains("token")) {
						metadata=maintag.getJSONObject("metadata").put(metaValues[i],token);
					}
				}
			}

			apirequest=jObject.toString();
			logStep("Edited request is : ----"+apirequest);
		}catch(Exception e) {
			logStep("request is not in JSON formate : --"+apirequest);
			//apirequests=apirequest.split("&");
		}
		System.out.println("metadata -- :"+apirequest);

	}

	//Reading Json response
	public static void readJson(int lineNumber, String getValue) {
		jSonTags=excelData2[lineNumber][5].toString();
		jSonObj=excelData2[lineNumber][6].toString();
		//		 JSONParser parser = new JSONParser();
		if(jSonTags.contains(",")) {
			JSONObject maintag = (JSONObject) jsonObj.getJSONObject("result");
			JSONObject metadata = maintag.getJSONObject("metadata");
			//metadata.get(key)
			if(getValue.equalsIgnoreCase("access_token")) {
				accessToken= (String) metadata.get("access_token");
				logStep(getValue+" value is :"+accessToken);
				System.out.println(getValue+" value is :"+accessToken);
			}else if(getValue.equalsIgnoreCase("token")) {
				token= (String) metadata.get("token");
				logStep(getValue+" value is :"+token);
				System.out.println(getValue+" value is :"+token);
			}

		}else {
			if(getValue.equalsIgnoreCase("access_token")) {
				accessToken= (String) jsonObj.get(getValue);
				logStep(getValue+" value is :"+accessToken);
				System.out.println(getValue+" value is :"+accessToken);
			}else if(getValue.equalsIgnoreCase("token")) {
				token= (String) jsonObj.get(getValue);
				logStep(getValue+" value is :"+token);
				System.out.println(getValue+" value is :"+token);
			}
			//token= (String) jsonObj.get(getValue);
		}
	}

	//Reading Excel File and get the data
	public static String[][] readingexcelFiles(String sheetname) throws Exception {

		try {
			String FilePath =path+ "/ExcelFile/API_inputs.xlsx";
			FileInputStream finputStream = new FileInputStream(new File(FilePath));
			workbook = new XSSFWorkbook(finputStream);
			sheet = workbook.getSheet(sheetname);
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			//System.out.println("Columns"+ colCount);
			rowCount = sheet.getPhysicalNumberOfRows();
			//System.out.println("Rows"+ rowCount);
			lastRow=sheet.getLastRowNum();
			excelData = new String[rowCount][colCount];
			for(int Nrow = 0; Nrow<rowCount; Nrow++) {
				row = sheet.getRow(Nrow);
				for(int Ncolumn =0; Ncolumn<colCount ; Ncolumn++) {
					cell = sheet.getRow(Nrow).getCell(Ncolumn);
					DataFormatter df = new DataFormatter();
					excelData[Nrow][Ncolumn] = df.formatCellValue(cell);
				}
			}
		}catch(Exception e) {}

		return excelData;
	}

	//read excel sheet based on sheetName
	public static void readingExcel(String sheetName) {
		logStep("Started reading data from Excel");
		try {
			excelData2 = readingexcelFiles(sheetName);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Reading RL and WOP Json response 
	public static void readjsonRLWOP(int lineNumber,String Apitype) {
		jSonTags=excelData2[lineNumber][5].toString();
		jSonObj=excelData2[lineNumber][6].toString();
		//		 JSONParser parser = new JSONParser();
		if(!jSonTags.contains(",")) {
			if(Apitype.equalsIgnoreCase("RL"))
			{
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> keyItr = jsonObj_1.keys();
				while(keyItr.hasNext()) {
					String key = keyItr.next();
					Object value = jsonObj_1.get("SeriesName");
					System.out.println("Value is : ---"+value.toString());
				}
				String val1=(String) jsonObj_1.get("SeriesName");
				System.out.println("Value is : ---"+val1.toString());
				JSONArray xyz=jsonObj_1.getJSONArray("genre");
				for(int i=0;i<=xyz.length()-1;i++)
				{
					JSONObject obj =(JSONObject) xyz.get(i);
					String x=obj.get("Genre").toString();
					System.out.println("x value is : "+x.toString());
				}
			}else if(Apitype.equalsIgnoreCase("WOP"))
			{
				System.out.println("Yet to be complete");
			}
			JSONObject maintag = (JSONObject) jsonObj.getJSONObject("result");
			JSONObject metadata = maintag.getJSONObject("metadata");
			//metadata.get(key)
			//			if(getValue.equalsIgnoreCase("access_token")) {
			//				accessToken= (String) metadata.get("access_token");
			//				logStep(getValue+" value is :"+accessToken);
			//				System.out.println(getValue+" value is :"+accessToken);
			//			}else if(getValue.equalsIgnoreCase("token")) {
			//				token= (String) metadata.get("token");
			//				logStep(getValue+" value is :"+token);
			//				System.out.println(getValue+" value is :"+token);
			//			}
		}else {
			//			if(getValue.equalsIgnoreCase("access_token")) {
			//				accessToken= (String) jsonObj.get(getValue);
			//				logStep(getValue+" value is :"+accessToken);
			//				System.out.println(getValue+" value is :"+accessToken);
			//			}else if(getValue.equalsIgnoreCase("token")) {
			//				token= (String) jsonObj.get(getValue);
			//				logStep(getValue+" value is :"+token);
			//				System.out.println(getValue+" value is :"+token);
			//			}
			//token= (String) jsonObj.get(getValue);
		}
	}

	//Reading values from Json response
	public static String readjson(String JsonType, String Param) {
		logStep("Started reading Responses from "+JsonType+" API ");
		String Objecttype=null;
		readingExcel(JsonType);
		String paramvalue;
		String jsontypes = null;
		String[] jTypes;
		JSONObject rlObject = null;
		JSONObject wopObject = null;
		JSONObject mpObject = null;
		if(JsonType.equalsIgnoreCase("rl")) {
			rlObject=jsonObj_matser;
		}else if(JsonType.equalsIgnoreCase("mp")) {
			mpObject=jsonObj_matser;
		}else if(JsonType.equalsIgnoreCase("wop")){
			wopObject=jsonObj_Compare;
		}
		if(JsonType.equalsIgnoreCase("rl")) {
			jsonObj_1=rlObject;
		}else if(JsonType.equalsIgnoreCase("mp")) {
			jsonObj_1=mpObject;
		}else{
			jsonObj_1=wopObject;
		}
		for(int excellist=0;excellist<=lastRow;excellist++) {

			paramName=excelData2[excellist][3].toString();
			paramvalue= excelData2[excellist][0].toString();
			if(Param.equalsIgnoreCase(paramName)) {
				logStep("Validating "+Param+" param name in "+JsonType+" API");
				pName=paramName;
				jsontypes=excelData2[excellist][2].toString();
				if(jsontypes.contains(",")) {
					jTypes=jsontypes.split(",");
				}else {
					jTypes=jsontypes.split("  ");
				}
				for(int i=0;i<=jTypes.length-1;i++) {
					if(jTypes[i].toString().contains("direct")) {
						paramText=jsonObj_1.get(paramvalue).toString();
						//System.out.println("Param Text is : "+paramText);
						break;
					}else {
						//System.out.println("json object  : "+jTypes[i].toString());
						Object obj_reult = jsonObj_1.get(jTypes[i].toString());
						JSONObject Obj_resultTag = null;
						JSONArray Obj_resultArray = null;
						if (obj_reult instanceof JSONObject) {
							jsonObj_1 = (JSONObject) obj_reult;
							//System.out.println("Obj_resultTag : "+jsonObj_1.toString());
							Objecttype="JsonObject";
						}
						else  {
							Obj_resultArray = (JSONArray) jsonObj_1.get(jTypes[i].toString());
							System.out.println("Size of the array :"+Obj_resultArray.length());
							Objecttype="JsonArray";
							i=i+1;
							jsonObj_1 = (JSONObject) Obj_resultArray.get(Integer.parseInt(jTypes[i].toString()));

						}
					}
				}
				paramText=jsonObj_1.get(paramvalue).toString();
				if(JsonType.equalsIgnoreCase("RL")) {
					rlParamvalues.add(paramText);
					System.out.println("RL Param values are ------ : "+rlParamvalues);
					logStep("RL Param values are ------ : "+rlParamvalues);
				}else if(JsonType.equalsIgnoreCase("WOP")) {
					wopParamvalues.add(paramText);
					System.out.println("WOP Param values are ---- : "+wopParamvalues);
					logStep("WOP Param values are ---- : "+wopParamvalues);
				}else if(JsonType.equalsIgnoreCase("MP")) {
					mpParamvalues.add(paramText);
					System.out.println("MP Param values are ---- : "+mpParamvalues);
					logStep("MP Param values are ---- : "+mpParamvalues);
				}

				//				if(Objecttype.equalsIgnoreCase("JsonObject")) {
				//					String tagName = Obj2_resultTag.getString("name");
				//					obj2_tagname.add(tagName);
				//				}else {
				//					for(int i=0;i<=Obj2_resultArray.length()-1;i++) {
				//						JSONObject obj2_tagName = (JSONObject) Obj2_resultArray.get(i);
				//						obj2_tagname.add(obj2_tagName.getString("name"));
				//					}
				//				}

			}
		}
		return jsontypes;

	}

	//clear the list
	public static void clearlist() {
		rlParamvalues.clear();
		wopParamvalues.clear();
	}

	//Compare values from API values
	public static void compareValues(String sheetname) {
		String rlValue;
		String wopValue;
		logStep("Comparing values for APIs Params");
		readingExcel(sheetname);
		String[]rlMpas = null;
		String[] wopMaps;

		for(int maplist=0;maplist<=lastRow;maplist++) {
			//System.out.println(excelData2[maplist][2].toString());
			if(pName.equalsIgnoreCase(excelData2[maplist][2].toString())) {
				logStep("Comparing "+pName +"from APIs");
				String rlMapvalue =excelData2[maplist][1].toString();
				String wopMapvalue =excelData2[maplist][3].toString();
				if(rlMapvalue.contains(",")) {
					rlMpas=rlMapvalue.split(",");
				}else {
					rlMpas=rlMapvalue.split(" ");
				}
				if(wopMapvalue.contains(",")) {
					wopMaps=wopMapvalue.split(",");
				}else {
					wopMaps=wopMapvalue.split(" ");
				}
				if(rlMapvalue.equalsIgnoreCase(wopMapvalue)) {
					for(int rlparam=0;rlparam<=rlParamvalues.size()-1;rlparam++) {
						for(int wopparam=0;wopparam<=wopParamvalues.size()-1;wopparam++) {

							if(wopParamvalues.get(wopparam).contains(rlParamvalues.get(rlparam))) {
								System.out.println("Param values are matched for ------------------------"+pName);
								logStep("Param values are matched for -------"+pName);
								System.out.println(wopParamvalues.get(wopparam)+"--------------"+rlParamvalues.get(rlparam));
								logStep(wopParamvalues.get(wopparam)+"-------"+rlParamvalues.get(rlparam));
							}else if(rlParamvalues.get(rlparam).contains(wopParamvalues.get(wopparam))){
								System.out.println("Param values are matched for------------------------"+pName);
								logStep("Param values are matched for----------"+pName);
								System.out.println(wopParamvalues.get(wopparam)+"--------------"+rlParamvalues.get(rlparam));
								logStep(wopParamvalues.get(wopparam)+"-------"+rlParamvalues.get(rlparam));
							}

						}
					}
				}else if(excelData2[maplist][3].toString().equalsIgnoreCase("addBefore")) {
					outer:
						for(int rlparam=0;rlparam<=rlParamvalues.size()-1;rlparam++) {
							for(int wopparam=0;wopparam<=wopParamvalues.size()-1;wopparam++) {
								rlValue=excelData2[maplist][4].toString()+rlParamvalues.get(rlparam);
								wopValue=wopParamvalues.get(wopparam);
								if(rlValue.equalsIgnoreCase(wopValue)) {
									System.out.println("Param values are matched for--------------"+pName);
									logStep("Param values are matched for-----"+pName+"--"+rlValue+"="+wopValue);
									break outer;
								}else {
									System.out.println("Param values are not matched for--------------"+pName);
									logStep("Param values arenot  matched for-----"+pName+"--"+rlValue+" != "+wopValue);
									if(rlparam==rlParamvalues.size()-1) {
										Assert.fail("Param values are not  matched for-----"+pName+"--"+rlValue+"!="+wopValue);
									}
								}
							}
						}
				}else if(excelData2[maplist][3].toString().equalsIgnoreCase("addAfter")) {
					outer:
						for(int rlparam=0;rlparam<=rlParamvalues.size()-1;rlparam++) {
							for(int wopparam=0;wopparam<=wopParamvalues.size()-1;wopparam++) {
								rlValue=rlParamvalues.get(rlparam)+excelData2[maplist][4].toString();
								wopValue=wopParamvalues.get(wopparam);
								if(rlValue.equalsIgnoreCase(wopValue)) {
									System.out.println("Param values are matched for--------------"+pName);
									logStep("Param values are matched for-----"+pName+"--"+rlValue+"="+wopValue);
									break outer;
								}else {
									System.out.println("Param values are not matched for--------------"+pName);
									logStep("Param values arenot  matched for-----"+pName+"--"+rlValue+" != "+wopValue);
									if(rlparam==rlParamvalues.size()-1) {
										Assert.fail("Param values are not  matched for-----"+pName+"--"+rlValue+" != "+wopValue);
									}
								}
							}
						}
				}else {
					outer:
						for(int rlparam=0;rlparam<=rlParamvalues.size()-1;rlparam++) {
							for(int wopparam=0;wopparam<=wopParamvalues.size()-1;wopparam++) {

								for(int rlmap=0;rlmap<=rlMpas.length-1;rlmap++) {
									if(rlMpas[rlmap].toString().equalsIgnoreCase(rlParamvalues.get(rlparam))) {

										for(int wopmap=0;wopmap<=wopMaps.length-1;wopmap++) {
											System.out.println(wopMaps[wopmap].toString()+"--"+wopParamvalues.get(wopparam));

											if(wopMaps[wopmap].toString().equalsIgnoreCase(wopParamvalues.get(wopparam))) {
												System.out.println("Param values are matched for--------------"+pName);
												logStep("Param values are matched for--------"+pName);
												if(excelData2[maplist][3].toString().equalsIgnoreCase("addBefore")) {
													rlValue=excelData2[maplist][4].toString()+rlParamvalues.get(rlparam);
													wopValue=wopParamvalues.get(wopparam);
													if(rlValue.equalsIgnoreCase(wopValue)) {
														System.out.println("Param values are matched for--------------"+pName);
														logStep("Param values are matched for-----"+pName+"--"+rlValue+"="+wopValue);
														break outer;
													}else {
														System.out.println("Param values are not matched for--------------"+pName);
														logStep("Param values arenot  matched for-----"+pName+"--"+rlValue+"!="+wopValue);
													}

												}if(excelData2[maplist][3].toString().equalsIgnoreCase("addAfter")) {
													rlValue=rlParamvalues.get(rlparam)+excelData2[maplist][4].toString();
													wopValue=wopParamvalues.get(wopparam);
													if(rlValue.equalsIgnoreCase(wopValue)) {
														System.out.println("Param values are matched for--------------"+pName);
														logStep("Param values are matched for-----"+pName+"--"+rlValue+"="+wopValue);
														break outer;
													}else {
														System.out.println("Param values are not matched for--------------"+pName);
														logStep("Param values arenot  matched for-----"+pName+"--"+rlValue+"!="+wopValue);
													}
												}else if(excelData2[maplist][3].toString().equalsIgnoreCase("direct")){
													rlValue=rlParamvalues.get(rlparam);
													wopValue=wopParamvalues.get(wopparam);
													if(rlValue.equalsIgnoreCase(wopValue)) {
														System.out.println("Param values are matched for--------------"+pName);
														logStep("Param values are matched for-----"+pName+"--"+rlValue+"="+wopValue);
														break outer;
													}else {
														System.out.println("Param values are not matched for--------------"+pName);
														logStep("Param values arenot  matched for-----"+pName+"--"+rlValue+"!="+wopValue);
													}
												}else {
													break outer;
												}
											}else {
												if(wopmap==wopMaps.length-1) {
													logStep("WOP Mapping value not matched : "+wopMapvalue+"---"+rlParamvalues.get(wopparam));
													Assert.fail("WOP Mapping value not matched : "+wopMapvalue+"---"+rlParamvalues.get(wopparam));
												}
											}
										}
									}else {
										if(rlmap==rlMpas.length-1) {
											logStep("RL Mapping value not matched : "+rlMapvalue+"---"+rlParamvalues.get(rlparam));
											Assert.fail("RL Mapping value not matched : "+rlMapvalue+"---"+rlParamvalues.get(rlparam));
										}
									}
								}

								//							if(rlMapvalue.contains(rlParamvalues.get(rlparam))&& wopMapvalue.contains(wopParamvalues.get(wopparam))){
								//								System.out.println("Param values are matched for------------------------"+paramName);
								//								System.out.println(wopParamvalues.get(wopparam)+"--------------"+rlParamvalues.get(rlparam));
								//							}else {
								//								Assert.fail("Param values are matched for---"+paramName+"-----"+wopParamvalues.get(wopparam)+"----"+rlParamvalues.get(rlparam));
								//							}

							}
						}

				}
			}
		}

	}
}
