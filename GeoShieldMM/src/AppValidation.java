import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppValidation {
	// Getting layernamea from ON OFF Toogle button Left Panel
	public static List<String> GetLayerListFromLeftPanel(WebDriver driver) {
		List<WebElement> ToggleBtnList = driver.findElements(By
				.className("toggleOn"));
		List<String> LayerNameListLeftPanel = new ArrayList<String>();
		for (WebElement ItemToggleOn : ToggleBtnList) {
			String IdItem = ItemToggleOn.getAttribute("id");
			String IdItemReplace = IdItem.replace("_", " ");
			String IdItemReplaceSpaces = IdItemReplace.replace(" ", "");
			LayerNameListLeftPanel.add(IdItemReplaceSpaces);
		}
		return LayerNameListLeftPanel;
	}

	// Getting layername from Layer List Data Panel Bottom
	public static List<String> GetLayerListFromBottomPanel(WebDriver driver)
			throws Exception {
		WebElement LayerListMain = driver.findElement(By
				.className("layerDataContainer"));
		List<String> LayerNameListDownPanel = new ArrayList<String>();
		List<WebElement> layerList = LayerListMain.findElements(By
				.className("layerContentRow"));
		for (WebElement LayerListItem : layerList) {
			String LayerListName = LayerListItem.getText();
			String LayerListNameReplaceSpace = LayerListName.replace(" ", "");
			LayerNameListDownPanel.add(LayerListNameReplaceSpace);

		}

		return LayerNameListDownPanel;

	}

	// Getting LayerList from RightPanel Active Data TOC tab
	public static List<String> GetLayerListFromRightPanel(WebDriver driver)
			throws Exception {
		List<String> LayerNameListRightPanel = new ArrayList<String>();
		List<WebElement> layerList = driver.findElements(By
				.className("tocLayerName"));
		for (WebElement LayerListItem : layerList) {
			String LayerListName = LayerListItem.getText();
			String LayerListNameReplaced = LayerListName.replace(" ", "");
			LayerNameListRightPanel.add(LayerListNameReplaced);

		}
		return LayerNameListRightPanel;

	}

	// Getting LayerNames with Feature count from BottomPanel for each layer
	public static HashMap<String, Integer> GetIndividualLayerNameAndCountBottomPanel(WebDriver driver) throws Exception
			{
				GeoshieldActions.ClickOnSelectLayerButton(driver);
	  			WebElement LayerListMain= driver.findElement(By.className("layerDataContainer"));
	  			HashMap<String, Integer> hm = new HashMap<String, Integer>();
	  			List <WebElement> layerList= LayerListMain.findElements(By.className("layerContentRow"));
	  			for (int i = 0; i < layerList.size(); i++) 
	  			{
					String y=layerList.get(i).getText();
					String Z2=y.replace(" ", "");
					layerList.get(i).click();
					Thread.sleep(1000);
					try{
					WebElement FeatureCountDataPanel=driver.findElement(By.className("gridTotalResultsPanel"));
	  				String x=FeatureCountDataPanel.getText();
	  				Integer Z1=Integer.parseInt(x.replace(" result(s)", ""));
	  				hm.put(Z2, Z1);}
					catch(Exception e)
					{hm.put(Z2,null);}
	  				GeoshieldActions.ClickOnSelectLayerButton(driver);
				}
	  			System.out.println(hm);
	  			return hm;	
				
			}

	// Getting Features Count value from Active data Panel
	public static HashMap<String, Integer> GetfeatureCountAndNameforeachLayers(
			WebDriver driver) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		List<WebElement> NumberList = driver
				.findElements(By
						.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"));
		for (WebElement LayerItem : NumberList) {
			String layernam = LayerItem.getAttribute("id");
			String layernam1 = layernam.replace("|graphicCount", "");
			String layernam2 = layernam1.replace("_", " ");
			String layernam3 = layernam2.replace(" ", "");
			Integer numbervalue = Integer.parseInt(LayerItem.getText());
			hm.put(layernam3, numbervalue);

		}


		return hm;

	}
	
	// Getting Features Count value from Active data Panel individually List 
		public static HashMap<String,Integer> GetfeatureCountAndNameLayers(WebDriver driver,String panelname) 
		{
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			List<String> LayernameList= new ArrayList<String>();
			List<Integer> FeatureCount= new ArrayList<Integer>();
			
			List<WebElement> NumberList = driver.findElements(By.xpath("//div[@id='"+panelname+"']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"));
			
			for (WebElement LayerItem : NumberList) 
			{
				Integer numbervalue = Integer.parseInt(LayerItem.getText());
				FeatureCount.add(numbervalue);
			}
			List<WebElement> ListGraphicsLayer = driver.findElements(By.xpath("//div[@id='"+panelname+"']/div/div/div/div/div[2]/div[1]"));
			for (WebElement LayerItemNameList : ListGraphicsLayer) 
			{	
				String layertext= LayerItemNameList.getText();
				String layernamewithoutSpace = layertext.replace(" ", "");
				LayernameList.add(layernamewithoutSpace);
			}
			
			
//			change code to below one so as to get the desired hashmap
		for(int i=0;i<LayernameList.size();i++)
		{
			hm.put(LayernameList.get(i), FeatureCount.get(i));
		}
		
			return hm;

		}
		
	//Getting Feature layer names List from RightPanel Graphics Layer
	@SuppressWarnings("null")
	public static List<String>GetGraphicsTabLayersList(WebDriver driver)
	{	
		List<String> GraphicsTabLayerName = new ArrayList<String>();
		List<WebElement> ListGraphicsLayer = driver.findElements(By.xpath("//div[@id='graphicLayers']/div/div/div/div/div[2]/div[1]"));
		for (WebElement LayerItem : ListGraphicsLayer) 
		{	
			String layertext= LayerItem.getText();
			String layernamewithoutSpace = layertext.replace(" ", "");
			GraphicsTabLayerName.add(layernamewithoutSpace);
		}
		return GraphicsTabLayerName;
	}
	
	
	//Getting Feature layer names List from RightPanel Active Data Layer
		@SuppressWarnings("null")
		public static List<String>GetActiveTabLayersNamesList(WebDriver driver)
		{	
			List<String> GraphicsTabLayerName = new ArrayList<String>();
			List<WebElement> ListGraphicsLayer = driver.findElements(By.xpath("//div[@id='activeData']/div/div/div/div/div[2]/div[1]"));
			for (WebElement LayerItem : ListGraphicsLayer) 
			{	
				String layertext= LayerItem.getText();
				String layernamewithoutSpace = layertext.replace(" ", "");
				GraphicsTabLayerName.add(layernamewithoutSpace);
			}
			return GraphicsTabLayerName;
		}
		
		
	//Getting Feature layer names List from RightPanel Active Tab Layers
		@SuppressWarnings("null")
		public static List<String> GetActiveTabLayersList(WebDriver driver)
		{	
//			List<String> GraphicsTabLayerName = null;
			List<String> ActiveTabLayerName = new ArrayList<String>();
			List<WebElement> NumberListGraphics = driver.findElements(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"));
			for (WebElement LayerItem : NumberListGraphics) 
			{	
				String layernam = LayerItem.getAttribute("id");
				String layernam1 = layernam.replace("|graphicCount", "");
				String layernam2 = layernam1.replace("_", " ");
				String layernam3 = layernam2.replace(" ", "");
				ActiveTabLayerName.add(layernam3);
				
			}
			return ActiveTabLayerName;
		
		}
		
		//Validating Feature Count from Bottom and Right Panel
		public static void ValidateFeatureCountWidgetWithRightPanel(int WidgetListCount, int RightPanelFeatureCount)
		{
			
				if(WidgetListCount>=RightPanelFeatureCount)
				{
					System.out.println("True Features Count Are Validated");
				}
		}
		
		//Validating feature is displayed or not
		public static void FeatureIsPresentOnMap(WebElement element)
		{
			if(element.isDisplayed())
			{
				System.out.println("Feature is present on map");
			}
		}
	// Validating LeftPanelLayerList with DownPanelLayerList and
	// RightPanelLayerList with DownPanelLayerList
	public static boolean Compare2StringsLists(List<String> List1,
			List<String> List2) {
		int flag = 0;
		for (int i = 0; i < List1.size(); i++) {
			for (int j = 0; j < List2.size(); j++) {
				if (!(List1.get(i).equals(List2.get(j)))) {
					continue;
				} else {
					flag++;
				}
			}
		}
		if (flag == List1.size()) {
			System.out.println("TRUE");
			return true;
		}
		System.out.println("FALSE");
		return false;

	}

	

}
