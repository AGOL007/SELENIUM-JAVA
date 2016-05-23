package DemoNG;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Utilities 
{
	
	public static WebDriver driver;

	
	 ////Dynamically wait for WebElements
	//public static WebDriverWait wait= new WebDriverWait(driver, 30);
	public static void example(){
		System.out.println("Working.......");
	}
	public static void FindDynamicallyElements(WebDriver driver2, String elementname, String elementlocator)
	{
		System.out.println("Entere in Method");
		WebDriverWait wait= new WebDriverWait(driver, 30);
		/*switch(elementlocator)
		{
			
			case "ClassName":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementname)));
			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementname)));
			case "TagName":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(elementname)));
			case "XPath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementname)));
			case "Name":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementname)));
			case "CSSSelector":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementname)));
			case "LinkText":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementname)));
		}*/
	
	}
	
	
	//Loading Indicator Wait Dynamically
	
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
	
	
	// Click on an Element which is Displayed & Enabled 
	
	public static void ClickOnEnabledDisplayedItem(WebElement element) throws Exception
	{
		if(element.isEnabled()&& element.isDisplayed())
		{
			element.click();
		}
	}
	////GET String values from XML Node VALUES 
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
	//Switch for Driver Selection
	public static void SelectBrowser() throws Exception
	{	String browser =DemoTest.getXMLNodesValues("BrowserName");
		switch (browser)
        {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new Exception("Unknown browser");
        }
	
	}
	
	
}
