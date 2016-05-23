package DemoNG;

import java.awt.RenderingHints.Key;
import java.awt.datatransfer.FlavorTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.el.ELException;
import javax.swing.plaf.LayerUI;
import javax.swing.plaf.ToolTipUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.mustache.Value;
import org.apache.commons.collections.map.StaticBucketMap;
import org.apache.http.conn.routing.RouteInfo.LayerType;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.bouncycastle.asn1.eac.Flags;
import org.openqa.jetty.html.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.DriverSessions;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
 
import java.io.FileInputStream;
 
import java.io.IOException;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
 
import org.apache.poi.ss.usermodel.Sheet;
 
import org.apache.poi.ss.usermodel.Workbook;
 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLCollection;
import com.gargoylesoftware.htmlunit.javascript.host.speech.webkitSpeechRecognition;

import bsh.UtilEvalError;
import DemoNG.Utilities;



public class DemoTest extends Utilities{
	
	//GET String values from XML Node VALUES 
			public static String getXMLNodesValues(String XmlNode) throws Exception
			{
				File xmlfile= new File("D:\\JavaSelenium\\GeoShieldMissionModule\\src\\DemoNG\\APPCONFIG.xml");
				DocumentBuilderFactory dbuilder= DocumentBuilderFactory.newInstance();
				DocumentBuilder build = dbuilder.newDocumentBuilder();
				Document doc = (Document) build.parse(xmlfile);
				Element rootElement = doc.getDocumentElement();
				NodeList list = rootElement.getElementsByTagName(XmlNode);
				String textvalue=list.item(0).getTextContent();
				System.out.println(textvalue);
				return textvalue;
				
			}
	public static WebDriver driver = new FirefoxDriver();
	//Actions action= new Actions(driver);
	//getXMLNodesValues("appurl");
    
      @Test
	  public void browseURL() throws Exception
      {
    	  
    	 // getXMLNodesValues("author");
    	  //getXMLNodesValues("title");
		  driver.get(Url);
		  driver.manage().window().maximize();
		  Utilities.LoadingIndicatorWait(driver, 30);
		  //Thread.sleep(15000);
		  //Utilities.FindDynamicallyElements(driver, "panelLeftArrowIcon", "ClassName");
		  OpeningPanels("panelLeftArrowIcon");
		  Thread.sleep(7000);
		  ChangingPredefinedDate("Last 90 days");
		  ChangingDateRange();
		  //Thread.sleep(2000);
		  Utilities a1 = new Utilities();
		 
		  EventsExpandingOnCategoryTitle("Part 1 - Non Violent Crimes");
		  //EventsExpandingOnCategoryTitle("Part 1 - Violent Crimes");
		  EventsExpandingOnCategoryTitle("Priority");
		 // EventsExpandingOnCategoryTitle("Persons");
		 // ToggleButtonMain("Part 1 - Non Violent Crimes");
		 ToggleButtonMain("Part 1 - Violent Crimes");
		  //ToggleButtonSubCategory("Burglary");
		 //ToggleButtonSubCategory("Larceny/Theft Other");
		 //ToggleButtonSubCategory("Motor Vehicle Theft");
		//  ToggleButtonSubCategory("Aggravated Assault");
		 // EventsExpandingOnCategoryTitle("categoryGroupContainerPart 1 - Violent Crimes ");
		  //EventsExpandingOnCategoryTitle("categoryGroupContainerPart 2 ");
		  ///EventsExpandingOnCategoryTitle("categoryGroupContainerPriority");
		  //OpeningPanels("panelUpArrowIcon");
		  //OpeningPanels("panelRightArrowIcon");
		  List<String> LeftPanelList =getLayerNameFromOnOFFButton();
		  List<String> DownPanelList =GetStringFromDataPanel();
		  CompareRT2LF(LeftPanelList, DownPanelList);
		  GetLayerNamesFromRightAcivePanel("Active Data");
		  List<String>RightPanelList =GetStringFromTOCPanel();
		  //CompareRT2LF(LeftPanelList, RightPanelList);
		  //CompareRT2LF(LeftPanelList, DownPanelList);
		  Map<Integer, String> hm1=GetfeatureCountAndNameforeachLayers();
		  //System.out.println(hm1);
		  Map<Integer, String> hm2=GetIndividualLayers();
		  //System.out.println(hm2);
		 // CompareHashMapDownPanelDatawithActiveData(hm1, hm2);
		  ClickOnTool("Live Feeds");
		  WidgetIconClickByName("YouTube");
		  TwitterPanel("Distance");
		  //WidgetIconClickByName("Twitter");
		  //TwitterPanel("Distance (in Miles)");
		  //String filePath = "D:\\JavaSelenium\\GeoShieldMissionModule\\src\\DemoNG\\";
		  readExcel(filePath,"Twitter.xlsx","Sheet1");
		  ClickOnTool("Tools");
		  WidgetIconClickByName("Show Address");
		  WidgetIconClickByName("Simple Draw");
		  
		  //OpenTabsFromName();
			  
		  
      }
   
	@SuppressWarnings("null")
	public static List<String> readExcel(String filePath,String 
.,String sheetName) throws IOException
      {
			File file =    new File(filePath+"\\"+fileName);
			FileInputStream inputStream = new FileInputStream(file);
    	    Workbook TestWorkbook = null;
    	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
    	    if(fileExtensionName.equals(".xlsx"))
    	    {
    	    	TestWorkbook = new XSSFWorkbook(inputStream);
    	    }
    	    else if(fileExtensionName.equals(".xls"))
    	    {
    	    	TestWorkbook = new XSSFWorkbook(inputStream);
    	    }
    	    Sheet TestSheet = TestWorkbook.getSheet(sheetName);
    	    int rowCount = TestSheet.getLastRowNum();
    	    List<String> y=null;
    	    
    	   
	    	    for (int i = 1; i < rowCount+1; i++) 
	    	     {
	    		   Row row = TestSheet.getRow(i);
		    		   for (int j = 0; j < row.getLastCellNum(); j++) 
		    		   {	Cell cell = row.getCell(j);
				    		   switch (cell.getCellType())
				               {
				               		case Cell.CELL_TYPE_NUMERIC:
										int a= (int) cell.getNumericCellValue();
										String X= String.valueOf(a);
										y.add(X);
;										System.out.println(a);
										break;
				               		case Cell.CELL_TYPE_STRING:
				                        String b= (String) cell.getStringCellValue();
				                        
				                        System.out.println(b);
				                        y.add(b);
				                        break;
				                }
		    		   }
	    	     }
				return null;
	    	    
	    	   
	    	    	    
    	 }
     /* public static void getexcelDATA()
      {
      File file =    new File(filePath+"\\"+fileName);
      String filepath="D://JavaSelenium/GeoShieldMissionModule/src/DemoNG/Twitter.xlsx";
      FileInputStream fs = new FileInputStream(filepath);
      Workbook wb = Workbook.getWorkbook(fs);
      Workbook wb1 = Workbook.getWorkbook(new File("D://JavaSelenium/GeoShieldMissionModule/src/DemoNG/Twitter.xlsx"));
      }*/
      public static void WidgetIconClickByName(String WidgetName)
      {
    	  String xpath="//div[@class='backGroundToolIcon']/div[@title='"+WidgetName+"']";
    	  driver.findElement(By.xpath(xpath)).click();
      }
      
      // Twitter Panel
      public static  void TwitterPanel(String TwitterText)
	    {
    	  List<WebElement> toolItemList =driver.findElements(By.className("searchTweetDiv"));
    	  for (WebElement toolItem : toolItemList) 
    	  {
			WebElement TweetDiv=toolItem.findElement(By.className("leftPanelLabel"));
			if(TweetDiv.getText().equals(TwitterText))
			{
				WebElement TweetTextBox = toolItem.findElement(By.className("dijitInputContainer"));
				WebElement Tweetbox =TweetTextBox.findElement(By.className("dijitInputInner"));
				Tweetbox.clear();
				Tweetbox.sendKeys("ajhj");
			}
			
    	  }	/*WebElement SpanList= toolItem.findElement(By.tagName("span"));
			
			for (WebElement DivListItem : DivList) 
			{	
				if(DivListItem.getText().equals("Keyword ")&& SpanList.getText().equals("Eg. crime"));
				{
					SpanList.sendKeys("NULKL");
				}
				
			}
			
    	  }
	    }*/
      //LeftPanel BROWSE, LIVEFEEDS , Tools icon click 
      public static void ClickOnTool(String toolname)
      {
      //Selecting Left ICONS on Bases of name
      WebElement Toollist = driver.findElement(By.className("applicationToolContent"));
      List<WebElement> toolItemList =Toollist.findElements(By.className("iconContainer"));
      for (WebElement toolItem : toolItemList) 
		{
    	  if(toolItem.getAttribute("title").equals(toolname))
    	  {
    		  toolItem.click();
    	  }
		}
      }
      
      //Validating Right panel in reference to Left Panel   AND DownPanel to Left Panel
      public static void CompareRT2LF(List<String>LeftPanelList2, List<String> DownPanelList2)
      {
    	  int flag=0;
		  for (int i = 0; i < LeftPanelList2.size(); i++) 
		  {
			for (int j = 0; j < DownPanelList2.size(); j++) 
			{
				if(!(LeftPanelList2.get(i).equals(DownPanelList2.get(j))))
				{
					continue;
				}
				else
				{
					flag++;
				}
			}
		  }
		  if (flag == LeftPanelList2.size())
            { //return true;
                System.out.println("TRUE");
            }
		  else{System.out.println("FALSE");}
            //return false;
    	  
      }
      
     //Validating DataPanel LayerName & Results count using HashMap with Active Panel Data
      public void CompareHashMapDownPanelDatawithActiveData(HashMap<Integer,String> HM1, Map<Integer, String> HM2)
      {
    	  for(HashMap.Entry<Integer,String> entry1: HM1.entrySet()) {
    		   Integer key1 = entry1.getKey();
    		   int hash1 = System.identityHashCode(key1);
    		   String value1 = entry1.getValue();
    		   for(Map.Entry<Integer,String> entry2: HM2.entrySet()) {
    		       Integer key2 = entry2.getKey();
    		       if (key1 > System.identityHashCode(key2)) continue;

    		       String value2 = entry1.getValue();
    		       // compare value1 and value2;
    		       int flag=0;
    		       for (int i = 0; i < HM1.size(); i++) 
    				  {
    					for (int j = 0; j < HM2.size(); j++) 
    					{
    						if(!(value1.equals(value1)&&(key1.equals(key1))))
    						{
    							continue;
    						}
    						else
    						{
    							flag++;
    						}
    					}
    				  }
    				  if (flag == HM1.size())
    		            { //return true;
    		                System.out.println("TRUE");
    		            }
    				  else{System.out.println("FALSE");}
    		            //return false;
    		    	  
    		      }
    		   }
    		}
      
/* int flag=0;
    	  for (int i = 0; i < HM1.size(); i++) 
		  {
			for (int j = 0; j < HM2.size(); j++) 
			{
				if(!(HM1.equals(HM2)))
				{
					continue;
				}
				else
				{
					flag++;
				}
			}
		  }
		  if (flag == HM1.size())
            { //return true;
                System.out.println("TRUE");
            }
		  else{System.out.println("FALSE");}
            //return false;
    	  
      }
      
      */
      public void OpeningPanels(String name) throws Exception
      {
    	  WebElement Arrows =driver.findElement(By.className(name));
		 Utilities.ClickOnEnabledDisplayedItem(Arrows);
      }
      
      public static void EventsExpandingOnCategoryTitle(String CategoryName) throws Exception
  	{
  		List<WebElement> CategoryGrouplist= driver.findElements(By.className("categoryGroupPanel"));
  		for (WebElement CategoryGrouplistItem1 : CategoryGrouplist) 
  		{
  			List<WebElement> CategoryGrouplistDivList = CategoryGrouplistItem1.findElements(By.tagName("div"));
  			  int i = 0;
  			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) 
  			{
  				int increment = i++;
  				int expandIcon = increment-1;
  				if(CategoryGrouplistDivListItem.getText().equals(CategoryName))
  				{
  					CategoryGrouplistDivList.get(expandIcon).click();
  					
  					
  				}
  			}
  		}
  		 
  	}
  	
  	//Toggling for ON/OFF options for Parent Category options
  	
  	public static void ToggleButtonMain(String ParentCategoryName) throws Exception
  	{
  		List<WebElement> CategoryGrouplist= driver.findElements(By.className("categoryGroupPanel"));
  		for (WebElement CategoryGrouplistItem : CategoryGrouplist) 
  		{
  			  List<WebElement> CategoryGrouplistDivList = CategoryGrouplistItem.findElements(By.tagName("div"));
  			  int i = 0;
  			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) 
  			{
  				int increment = i++;
  				int OnOffIcon = increment+5;
  				if(CategoryGrouplistDivListItem.getText().equals(ParentCategoryName))
  				{
  					ClickOnEnabledDisplayedItem(CategoryGrouplistDivList.get(OnOffIcon));
  					
  					
  				}
  			}
  		}
  	}
  	
  	//Toggling for ON/OFF options for Child Category options
  	
  	public static void ToggleButtonSubCategory(String SubCategoryName) throws Exception
  	{
  		List<WebElement> SubCategoryGrouplist= driver.findElements(By.className("subCategoryDataPanel"));
  		for (WebElement SubCategoryGrouplistItem : SubCategoryGrouplist) 
  		{
  			  List<WebElement> CategoryGrouplistDivList = SubCategoryGrouplistItem.findElements(By.tagName("div"));
  			  int i = 0;
  			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) 
  			{
  				int increment = i++;
  				int OnOffIcon = increment+4;
  				if(CategoryGrouplistDivListItem.getText().equals(SubCategoryName))
  				{
  					
  					ClickOnEnabledDisplayedItem(CategoryGrouplistDivList.get(OnOffIcon));
  					
  				}
  			}
  		}
  	}
		public static void ChangingPredefinedDate(String datevalue) throws Exception 
		{
		  WebElement timeFilterBtn= driver.findElement(By.className("filterSelectedDateIcon"));
		  Utilities.ClickOnEnabledDisplayedItem(timeFilterBtn);
		  Thread.sleep(1000);
		  List<WebElement> mylist = driver.findElements(By.className("layerContentRow"));
			for(WebElement test : mylist) 
			{
				if(test.getText().equals(datevalue))
				{
				test.click();
				Thread.sleep(1000);
				}
			}
			WebElement OkBtn= driver.findElement(By.className("okBtn"));
			Utilities.ClickOnEnabledDisplayedItem(OkBtn);
		}
		
		//Selecting Date Values in different formats
		public static void ChangingDateRange() throws Exception
		{
			WebElement timeFilterBtn= driver.findElement(By.className("filterSelectedDateIcon"));
			Utilities.ClickOnEnabledDisplayedItem(timeFilterBtn);
			WebElement daterangetab=driver.findElement(By.className("removeRightBorder"));
			Utilities.ClickOnEnabledDisplayedItem(daterangetab);
			Thread.sleep(1200);
			List<WebElement> dateelements = driver.findElements(By.className("dijitArrowButtonContainer"));
			Utilities.ClickOnEnabledDisplayedItem(dateelements.get(0));
			WebElement Fromdate= driver.findElement(By.id("dijit_form_DateTextBox_0_popup"));
			WebElement Fromdate1=Fromdate.findElement(By.className("dijitCalendarBodyContainer"));
			List<WebElement> allFromDates = Fromdate1.findElements(By.tagName("span"));
			for (WebElement dateelement : allFromDates) 
			{
				if(dateelement.getText().equals("2"))
					{
					 dateelement.click();
					}
			}
			Thread.sleep(500);
			Utilities.ClickOnEnabledDisplayedItem(dateelements.get(1));
			WebElement Todate= driver.findElement(By.id("dijit_form_DateTextBox_1_popup"));
			WebElement Todate1=Todate.findElement(By.className("dijitCalendarBodyContainer"));
			List<WebElement> allToDates = Todate1.findElements(By.tagName("span"));
			for (WebElement dateelement : allToDates) 
			{
				if(dateelement.getText().equals("27"))
					{
					 dateelement.click();
					}
			}
			WebElement OkBtn= driver.findElement(By.className("okBtn"));
			Utilities.ClickOnEnabledDisplayedItem(OkBtn);
		}
		
		//Getting layername from ONOFF button
		public static List<String> getLayerNameFromOnOFFButton(WebDriver driver)
		{
			List<WebElement> ToggleBtnList= driver.findElements(By.className("toggleOn"));
			List <String>LayerNameListLeftPanel = new ArrayList<String>();
			for (WebElement ItemToggleOn : ToggleBtnList) 
			{
				String X=ItemToggleOn.getAttribute("id");
				String Z= X.replace("_"," ");
				String Z1= Z.replace(" ","");
				System.out.println(Z1);
				LayerNameListLeftPanel.add(Z1);
				
			}
			return LayerNameListLeftPanel;
		}
		//Getting layername from Data panel button
		public static List<String> GetStringFromDataPanel() throws Exception
  		{
  			WebElement SelectLayerBtn= driver.findElement(By.className("selectedLayerValue"));
  			ClickOnEnabledDisplayedItem(SelectLayerBtn);
  			WebElement LayerListMain= driver.findElement(By.className("layerDataContainer"));
  			List <String>LayerNameListDownPanel = new ArrayList<String>();
  			List <WebElement> layerList= LayerListMain.findElements(By.className("layerContentRow"));
  			for (WebElement LayerListItem : layerList) 
  			{
  				String y=LayerListItem.getText();
  				String Z2= y.replace(" ","");
				System.out.println(Z2);
				LayerNameListDownPanel.add(Z2);
			
			}
  			ClickOnEnabledDisplayedItem(SelectLayerBtn);
			return LayerNameListDownPanel;
  			
  		}
		//Opening panels from Different tabs using title
		public static void OpenTabsFromName()
		{
			List<WebElement> ActiveElementslist= driver.findElements(By.className("dijitAccordionText"));
			for (WebElement ActiveElementslistItem : ActiveElementslist) 
			{ 
				if(ActiveElementslistItem.getText().equals("Active Data"))
				{
					ActiveElementslistItem.click();
				}
				
			}
			
		}
		
		//Opening Active Panel and getting Layernames
		public static void GetLayerNamesFromRightAcivePanel(String panelname )
		{
			String XpathValue= "//span[@class='dijitAccordionText'][contains(text(), '"+panelname+"')]";
			WebElement Panelelement= driver.findElement(By.xpath(XpathValue));
			Panelelement.click();
		}
		
		//Getting Features Count value from Active data Panel
		public static HashMap<Integer, String> GetfeatureCountAndNameforeachLayers()
		{
			HashMap<Integer, String> hm = new HashMap<Integer,String>();
			List<WebElement> NumberList=driver.findElements(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"));
			for (WebElement LayerItem : NumberList) 
			{
				String layernam=LayerItem.getAttribute("id");
				String layernam1=layernam.replace("|graphicCount","");
				String layernam2=layernam1.replace("_"," ");
				String layernam3=layernam2.replace(" ", "");
				Integer numbervalue= Integer.parseInt(LayerItem.getText());   
				hm.put(numbervalue, layernam3);
				
			}
			
		return hm;
		
		}
		
		
		// Getting LayerName from RightPanel
		public static List<String> GetStringFromTOCPanel() throws Exception
  		{
  			List <String>LayerNameListRightPanel = new ArrayList<String>();
  			List <WebElement> layerList= driver.findElements(By.className("tocLayerName"));
  			for (WebElement LayerListItem : layerList) 
  			{
  				String y=LayerListItem.getText();
  				String Z2= y.replace(" ","");
				System.out.println(Z2);
				LayerNameListRightPanel.add(Z2);
			
			}
			return LayerNameListRightPanel;
  			
  		}
		
		
		//getting LayerNames with feature count from DownPanel
		public static HashMap<Integer,String> GetIndividualLayers() throws Exception
		{
			
  			WebElement LayerListMain= driver.findElement(By.className("layerDataContainer"));
  			HashMap<Integer, String> hm = new HashMap<Integer, String>();
  			List <WebElement> layerList= LayerListMain.findElements(By.className("layerContentRow"));
  			for (int i = 0; i < layerList.size(); i++) 
  			{
				String y=layerList.get(i).getText();
				String Z2=y.replace(" ", "");
				layerList.get(i).click();
				Thread.sleep(1000);
				WebElement FeatureCountDataPanel=driver.findElement(By.className("gridTotalResultsPanel"));
  				String x=FeatureCountDataPanel.getText();
  				Integer Z1=Integer.parseInt(x.replace(" result(s)", ""));
  				hm.put(Z1,Z2);
  				driver.findElement(By.className("selectedLayerValue")).click();
			}
  			/*for (WebElement ItemElement : layerList) 
  			{
  				String y=ItemElement.getText();
  				String Z2= y.replace(" ","");
  				ItemElement.click();
  				WebElement FeatureCountDataPanel=driver.findElement(By.className("gridTotalResultsPanel"));
  				String Z1=FeatureCountDataPanel.getText();
  				hm.put(Z1, Z2);
  				driver.findElement(By.className("selectedLayerValue")).click();
  				System.out.println(hm);
			}
			return hm;
		*/
			
  			return hm;	
			
		}
		
			
		}
		


			/*String XXPATH= "//div[@id='"+id+"']/div/div[@class='expandButtonDiv']/div";
			
			WebElement RMS1=driver.findElement(By.xpath(XXPATH));
			RMS1.click();
			Thread.sleep(1000);
		*/
		
		
		
		
		
      /*@AfterTest
      public void teardown() 
      {
      // closes all the browser windows opened by web driver
         driver.quit();     
      }*/
  

 
