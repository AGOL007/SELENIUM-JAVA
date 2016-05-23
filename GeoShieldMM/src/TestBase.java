import org.testng.annotations.Test;

public class TestBase extends AppCommonUtility 
{
	@Test
	public static void MainGeoShield() throws Exception 
	{
		//Tools.OperationsMultipleQueryLayer(driver);
		// Getting Browser and AppUrl String from XML and Launching it
		//String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		//AppCommonUtility.SelectingDriver(Browser);
		//String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
		//driver.manage().window().maximize();
		//driver.get(AppUrl);
		/*Tools.OperationsMultipleQueryLayer();
		
		//Code for Search Attributes Widget Multiple DATA SETS 
		String[] SelectLayer= GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabSearchAttributes", "SelectLayer");
		for (int i = 0; i < SelectLayer.length; i++)
		{
			Tools.OperationsSearchAttributes(i);
		}
		
		//SEarch Address
		Tools.SearchAddressOperations();
		
		// Method for Show Address Widget TOOLS TAB
		String[] UTMZone = GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolsTabFindCordinateSystem", "UTMZone");
		for (int j = 0; j < UTMZone.length; j++)
		{
			Tools.OperationsFindCordinates(j);
			
		}
		
		//Method for BOMB THRAEAT Tools Tab3
		Tools.OperationsBombThreat();
		
		//Method for ShowCordinates Tools Tab
		Tools.ShowCordinatesOperations();
		
		//Method for Routing Tools Tab
		Tools.RoutingOperations();
		
		//Method for Google Street View
		Tools.GoogleStreetViewOperations();*/
		
		//Method for Measure Tool Tools Tab
		String[] SelectUnit=GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "ToolTabMeasureSelectUnit", "SelectUnit");
		for (int i = 0; i < SelectUnit.length; i++) {
			Tools.MeasureWidgetToolsTab(i);
		}
		
		
		// Method for Draw Widget Tools Tab
		Tools.DrawWidgetToolsTab();
		
		//Method for Selection Widget
	//	Tools.SelectionWidgetOperations();
		
		//Method for Load From Agol
	//	Tools.LoadFromAgolOperations();
		
		//Method for Publish to AGOL
		//Tools.PublishToAgolOperations();
		
		
		//Method for Workflows Tab
		//WorkFlows.WorkFLowHyperLinks();
		// Dynamic wait for Loading Indicator
		//AppCommonUtility.LoadingIndicatorWait(driver, 30);
		
		
		//AppCommonUtility.FindDynamicallyElements(driver, "panelLeftArrowIcon", "ClassName");
		
		
		// Opening Left Panel
		//GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");/*

		// Click On DateFilterPanel
	/*	String Date = AppCommonUtility.getXMLNodesValues("predefinedDate");

		// Open Date Panel
		GeoshieldActions.OpenDateFilterPanels();

		// Changing Predefined date and Click on OK Button
		GeoshieldActions.ChangingPredefinedDate(driver, Date);
		Thread.sleep(250);
		GeoshieldActions.OkButtonClick();

		// Open Date Panel and Switch to DateSelection Tab
		/*GeoshieldActions.OpenDateFilterPanels();
		GeoshieldActions.SwitchToDateTab(driver);

		// Opening DatePicker for From Tab
		String FromText = AppCommonUtility.getXMLNodesValues("OpenDatePickerFromText");
		GeoshieldActions.SelectDatePicker(driver, FromText);
		Thread.sleep(500);
		driver.findElement(By.xpath("//span[@id='dijit_form_DateTextBox_0_popup_mddb_label']/div[@class='dijitCalendarMonthLabel dijitCalendarCurrentMonthLabel']")).click();

		// Getting Month Values from XML and passing it to Application
		String FromMonth = AppCommonUtility.getXMLNodesValues("FromMonth");
		GeoshieldActions.SelectMonth(driver, FromMonth);
		Thread.sleep(500);
		driver.findElement(By.xpath("//table[@id='dijit_form_DateTextBox_0_popup']/tbody/tr/td/span[@class='dijitCalendarDateLabel'][contains(text(), '24')]")).click();

		// Opening DatePicker for To Tab
		String ToText = AppCommonUtility.getXMLNodesValues("OpenDatePickerToText");
		GeoshieldActions.SelectDatePicker(driver, ToText);
		Thread.sleep(200);
		driver.findElement(By.xpath("//span[@id='dijit_form_DateTextBox_1_popup_mddb_label']/div[@class='dijitCalendarMonthLabel dijitCalendarCurrentMonthLabel']")).click();
		String ToMonth = AppCommonUtility.getXMLNodesValues("ToMonth");
		GeoshieldActions.SelectMonth(driver, ToMonth);
		Thread.sleep(500);
		driver.findElement(By.xpath("//table[@id='dijit_form_DateTextBox_1_popup']/tbody/tr/td/span[@class='dijitCalendarDateLabel'][contains(text(), '27')]")).click();
		//AppCommonUtility.FindDynamicallyElements(driver, "//span[@class='customDialogCloseIcon']", "XPath");
		
		try{// Click on OK Button
		GeoshieldActions.OkButtonClick();
		}
		catch(Exception e){driver.findElement(By.className("okBtn")).click();}
		
		// Expanding Layer
		GeoshieldActions.EventsExpandingOnCategoryTitle(driver,"Part 1 - Non Violent Crimes");

		// Toggling OnOff Main Category Option
		if (AppCommonUtility.getXMLNodesValuesBoolean("MainPart1-NonViolentCrimes")) 
		{
			GeoshieldActions.ToggleButtonMain(driver,"Part 1 - Non Violent Crimes");
			
		}

		if (AppCommonUtility.getXMLNodesValuesBoolean("SubCategoryBurglary")&& AppCommonUtility.getXMLNodesValuesBoolean("SubCategoryLarcenyTheftOther")&& AppCommonUtility.getXMLNodesValuesBoolean("SubCategoryArson")) 
		{
			GeoshieldActions.ToggleButtonSubCategory(driver, AppCommonUtility.getXMLNodesValues("SubCategoryBurglaryLayerName"));
			
			//GeoshieldActions.ToggleButtonSubCategory(driver,AppCommonUtility.getXMLNodesValues("SubCategoryLarcenyTheftLayerName"));
			
			//GeoshieldActions.ToggleButtonSubCategory(driver, "Arson");
			
		}
		
		//Validate features present for particular layer
		GeoshieldActions.ValidateFeaturesPresent();
		
		// Expanding Layer
		GeoshieldActions.EventsExpandingOnCategoryTitle(driver, "Part 2");

		// Toggling OnOff Main Category Option
		 GeoshieldActions.ToggleButtonMain(driver,"Part 2");

		// Expanding Layer
		GeoshieldActions.EventsExpandingOnCategoryTitle(driver, "Priority");

		// Toggling OnOff Main Category Option
		// GeoshieldActions.ToggleButtonMain(driver,"Priority");

		// Expanding Layer
		GeoshieldActions.EventsExpandingOnCategoryTitle(driver, "Persons");

		// Toggling OnOff Main Category Option
		// GeoshieldActions.ToggleButtonMain(driver,"Persons");

		// Expanding Layer
		GeoshieldActions.EventsExpandingOnCategoryTitle(driver, "Misc");

		// Toggling OnOff Main Category Options
		// GeoshieldActions.ToggleButtonMain(driver,"Misc");

		// Getting values for LayerList from LeftPanel
		List<String> LeftPanelLayerList = AppValidation.GetLayerListFromLeftPanel(driver);

		// Opening Down Panel
		GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");

		// Opening SelectLayer Dropdown from Down Panel
		GeoshieldActions.ClickOnSelectLayerButton(driver);

		// Getting values for LayerList from BottomPanel
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);

		// Closing SelectLayer Dropdown from Down Panel
		GeoshieldActions.ClickOnSelectLayerButton(driver);

		// Validating values for LayerList from Left to Down Panel
		AppValidation.Compare2StringsLists(LeftPanelLayerList,BottomPanelLayerList);

		// Opening Right Panel
		GeoshieldActions.OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		// Switching to Active data panel
		GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");

		// Getting values for LayerList from RightPanel
		List<String> RightPanelLayerList = AppValidation.GetLayerListFromRightPanel(driver);

		// Validating Values for LayerList from Leftpanel to Rightpanel
		AppValidation.Compare2StringsLists(LeftPanelLayerList,RightPanelLayerList);

		// Getting Individual Layernames and Count Together Bottom Panel
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);

		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameforeachLayers(driver);

		//Compare2 HashMaps Values and comparing them together
		GeoshieldActions.Compare2HashMap(HM1, HM2);
		
		// Clearing All results when Functionality of one panel is already performed
		//GeoshieldActions.ClearAllResults(driver);
		GeoshieldActions.ValidateFeaturesPresent();
		*/
		//GeoshieldActions.ClickOnTool(driver,"Live Feeds");

		//String[] TestList =GeoshieldActions.readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "Twitter","Refresh Time");
		//System.out.println(TestList[0]);
		//GeoshieldActions.ClickOnIcon(driver, "Twitter");
		//Thread.sleep(1000);
		//GeoshieldActions.DataFillTwitterPanel(driver);
		//GeoshieldActions.CloseWiget(driver);
		//GeoshieldActions.ClickOnIcon(driver, "YouTube");
		//GeoshieldActions.DataFillYouTubePanel(driver);
		//GeoshieldActions.CloseWiget(driver);
		//GeoshieldActions.ClickOnIcon(driver, "Flickr");
		//GeoshieldActions.DataFillFlickrPanel(driver);
		//GeoshieldActions.CloseWiget(driver);
		
		/*
		GeoshieldActions.ClickOnIcon(driver, "Social Media Search");
		GeoshieldActions.DataFillSocialMediaPanel(driver);
		GeoshieldActions.CloseWiget(driver);
		GeoshieldActions.ClickOnIcon(driver, "Current Calls For Service");
		GeoshieldActions.DataFillAgencyFeedsCurrentCalls(driver);
		GeoshieldActions.CloseWiget(driver);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		
		GeoshieldActions.ClickOnIcon(driver, "AVL");
		GeoshieldActions.DataFillAgencyFeedsAVL(driver);
		Thread.sleep(1500);
		GeoshieldActions.CloseWiget(driver);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnIcon(driver, "License Plate Reader");
		GeoshieldActions.FillDataLPR(driver);
		GeoshieldActions.CloseWiget(driver);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnIcon(driver, "CameraTool");
		GeoshieldActions.ValidateAgencyFeedsCameraTool(driver);
		GeoshieldActions.CloseWiget(driver);*/
		
		//GeoshieldActions.ClickOnTool(driver,"Tools");
		//GeoshieldActions.ClickOnIcon(driver, "Multiple Layer Search");
		//ToolsActions.CheckBoxUsingLayerNames(driver);
		
		//GeoshieldActions.ClickOnTool("Workflows");
}
}
