import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class WorkFlows extends AppCommonUtility {
	
	@Test
	public static void WorkFLowHyperLinks() throws Exception
	{	
		String Browser = AppCommonUtility.getXMLNodesValues("BrowserName");
		AppCommonUtility.SelectingDriver(Browser);
		Thread.sleep(1000);
		String AppUrl = AppCommonUtility.getXMLNodesValues("appurl");
		driver.get(AppUrl);	
		driver.manage().window().maximize();
		AppCommonUtility.LoadingIndicatorWait(driver, 30);
		GeoshieldActions.OpeningPanels(driver, "panelLeftArrowIcon");
		GeoshieldActions.ClickOnTool(driver,"Workflows");
		Thread.sleep(1000);
		List<WebElement> LinkList= driver.findElements(By.className("hyperlinkContainer"));
		for (WebElement LinkListItem : LinkList) {
			List<WebElement> LinkDivList= LinkListItem.findElements(By.tagName("a"));
			for (WebElement LinkDivListItem : LinkDivList) {
				String UrlWebElement =LinkDivListItem.getAttribute("href");
				LinkDivListItem.click();
				Thread.sleep(1000);
				
				Set<String> handles = driver.getWindowHandles();
				List<String> handlesList = new ArrayList<String> (handles);
				
				
				driver.switchTo().window(handlesList.get(1));
				
				try{
					
					Runtime.getRuntime().exec("D:\\JavaSelenium\\HandleAuthentication.exe");
				}
				catch(Exception e){}
				Thread.sleep(1000);
				String BrowserUrl = driver.getCurrentUrl();
				Thread.sleep(1000);
				if(UrlWebElement.equals(BrowserUrl))
				{
					System.out.println("Link Urls Are Validated");
				}
				driver.close();
				driver.switchTo().window(handlesList.get(0));
			}
			
		}driver.quit();
	}
}
