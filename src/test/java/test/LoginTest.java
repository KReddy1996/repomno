package test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	Logger log;
	WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {
	  
		

		LandingPage landingPage = new LandingPage(driver);
   
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on login option");
		landingPage.loginOption().click();
        log.debug("Clicked on login option");
		Thread.sleep(3000);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailFieldOption().sendKeys(email);
		log.debug("Email addressed got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("Password got Entered");
		loginPage.loginButton().click();
		log.debug("Clicked on Login Button");

		AccountPage accountPage = new AccountPage(driver);
		String actualResult = null;
		try {
			if (accountPage.edityouraccountInformationLink().isDisplayed()) {
				log.debug("User got logged in");
				actualResult = "Successfull";
			}
		} catch (Exception e) {
			log.debug("User didn't login");
			actualResult = "Failure";

		}
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login Test got passed");

	}

	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		driver = intialiseDriver();
		
        log.debug("Browser got launched");
        
		driver.get(prop.getProperty("url"));
		
		log.debug("Navigated to application URL");
	}

	@AfterMethod
	public void closerMethod() {
		driver.close();
		log.debug("Browser got closed");
	}

	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = { { "kundansinghreddy@gmail.com", "Kundan@26", "Successfull" },
				{ "kundansinghreddy@gmail.com", "Kundan26", "Failure" } };
		return data;
	}

}
