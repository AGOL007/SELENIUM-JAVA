import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ToolsActions extends AppCommonUtility
{
	//Close InfoPopup in case open
	public static void CloseInfopopup(WebDriver driver) throws Exception
	{
		List<WebElement> CloseIconList=driver.findElements(By.xpath("//div[@title='Close']"));
		for (WebElement CloseIconItem : CloseIconList) 
		{
			ClickOnEnabledDisplayedItem(CloseIconItem);
		}
	}
	//Getting a List of LayerNames from Mutilple Query Select Layer tab
	public static List<String> GetAllLayersNameSelectLayerTab(WebDriver driver)
	{
		ArrayList<String> LayerNamesListSelectLayer=new ArrayList<String>();
		List<WebElement> LayerNames=driver.findElements(By.xpath("//div[@class='mlqLayerListContainer']/div/div/div[@class='mlqLayerItemLabel']"));
		for (WebElement LayerNamesItem : LayerNames) 
		{
			System.out.println(LayerNamesItem.getText());
			if(!LayerNamesItem.getText().isEmpty())
			{
				String LayerNamesItemText=LayerNamesItem.getText().toString();
				String LayerNamesItemTextTrim=LayerNamesItemText.replace(" ", "");
				LayerNamesListSelectLayer.add(LayerNamesItemTextTrim);
			}
		}
			
		
		return LayerNamesListSelectLayer;
	}
	
	//Getting a List of LayerNames from Mutilple Query Export tab
		public static List<String> GetAllLayersNameExportTab(WebDriver driver)
		{
			ArrayList<String> LayerNamesListExport=new ArrayList<String>();
			List<WebElement> LayerNamesExport=driver.findElements(By.xpath("//div[@class='mlqLayerListContainer']/div/div/div[@class='mlqPDFLayerItemLabel']"));
			for (int i = 0; i < LayerNamesExport.size(); i++) 
			{
				System.out.println(LayerNamesExport.get(i).getText());
				String LayerNamesItemText=LayerNamesExport.get(i).getText().toString();
				String LayerNamesItemTextTrim=LayerNamesItemText.replace(" ", "");
				LayerNamesListExport.add(LayerNamesItemTextTrim);
				
			}
			return LayerNamesListExport;
		}
	
	//Checking and Unchecking entire list of Layers
	public static void CheckBoxUsingLayerNames(WebDriver driver) throws Exception
	{
		
		WebElement ListContainerMain=driver.findElement(By.className("mlqLayerListContainer"));
		List<WebElement> CheckBox=ListContainerMain.findElements(By.className("mlqLayerItemCheckBox"));
		for (WebElement CheckBoxItem : CheckBox) 
		{
			if(CheckBoxItem.isDisplayed()&& CheckBoxItem.isEnabled())
			{
				String CheckBoxID=CheckBoxItem.getAttribute("id");
				String CheckBoxIdTrim= CheckBoxID.substring(12);
				String CheckBoxTrimtext= CheckBoxIdTrim.replace("_", " ");
				CheckBoxItem.click();
				Thread.sleep(1000);
				System.out.println(CheckBoxID);
				System.out.println(CheckBoxTrimtext);
				List<WebElement> SVGList = driver.findElements(By.tagName("svg"));
				for (WebElement SVGListItem : SVGList) 
				{
					List<WebElement> GList = SVGListItem.findElements(By.tagName("g"));
					
					for (WebElement GListItem : GList)
					{	
						String AttributeIdItem = GListItem.getAttribute("id");
						String AttributeIdItemTrim=AttributeIdItem.replace("_"," ");
						System.out.println(AttributeIdItemTrim);
						System.out.println(AttributeIdItem);
						if(AttributeIdItemTrim.contains(CheckBoxTrimtext))
						{
							GeoshieldActions.SelectPointCircle(driver);
							Thread.sleep(500);
							GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
							//driver.findElement(By.className("panelRightArrowIcon")).click();
							Thread.sleep(2500);
							List<WebElement> ImageItemList = GListItem.findElements(By.tagName("image"));
							int ImageCount = ImageItemList.size();
							if (ImageCount > 0)
							{
								ImageItemList.get(0).click();
								
								}
								Thread.sleep(1000);
								CloseInfopopup(driver);
								GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
								GeoshieldActions.OkBtnClickList(driver, "proximityButton");
								AppCommonUtility.LoadingIndicatorWait(driver, 30);
								try
								{
									List<WebElement> AlertOkButtonList= driver.findElements(By.className("alertOkButton"));
									for (WebElement  AlertOkButtonItem: AlertOkButtonList) 
									{
										ClickOnEnabledDisplayedItem(AlertOkButtonItem);
										Thread.sleep(500);
									}
								}catch(Exception e)
								{
									
								}
								CheckBoxItem.click();
								Thread.sleep(1000);
								break;
								}
						
					}
					break;
				}
				
				
				
			}
			
			
		}
		
	}
	
		//Search attribute widgets to send data on label bases
		public static void DataSendTextboxUsinglabelName(WebDriver driver, String LabelName, String ExcelData)
		{
			List<WebElement> AttributeList=driver.findElements(By.className("attributeQueryRow"));
			for (WebElement AttributeListItem : AttributeList) 
			{ 
				if(AttributeListItem.isDisplayed()&& AttributeListItem.isEnabled())
				{
					WebElement LabelItem= AttributeListItem.findElement(By.className("attributeQueryCellLabel"));
					String LabelText=LabelItem.getText().toString();
					if(LabelText.equals(LabelName))
					{
						WebElement textBoxItem=AttributeListItem.findElement(By.className("dijitInputInner"));
						textBoxItem.clear();
						textBoxItem.sendKeys(ExcelData);
						textBoxItem.sendKeys(Keys.ENTER);
						break;
					}
			
				}
			
			}
		}
		
		//Switch Radio Buttons for Search Attributes
		public static void SwitchRadio(WebDriver driver, String ExcelData) throws Exception
		{
			List<WebElement> LogicalOperatorDiv= driver.findElements(By.className("logicalOperatorsDiv"));
			for (WebElement LogicalOperatorDivItem : LogicalOperatorDiv)
			{	
				if(LogicalOperatorDivItem.isDisplayed())
				{
				List<WebElement> RadiocontentsList= LogicalOperatorDivItem.findElements(By.tagName("div"));
				int flag=0;
					for(WebElement RadionItem : RadiocontentsList) 
					{
						flag++;
						if (RadionItem.getText().equals(ExcelData))
						{
							
							System.out.println(flag-2);
							AppCommonUtility.ClickOnEnabledDisplayedItem(RadiocontentsList.get(flag-2));
							break;
						}
					}
				}
			}
		}
		
		//Radio Button Switch for Show Cordinates
		public static void SwitchRadioShowCordinates(WebDriver driver, String ExcelData) throws Exception
		{
			List<WebElement> ShowCodinatesPlaceHolderList= driver.findElements(By.className("showCordsItemPlaceHolder"));
			for (WebElement ShowCodinatesPlaceHolderItem : ShowCodinatesPlaceHolderList) 
			{
				WebElement showCordLabel= ShowCodinatesPlaceHolderItem.findElement(By.className("showCordLabel"));
				if(showCordLabel.getText().equals(ExcelData))
				{
					WebElement showCordCheckbox= ShowCodinatesPlaceHolderItem.findElement(By.className("showCordCheckboxContainer"));
					GeoshieldActions.ClickOnEnabledDisplayedItem(showCordCheckbox);
					break;
					
				}
			}
		}
		
		
		//Radio Button Switch for FIND CordinatesUTM
				public static void SwitchRadioFindCordinatesUTM(WebDriver driver, String ExcelData) throws Exception
				{
					List<WebElement> MainContainerList = driver.findElements(By.className("findCoordinatesContainer"));
					for (WebElement MainContainer : MainContainerList) 
					{
						if(MainContainer.isDisplayed() && MainContainer.isEnabled())
						{
					
						List<WebElement> ElementList=MainContainer.findElements(By.xpath("//div[@class='findCoordinatesUTMRadioButtonLabels']"));
						for (WebElement ElementListItem : ElementList) 
						{	List<WebElement> RadioButtonsList= MainContainer.findElements(By.className("findCoordinatesUTMRadioButtons"));
							Thread.sleep(1000);
							if( ElementListItem.getText().equals(ExcelData)&&  ElementListItem.getText().equals("N") )
								{
									RadioButtonsList.get(0).click();
									break;
								}
							if(ElementListItem.getText().equals(ExcelData)&&  ElementListItem.getText().equals("S"))
								{RadioButtonsList.get(1).click();
								break;}
							
							
						
				}}}}
			// Enter Address in Routing Widget 
	public static void EnterDataInRoutingWidget(WebDriver driver,String TabText, String ExcelData)
	{
		List<WebElement> StopsList=driver.findElements(By.className("dojoDndItem"));
		for (WebElement StopsItem : StopsList) 
		{
			WebElement StopDiv= StopsItem.findElement(By.className("esriStopIconColumn"));
			if(StopDiv.getText().equals(TabText))
			{
				WebElement StopTextBox= StopsItem.findElement(By.className("directionsInputs"));
				StopTextBox.sendKeys(ExcelData);
				StopTextBox.sendKeys(Keys.ENTER);
				break;
			}
		}
		
	}
	
	// Get Directions and Clear Button Click
	public static void ClickOnButton(WebDriver driver,String TabText)
	{
		WebElement ButtonsDiv =driver.findElement(By.className("esriStopsGetDirectionsContainer"));
		List<WebElement> ButtonDivList= ButtonsDiv.findElements(By.tagName("div"));
		for (WebElement ButtonDivListItem : ButtonDivList) 
		{
			if(ButtonDivListItem.getText().equals(TabText))
			{
				ButtonDivListItem.click();
			}
		}
	}
	
	//Find Cordinates Widget DD EnterText
	public static void EnterDataDDFindCordinates(WebDriver driver, String Paneltext, String ExcelData)
	{
		List<WebElement> MainContainerList = driver.findElements(By.className("findCoordinatesContainer"));
		for (WebElement MainContainer : MainContainerList) 
		{
			if(MainContainer.isDisplayed() && MainContainer.isEnabled())
			{
		
			List<WebElement> ElementList=MainContainer.findElements(By.className("findCoordinatesElements"));
			for (WebElement ElementListItem : ElementList) 
			{	int flag=0;
				List<WebElement> ElementsItem= ElementListItem.findElements(By.className("findCoordinatesElementContents"));
				for (WebElement ElementsItemTextBox : ElementsItem) 
					
				{	flag++;
				System.out.println(ElementsItemTextBox.getText().toString());
					if(ElementsItemTextBox.isDisplayed() &&ElementsItemTextBox.getText().equals(Paneltext))
					{
						WebElement TextBox=ElementsItem.get(flag).findElement(By.className("dijitInputContainer")).findElement(By.className("dijitInputInner"));
						TextBox.sendKeys(ExcelData);
						break;
					}
					
				}
			}
		}
	}
	}
	
	//Common Switch method for BombWidget
	
	public static void BombThreat(WebDriver driver, String ExcelData) throws Exception
	{
		List<WebElement> BombList= driver.findElements(By.className("bombThreatIconElement"));
		for (WebElement BombListItem : BombList) 
		{
			if(BombListItem.getAttribute("title").equals(ExcelData))
			{
				BombListItem.click();
				GeoshieldActions.MinimizeIconClick(driver, "Bomb Threat");
				driver.findElement(By.id("mainContainer")).click();
				Thread.sleep(2500);
				GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
				HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
				GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
				HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"graphic");
				GeoshieldActions.Compare2HashMap(HM2, HM1);
				GeoshieldActions.ClearAllResults(driver);
				
				GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
				GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
				GeoshieldActions.MinimizeIconClick(driver, "Find Coordinates");
				GeoshieldActions.MinimizeIconClick(driver, "Bomb Threat");
				break;
			}
		}
		
	}
	
	//Code for DropDown List LayerNames Selection Widget
	public static List<WebElement> DropDownSelection(WebDriver driver, String panelname) throws Exception
	{
			WebElement element= driver.findElement(By.id("widget_"+panelname));
			element.findElement(By.className("dijitArrowButtonInner")).click();
			Thread.sleep(800);
			WebElement Divlist=driver.findElement(By.id("widget_"+panelname+"_dropdown"));
			WebElement divlist= Divlist.findElement(By.id(panelname+"_popup"));
			Thread.sleep(200);
			List<WebElement> DivTagList= divlist.findElements(By.tagName("div"));
			
			return DivTagList;
			
			
	}
	//Code for Entering Text in Distance in Selection Widget for BUFFER and Proximity Tab textbox
	public static void EnterDataSelectionBufferTab(WebDriver driver,String Classname, String Paneltext, String ExcelData)
	{
		List<WebElement> selectBufferRowList= driver.findElements(By.className(Classname));
		for (WebElement BufferRowListItem : selectBufferRowList) 
		{
			if(BufferRowListItem.getText().equals(Paneltext))
			{WebElement InputDiv= BufferRowListItem.findElement(By.className("dijitInputInner"));
			InputDiv.clear();
			InputDiv.sendKeys(ExcelData);
				
			}
		}
	}
	//Radio Button Switch for LOAD From AGOL
			public static void SwitchRadioLoadFromAgol(WebDriver driver, String ExcelData) throws Exception
			{
				WebElement MainDiv= driver.findElement(By.className("avlTabContent"));
				List<WebElement> DivMainList= MainDiv.findElements(By.tagName("div"));
				int flag=0;
				for (WebElement DivMainListItem : DivMainList) 
				{flag++;
					
					if(DivMainListItem.getText().equals(ExcelData))
					{
						DivMainList.get(flag-1).click();
						break;
						
					}
				}
			}
			//Radio Button Switch for PUBLISH To AGOL
			public static void SwitchRadioPublishToAgol(WebDriver driver, String ExcelData) throws Exception
			{
				WebElement MainDiv= driver.findElement(By.className("webMapRow"));
				List<WebElement> DivMainList= MainDiv.findElements(By.tagName("div"));
				int flag=0;
				for (WebElement DivMainListItem : DivMainList) 
				{flag++;
					
					if(DivMainListItem.getText().equals(ExcelData))
					{
						DivMainList.get(flag-1).click();
						break;
						
					}
				}
			}
		//Click on SearchIcon for Content/ArcGIS tab LoadFromAgol widget
			public static void SearchIconClick(WebDriver driver) throws Exception
			{
				
				List<WebElement> SearchIconList= driver.findElements(By.className("searchAgolImage"));
				for (WebElement SearchIconListItem : SearchIconList) {
					if(SearchIconListItem.isDisplayed())
					{
						SearchIconListItem.click();	
					}
					
				}
			}
			
			//Click on SearchIcon for Content/ArcGIS tab LoadFromAgol widget
			public static void AddWebmapByName(WebDriver driver,String ExcelData) throws Exception
			{
				
				List<WebElement> SearchIconList= driver.findElements(By.className("singleContentContainer"));
				for (WebElement SearchIconListItem : SearchIconList) 
				{
					List<WebElement> WebMapName=SearchIconListItem.findElements(By.className("loadAGOLPortalContentData"));
					for (WebElement WebMapNameitem : WebMapName) {
						if(WebMapNameitem.getText().equals(ExcelData))
						{
							SearchIconListItem.findElement(By.className("addDiv")).findElement(By.tagName("span")).click();	
						}
						
					}
					
					
				}
			}
			
			//Enter Credentials in PUBLISHToAgol widget
			public static void EnterDataPublishAgol(WebDriver driver,String PanelName,String ExcelData) throws Exception
			{
				List<WebElement> DivRowList= driver.findElements(By.className("webMapRow"));
				Thread.sleep(1000);
				for (int i = 1; i < DivRowList.size(); i++) 
				{
					WebElement LabelName= DivRowList.get(i).findElement(By.className("publishColLabel"));
					if(LabelName.getText().equals(PanelName))
					{
						
						DivRowList.get(i).findElement(By.className("dijitInputInner")).sendKeys(ExcelData);
						break;
					}
				}	
				}
				
			//Enter data in ArcGIS page
			public static void EnterDataArcGisPage(WebDriver driver,String ID,String ExcelData) throws Exception
			{		
			List<WebElement> UsernameList= driver.findElements(By.id(ID));
        	for (WebElement UsernameListItem : UsernameList) {
        		if(UsernameListItem.isDisplayed())
        		{
        			UsernameListItem.sendKeys(ExcelData);
        		}
			}
			}
}

	
	
	
