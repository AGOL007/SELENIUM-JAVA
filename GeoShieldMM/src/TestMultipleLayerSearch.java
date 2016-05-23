import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class TestMultipleLayerSearch  extends AppCommonUtility{
	@Test
	public static void OperationsMultipleQueryLayer() throws Exception
	{	
		InitialiseBrowser();
		
		OpenEventsLayer();
		
		SwitchtoTabCLickWidget();
		
		MinimizeWidgetAndOpenRightPanel();
		
		List<String> CombinedAllLayers= CheckBoxLayersAndGetLayerNames();
		
		List<String> ActiveLayersList= GetLayerNamesRightPanel();
		
		ValidatingLayersListRightPanel(ActiveLayersList, CombinedAllLayers);
		
		List<String> BottomPanelLayerList= GettingLayerListBottomPanel();
		
		List<String> CombinedAllRightPanelLayers= GetLayerNamesRightPanel();
		
		ValidatingLayersListBottomPanelAndRightPanel(BottomPanelLayerList, CombinedAllRightPanelLayers);
		
		HashMap<String, Integer> HM1 = GetLayerNameAndCountTogetherBottomPanel();
		
		HashMap<String, Integer> HM2 = GetLayerNameAndCountTogetherActiveDataPanel();
		
		ValidatingLayersNameAndCountTogether(HM2, HM1);
		
		ClearAllLayers();
		
		CloseWidgetAndBrowser();
	}
	
	public static void OpenEventsLayer() throws Exception 
	{
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		if (AppCommonUtility.getXMLNodesValuesBoolean("MainPart1-ViolentCrimes")) 
		{
			GeoshieldActions.ToggleButtonMain(driver,"Part 1 - Violent Crimes");
	
		}
	}
	public static void SwitchtoTabCLickWidget() throws Exception 
	{
		GeoshieldActions.ClickOnTool(driver,"Tools");
		
		GeoshieldActions.ClickOnIcon(driver, "Multiple Layer Search");
	}
		
	public static void MinimizeWidgetAndOpenRightPanel() 
	{
		GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
		
		List<WebElement> PanelRight=driver.findElements(By.className("panelRightArrowIcon "));
		PanelRight.get(1).click();
		GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
	}
	
	public static List<String> CheckBoxLayersAndGetLayerNames() throws Exception 
	{
		ToolsActions.CheckBoxUsingLayerNames(driver);
		List<String> SelectLayer=ToolsActions.GetAllLayersNameSelectLayerTab(driver);
	
		GeoshieldActions.SwitchToDateTab(driver);
		List<String> ExportTab=ToolsActions.GetAllLayersNameExportTab(driver);
	
		List<String> CombinedAllLayers = new ArrayList<String>();
		CombinedAllLayers.addAll(ExportTab);
		CombinedAllLayers.addAll(SelectLayer);
		return CombinedAllLayers;
	}
	
	public static List<String> GetLayerNamesRightPanel() throws Exception
	{
		GeoshieldActions.MinimizeIconClick(driver, "MultiLayer Query");
		
		GeoshieldActions.OpeningPanels(driver,"panelRightArrowIcon");
		
		GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
		List<String> ActiveLayersList=AppValidation.GetActiveTabLayersNamesList(driver);
		return ActiveLayersList;
	}
		
		
		//Validating widget layers List with Right Panel Active Data Tab
	public static void ValidatingLayersListRightPanel(List<String> ActiveLayersList, List<String> CombinedAllLayers)
	{
		AppValidation.Compare2StringsLists(ActiveLayersList, CombinedAllLayers);
	}
		
	public static List<String> GettingLayerListBottomPanel() throws Exception
	{
		GeoshieldActions.OpeningPanels(driver, "panelUpArrowIcon");
		GeoshieldActions.ClickOnSelectLayerButton(driver);
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
		GeoshieldActions.ClickOnSelectLayerButton(driver);
		return BottomPanelLayerList;
	}
	public static List<String> GettingLayerListRightPanel() throws Exception
	{
		List<String> ActiveLayersList=AppValidation.GetActiveTabLayersNamesList(driver);
		GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
		List<String> GraphicsLayersList=AppValidation.GetGraphicsTabLayersList(driver);
		List<String> CombinedAllRightPanelLayers = new ArrayList<String>();
		CombinedAllRightPanelLayers.addAll(ActiveLayersList);
		CombinedAllRightPanelLayers.addAll(GraphicsLayersList);
		return CombinedAllRightPanelLayers;
		
	}
		//Validating bottom and RightPanel all layers
	public static void ValidatingLayersListBottomPanelAndRightPanel(List<String> CombinedAllRightPanelLayers, List<String> BottomPanelLayerList)
	{
		AppValidation.Compare2StringsLists(CombinedAllRightPanelLayers, BottomPanelLayerList);
	}
		
		
		public static HashMap<String, Integer> GetLayerNameAndCountTogetherBottomPanel() throws Exception
		{
			HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			return HM1;
		}
		
		public static  HashMap<String, Integer> GetLayerNameAndCountTogetherActiveDataPanel() throws Exception
		{
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Active Data");
			
			HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			return HM2;
		}
		
		
		//validating Bottom Layer Contents and Active layer contents
		public static void ValidatingLayersNameAndCountTogether(HashMap<String, Integer> HM2 , HashMap<String, Integer> HM1 )
		{
			GeoshieldActions.Compare2HashMap(HM2, HM1);
		}
		
		//Clear All Results ActiveData Panel and Graphics Panel
		public static void ClearAllLayers() throws Exception
		{
			GeoshieldActions.ClearAllResults(driver);
			GeoshieldActions.SwitchTabsInRightPanel(driver, "Graphics");
			GeoshieldActions.ClearAllResults(driver);
		}
		
		//Close Widget and Browser
		public static void CloseWidgetAndBrowser() throws Exception
		{
			GeoshieldActions.OpeningPanels(driver, "panelDownArrowIcon");
			GeoshieldActions.CloseWiget(driver);
			driver.quit();
		}
	}
