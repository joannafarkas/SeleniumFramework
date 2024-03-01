package uk.co.automationtesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BasePage;
import base.ExtendManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomePage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)
public class AddRemoveItemBasketE2ETest extends Hooks {

	public AddRemoveItemBasketE2ETest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Test
	public void addRemoveItem()   throws IOException, InterruptedException {
		
		ExtendManager.log("Starting AddRemoveItemBasketE2ETest.. ");
		
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();
		
		ShopHomePage shopHome = new ShopHomePage();
		ExtendManager.pass("Reached the shop home page");
		shopHome.getProdOne().click();
		
		
		ShopProductPage shopProd = new ShopProductPage();
		ExtendManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtendManager.pass("Have successfully selected product size");		
		shopProd.getQuantIncrease().click();
		ExtendManager.pass("Have successfully increased quantity" );		
		shopProd.getAddToCartBtn().click();
		ExtendManager.pass("Have successfully added product to basket" );	
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		
		shopProd.getHomepageLink().click();
		
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		Thread.sleep(500);
		waitForElement(cart.getDeleteItemTwo(), Duration.ofMillis(100));
		Thread.sleep(500);
		System.out.println(cart.getTotalAmount().getText());
		Thread.sleep(500);
		try {
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24" );
			ExtendManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("The total amount did not matches the expected amount. It found:  " + cart.getTotalAmount().getText() + " but expected $45.24. ");
			ExtendManager.fail("The total amouint did not match the expected amount");
		}
	
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		Thread.sleep(5000);
		cart.getProceedCheckoutBtn().click();
		Thread.sleep(5000);
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("Joanna");
		pInfo.getLastnameField().sendKeys("Test");
		pInfo.getEmailField().sendKeys("joanna@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getRecOfferCheckbox().click();
		pInfo.getNewsletterCheckbox().click();		
		pInfo.getContinueBtn().click();


		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();


		OrderFormShippingMethod shippingMethod = new OrderFormShippingMethod();
		shippingMethod.getDeliveryMsgTextbox().sendKeys("Please contact me before delivery");;
		shippingMethod.getContinueBtn().click();
		
		OrderFormPayment orderPayment = new OrderFormPayment();
		orderPayment.getPayByCheckRadioBtn().click();
		orderPayment.getTermsConditionsCheckbox().click();
		orderPayment.getOrderBtn().click();
	}
	

}
