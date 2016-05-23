import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.bcel.generic.POP;
import org.apache.log4j.Priority;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.internal.annotations.IListeners;
public class Tools extends AppCommonUtility
{

		//public static WebDriver driver;
		
		@Test
		public static void OperationsMultipleQueryLayer() throws Exception
		{	
			InitialiseBrowser();
			GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
			if (AppCommonUtility.getXMLNodesValuesBoolean("MainPart1-ViolentCrimes")) 
			{
				GeoshieldActions.ToggleButtonMain(driver,"Part 1 - Violent Crimes");
			
			}
			GeoshieldActions.ClickOnTool(driver,"Tools");
			GeoshieldActions.ClickOnIcon(driver, "Multiple Layer Search");
			GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
			List<WebElement> PanelRight=driver.findElements(By.className("panelRightArrowIcon "));
			PanelRight.get(1).click();
			GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
			ToolsActions.CheckBoxUsingLayerNames(driver);
			List<String> SelectLayer=ToolsActions.GetAllLayersNameSelectLayerTab(driver);
			GeoshieldActions.SwitchToDateTab(driver);
			List<String> ExportTab=ToolsActions.GetAllLayersNameExportTab(driver);
			List<String> CombinedAllLayers = new ArrayList<String>();
			List<String> CombinedAllRightPanelLayers = new ArrayList<String>();
			CombinedAllLayers.addAll(ExportTab);
			CombinedAllLayers.addAll(SelectLayer);
			GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
			GeoshieldActions.OpeningPanels(driver,"panelRightArrowIcon");
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
			List<String> ActiveLayersList=AppValidation.GetActiveTabLayersNamesList(driver);
			
			//Validating widget layers List with Right Panel Active Data Tab
			AppValidation.Compare2StringsLists(ActiveLayersList, CombinedAllLayers);
			
			GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
			GeoshieldActions.ClickOnSelectLayerButton(driver);
			List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
			GeoshieldActions.ClickOnSelectLayerButton(driver);
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
			List<String> GraphicsLayersList=AppValidation.GetGraphicsTabLayersList(driver);
			CombinedAllRightPanelLayers.addAll(ActiveLayersList);
			CombinedAllRightPanelLayers.addAll(GraphicsLayersList);
			
			//Validating bottom and RightPanel all layers
			AppValidation.Compare2StringsLists(CombinedAllRightPanelLayers, BottomPanelLayerList);
			
			
			HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
			HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			
			//validating Bottom Layer Contents and Active layer contents
			GeoshieldActions.Compare2HashMap(HM2, HM1);
			GeoshieldActions.ClearAllResults(driver);
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
			GeoshieldActions.ClearAllResults(driver);
			GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
			GeoshieldActions.CloseWiget(driver);
			driver.quit();
		}
		
		//Operations for Search Attributes
		public static void OperationsSearchAttributes(int index) throws Exception
		{
			String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
			AppCommonUtility.SelectingDriver(Browser);
			Thread.sleep(1000);
			String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
			driver.get(AppUrl);	
			driver.manage().window().maximize();
			AppCommonUtility.LoadingIndicatorWait(driver, 30);
			GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
			GeoshieldActions.ClickOnTool(driver,"Tools");
			GeoshieldActions.ClickOnIcon(driver, "Search Attributes");
			String[] SelectLayer= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "SelectLayer");
			String[] Field1= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Field1");
			String[] Field2= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Field2");
			String[] Operator1= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Operator1");
			String[] Operator2= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Operator2");
			String[] Value1= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Value1");
			String[] Value2= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "Value2");
			String[] QueryTitle= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "QueryTitle");
			String[] ANDOR= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "ANDOR");
			ToolsActions.DataSendTextboxUsinglabelName(driver, "Select Layer", SelectLayer[index]);
			driver.findElement(By.className("attributeQueryAddMoreButton")).click();
			GeoshieldActions.MinimizeIconClick(driver, "Attribute Query");
			GeoshieldActions.MinimizeIconClick(driver, "Attribute Query");
			
			List<WebElement> AttributeList= driver.findElements(By.xpath("//div[@class='attributeQueryCell attributeQueryControl']/div/div[@class='dijitReset dijitInputField dijitInputContainer']/input[@class='dijitReset dijitInputInner']"));
			AttributeList.get(0).sendKeys(Field1[index]);
			AttributeList.get(0).sendKeys(Keys.ENTER);
			GeoshieldActions.SelectingByLPNDropdown(driver,"operator_0", Operator1[index]);
			AttributeList.get(2).sendKeys(Value1[index]);
			ToolsActions.SwitchRadio(driver, ANDOR[index]);
			AttributeList.get(3).sendKeys(Field2[index]);
			AttributeList.get(3).sendKeys(Keys.ENTER);
			GeoshieldActions.SelectingByLPNDropdown(driver,"operator_1", Operator2[index]);
			AttributeList.get(5).sendKeys(Value2[index]);
			WebElement QueryTitleElement=driver.findElement(By.xpath("//div[@class='saveQueryDiv']/div/div/input"));
			QueryTitleElement.sendKeys(QueryTitle[index]);
			WebElement SaveButton =driver.findElement(By.className("attributeQuerySaveQueryButton"));
			SaveButton.click();
			GeoshieldActions.LoadingIndicatorWait(driver, 30);
			String DialogMessage= driver.findElement(By.className("alertDialogMessage")).getText();
			if(DialogMessage.equals(QueryTitle[index]+" Query Title saved successfully."))
			{
				GeoshieldActions.OkBtnClickList(driver, "alertOkButton");
				Thread.sleep(1000);
				GeoshieldActions.SwitchToDateTab(driver);
				ToolsActions.DataSendTextboxUsinglabelName(driver, "Select Layer", SelectLayer[index]);
				ToolsActions.DataSendTextboxUsinglabelName(driver, "Load Saved Search", QueryTitle[index]);
				GeoshieldActions.OkBtnClickList(driver, "attributeQueryPriorQueryButton");
				GeoshieldActions.MinimizeIconClick(driver, "Attribute Query");
				GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
				HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
				HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
				GeoshieldActions.Compare2HashMap(HM2, HM1);
				GeoshieldActions.ClearAllResults(driver);
				GeoshieldActions.CloseWiget(driver);
				driver.quit();
			}	
		}
		
		//Method for Show Address widget Tools panel
		public static void SearchAddressOperations() throws Exception
		{
			String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
			AppCommonUtility.SelectingDriver(Browser);
			Thread.sleep(1000);
			String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
			driver.get(AppUrl);	
			driver.manage().window().maximize();
			AppCommonUtility.LoadingIndicatorWait(driver, 30);
			GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
			GeoshieldActions.ClickOnTool(driver,"Tools");
			
			GeoshieldActions.ClickOnIcon(driver, "Show Address");
			Thread.sleep(1000);
			WebElement ZoomOutIcon= driver.findElement(By.className("icon-minus"));
			for (int i = 0; i < 2; i++) 
			{
				ZoomOutIcon.click();
			}
			driver.findElement(By.id("mainContainer")).click();
			Thread.sleep(500);
			List<WebElement> AlertOkButtonMessage= driver.findElements(By.className("alertDialogMessage"));
			try
			{
			
				//List<WebElement> AlertOkButtonMessage= driver.findElements(By.className("alertDialogMessage"));
				for (WebElement  AlertOkButtonMessageItem: AlertOkButtonMessage) 
				{
					if(AlertOkButtonMessageItem.getText().equals("Unable to find address.")&& AlertOkButtonMessageItem.isDisplayed())
					{
						List<WebElement> AlertOkButtonList= driver.findElements(By.className("alertOkButton"));
						for (WebElement  AlertOkButtonItem: AlertOkButtonList) 
						{
							ClickOnEnabledDisplayedItem(AlertOkButtonItem);
							Thread.sleep(500);
						}
					}	
				}
			}
			catch(Exception e)
			{}
			GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
			HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
			HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
			GeoshieldActions.Compare2HashMap(HM2, HM1);
			
			String AddressTextFromMap=driver.findElement(By.id("labelLayer|ShowAddress_layer")).getText();
			GeoshieldActions.ClickOnSelectLayerButton(driver);
			
			String AddressTextFromDataRow=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
			if(AddressTextFromMap.equals(AddressTextFromDataRow))
			{
				System.out.println("Address Names are Validated");
			}
			driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[1]/input")).click();
			Thread.sleep(2000);
			WebElement HighLigtElementDiv=driver.findElement(By.id("gridHighlightGraphicLayer_layer"));
			WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
			AppValidation.FeatureIsPresentOnMap(HighlightCircle);
			GeoshieldActions.ClearAllResults(driver);
			driver.quit();
		}
		
	
		
		
		
		
		
		//Method for Show Co-ordinates widget Tools panel
	public static void ShowCordinatesOperations() throws Exception
	{
		String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		AppCommonUtility.SelectingDriver(Browser);
		Thread.sleep(1000);
		String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
	
		driver.get(AppUrl);	
		driver.manage().window().maximize();
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnTool(driver,"Tools");
		GeoshieldActions.ClickOnIcon(driver, "Show Coordinates");
		String[] CordinatesOptions= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabShowCordinates", "Co-ordinates Options");
		ToolsActions.SwitchRadioShowCordinates(driver, CordinatesOptions[0]);
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(500);
		List<WebElement> PopUpLabel=driver.findElements(By.xpath("//div[@class='esriPopupWrapper']/div[@class='sizer']/div/div[@class='title']"));
		for (WebElement PopUpLabelItem : PopUpLabel) 
		{
			if(PopUpLabelItem.isDisplayed()&& PopUpLabelItem.getText().equals(CordinatesOptions[0]+" Coordinates"))
			{
				System.out.println("Coordinates Title  POPUP is validated");
				break;
			}
		}
		GeoshieldActions.MinimizeIconClick(driver, "Show Coordinates");
		GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
		GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		String CordinatesValueFromMap=driver.findElement(By.xpath("//div[@class='esriPopupWrapper']/div[@class='sizer content']/div")).getText();
		String CordinatesValueFromDataRow=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
		if(CordinatesValueFromDataRow.equals(CordinatesValueFromMap))
		{
			System.out.println("Cordinates Values are Validated");
		}
		GeoshieldActions.ClickOnSelectLayerButton(driver);
		driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[1]/input")).click();
		Thread.sleep(2000);
		WebElement HighLigtElementDiv=driver.findElement(By.id("gridHighlightGraphicLayer_layer"));
		WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
		AppValidation.FeatureIsPresentOnMap(HighlightCircle);
		Thread.sleep(1000);
		driver.quit();
	}
	
	//Code for Routing Widget Tools Tab
	
	public static void RoutingOperations() throws Exception
	{
		String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		AppCommonUtility.SelectingDriver(Browser);
		Thread.sleep(1000);
		String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
	
		driver.get(AppUrl);	
		driver.manage().window().maximize();
		String[] FromAddressList= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabRouting", "FROM Address");
		String[] ToAddressList= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabRouting", "TO Address");

		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnTool(driver,"Tools");
		GeoshieldActions.ClickOnIcon(driver, "Routing");
		ToolsActions.EnterDataInRoutingWidget(driver, "A", FromAddressList[0]);
		ToolsActions.EnterDataInRoutingWidget(driver, "B", ToAddressList[0]);
		ToolsActions.ClickOnButton(driver,"Get Directions");
		AppCommonUtility.LoadingIndicatorWait(driver, 10);
		Thread.sleep(5000);
		WebElement FeatureElement= driver.findElement(By.id("mapContainer_graphics_layer"));
		List<WebElement> FeatureElements = FeatureElement.findElements(By.tagName("path"));
		for (WebElement FeatureElementItem : FeatureElements) 
		{
			if(FeatureElementItem.isDisplayed()&& FeatureElementItem.isEnabled())
			{
				System.out.println("Route Path's is present on Map");
			}
			else
			{
				System.out.println("Route Path's is NOT present on Map");
			}
		}
		
		driver.quit();
		
	}
	
	// Code for Google Street View
	public static void GoogleStreetViewOperations() throws Exception
	{
		String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		AppCommonUtility.SelectingDriver(Browser);
		Thread.sleep(1000);
		String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
		
		driver.get(AppUrl);	
		driver.manage().window().maximize();
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnTool(driver,"Tools");
		GeoshieldActions.ClickOnIcon(driver, "Google Street View");
		driver.findElement(By.className("icon-minus")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(2000);
		 Set<String> handles = driver.getWindowHandles();
		 List<String> handlesList = new ArrayList<String> (handles);
		System.out.println(handles.size()+ handlesList.get(0));
		for (String window : handlesList) 
		{
            driver.switchTo().window(window);
            if (driver.getTitle().contains("Google Street View")) 
            {
            	WebElement GoogleLogo= driver.findElement(By.className("googleStreetViewLogo"));
        		if(GoogleLogo.isDisplayed())
        		{
        			System.out.println("GeoShield Logo is displayed ");
        		}
        		List<WebElement> MapOtionsList= driver.findElements(By.className("gm-style-mtc"));
	        	for (WebElement MapOtionsListItem : MapOtionsList) 
	        		{
	        			if(MapOtionsListItem.getText().equals("Satellite"))
	        			{
	        				MapOtionsListItem.click();
	        				break;
	        			}
	        		}
	        	WebElement StreetViewPlaceHolder= driver.findElement(By.className("streetViewPlaceHolder"));
        			if(StreetViewPlaceHolder.isDisplayed())
            		{
            			System.out.println("Validating Street View PlaceHolder is displayed ");
            		}
        		WebElement FullScreenDiv = driver.findElement(By.className("iv-tactile-compass-background"));
        		try{
	        			if(FullScreenDiv.isDisplayed())
	        			{
	        				System.out.println("StreetViewImage is  available");
	        			}
	        			else{System.out.println("StreetViewImage is not available");}
        			}
        		catch(Exception e)
        			{}
				  }
        		}
          
		driver.quit();	
	}
	
	//Code for FIND Cordinates Tools Tab
	
	public static void OperationsFindCordinates(int j) throws Exception
		{
			String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
			AppCommonUtility.SelectingDriver(Browser);
			Thread.sleep(1000);
			String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
			driver.get(AppUrl);	
			driver.manage().window().maximize();
			AppCommonUtility.LoadingIndicatorWait(driver, 30);
			GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
			GeoshieldActions.ClickOnTool(driver,"Tools");
			GeoshieldActions.ClickOnIcon(driver, "Find Coordinates");
			String[] FindCordinateType = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateType", "Cordinate System");
			for (int i = 0; i < FindCordinateType.length; i++)
			{
				Thread.sleep(500);
				GeoshieldActions.SelectingByLPNDropdown(driver, "cordSystemSelect", FindCordinateType[i]);
				Thread.sleep(1000);
				switch(FindCordinateType[i])
				{
					case "DD":
						String[] DDLatitude = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DDLatitude");
						String[] DDLongitude = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DDLongitude");
						ToolsActions.EnterDataDDFindCordinates(driver, "Longitude", DDLongitude[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "Latitude", DDLatitude[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						List<WebElement> FeatureIcon=	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						for (WebElement FeatureIconItem : FeatureIcon)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMap= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRow=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRow.equals(CoordinateValueMap))
						{
							System.out.println("Cordinates Values are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM2, HM1);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
					case "Deg. Min.":
						String[] DLong = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DegMinLongDeg");
						String[] DLat = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DegMinLatDeg");
						String[] MLong = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DegMinLongMin");
						String[] MLat = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DegMinLatMin");
						List<WebElement> TextBoxList=driver.findElements(By.xpath("//div[@class='findCoordinatesElementContents findCoordinatesDegMinElementContents']/div/div[2]/input[@class='dijitReset dijitInputInner']"));
						TextBoxList.get(0).sendKeys(DLong[j]);
						TextBoxList.get(1).sendKeys(MLong[j]);
						TextBoxList.get(2).sendKeys(DLat[j]);
						TextBoxList.get(3).sendKeys(MLat[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						List<WebElement> FeatureIconDegMin =	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						for (WebElement FeatureIconItem : FeatureIconDegMin)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMapDegMin= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM3 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRowDegMin=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRowDegMin.equals(CoordinateValueMapDegMin))
						{
							System.out.println("Cordinates Values Deg Min are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM4 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM4, HM3);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
					case "DMS":
						String[] DMSDLong = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLongDeg");
						String[] DMSDLat = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLatDeg");
						String[] DMSMLong = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLongMin");
						String[] DMSMLat = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLatMin");
						String[] DMSSLong = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLatSec");
						String[] DMSSLat = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "DMSLongSec");
						List<WebElement> DMSTextBoxes=driver.findElements(By.xpath("//div[@class='findCoordinatesElementContents findCoordinatesDMSElementContents']/div/div[2]/input[@class='dijitReset dijitInputInner']"));
						DMSTextBoxes.get(0).sendKeys(DMSDLong[j]);
						DMSTextBoxes.get(1).sendKeys(DMSMLong[j]);
						DMSTextBoxes.get(2).sendKeys(DMSSLong[j]);
						DMSTextBoxes.get(3).sendKeys(DMSDLat[j]);
						DMSTextBoxes.get(4).sendKeys(DMSMLat[j]);
						DMSTextBoxes.get(5).sendKeys(DMSSLat[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						List<WebElement> FeatureIconDMS =	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						for (WebElement FeatureIconItem : FeatureIconDMS)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMapDMS= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM5 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRowDMS=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRowDMS.equals(CoordinateValueMapDMS))
						{
							System.out.println("Cordinates Values DMS DEG_MIN_SEC are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM6 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM6, HM5);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
					case "MGRS":
						String[] MGRSZone = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "MGRSZone");
						String[] MGRSZDL = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "MGRS ZDL");
						String[] MGRS2Letters = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "MGRS2Letters");
						String[] MGRSEN = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "MGRSE-N");
						ToolsActions.EnterDataDDFindCordinates(driver, "Zone", MGRSZone[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "ZDL", MGRSZDL[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "2-Letters", MGRS2Letters[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "E-N", MGRSEN[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						Thread.sleep(3000);
						List<WebElement> FeatureIconMGRS =	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						for (WebElement FeatureIconItem : FeatureIconMGRS)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMapMGRS= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM7 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRowMGRS=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRowMGRS.equals(CoordinateValueMapMGRS))
						{
							System.out.println("Cordinates Values MGRS  are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM8 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM8, HM7);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
					case "USNG":
						String[] USNGZone = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "USNGZone");
						String[] USNGZDL = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "USNGZDL");
						String[] USNG2Letters = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "USNG2Letters");
						String[] USNGEN = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "USNGE-N");
						ToolsActions.EnterDataDDFindCordinates(driver, "Zone", USNGZone[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "ZDL", USNGZDL[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "2-Letters", USNG2Letters[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "E-N", USNGEN[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						List<WebElement> FeatureIconUSNG =	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						
						for (WebElement FeatureIconItem : FeatureIconUSNG)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMapUSNG= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM9 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRowUSNG=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRowUSNG.equals(CoordinateValueMapUSNG))
						{
							System.out.println("Cordinates Values USNG  are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM10 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM10, HM9);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
					case "UTM":
						String[] UTMZone = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "UTMZone");
						String[] UTMHemisphere = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "UTMHem");
						String[] UTMEasting= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "UTMEasting");
						String[] UTNNorthing = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "UTMNorthing");
						ToolsActions.EnterDataDDFindCordinates(driver, "Zone", UTMZone[j]);
						ToolsActions.SwitchRadioFindCordinatesUTM(driver,  UTMHemisphere[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "Easting", UTMEasting[j]);
						ToolsActions.EnterDataDDFindCordinates(driver, "Northing", UTNNorthing[j]);
						driver.findElement(By.className("findButton")).click();
						Thread.sleep(3000);
						List<WebElement> FeatureIconUTM =	driver.findElement(By.id("findCoordinates_layer")).findElements(By.tagName("circle"));
						for (WebElement FeatureIconItem : FeatureIconUTM)
						{
							if(FeatureIconItem.isDisplayed()&& FeatureIconItem.isEnabled())
							{
								System.out.println("Feature present on Map");
								FeatureIconItem.click();
							}
						}
						Thread.sleep(500);
						String CoordinateValueMapUTM= driver.findElement(By.className("attrValue")).getText();
						Thread.sleep(200);
						driver.findElement(By.xpath("//div[@class='esriPopup esriPopupVisible']//div[@class='titleButton close']")).click();
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						HashMap<String, Integer> HM11 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
						String CordinatesValueFromDataRowUTM=driver.findElement(By.xpath("//table[@class='dgrid-row-table']/tr/td[2]")).getText();
						if(CordinatesValueFromDataRowUTM.equals(CoordinateValueMapUTM))
						{
							System.out.println("Cordinates Values UTM  are Validated");
						}
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						HashMap<String, Integer> HM12 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
						GeoshieldActions.Compare2HashMap(HM12, HM11);
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
						break;
				}
				
			}
			driver.quit();		
		}
	//Code for Bomb Threat Widget Tools Tab
	
		public static void OperationsBombThreat() throws Exception
			{
				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
				AppCommonUtility.SelectingDriver(Browser);
				Thread.sleep(1000);
				String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
				driver.get(AppUrl);
				
				driver.manage().window().maximize();				
				AppCommonUtility.LoadingIndicatorWait(driver, 30);
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				GeoshieldActions.ClickOnTool(driver,"Tools");
				GeoshieldActions.ClickOnIcon(driver, "Bomb Blast");
				
				String[] BombTypeType = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabBombThreat", "BombName");
				for (int i = 0; i < BombTypeType.length; i++)
				{
					switch(BombTypeType[i])
					{	
						case "Pipe Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "Suicide Vest Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "Briefcase Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "Sedan Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "SUV Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "Small Delivery Truck Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						case "Container Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
							
						case "Semi-Trailer Bomb":
							ToolsActions.BombThreat(driver,BombTypeType[i]);
							break;
						}
				}
				driver.quit();
			}
		
		//Code for Measure Widget Tools Tab
		
			public static void MeasureWidgetToolsTab(int i) throws Exception
				{

				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
					AppCommonUtility.SelectingDriver(Browser);
					Thread.sleep(1000);
					String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
					
					driver.get(AppUrl);	
					driver.manage().window().maximize();
					AppCommonUtility.LoadingIndicatorWait(driver, 30);
					GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
					GeoshieldActions.ClickOnTool(driver,"Tools");
					GeoshieldActions.ClickOnIcon(driver, "Measure");
					String[] SelectUnit = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabMeasureSelectUnit", "SelectUnit");
					String[] SelectDraw = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabMeasureSelectUnit", "SelectDrawTool");
					WebElement map= driver.findElement(By.id("mainContainer"));
					Actions act = new Actions(driver);
					WebElement ZoomOutIcon= driver.findElement(By.className("icon-plus"));
					WebElement ZoomOutIcon1= driver.findElement(By.className("icon-minus"));
					WebElement DownArrow=driver.findElement(By.className("panelUpArrowIcon"));
					GeoshieldActions.SelectingByLPNDropdown(driver, "measureUnit", SelectUnit[i]);
					switch(SelectDraw[i])
					{	
						case "Line":
							driver.findElement(By.className("measurePolylineSelected")).click();
							map.click();
							Thread.sleep(500);
							for (int j = 0; j < 4; j++) 
							{
								ZoomOutIcon.click();
								Thread.sleep(500);
							}
							map.click();
							act.doubleClick(map).build().perform();
							Thread.sleep(2000);
							GeoshieldActions.MinimizeIconClick(driver, "Measure");
							break;
						case "Polygon":
							driver.findElement(By.className("measurePolygon")).click();
							map.click();
							GeoshieldActions.MinimizeIconClick(driver, "Measure");
							Thread.sleep(1000);
							act.moveToElement(DownArrow, 100, 0).click().build().perform();
							ZoomOutIcon1.click();
							Thread.sleep(1000);
							ZoomOutIcon1.click();
							Thread.sleep(1000);
							act.click(map).doubleClick().build().perform();
							break;
						case "Freehand":
							driver.findElement(By.className("measureFreehand")).click();
							map.click();
							GeoshieldActions.MinimizeIconClick(driver, "Measure");
							Thread.sleep(1000);
							act.clickAndHold(map).moveToElement(ZoomOutIcon1).moveToElement(DownArrow, 100, 0).release(DownArrow).build().perform();
							break;
						
						
						}
					
					String DistanceValue= driver.findElement(By.id("MeasureDistance0_layer")).findElement(By.tagName("text")).getText();
					System.out.println(DistanceValue);
					if(DistanceValue.contains(SelectUnit[0]))
					{
						System.out.println("Units Are Validated");
					}
					
					GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
					GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
					List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
					AppValidation.Compare2StringsLists(BottomPanelLayerList, GraphicsLayerList);
					GeoshieldActions.ClearAllResults(driver);
					driver.quit();
				}
			
			public static void SelectionWidgetOperations() throws Exception
			{
				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
				AppCommonUtility.SelectingDriver(Browser);
				Thread.sleep(1000);
				String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
				driver.get(AppUrl);	
				driver.manage().window().maximize();
				AppCommonUtility.LoadingIndicatorWait(driver, 30);
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				if (AppCommonUtility.getXMLNodesValuesBoolean("MainPart1-ViolentCrimes")) 
				{
					GeoshieldActions.ToggleButtonMain(driver,"Part 1 - Violent Crimes");
				
				}
				GeoshieldActions.ClickOnTool(driver,"Tools");
				GeoshieldActions.ClickOnIcon(driver, "Selection");
				
				List<WebElement> DropDownLayerList=ToolsActions.DropDownSelection(driver, "selectBufferLayersDP");
				driver.findElement(By.xpath("//div[@id='widget_selectBufferLayersDP']/div[1]/input")).click();
				for (int i = 0; i < DropDownLayerList.size()-3; i++) 
				{ 
					List<WebElement> DropDownLayerList1=ToolsActions.DropDownSelection(driver, "selectBufferLayersDP");
					Thread.sleep(1500);
					
					DropDownLayerList1.get(i+2).click();
					
					String[] Distance = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabSelectionWidget", "Distance");
					String[] SelectUnitType = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabSelectionWidget", "UnitType");
					ToolsActions.EnterDataSelectionBufferTab(driver,"selectBufferRow","Distance",Distance[0]);
					GeoshieldActions.SelectingByLPNDropdown(driver, "selectBufferUnitsDP", SelectUnitType[0]);
					GeoshieldActions.SelectPoint(driver);
					GeoshieldActions.MinimizeIconClick(driver, "Selection");
					Thread.sleep(300);
					driver.findElement(By.id("mainContainer")).click();
					Thread.sleep(300);
					GeoshieldActions.MinimizeIconClick(driver, "Selection");
					Thread.sleep(1000);
					GeoshieldActions.OkBtnClickList(driver, "proximityButton");
					Thread.sleep(200);
					GeoshieldActions.LoadingIndicatorWait(driver, 15);
					Thread.sleep(1000);
					GeoshieldActions.MinimizeIconClick(driver,"Selection");
					GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
					Thread.sleep(1000);
					GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
					List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
					GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
					List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
					List<String> combined = new ArrayList<String>();
					combined.addAll(ActiveLayerList);
					combined.addAll(GraphicsLayerList);
					AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
					GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
					GeoshieldActions.ClearAllResults(driver);
					GeoshieldActions.MinimizeIconClick(driver,"Selection");
					GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
					GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
					Thread.sleep(1000);
					driver.findElement(By.className("icon-home")).click();
					Thread.sleep(4000);
				}
				GeoshieldActions.MinimizeIconClick(driver, "Selection");
				GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
				HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
				HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
				GeoshieldActions.Compare2HashMap(HM2, HM1);
				//GeoshieldActions.ClearAllResults(driver);
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
				GeoshieldActions.ClearAllResults(driver);
				GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				GeoshieldActions.MinimizeIconClick(driver, "Selection");
				GeoshieldActions.SwitchToDateTab(driver);
				String[] DistanceProximity = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabSelectionProximityWidget", "Distance");
				String[] SelectUnitTypeProximity = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabSelectionProximityWidget", "UnitType");
				
				List<WebElement> POLDropDownLayerListProximity=ToolsActions.DropDownSelection(driver, "proximitySourceObjectValue");
				
				driver.findElement(By.id("mainContainer")).click();
				Thread.sleep(1100);
				List<WebElement> NOLDropDownLayerListProximity=ToolsActions.DropDownSelection(driver, "proximityNearByObjectValue");
				driver.findElement(By.id("mainContainer")).click();
				Thread.sleep(1100);
				for (int i = 0; i < POLDropDownLayerListProximity.size()-3; i++) 
				{ 
					List<WebElement> POLDropDownLayerListProximity1=ToolsActions.DropDownSelection(driver, "proximitySourceObjectValue");
					
					Thread.sleep(1500);
					
					POLDropDownLayerListProximity1.get(i+2).click();
					
					for(int j = 0; j < NOLDropDownLayerListProximity.size()-3; j++){
						List<WebElement> NOLDropDownLayerListProximity1=ToolsActions.DropDownSelection(driver, "proximityNearByObjectValue");
						
						Thread.sleep(1500);
						
						NOLDropDownLayerListProximity1.get(j+2).click();
						ToolsActions.EnterDataSelectionBufferTab(driver,"proximityRowContainer","Distance",DistanceProximity[0]);
						GeoshieldActions.SelectingByLPNDropdown(driver, "proximityDistanceUnitValue", SelectUnitTypeProximity[0]);
						GeoshieldActions.OkBtnClickList(driver, "proximityButton");
						Thread.sleep(200);
						GeoshieldActions.LoadingIndicatorWait(driver, 10);
						Thread.sleep(1000);
						GeoshieldActions.MinimizeIconClick(driver,"Selection");
						GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
						Thread.sleep(1000);
						List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
						GeoshieldActions.ClickOnSelectLayerButton(driver);
						GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
						Thread.sleep(1000);
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
						List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
						List<String> combined = new ArrayList<String>();
						combined.addAll(ActiveLayerList);
						combined.addAll(GraphicsLayerList);
						AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
						GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
						GeoshieldActions.ClearAllResults(driver);
						GeoshieldActions.MinimizeIconClick(driver,"Selection");
						GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
						GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
						Thread.sleep(300);
						}
				}
				GeoshieldActions.MinimizeIconClick(driver, "Selection");
				GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
				HashMap<String, Integer> HM3 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
				HashMap<String, Integer> HM4 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
				GeoshieldActions.Compare2HashMap(HM4, HM3);
				GeoshieldActions.ClearAllResults(driver);	
				GeoshieldActions.CloseWiget(driver);
				driver.quit();
				
			}
			//Load From AGOL operations 
			public static void LoadFromAgolOperations() throws Exception
			{
				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
				AppCommonUtility.SelectingDriver(Browser);
				Thread.sleep(1000);
				String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
				driver.get(AppUrl);	
				driver.manage().window().maximize();
				AppCommonUtility.LoadingIndicatorWait(driver, 30);
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				GeoshieldActions.ClickOnTool(driver,"Tools");
				GeoshieldActions.ClickOnIcon(driver, "Load From AGOL/Portal");
				Thread.sleep(2000);
				ToolsActions.SwitchRadioLoadFromAgol(driver, "AGOL");
				driver.findElement(By.className("loadPortalButton")).click();
				Thread.sleep(1000);
				String[] UserNameAgol = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabLoadFromAGOL", "UsernameAGOL");
				String[] PasswordAgol = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabLoadFromAGOL", "PasswordAGOL");
				String[] SearchString = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabLoadFromAGOL", "SearchString");
				String[] WebMapName = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabLoadFromAGOL", "WebMapName");
				driver.findElement(By.className("esriIdUser")).findElement(By.className("dijitInputInner")).sendKeys(UserNameAgol[0]);
				driver.findElement(By.className("esriIdPwd")).findElement(By.className("dijitInputInner")).sendKeys(PasswordAgol[0]);
				driver.findElement(By.className("esriIdSubmit ")).click();
				AppCommonUtility.LoadingIndicatorWait(driver, 5);
				driver.findElement(By.className("searchLayer")).findElement(By.tagName("input")).sendKeys(SearchString[0]);
				ToolsActions.SearchIconClick(driver);
				AppCommonUtility.LoadingIndicatorWait(driver, 15);
				Thread.sleep(2000);
				ToolsActions.AddWebmapByName(driver, WebMapName[1]);
				AppCommonUtility.LoadingIndicatorWait(driver, 25);
				GeoshieldActions.MinimizeIconClick(driver, "Load From AGOL/Portal");
				GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
				Thread.sleep(1000);
				GeoshieldActions.ClickOnSelectLayerButton(driver);
				List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
				GeoshieldActions.ClickOnSelectLayerButton(driver);
				GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
				List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
				AppValidation.Compare2StringsLists(BottomPanelLayerList, ActiveLayerList);
				GeoshieldActions.ClearAllResults(driver);	
				GeoshieldActions.CloseWiget(driver);
				driver.quit();
			}
			
			//Publish to AGOL 
			public static void PublishToAgolOperations() throws Exception
			{
				String[] UserNameAgol = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabPublishToAGOL", "UsernameAGOL");
				String[] PasswordAgol = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabPublishToAGOL", "PasswordAGOL");
				String[] WebMapName = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabPublishToAGOL", "WebMapName");
				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
				AppCommonUtility.SelectingDriver(Browser);
				Thread.sleep(1000);
				String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
				driver.get(AppUrl);	
				driver.manage().window().maximize();
				AppCommonUtility.LoadingIndicatorWait(driver, 30);
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				GeoshieldActions.ClickOnTool(driver,"Tools");
				GeoshieldActions.ClickOnIcon(driver, "Publish To AGOL/Portal");
				Thread.sleep(2000);
				ToolsActions.SwitchRadioPublishToAgol(driver,"AGOL");
				ToolsActions.EnterDataPublishAgol(driver, "Username", UserNameAgol[0]);
				ToolsActions.EnterDataPublishAgol(driver, "Password", PasswordAgol[0]);
				ToolsActions.EnterDataPublishAgol(driver, "Webmap Title", WebMapName[0]);
				GeoshieldActions.OkBtnClickList(driver,"publishMapButton");
				AppCommonUtility.LoadingIndicatorWait(driver, 15);
				Thread.sleep(8000);
				 Set<String> handles = driver.getWindowHandles();
				 List<String> handlesList = new ArrayList<String> (handles);
				System.out.println(handles.size()+ handlesList.get(0));
				for (String window : handlesList) 
				{
		            driver.switchTo().window(window);
		            if (driver.getTitle().contains("ArcGIS - Sign In")) 
		            {
		            	driver.switchTo().frame(driver.findElement(By.id("oAuthFrame")));
		            	ToolsActions.EnterDataArcGisPage(driver, "user_username", UserNameAgol[0]);
		            	ToolsActions.EnterDataArcGisPage(driver, "user_password", PasswordAgol[0]);
		            	driver.findElement(By.className("buttons")).findElement(By.id("signIn")).click();
		            	Thread.sleep(8000);
		            	String WebMapNameAGOL= driver.findElement(By.id("webmap-title-text")).getText();
		            	if(WebMapNameAGOL.equals(WebMapName[0]))
		            	{
		            		System.out.println("WebMap is Sucessfully Published");
		            	}
		            	driver.close();
		            }
		            
			}driver.quit();
		}
			
			//Code for SIMPLE DRAW Widget Tools Tab
			
			public static void DrawWidgetToolsTab() throws Exception
				{

				String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
					AppCommonUtility.SelectingDriver(Browser);
					Thread.sleep(1000);
					String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
					driver.get(AppUrl);	
					driver.manage().window().maximize();
					AppCommonUtility.LoadingIndicatorWait(driver, 30);
					GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
					Thread.sleep(500);
					Actions act = new Actions(driver);
					WebElement map= driver.findElement(By.id("mainContainer"));
					WebElement ZoomOutIcon1= driver.findElement(By.className("icon-minus"));
					WebElement ZoomOutIcon= driver.findElement(By.className("icon-plus"));
					WebElement DownArrow=driver.findElement(By.className("panelUpArrowIcon"));
					Thread.sleep(1500);
					WebElement ArrowWidgetList=driver.findElement(By.className("leftSidePanelCollapseOpen"));
					String[] DrawOptionsList=GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsDrawOnMap", "List Draw Options");
					GeoshieldActions.ClickOnTool(driver,"Tools");
					GeoshieldActions.ClickOnIcon(driver, "Simple Draw");
					for (int i = 0; i < DrawOptionsList.length; i++)
					{
						switch(DrawOptionsList[i])
						{	
							case "POINT":
								driver.findElement(By.className("simpleDrawPoint")).click();
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								Thread.sleep(500);
								map.click();
								Thread.sleep(500);
								break;
							case "POLYLINE":
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("simpleDrawPolyline")).click();
								map.click();
								Thread.sleep(500);
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								act.moveToElement(ArrowWidgetList,10,50).click().doubleClick().build().perform();
								Thread.sleep(1000);
								break;
							case "POLYGON":
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("simpleDrawPolygon")).click();
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								map.click();
								Thread.sleep(500);
								act.moveToElement(DownArrow, 100, 0).click().build().perform();
								Thread.sleep(500);
								ZoomOutIcon.click();
								Thread.sleep(800);
								ZoomOutIcon.click();
								Thread.sleep(800);
								act.click(map).doubleClick().build().perform();
								Thread.sleep(500);
								break;
							case "RECTANGLE":
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("simpleDrawRectangle")).click();
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								Thread.sleep(500);
								map.click();
								Thread.sleep(500);
								act.clickAndHold(map).moveToElement(ZoomOutIcon, 100, 0).release(ZoomOutIcon).build().perform();
								break;
							case "CIRCLE":
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("simpleDrawCircle")).click();
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								map.click();
								Thread.sleep(1000);
								act.clickAndHold(map).moveToElement(DownArrow, 100, 0).release(DownArrow).build().perform();
								break;
							case "FREEHAND":
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("simpleDrawFreehand")).click();
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								driver.findElement(By.className("icon-home")).click();
								Thread.sleep(1000);
								GeoshieldActions.MinimizeIconClick(driver, "Simple Draw");
								act.clickAndHold(map).moveToElement(ZoomOutIcon1).moveToElement(DownArrow, 100, 0).moveToElement(ArrowWidgetList, 10, 0).release(ZoomOutIcon1).build().perform();
								break;
							
							}
					}
					GeoshieldActions.CloseWiget(driver);
					GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
					Thread.sleep(1000);
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
					GeoshieldActions.ClickOnSelectLayerButton(driver);
					GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
					GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
					List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
					AppValidation.Compare2StringsLists(BottomPanelLayerList, GraphicsLayerList);
					GeoshieldActions.ClearAllResults(driver);
				}
}	
