package Cucumber_Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition {
	
	public static WebDriver driver;
	
	@Given("^Open chrome and login$")
	public void initialization() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///C:/Users/Pushpendra/Desktop/Trading%20Application.html");
		
		driver.manage().window().maximize();
		
	}
		
		@When(("^Input and click on submit$"))
		public void input() throws ParseException {
		WebElement Fromdate = driver.findElement(By.id("FromDate1"));
		WebElement ToDate = driver.findElement(By.id("ToDate"));
		WebElement Result = driver.findElement(By.id("trade"));
		
		Fromdate.clear();
		Fromdate.sendKeys("12/30/2017");
		ToDate.sendKeys("11/30/2017");
		
		ToDate.clear();
		
		
		String FromDateText = Fromdate.getAttribute("value") ;
		String ToDateText = ToDate.getAttribute("value") ;
		Result.click();
		
		}
		
		
		@Then("^Fetch data from excel and print output")
		public void output(String FromDateText,String ToDateText) throws ParseException {
		excel_Reader reader = new excel_Reader("D:\\Trades.xlsx");
		int rowcount =reader.getRowCount();
		
		for(int rownum=2; rownum<=rowcount;rownum++) {
			
			
			
			String returndate =reader.getCellData("TradeDtTime", rownum);
			
			String qty =reader.getCellData("qty", rownum);
			
			String instID =reader.getCellData("InID", rownum);
			System.out.println(instID);
			String instname =reader.getCellData("instname", rownum);
			
			
			SimpleDateFormat sdf=new SimpleDateFormat();
			
			
			Date fromdatetext =sdf.parse(FromDateText);
			Date todatetext =sdf.parse(ToDateText);
			Date returndatetext =sdf.parse(returndate);
			
			
			if (returndate.compareTo(FromDateText)>-0 && returndate.compareTo(ToDateText)<=0)
			{
				
				System.out.println("Intrument traded between the date" +instID  +instname +qty);
			} else {
				
				System.out.println("List of instrument not traded between the dates" +instID  +instname );
			}
			
			
		}
	
			for(int rownum=2; rownum<=rowcount;rownum++) {
			
			String returndate =reader.getCellData("TradeDtTime", rownum);
			
			String qty =reader.getCellData("qty", rownum);
			
			String instID =reader.getCellData("InID", rownum);
			
			String instname =reader.getCellData("instname", rownum);
			
			
			SimpleDateFormat sdf=new SimpleDateFormat();
			
			
			Date fromdatetext =sdf.parse(FromDateText);
			Date todatetext =sdf.parse(ToDateText);
			Date returndatetext =sdf.parse(returndate);
			
			
			if (returndate.compareTo(FromDateText)<-0 && returndate.compareTo(ToDateText)>=0)
			{
				
				System.out.println("Intrument traded between the date" +instID  +instname  +qty);
			} else {
				
				System.out.println("List of instrument not traded between the dates" +instID  +instname +qty );
			}
			
			
		}
		}
		
		

	}


