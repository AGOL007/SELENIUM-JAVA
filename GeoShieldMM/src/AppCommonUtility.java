import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;




public class AppCommonUtility 
{
	 static WebDriver driver;
	
	// Click on an Element which is Displayed & Enabled 
	
			public static void ClickOnEnabledDisplayedItem(WebElement element) throws Exception
			{
 				if(element.isEnabled()&& element.isDisplayed())
				{
					element.click();
				}
			}
			
	// Loading Indicator Wait
	public static void LoadingIndicatorWait(WebDriver driver, int timeoutSeconds) throws InterruptedException
	{
		driver.switchTo().defaultContent();
		WebElement loading= null;
		int i=0;
		do
		  {
			i++;
			Thread.sleep(1000);
			loading = driver.findElement(By.className("loadingIndicatorContainer")); 

		  }
		while (loading.isDisplayed() && (i <= timeoutSeconds));
	
	}
	
	// Getting XML Node Values String 
	public static String getXMLNodesValues(String XmlNode) throws Exception
	{
		File xmlfile= new File("D:\\JavaSelenium\\GeoShieldMM\\src\\APPCONFIG.xml");
		DocumentBuilderFactory dbuilder= DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dbuilder.newDocumentBuilder();
		Document doc = (Document) build.parse(xmlfile);
		Element rootElement = doc.getDocumentElement();
		NodeList list = rootElement.getElementsByTagName(XmlNode);
		String textvalue=list.item(0).getTextContent();
		return textvalue;
		
	}
	
	//Getting XML Node Values Boolean
	public static boolean getXMLNodesValuesBoolean(String XmlNode) throws Exception
	{
		File xmlfile= new File("D:\\JavaSelenium\\GeoShieldMM\\src\\Browse.xml");
		DocumentBuilderFactory dbuilder= DocumentBuilderFactory.newInstance();
		DocumentBuilder build = dbuilder.newDocumentBuilder();
		Document doc = (Document) build.parse(xmlfile);
		Element rootElement = doc.getDocumentElement();
		NodeList list = rootElement.getElementsByTagName(XmlNode);
		String  textvalue=list.item(0).getTextContent();
		boolean booleanvalue=Boolean.valueOf(textvalue);
		return booleanvalue;
		
	}
	
	// Dynamic Waits
	public static void FindDynamicallyElements(WebDriver driver, String elementname, String elementlocator)
	{	WebDriverWait wait= new WebDriverWait(driver, 30);
			switch(elementlocator)
			{
				
				case "ClassName":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementname)));
				case "id":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementname)));
				case "TagName":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(elementname)));
				case "XPath":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementname)));
				case "CSSSelector":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementname)));
				case "LinkText":
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementname)));
			}

}
	
	//Selecting Browser dynamically
	public static void SelectingDriver(String browser) throws Exception
	{
		
		switch (browser)
        {
            case "Chrome":
            	System.setProperty("webdriver.chrome.driver", "D:\\JavaSelenium\\GeoShieldMM\\src\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
            	System.setProperty("webdriver.ie.driver", "D:\\JavaSelenium\\GeoShieldMM\\src\\chromedriver_win32\\IEDriverServer.exe");
            	DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            	caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            	caps.setCapability("ignoreZoomSetting", true);
                driver = new InternetExplorerDriver(caps);
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new Exception("Unknown browser");
        }
		//return driver;
	}
	
	//Intialising Browser for each Widget
	
	public static void InitialiseBrowser() throws Exception
	{
		String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		AppCommonUtility.SelectingDriver(Browser);
		String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
		Thread.sleep(1000);
		driver.get(AppUrl);	
		driver.manage().window().maximize();
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
	}
	
}
