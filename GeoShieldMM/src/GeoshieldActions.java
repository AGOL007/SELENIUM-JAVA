import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.parsers.IntegratedParserConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeoshieldActions extends AppCommonUtility {
	// Click on Main Panel Tools using BROWSE, LiveFeeds, Tools, Workflows, etc.
	public static void ClickOnTool(WebDriver driver, String toolname)
			throws Exception {
		WebElement Toollist = driver.findElement(By
				.className("applicationToolContent"));
		List<WebElement> toolItemList = Toollist.findElements(By
				.className("iconContainer"));
		for (WebElement toolItem : toolItemList) {
			if (toolItem.getAttribute("title").equals(toolname)) {
				toolItem.click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	// Opening Panels on Arrow Clicks
	public static void OpeningPanels(WebDriver driver, String name)
			throws Exception {
		WebElement element;
		while (((driver.findElements(By.className(name)).size() > 0))
				&& driver.findElement(By.className(name)).isEnabled()) {
			try {
				WebElement Arrow = driver.findElement(By.className(name));
				Arrow.click();
				break;
			} catch (Exception x) {
				WebElement Arrow = driver.findElement(By.className(name));
				Arrow.click();
				break;
			}
		}
	}

	// Click on Date Filter Panel
	public static void OpenDateFilterPanels() throws Exception {
		WebElement timeFilterBtn = driver.findElement(By
				.className("filterSelectedDateIcon"));
		AppCommonUtility.ClickOnEnabledDisplayedItem(timeFilterBtn);
		Thread.sleep(1000);
	}

	// Changing Predefined Date in Browse panel
	public static void ChangingPredefinedDate(WebDriver driver, String datevalue)
			throws Exception {

		List<WebElement> PreDefinedDateList = driver.findElements(By
				.className("layerContentRow"));
		for (WebElement PreDefinedDateListItem : PreDefinedDateList) {
			if (PreDefinedDateListItem.getText().equals(datevalue)) {
				PreDefinedDateListItem.click();
				Thread.sleep(500);
				break;
			}
		}

	}

	// Switching To Date Panel Tab
	public static void SwitchToDateTab(WebDriver driver) throws Exception 
	{
		List<WebElement> DateRangeTab = driver.findElements(By
				.className("removeRightBorder"));
		for (WebElement Item : DateRangeTab) 
		{
			ClickOnEnabledDisplayedItem(Item);
		}
	
		//AppCommonUtility.ClickOnEnabledDisplayedItem(DateRangeTab);
		//Thread.sleep(1000);
	}

	// Opening LayerList Dropdown from BottomPanel
	public static void ClickOnSelectLayerButton(WebDriver driver)
			throws Exception {
		WebElement SelectLayerBtn = driver.findElement(By
				.className("selectedLayerValue"));
		AppCommonUtility.ClickOnEnabledDisplayedItem(SelectLayerBtn);
		Thread.sleep(1000);
	}

	// Opening DatePicker
	public static void SelectDatePicker(WebDriver driver, String DivText)
			throws Exception {
		List<WebElement> DateListPanel = driver.findElements(By
				.className("clearPanelDate"));
		for (WebElement ItemDateListPanel : DateListPanel) {
			WebElement TextLabelElement = ItemDateListPanel.findElement(By
					.className("timeLabel"));
			if (TextLabelElement.getText().equals(DivText)) {
				WebElement ClickonDatePicker = ItemDateListPanel.findElement(By
						.className("dijitArrowButtonContainer"));
				ClickonDatePicker.click();
				Thread.sleep(1000);
				break;
			}
		}
	}

	// Selecting Month Value from DatePicker
	public static void SelectMonth(WebDriver driver, String Month)
			throws Exception {
		// WebElement MonthDiv=
		// driver.findElement(By.xpath("//table[@class='dijitCalendarContainer dijitCalendar dijitCalendarFocused dijitFocused']/thead/tr[@class='dijitReset dijitCalendarMonthContainer']/th[2]/span/span/span/span[2]/div[@class='dijitCalendarMonthLabel dijitCalendarCurrentMonthLabel']"));
		// MonthDiv.click();
		List<WebElement> MonthList = driver.findElements(By
				.className("dijitCalendarMonthLabel"));
		for (WebElement MonthItem : MonthList) {
			if (MonthItem.getText().equals(Month)) {
				MonthItem.click();
				Thread.sleep(500);
				break;
			}
		}

	}

	// Click on OK Button
	public static void OkButtonClick() throws Exception {
		try {
			WebElement OkBtnDiv = driver.findElement(By
					.className("filterBtnContainer"));
			List<WebElement> ButtonList = OkBtnDiv.findElements(By
					.className("buttonText"));
			for (WebElement btnElement : ButtonList) {
				if (btnElement.getText().equals("OK")) {
					btnElement.click();
					break;
				}

			}
		} catch (Exception e) {
			driver.findElement(By.className("okBtn")).click();
		}

	}

	// Expanding Layers from Left Panel
	public static void EventsExpandingOnCategoryTitle(WebDriver driver,
			String CategoryName) throws Exception {
		List<WebElement> CategoryGrouplist = driver.findElements(By
				.className("categoryGroupPanel"));
		for (WebElement CategoryGrouplistItem : CategoryGrouplist) {
			List<WebElement> CategoryGrouplistDivList = CategoryGrouplistItem
					.findElements(By.tagName("div"));
			int i = 0;
			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) {
				int increment = i++;
				int expandIcon = increment - 1;
				if (CategoryGrouplistDivListItem.getText().equals(CategoryName)) {
					CategoryGrouplistDivList.get(expandIcon).click();
					break;
				}
			}
		}
	}

	// Toggling ON OFF For Layer Main Category
	public static void ToggleButtonMain(WebDriver driver,
			String ParentCategoryName) throws Exception {
		List<WebElement> CategoryGrouplist = driver.findElements(By
				.className("categoryGroupPanel"));
		for (WebElement CategoryGrouplistItem : CategoryGrouplist) {
			List<WebElement> CategoryGrouplistDivList = CategoryGrouplistItem
					.findElements(By.tagName("div"));
			int i = 0;
			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) {
				int increment = i++;
				int OnOffIcon = increment + 5;
				if (CategoryGrouplistDivListItem.getText().equals(
						ParentCategoryName)) {
					ClickOnEnabledDisplayedItem(CategoryGrouplistDivList
							.get(OnOffIcon));
					break;
				}
			}
		}
	}

	// Toggling for ON/OFF options for Child Category options

	public static void ToggleButtonSubCategory(WebDriver driver,
			String SubCategoryName) throws Exception {
		List<WebElement> SubCategoryGrouplist = driver.findElements(By
				.className("subCategoryDataPanel"));
		for (WebElement SubCategoryGrouplistItem : SubCategoryGrouplist) {
			List<WebElement> CategoryGrouplistDivList = SubCategoryGrouplistItem
					.findElements(By.tagName("div"));
			int i = 0;
			for (WebElement CategoryGrouplistDivListItem : CategoryGrouplistDivList) {
				int increment = i++;
				int OnOffIcon = increment + 4;
				if (CategoryGrouplistDivListItem.getText().equals(
						SubCategoryName)) {

					ClickOnEnabledDisplayedItem(CategoryGrouplistDivList
							.get(OnOffIcon));
					break;

				}
			}
		}
	}

	// Switching Tabs in RightPanel on Bases of TabName
	public static void SwitchTabsInRightPanel(WebDriver driver, String TabName)
			throws Exception {
		String XpathValue = "//span[@class='dijitAccordionText'][contains(text(), '"
				+ TabName + "')]";
		WebElement Tabelement = driver.findElement(By.xpath(XpathValue));
		ClickOnEnabledDisplayedItem(Tabelement);
	}

	// Comparing HashMaps values
	public static boolean Compare2HashMap(HashMap<String, Integer> HashMap1,
			HashMap<String, Integer> HashMap2) {
		Set<String> key_Set1 = HashMap1.keySet();

		String[] key_string1 = (String[]) key_Set1.toArray(new String[key_Set1
				.size()]);

		 
		

		for (String string : key_string1) {
			if (HashMap2.containsKey(string)) 
			{
				System.out.println("false");

				if (HashMap1.get(string) == HashMap2.get(string))
					System.out.println("true");
				return true;
			}
		}
		return false;
	}

	
	// Comparing HashMaps values with HashMap in LIST 
		/*public static boolean Compare2HashMapLists(HashMap<List<String>, List<Integer>> HashMap1,
				HashMap<String, Integer> HashMap2) {
			
			
			Set<List<String>> key_Set1 = HashMap1.keySet();
			

			String[] key_string1 = (String[]) key_Set1.toArray(new String[key_Set1
					.size()]);

			// Collection<Integer> value_S1 = HM1.values();
			//
			// Integer[] value_i1 = (Integer[]) value_S1.toArray();

			for (String string : key_string1) {
				if (HashMap2.containsKey(string)) {
					System.out.println(HashMap1.get(string));

					if (HashMap1.get(string) == HashMap2.get(string))
						System.out.println("true");
					return true;
				}
			}
			return false;
		}*/
	// Clearing All results from the map
	public static void ClearAllResults(WebDriver driver) throws Exception {
		WebElement ClearAllResultsBtn = driver.findElement(By
				.className("tocClearAllContent"));
		ClickOnEnabledDisplayedItem(ClearAllResultsBtn);
	}

	// Click on Live Feeds widget by Name
	public static void ClickOnIcon(WebDriver driver, String WidgetName)
			throws Exception {
		String xpath = "//div[@class='backGroundToolIcon']/div[@title='"
				+ WidgetName + "']";
		WebElement WidgetIcon = driver.findElement(By.xpath(xpath));
		ClickOnEnabledDisplayedItem(WidgetIcon);
	}

	// Closing widgets by clicking on X icon
	public static void CloseWiget(WebDriver driver) throws Exception {

		List<WebElement> WidgetCloseIcon = driver.findElements(By.className("customDialogCloseIcon"));
		for (WebElement itemelement : WidgetCloseIcon) 
		{
			ClickOnEnabledDisplayedItem(itemelement);

		}
	}

	// Get Features Present on Map with Layer List
	public static void ValidateFeaturesPresent() throws Exception {
		List<WebElement> SVGList = driver.findElements(By.tagName("svg"));
		for (WebElement SVGListItem : SVGList) {
			List<WebElement> GList = SVGListItem.findElements(By.tagName("g"));
			for (WebElement GListItem : GList) {
				String AttributeIdItem = GListItem.getAttribute("id");
				String X = AppCommonUtility
						.getXMLNodesValues("SubCategoryBurglaryLayerName");
				if (AttributeIdItem.contains(X)) {
					List<WebElement> ImageItemList = GListItem.findElements(By
							.tagName("image"));
					int ImageCount = ImageItemList.size();
					if (ImageCount > 0) {
						System.out
								.println("Feature is present for"
										+ AppCommonUtility
												.getXMLNodesValues("SubCategoryBurglaryLayerName"));

					} else {
						// driver.findElement(By.className("icon-minus")).click();
						System.out
								.println("Feature is not present for"
										+ AppCommonUtility
												.getXMLNodesValues("SubCategoryBurglaryLayerName"));

					}
				}
			}
		}
	}

	// Read Excel Data using FileName and Sheetname
	@SuppressWarnings({ "resource", "null" })
	// public static String readExcel(String filePath,String fileName,String
	// sheetName, String ColumnName) throws IOException
	public static String[] readExcel(String filePath, String fileName,String sheetName, String ColumnName) throws IOException 
	{
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook TestWorkbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
			if (fileExtensionName.equals(".xlsx")) 
			{
				TestWorkbook = new XSSFWorkbook(inputStream);
			} 
			else if (fileExtensionName.equals(".xls")) 
			{
				TestWorkbook = new XSSFWorkbook(inputStream);
			}
			Sheet TestSheet = TestWorkbook.getSheet(sheetName);
			int rowcount = TestSheet.getLastRowNum() + 1;
			//System.out.println("rowcount = " + rowcount);
			// String ExcelData=null;
			String[] ExcelData = new String[rowcount - 1];
			for (int i = 0; i < rowcount; i++) 
			{
				Row row = TestSheet.getRow(i);
				for (int j = 0; j < row.getLastCellNum(); j++) 
				{
					if (row.getCell(j).getCellType() == Cell.CELL_TYPE_STRING)
					if (row.getCell(j).getStringCellValue().equals(ColumnName)) 
					{
						for (int k = i+1; k < rowcount; k++) 
						{
							// row = TestSheet.getRow(i + 1);
							row = TestSheet.getRow(k);
							Cell cell = row.getCell(j);
							int ColumnIndex = cell.getColumnIndex();
							System.out.println(ColumnIndex + 1);
							switch (cell.getCellType()) 
							{
								case Cell.CELL_TYPE_NUMERIC:
									int a = (int) cell.getNumericCellValue();
									String X = String.valueOf(a);
									System.out.println(X);
									ExcelData[k-1] = X;
									break;
								case Cell.CELL_TYPE_STRING:
									String b = (String) cell.getStringCellValue();
									System.out.println(b);
									ExcelData[k-1] = b;
									break;
							}
						}// return ExcelData;
					}

				}
			}
		return ExcelData; // for returning array use this statement
		// return null; in case of single value use this

	}

	// Get data filled to textBox values
	public static void EnterDataInTextBoxes(String SheetName,String PanelName, List<WebElement> toolItemList,WebDriver driver, String LeftPanelText, String LeftPanelTextInExcel)throws IOException, Exception 
	{
		String filepath = "D:\\JavaSelenium\\GeoShieldMM\\src\\";
		String[] TwitterData= readExcel(filepath,"GeoshieldData.xlsx",SheetName,LeftPanelTextInExcel);
		for (WebElement toolItem : toolItemList) 
		{
			WebElement LeftDiv = toolItem.findElement(By.className(PanelName+"LeftPanelLabel"));
			if (LeftDiv.getText().equals(LeftPanelText)) 
			{
				WebElement TextBox = toolItem.findElement(By.className("dijitInputContainer"));
				WebElement TxtBox = TextBox.findElement(By.className("dijitInputInner"));
				TxtBox.clear();
				TxtBox.sendKeys(TwitterData[0]);
				Thread.sleep(1000);
				break;

			}

		}
	}

	
	//OK Click for different Widgets on bases of Unique ClassName 
	
	public static void OkBtnClickClassName(WebDriver driver, String Classname)
	{
		String XpathValue= "//div[@class='"+Classname+"'][contains(text(),'OK')]";
		driver.findElement(By.xpath(XpathValue)).click();
	}
	
	//Minimize widget on Bases Widget Name
	public static void MinimizeIconClick(WebDriver driver, String WidgetPanelName)
	{
		List<WebElement> DialogTitleList= driver.findElements(By.className("customDialogTitleBar"));
		for (WebElement DialogTitleListItem : DialogTitleList) 
		{
			List<WebElement> textElement=DialogTitleListItem.findElements(By.tagName("span"));
			
			for (WebElement textElementItem : textElement)
			{
				if(textElementItem.getText().equals(WidgetPanelName))
				{
					DialogTitleListItem.findElement(By.className("minmaxIcon")).click();
				}
				
			}
		}
	}
	
	//Clicking on Units Dropdown
		public static void SelectingDate(WebDriver driver,String panelname, String ExcelUnit) throws Exception
		{
			WebElement element= driver.findElement(By.id("widget_"+panelname+"Units"));
			element.findElement(By.className("dijitArrowButtonInner")).click();
			Thread.sleep(500);
			WebElement Divlist=driver.findElement(By.id("widget_"+panelname+"Units_dropdown"));
			WebElement divlist= Divlist.findElement(By.id(panelname+"Units_popup"));
			Thread.sleep(200);
			List<WebElement> DivList= divlist.findElements(By.tagName("div"));
			for (WebElement DivItem : DivList) 
			{
				if(DivItem.getText().equals(ExcelUnit))
				{
					DivItem.click();
					break;
				}
			}
		}
		
		//Clicking on LPR Select By LPN Dropdown
				public static void SelectingByLPNDropdown(WebDriver driver,String panelname, String ExcelUnit) throws Exception
				{
					WebElement element= driver.findElement(By.id("widget_"+panelname));
					element.findElement(By.className("dijitArrowButtonInner")).click();
					Thread.sleep(800);
					WebElement Divlist=driver.findElement(By.id("widget_"+panelname+"_dropdown"));
					WebElement divlist= Divlist.findElement(By.id(panelname+"_popup"));
					Thread.sleep(200);
					List<WebElement> DivListDivTagList= divlist.findElements(By.tagName("div"));
					for (WebElement DivItem : DivListDivTagList) 
					{
						if(DivItem.getText().equals(ExcelUnit))
						{
							DivItem.click();
							break;
						}
					}
				}
				
		
	// Data Entering in TwitterPanel

	public static void DataFillTwitterPanel(WebDriver driver)throws Exception 
	{
		List<WebElement> toolItemList = driver.findElements(By.className("searchTweetDiv"));
		Thread.sleep(1000);
		EnterDataInTextBoxes("Twitter","",toolItemList, driver, "Keyword","KeyWords");
		EnterDataInTextBoxes("Twitter","",toolItemList, driver, "Distance (in Miles)","Distance");
		EnterDataInTextBoxes("Twitter","",toolItemList, driver, "Refresh Time (in Seconds)","Refresh Time");
		WebElement PointGeometryIcon= driver.findElement(By.className("searchPointGeometry"));
		AppCommonUtility.ClickOnEnabledDisplayedItem(PointGeometryIcon);
		Thread.sleep(500);
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(500);
		OkBtnClickClassName(driver, "searchSubmitButton");
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		MinimizeIconClick(driver,"Twitter");
		OpeningPanels(driver, "panelUpArrowIcon");
		ClickOnSelectLayerButton(driver);
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
		ClickOnSelectLayerButton(driver);
		OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		SwitchTabsInRightPanel(driver, "Graphics");
		List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
		SwitchTabsInRightPanel(driver, "Active Data");
		List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
		List<String> combined = new ArrayList<String>();
		combined.addAll(ActiveLayerList);
		combined.addAll(GraphicsLayerList);
		AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
		SwitchTabsInRightPanel(driver, "Active Data");
		MinimizeIconClick(driver,"Twitter");
		List<WebElement> TweetCountTotal=driver.findElements(By.className("rowTweetContainer"));
		int TweetResultsCountWidget=TweetCountTotal.size();
		int numbervalue = Integer.parseInt(((driver.findElement(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"))).getText()));
		AppValidation.ValidateFeatureCountWidgetWithRightPanel(TweetResultsCountWidget,numbervalue);
		TweetCountTotal.get(0).click();
		WebElement HighLigtElementDiv=driver.findElement(By.id("twitterHighlightGraphicLayer_layer"));
		WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
		AppValidation.FeatureIsPresentOnMap(HighlightCircle);
		MinimizeIconClick(driver,"Twitter");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		//System.out.println(HM1);
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
		//System.out.println(HM2);
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		ClearAllResults(driver);
		SwitchTabsInRightPanel(driver, "Graphics");
		ClearAllResults(driver);
	}
	
	public static void DataFillYouTubePanel(WebDriver driver) throws Exception
	{
		List<WebElement> toolItemList = driver.findElements(By.className("searchYoutubeDiv"));
		EnterDataInTextBoxes("YouTube","youtube",toolItemList, driver, "Distance","DISTANCE");
		Thread.sleep(1000);
		String[] UnitsList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "YouTube", "Units");
		SelectingDate(driver,"youtube",UnitsList[0].toString());
		EnterDataInTextBoxes("YouTube","youtube",toolItemList, driver, "Keyword","Keyword");
		EnterDataInTextBoxes("YouTube","youtube",toolItemList, driver, "Refresh Time (in Seconds)","Refresh Time (in Seconds)");
		WebElement PointGeometryIcon= driver.findElement(By.className("youtubePointGeometry"));
		AppCommonUtility.ClickOnEnabledDisplayedItem(PointGeometryIcon);
		Thread.sleep(500);
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(500);
		OkBtnClickClassName(driver, "youtubeSubmitButton");
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		MinimizeIconClick(driver,"YouTube");
		OpeningPanels(driver, "panelUpArrowIcon");
		ClickOnSelectLayerButton(driver);
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
		ClickOnSelectLayerButton(driver);
		OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		SwitchTabsInRightPanel(driver, "Graphics");
		List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
		SwitchTabsInRightPanel(driver, "Active Data");
		List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
		List<String> combined = new ArrayList<String>();
		combined.addAll(ActiveLayerList);
		combined.addAll(GraphicsLayerList);
		AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
		SwitchTabsInRightPanel(driver, "Active Data");
		MinimizeIconClick(driver,"YouTube");
		List<WebElement> YoutubeCountTotal=driver.findElements(By.className("rowYoutubeContainer"));
		int YouTubeResultsCountWidget=YoutubeCountTotal.size();
		int numbervalue = Integer.parseInt(((driver.findElement(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"))).getText()));
		AppValidation.ValidateFeatureCountWidgetWithRightPanel(YouTubeResultsCountWidget,numbervalue);
		YoutubeCountTotal.get(0).click();
		Thread.sleep(1000);
		WebElement HighLigtElementDiv=driver.findElement(By.id("youtubeHighlightGraphicLayer_layer"));
		WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
		AppValidation.FeatureIsPresentOnMap(HighlightCircle);
		MinimizeIconClick(driver,"YouTube");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		//System.out.println(HM1);
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
		//System.out.println(HM2);
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		ClearAllResults(driver);
		SwitchTabsInRightPanel(driver, "Graphics");
		ClearAllResults(driver);
		}
	public static void DataFillFlickrPanel(WebDriver driver) throws Exception
	{
		List<WebElement> toolItemList = driver.findElements(By.className("searchFlickrDiv"));
		EnterDataInTextBoxes("Flickr","flick",toolItemList, driver, "Distance","DISTANCE");
		Thread.sleep(1000);
		String[] UnitsList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "Flickr", "Units");
		SelectingDate(driver,"flickrDropDown",UnitsList[0].toString());
		EnterDataInTextBoxes("Flickr","flick",toolItemList, driver, "Keyword","Keyword");
		EnterDataInTextBoxes("Flickr","flick",toolItemList, driver, "Refresh Time (in Seconds)","Refresh Time (in Seconds)");
		driver.findElement(By.xpath("//div[@id='widget_dijit_form_DateTextBox_4']/div[1]/input")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//table[@id='dijit_form_DateTextBox_4_popup']/tfoot/tr/td/div/span[1]")).click();
		driver.findElement(By.xpath("//table[@id='dijit_form_DateTextBox_4_popup']/tbody/tr[2]/td[2]/span")).click();
		driver.findElement(By.xpath("//div[@id='widget_dijit_form_DateTextBox_5']/div[1]/input")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//table[@id='dijit_form_DateTextBox_5_popup']/tbody/tr[4]/td[2]/span")).click();
		EnterDataInTextBoxes("Flickr","flick",toolItemList, driver, "Refresh Time (in Seconds)","Refresh Time (in Seconds)");
		WebElement RectangleFeature= driver.findElement(By.className("flickrInactiveRectButton"));
		RectangleFeature.click();
		Thread.sleep(500);
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(500);
		OkBtnClickClassName(driver, "flickrSearchSubmitButton");
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		MinimizeIconClick(driver,"Flickr");
		OpeningPanels(driver, "panelUpArrowIcon");
		ClickOnSelectLayerButton(driver);
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
		ClickOnSelectLayerButton(driver);
		OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		SwitchTabsInRightPanel(driver, "Graphics");
		List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
		SwitchTabsInRightPanel(driver, "Active Data");
		List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
		List<String> combined = new ArrayList<String>();
		combined.addAll(ActiveLayerList);
		combined.addAll(GraphicsLayerList);
		AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
		SwitchTabsInRightPanel(driver, "Active Data");
		MinimizeIconClick(driver,"Flickr");
		List<WebElement> YoutubeCountTotal=driver.findElements(By.className("rowflickrContainer"));
		int YouTubeResultsCountWidget=YoutubeCountTotal.size();
		int numbervalue = Integer.parseInt(((driver.findElement(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"))).getText()));
		AppValidation.ValidateFeatureCountWidgetWithRightPanel(YouTubeResultsCountWidget,numbervalue);
		YoutubeCountTotal.get(0).click();
		Thread.sleep(1000);
		WebElement HighLigtElementDiv=driver.findElement(By.id("flickrHighlightGraphicLayer_layer"));
		WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
		AppValidation.FeatureIsPresentOnMap(HighlightCircle);
		MinimizeIconClick(driver,"Flickr");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		//System.out.println(HM1);
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
		//System.out.println(HM2);
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		ClearAllResults(driver);
		SwitchTabsInRightPanel(driver, "Graphics");
		ClearAllResults(driver);
		
	}
	
	//Checkbox socialMedia options
	
	public static void CheckBoxSocialMedia(WebDriver driver, String SocialMedia)
	{
		List<WebElement> CheckBoxList= driver.findElements(By.className("socialMediaCheckboxContainer"));
		for (WebElement CheckBoxItem : CheckBoxList) 
		{
			if(CheckBoxItem.getAttribute("title").toString().equals(SocialMedia))
				{CheckBoxItem.click();};
		}
	}
	
	
	public static void DataFillSocialMediaPanel(WebDriver driver) throws Exception
	{
		List<WebElement> DataFillList = driver.findElements(By.xpath("//div[@class='rowContainer']/div/div/div[@class='dijitReset dijitInputField dijitInputContainer']/input[@class='dijitReset dijitInputInner']"));
		if (AppCommonUtility.getXMLNodesValuesBoolean("CheckBoxTwitter")) 
		{
			CheckBoxSocialMedia(driver, "Twitter");
		}
		if (AppCommonUtility.getXMLNodesValuesBoolean("CheckBoxYouTube")) 
		{
			CheckBoxSocialMedia(driver, "Youtube");
		}
		if (AppCommonUtility.getXMLNodesValuesBoolean("CheckBoxFlickr")) 
		{
			CheckBoxSocialMedia(driver, "Flickr");
		}
		String[] DistanceList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "SocialMediaSearch", "Distance");
		String[] UnitsList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "SocialMediaSearch", "Units");
		String[] KeywordList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "SocialMediaSearch", "Keyword");
		String[] RefreshList=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "SocialMediaSearch", "Refresh Time (in Seconds)");
		DataFillList.get(0).clear();
		DataFillList.get(0).sendKeys(DistanceList[0]);
		SelectingDate(driver, "socialMediaSelectBuffer",UnitsList[0].toString());
		DataFillList.get(2).clear();
		DataFillList.get(2).sendKeys(KeywordList[0].toString());
		DataFillList.get(3).clear();
		DataFillList.get(3).sendKeys(RefreshList[0].toString());
		driver.findElement(By.className("selectPointGeometry")).click();
		Thread.sleep(500);
		MinimizeIconClick(driver,"Social Media Search");
		driver.findElement(By.id("mainContainer")).click();
		Thread.sleep(500);
		MinimizeIconClick(driver,"Social Media Search");
		OkBtnClickClassName(driver, "socialMediaOkButton");
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		MinimizeIconClick(driver,"Social Media Search");
		OpeningPanels(driver, "panelUpArrowIcon");
		ClickOnSelectLayerButton(driver);
		List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
		ClickOnSelectLayerButton(driver);
		OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		SwitchTabsInRightPanel(driver, "Graphics");
		List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
		SwitchTabsInRightPanel(driver, "Active Data");
		List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
		List<String> combined = new ArrayList<String>();
		combined.addAll(ActiveLayerList);
		combined.addAll(GraphicsLayerList);
		AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
		MinimizeIconClick(driver,"Social Media Search");
		List<WebElement> IntegerList=driver.findElements(By.xpath("//div[@id='activeData']/div/div/div/div/div/div[@class='tocLayerGraphicCount']"));
		
		ArrayList<Integer> ar = new ArrayList<Integer>();
		for (WebElement IntegerItem : IntegerList)	
		{
			String Count=IntegerItem.getText();
			int CountInt= Integer.valueOf(Count);
			ar.add(CountInt);
		}
		int sum=0;
		for(int i = 0; i < ar.size(); i++)
	    {
	        sum = sum + ar.get(i);
	    }
		System.out.println(sum);
		//driver.findElement(By.xpath("//div[@class='dropDownImage']/div/div/input[@class='dijitReset dijitInputField dijitArrowButtonInner']")).click();
		//List<WebElement> DropDownList=driver.findElements(By.xpath("//div[@class='dijitReset dijitMenu dijitComboBoxMenu']/div[@class='dijitReset dijitMenuItem']"));
		//for (WebElement DropDownItem : DropDownList) 
		//{
		//	DropDownItem.click();
		//}
		List<WebElement> SocialResults=driver.findElements(By.className("socilaMediaResultText"));
		int SocialResultsCount =SocialResults.size();
		AppValidation.ValidateFeatureCountWidgetWithRightPanel(sum,SocialResultsCount);
		//SocialResults.get(0).click();
		//Thread.sleep(1000);
		//MinimizeIconClick(driver,"Social Media Search");
		//WebElement HighLigtElementDiv=driver.findElement(By.id("socialMediaHighlightGraphicLayer_layer"));
		//WebElement HighlightCircle=HighLigtElementDiv.findElement(By.tagName("circle"));
		//AppValidation.FeatureIsPresentOnMap(HighlightCircle);
		MinimizeIconClick(driver,"Social Media Search");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		//System.out.println(HM1);
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
		//System.out.println(HM2);
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		ClearAllResults(driver);
		SwitchTabsInRightPanel(driver, "Graphics");
		ClearAllResults(driver);
		
	}
	
	// Data validation for Agency Feeds Camera Tool
	public static void ValidateAgencyFeedsCameraTool(WebDriver driver) throws Exception
	{
		MinimizeIconClick(driver, "Live Camera");
		OpeningPanels(driver, "panelUpArrowIcon");
		HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
		//System.out.println(HM1);
		OpeningPanels(driver, "panelRightArrowIcon");
		Thread.sleep(1000);
		SwitchTabsInRightPanel(driver, "Live");
		HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"live");
		//System.out.println(HM2);
		GeoshieldActions.Compare2HashMap(HM2, HM1);
		ClearAllResults(driver);	
	}
	//CheckBox controls for LiveCAD and AVL Subcategory
	public static void CheckBoxAgencyFeeds(WebElement MainElement, String Placeholder, String CheckBoxLabel, String XmlNodeValues, String CheckBoxName) throws Exception
	{
		List<WebElement> CheckBoxDiv= MainElement.findElements(By.className(Placeholder));
		for (WebElement CheckBoxDivItem : CheckBoxDiv) 
		{
			String GetString= CheckBoxDivItem.findElement(By.className(CheckBoxLabel)).getText();
			if(GetString.equals(AppCommonUtility.getXMLNodesValues(XmlNodeValues)))
			{
				CheckBoxDivItem.findElement(By.className(CheckBoxName)).click();
			}
		}
		
	}
	
	//Data Validaton for Agency Feeds Current Calls For Service 
	public static void DataFillAgencyFeedsCurrentCalls(WebDriver driver) throws Exception
	{
		WebElement MainElement = driver.findElement(By.className("liveCADTool"));
		//WebElement SelectAllElement=MainElement.findElement(By.xpath("//div[@class='liveCADSection selectAllDiv']"));
		//String GetStringText= SelectAllElement.findElement(By.className("liveSectionItemLabel")).getText();
		//if(GetStringText.equals(AppCommonUtility.getXMLNodesValues("MainCheckBoxCurrentCallsForService")))
		//{
		//	SelectAllElement.findElement(By.className("liveSectionItemCheckBox")).click();
		//}
		CheckBoxAgencyFeeds(MainElement, "liveCADItemPlaceHolder", "liveCADLabel", "Priority1CheckBoxCurrentCallsForService", "liveCADCheckboxContainer");
		CheckBoxAgencyFeeds(MainElement, "liveCADItemPlaceHolder", "liveCADLabel", "Priority2CheckBoxCurrentCallsForService", "liveCADCheckboxContainer");
		CheckBoxAgencyFeeds(MainElement, "liveCADItemPlaceHolder", "liveCADLabel", "Priority3CheckBoxCurrentCallsForService", "liveCADCheckboxContainer");
		CheckBoxAgencyFeeds(MainElement, "liveCADItemPlaceHolder", "liveCADLabel", "Priority4CheckBoxCurrentCallsForService", "liveCADCheckboxContainer");
		OkBtnClickClassName(driver, "liveCADButton");
		try
		{
			List<WebElement> AlertOkButtonList= driver.findElements(By.className("alertOkButton"));
			for (WebElement  AlertOkButtonItem: AlertOkButtonList) 
			{
				ClickOnEnabledDisplayedItem(AlertOkButtonItem);
			}
		}
		catch(Exception e)
		{
			System.out.println("Results are displayed");
		}
			MinimizeIconClick(driver, "Live CAD");
			OpeningPanels(driver, "panelUpArrowIcon");
			HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			OpeningPanels(driver, "panelRightArrowIcon");
			Thread.sleep(1000);
			SwitchTabsInRightPanel(driver, "Live");
			HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"live");
			GeoshieldActions.Compare2HashMap(HM2, HM1);
			ClearAllResults(driver);
			OpeningPanels(driver, "panelLeftArrowIcon");
			
			}
	
	//Filling Data in AVL Historic Tab
	public static void AgencyFeedFillData(WebDriver driver, String AvlItemTextWidget,String ExcelData)
	{
		List<WebElement> avl_tab_content = driver.findElements(By.className("avlTabContent"));
		for (WebElement AvlItem : avl_tab_content)
		{
			WebElement AvlTxtBox=AvlItem.findElement(By.className("avlLiveTextBox"));
			if(AvlTxtBox.getText().equals(AvlItemTextWidget))
			{
				WebElement InputContainerDiv=AvlItem.findElement(By.className("dijitInputContainer"));
				WebElement InputInner= InputContainerDiv.findElement(By.className("dijitInputInner"));
				InputInner.clear();
				InputInner.sendKeys(ExcelData);
				break;
			}
			
		} 
	}
	
	//Filling Data in LPR TextBox  Historic Tab
		public static void AgencyFeedFillDataLPR(WebDriver driver, String LPRItemTextWidget,String ExcelData)
		{
			List<WebElement> avl_tab_content = driver.findElements(By.className("lprTabContent"));
			for (WebElement AvlItem : avl_tab_content)
			{
				WebElement AvlTxtBox=AvlItem.findElement(By.className("lprLiveTextBox"));
				if(AvlTxtBox.getText().equals(LPRItemTextWidget))
				{
					WebElement InputContainerDiv=AvlItem.findElement(By.className("dijitInputContainer"));
					WebElement InputInner= InputContainerDiv.findElement(By.className("dijitInputInner"));
					InputInner.clear();
					InputInner.sendKeys(ExcelData);
					break;
				}
				
			} 
		}
	// Radio Button Switch for AVLLive Feeds tab
	public static void SwitchRadioAvl(WebDriver driver, String OptionName, String IdValue)
	{
		WebElement MainElement= driver.findElement(By.xpath("//div[@id='dijit__"+IdValue+"']/div[2]/div[1]"));
		List<WebElement> DivList= MainElement.findElements(By.tagName("div"));
		int flag=0;
		for (WebElement DivItem : DivList) 
		{	flag++;
			if(DivItem.getText().equals(OptionName))
			{
				DivList.get(flag-2).click();
				break;
			}
			
		}
	}
	//OK Button click LIVE FEEDS AVL tab
	public static void OkBtnClickList(WebDriver driver, String Classname)
	{
		List<WebElement> OkButtonList=driver.findElements(By.className(Classname));
		for (WebElement OkButtonListItem : OkButtonList) 
		{
			if(OkButtonListItem.isDisplayed() && OkButtonListItem.getText().equals("OK"))
			{
				OkButtonListItem.click();
			}
		}
	}
	//Data Validaton for Agency Feeds AVL
		public static void DataFillAgencyFeedsAVL(WebDriver driver) throws Exception
		{
			//CODE FOR  LIVE TAB
			WebElement MainElement = driver.findElement(By.className("avlTool"));
			CheckBoxAgencyFeeds(MainElement, "liveSectionItemConatiner", "liveSectionItemLabel", "Agency1CheckBoxCurrentCallsForService", "liveSectionItemCheckBox");
			CheckBoxAgencyFeeds(MainElement, "liveSectionItemConatiner", "liveSectionItemLabel", "Agency2CheckBoxCurrentCallsForService", "liveSectionItemCheckBox");
			CheckBoxAgencyFeeds(MainElement, "liveSectionItemConatiner", "liveSectionItemLabel", "Agency3CheckBoxCurrentCallsForService", "liveSectionItemCheckBox");
			OkBtnClickList(driver, "avlOkContent");
			try
			{
				List<WebElement> AlertOkButtonList= driver.findElements(By.className("alertOkButton"));
				for (WebElement  AlertOkButtonItem: AlertOkButtonList) 
				{
					ClickOnEnabledDisplayedItem(AlertOkButtonItem);
					Thread.sleep(500);
				}
			}
			catch(Exception e)
			{
				System.out.println("Results are displayed");
			}
				MinimizeIconClick(driver, "AVL");
				OpeningPanels(driver, "panelUpArrowIcon");
				HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				OpeningPanels(driver, "panelRightArrowIcon");
				Thread.sleep(1000);
				SwitchTabsInRightPanel(driver, "Live");
				HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"live");
				GeoshieldActions.Compare2HashMap(HM2, HM1);
				ClearAllResults(driver);
				SwitchTabsInRightPanel(driver, "Graphics");
				ClearAllResults(driver);
				MinimizeIconClick(driver, "AVL");
			
			// Code for Historic tab
			String[] AvlSelectUnitUnitId=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricUnit", "UNITID");
			String[] AvlSelectUnitDate1=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricUnit", "Date1");
			String[] AvlSelectUnitDate2=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricUnit", "Date2");
			String[] AvlSelectUnitTime1=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricUnit", "Time1");
			String[] AvlSelectUnitTime2=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricUnit", "Time2");
			String[] AvlSelectAreaDistance=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "DISTANCE");
			String[] AvlSelectAreaUnit=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "Unit");
			String[] AvlSelectAreaDate1=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "Date1");
			String[] AvlSelectAreaDate2=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "Date2");
			String[] AvlSelectAreaTime1=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "Time1");
			String[] AvlSelectAreaTime2=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "AVLHistoricArea", "Time2");
			SwitchToDateTab(driver);
			//SwitchRadioAvl(driver, "Select by Unit");
			List<WebElement> DateList= driver.findElements(By.xpath("//div[@class='avlHistoricInnerContainer']/div[@class='avlRoundContainer']/div/div/div/div[@class='dijitReset dijitInputField dijitInputContainer']/input[1]"));
			AgencyFeedFillData(driver, "Unit Id", AvlSelectUnitUnitId[0].toString());
			DateList.get(0).clear();
			DateList.get(0).sendKeys(AvlSelectUnitDate1[0].toString());
			DateList.get(1).clear();
			DateList.get(1).sendKeys(AvlSelectUnitDate2[0].toString());
			/*DateList.get(2).clear();
			DateList.get(2).sendKeys(AvlSelectUnitTime1[0].toString());
			DateList.get(3).clear();
			DateList.get(3).sendKeys(AvlSelectUnitTime2[0].toString());*/
			OkBtnClickList(driver, "avlOkContent");
			LoadingIndicatorWait(driver, 30);
			MinimizeIconClick(driver, "AVL");
			OpeningPanels(driver, "panelUpArrowIcon");
			HashMap<String, Integer> HM11 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			OpeningPanels(driver, "panelRightArrowIcon");
			SwitchTabsInRightPanel(driver, "Active Data");
			HashMap<String, Integer> HM22 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			GeoshieldActions.Compare2HashMap(HM22, HM11);
			ClearAllResults(driver);
			MinimizeIconClick(driver, "AVL");
			Thread.sleep(500);
			SwitchRadioAvl(driver, "Select by Area","WidgetBase_6");
			Thread.sleep(1000);
			SelectPoint(driver);
			//driver.findElement(By.className("simpleDrawPoint")).click();
			driver.findElement(By.id("mainContainer")).click();
			Thread.sleep(500);
			AgencyFeedFillData(driver, "Distance", AvlSelectAreaDistance[0].toString());
			SelectingDate(driver, "historicAvl", AvlSelectAreaUnit[0].toString());
			DateList.get(4).clear();
			DateList.get(4).sendKeys(AvlSelectAreaDate1[0].toString());
			DateList.get(5).clear();
			DateList.get(5).sendKeys(AvlSelectAreaDate2[0].toString());
			OkBtnClickList(driver, "avlOkContent");
			LoadingIndicatorWait(driver, 30);
			MinimizeIconClick(driver, "AVL");
			OpeningPanels(driver, "panelUpArrowIcon");
			ClickOnSelectLayerButton(driver);
			List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
			ClickOnSelectLayerButton(driver);
			Thread.sleep(1000);
			SwitchTabsInRightPanel(driver, "Graphics");
			List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
			SwitchTabsInRightPanel(driver, "Active Data");
			List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
			List<String> combined = new ArrayList<String>();
			combined.addAll(ActiveLayerList);
			combined.addAll(GraphicsLayerList);
			AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
			HashMap<String, Integer> HM3 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			HashMap<String, Integer> HM4 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			GeoshieldActions.Compare2HashMap(HM4, HM3);
			ClearAllResults(driver);
			
			SwitchTabsInRightPanel(driver, "Graphics");
			ClearAllResults(driver);
			//OpeningPanels(driver, "panelDownArrowIcon");
			OpeningPanels(driver, "panelLeftArrowIcon");
			/*DateList.get(6).clear();
			DateList.get(6).sendKeys(AvlSelectUnitDate1[0].toString());
			DateList.get(7).clear();
			DateList.get(7).sendKeys(AvlSelectUnitDate1[0].toString());*/
	}
		//Enter Data fill in LIVE LPR tab
		public static void EnterDataLPRLive(WebDriver driver, String Paneltext, String ExcelData)
		{
			List<WebElement> LPRTabList=driver.findElements(By.className("lprLiveTabContent"));
			for (WebElement LPRTabItem : LPRTabList) 
			{
				String TabText=LPRTabItem.findElement(By.className("lprLiveLabel")).getText();
				if(TabText.equals(Paneltext))
				{
					WebElement LPRNumber =LPRTabItem.findElement(By.className("dijitInputInner"));
					LPRNumber.sendKeys(ExcelData);
					break;
				}
			}
		}
		//Click On Draw POINT for each Tab using LIST Elemenets
		public static void SelectPoint(WebDriver driver) throws Exception
		{
			List<WebElement> SelectDrawPointList= driver.findElements(By.className("simpleDrawPoint"));
			for (WebElement  SelectDrawPointItem: SelectDrawPointList) 
			{
				ClickOnEnabledDisplayedItem(SelectDrawPointItem);
				Thread.sleep(1000);
			}
		}
		
		//Click On Draw Circle for each Tab using LIST Elemenets
				public static void SelectPointCircle(WebDriver driver) throws Exception
				{
					List<WebElement> SelectDrawPointList= driver.findElements(By.className("simpleDrawCircle"));
					for (WebElement  SelectDrawPointItem: SelectDrawPointList) 
					{
						ClickOnEnabledDisplayedItem(SelectDrawPointItem);
						Thread.sleep(1000);
					}
				}
		public static void FillDataLPR(WebDriver driver) throws Exception
		{
			String[] LicensePlateNumber=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRLive", "LicensePlateNumber");
			EnterDataLPRLive(driver,"License Plate Number", LicensePlateNumber[0].toString());
			OkBtnClickList(driver, "lprOkContent");
			try
			{
				List<WebElement> AlertOkButtonList= driver.findElements(By.className("alertOkButton"));
				for (WebElement  AlertOkButtonItem: AlertOkButtonList) 
				{
					ClickOnEnabledDisplayedItem(AlertOkButtonItem);
					Thread.sleep(1000);
					
				}
				
			}
			catch(Exception e)
			{
				System.out.println("LPN FOR 24 hrs is not present");
			}
				MinimizeIconClick(driver, "LPR");
				//OpeningPanels(driver, "panelUpArrowIcon");
				//GeoshieldActions.ClickOnSelectLayerButton(driver);
				HashMap<String, Integer> HM1 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
				OpeningPanels(driver,"panelRightArrowIcon");
				SwitchTabsInRightPanel(driver, "Live");
				HashMap<String, Integer> HM2 = AppValidation.GetfeatureCountAndNameLayers(driver,"live");
				GeoshieldActions.Compare2HashMap(HM1, HM2);
				ClearAllResults(driver);
				OpeningPanels(driver,"panelLeftArrowIcon");
				OpeningPanels(driver,"panelDownArrowIcon");
				MinimizeIconClick(driver, "LPR");
				Thread.sleep(1000);
		
			
			
			SwitchToDateTab(driver);
			//SwitchRadioAvl(driver, "Select by LPN");
			String[] LprNumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "LicenceNumberplate");
			String[] HitsNumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "HITS");
			String[] UnitsNumberLPNData=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "Units");
			String[] Date1NumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "Date1");
			String[] Date2NumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "Date2");
			String[] Time1NumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "Time1");
			String[] Time2NumberLPN=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricLPN", "Time2");
			AgencyFeedFillDataLPR(driver,"License Plate Number",LprNumberLPN[0].toString());
			List<WebElement> DateTimelist=driver.findElements(By.xpath("//div[@class='lprHistoricInnerContainer']/div[@class='lprRoundContainer']/div/div/div/div[@class='dijitReset dijitInputField dijitInputContainer']/input[1]"));
			SelectingByLPNDropdown(driver, "historicLpnHits",HitsNumberLPN[0].toString());
			
			SelectingByLPNDropdown(driver, "historicLpnUnits", UnitsNumberLPNData[0].toString());
		 
			DateTimelist.get(0).clear();
			DateTimelist.get(0).sendKeys(Date1NumberLPN[0].toString());
			DateTimelist.get(1).clear();
			DateTimelist.get(1).sendKeys(Date2NumberLPN[0].toString());
			OkBtnClickList(driver, "lprOkContent");
			LoadingIndicatorWait(driver, 30);
			MinimizeIconClick(driver, "LPR");
			OpeningPanels(driver, "panelUpArrowIcon");
			OpeningPanels(driver, "panelRightArrowIcon");
			HashMap<String, Integer> HM3 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			SwitchTabsInRightPanel(driver, "Active Data");
			HashMap<String, Integer> HM4 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			GeoshieldActions.Compare2HashMap(HM4, HM3);
			ClearAllResults(driver);
			MinimizeIconClick(driver, "LPR");
			Thread.sleep(1000);
			SwitchRadioAvl(driver, "Select by Area","WidgetBase_7");
			SelectPoint(driver);
			//driver.findElement(By.className("simpleDrawPoint")).click();
			MinimizeIconClick(driver, "LPR");
			driver.findElement(By.id("mainContainer")).click();
			String[] DistanceNumberLPNArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Distance");
			String[] UnitsNumberLPNArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Unit");
			String[] Date1NumberArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Date1");
			String[] Date2NumberArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Date2");
			String[] Time1NumberArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Time1");
			String[] Time2NumberArea=readExcel("D:\\JavaSelenium\\GeoShieldMM\\src\\", "GeoshieldData.xlsx", "LPRHistoricArea", "Time2");
			MinimizeIconClick(driver, "LPR");
			driver.findElement(By.id("dijit_form_NumberTextBox_3")).sendKeys(DistanceNumberLPNArea[0].toString());
			SelectingByLPNDropdown(driver, "historicUnits", UnitsNumberLPNArea[0].toString());
			DateTimelist.get(4).clear();
			DateTimelist.get(4).sendKeys(Date1NumberArea[0].toString());
			DateTimelist.get(5).clear();
			DateTimelist.get(5).sendKeys(Date2NumberArea[0].toString());
			OkBtnClickList(driver, "lprOkContent");
			LoadingIndicatorWait(driver, 30);
			MinimizeIconClick(driver, "LPR");
			ClickOnSelectLayerButton(driver);
			List<String> BottomPanelLayerList = AppValidation.GetLayerListFromBottomPanel(driver);
			ClickOnSelectLayerButton(driver);
			List<String> ActiveLayerList = AppValidation.GetActiveTabLayersNamesList(driver);
			SwitchTabsInRightPanel(driver, "Graphics");
			List<String> GraphicsLayerList =AppValidation.GetGraphicsTabLayersList(driver);
			List<String> combined = new ArrayList<String>();
			combined.addAll(ActiveLayerList);
			combined.addAll(GraphicsLayerList);
			AppValidation.Compare2StringsLists(BottomPanelLayerList, combined);
			SwitchTabsInRightPanel(driver, "Active Data");
			HashMap<String, Integer> HM5 = AppValidation.GetIndividualLayerNameAndCountBottomPanel(driver);
			SwitchTabsInRightPanel(driver, "Active Data");
			HashMap<String, Integer> HM6 = AppValidation.GetfeatureCountAndNameLayers(driver,"activeData");
			GeoshieldActions.Compare2HashMap(HM6, HM5);
			ClearAllResults(driver);
			SwitchTabsInRightPanel(driver, "Graphics");
			ClearAllResults(driver);
			/*DateTimelist.get(2).clear();
			DateTimelist.get(2).sendKeys(Time1NumberLPN[0].toString());
			DateTimelist.get(3).clear();
			DateTimelist.get(3).sendKeys(Time2NumberLPN[0].toString());
			*/
		}
}
// Selecting Month and Date from DatePicker
/*socialMediaRowContainer
 * public static void SelectDateMonth(WebDriver driver,String Month, String
 * Date) throws Exception { WebElement
 * MonthItem=driver.findElement(By.className("dijitCalendarCurrentMonthLabel"));
 * MonthItem.click(); Thread.sleep(1000); //WebElement MonthDiv=
 * driver.findElement(By.className("dijitCalendarMonthMenuFocused")); List
 * <WebElement> MonthList =
 * driver.findElements(By.className("dijitCalendarMonthLabel")); for (WebElement
 * MonthItem1 : MonthList) { if(MonthItem1.getText().equals(Month)) {
 * MonthItem1.click(); Thread.sleep(1000); break; } } WebElement DatesDiv=
 * driver.findElement(By.className("dijitCalendarBodyContainer")); List
 * <WebElement> DateList= DatesDiv.findElements(By.tagName("span")); for
 * (WebElement DateItem : DateList) { if(DateItem.getText().equals(Date)) {
 * DateItem.click(); break; }
 * 
 * } } //Changing Date Range Values using From And To public static void
 * SelectDateValues(WebDriver driver, String MonthName, String date) throws
 * Exception { WebElement
 * MonthItem=driver.findElement(By.className("dijitCalendarCurrentMonthLabel"));
 * MonthItem.click(); Thread.sleep(1000); //WebElement MonthDiv=
 * driver.findElement(By.className("dijitCalendarMonthMenuFocused")); List
 * <WebElement> MonthList =
 * driver.findElements(By.className("dijitCalendarMonthLabel")); for (WebElement
 * MonthItem1 : MonthList) { if(MonthItem1.getText().equals(MonthName)) {
 * MonthItem1.click(); Thread.sleep(1000); WebElement DatesDiv=
 * driver.findElement(By.className("dijitCalendarBodyContainer")); List
 * <WebElement> DateList= DatesDiv.findElements(By.tagName("span")); for
 * (WebElement DateItem : DateList) { if(DateItem.getText().equals(date)) {
 * DateItem.click(); Thread.sleep(1500); break; }
 * 
 * }
 * 
 * } else{break;}
 * 
 * }
 * 
 * 
 * }
 * 
 * public static void SelectDate(WebDriver driver, String Date) throws Exception
 * { List <WebElement> dateList= driver.findElements(By.xpath(
 * "//table[@class='dijitCalendarContainer dijitCalendar dijitCalendarFocused dijitFocused']/tbody/tr/td/span"
 * )); System.out.println(dateList.size()); WebElement DatesDiv=
 * driver.findElement(By.className("dijitCalendarBodyContainer")); List
 * <WebElement> DateList= DatesDiv.findElements(By.tagName("span")); for
 * (WebElement DateItem : DateList) { if(DateItem.getText().equals(Date)) {
 * DateItem.click(); Thread.sleep(1500); break; }
 * 
 * }
 * 
 * }
 */

