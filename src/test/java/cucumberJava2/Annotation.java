package cucumberJava2;
 
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver; 


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;

public class Annotation { 
   WebDriver driver = null; 
	
   @Before public void setUp(){ 
System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe" );  
       
       //Instantiating driver object and launching browser
       driver = new ChromeDriver();
	     
	   } 

   
   
   @Given("^I open Google search engine$") 
   public void openGoogle() { 
	   driver.get("https://www.google.com/"); 
      
   } 
	
   @When("^I open Facebook website$") 
   public void goToFacebook() { 
      driver.get("https://www.facebook.com/"); 
      
      
   } 
   
   @Then("^Email placeholder should exist$") 
   public void loginButton() { 
	   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      if(driver.findElement(By.name("email")).isEnabled()) { 
         System.out.println("Test 1 Pass"); 
      } else { 
         System.out.println("Test 1 Fail"); 
      } 
      driver.close(); 
   }
   @After public void tearDown(Scenario scenario) {
	    if (scenario.isFailed()) {
	        // Take a screenshot...
	        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	        scenario.attach(screenshot, "image/png"," Error Screenshot"); // ... and embed it in the report.
	      }
	  }
}
