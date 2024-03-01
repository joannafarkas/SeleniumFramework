package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopLoginPage extends BasePage {
	
	public WebDriver driver;
	
	By email = By.name("email");
	By password = By.name("password");
	By signInBtn = By.id("submit-login");
		
	public ShopLoginPage() throws IOException {
	 super();
	}

	
	public WebElement getEmail()  throws IOException{
		this.driver = getDriver();
		return driver.findElement(email);
	}
	
	public WebElement getPassword()  throws IOException{
		this.driver = getDriver();
		return driver.findElement(password);
	}
	
	public WebElement getSigninBtn()  throws IOException{
		this.driver = getDriver();
		return driver.findElement(signInBtn);
	}
}
