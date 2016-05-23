package mypackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Test {
	public static void main(String[] arcgs) 
	{
		WebDriver driver= new FirefoxDriver();
		String baseurl="https://github.com/Esri/";
		String currenttitle="Esri";
		driver.get(baseurl);
		driver.manage().window().maximize();
		
		
		WebElement list1=driver.findElement(By.className("header-actions"));
		List<WebElement> mylist = list1.findElements(By.tagName("a"));
		for(WebElement test : mylist) 
		{
			if(test.getText().equals("Sign in"))
			{
				test.click();
				WebDriverWait wait= new WebDriverWait(driver, 20);
				WebElement elementwait = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("login_field"))));
				String actualtitle1 = driver.getTitle();
				WebElement username = driver.findElement(By.id("login_field"));
				username.clear();
				username.sendKeys("CTLocalGovTeam@cybertech.com");
				WebElement password = driver.findElement(By.id("password"));
				password.clear();
				password.sendKeys("Cssl#2013");
				WebElement elementsigninbtn = driver.findElement(By.name("commit"));
				elementsigninbtn.click();
				WebElement elementwaitafterlogin = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("query"))));
				elementwaitafterlogin.clear();
				elementwaitafterlogin.sendKeys("crowdsource");
				List<WebElement> applist = list1.findElements(By.className("repo-list-item"));
				if(applist.size()==3);
				{
					applist.get(0).click();
				}
				String actualtitle = driver.getTitle();
				if(actualtitle.contentEquals(currenttitle))
				{
					System.out.println("Passed");
				}
				else
				{
					System.out.println("Failed");
				}
			}
			else
			{
				continue;
			}
		}
		
		driver.close();
		//System.exit(0);
		}
		
	}
	
	

