package uk.co.automationtesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
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
public class OrderCompleteE2ETest extends Hooks {

public OrderCompleteE2ETest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Test
	public void endToEndTest() throws IOException, InterruptedException {
		ExtendManager.log("Starting OrderComplateE2ETest.. ");
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();
		ExtendManager.pass("Reached the shop home page");
		Thread.sleep(5000);
		ShopHomePage shopHome = new ShopHomePage();
		shopHome.getProdOne().click();
		
		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtendManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();
		Thread.sleep(5000);
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();
		Thread.sleep(200);
		ShoppingCart cart = new ShoppingCart();
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		ExtendManager.pass("Have successfully add promo code");
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
		ExtendManager.pass("Have successfully entered customer details");


		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Houston");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		ExtendManager.pass("Have successfully entered delivery info");

		OrderFormShippingMethod shippingMethod = new OrderFormShippingMethod();
		shippingMethod.getDeliveryMsgTextbox().sendKeys("Please contact me before delivery");;
		shippingMethod.getContinueBtn().click();
		
		OrderFormPayment orderPayment = new OrderFormPayment();
		orderPayment.getPayByCheckRadioBtn().click();
		orderPayment.getTermsConditionsCheckbox().click();
		orderPayment.getOrderBtn().click();
		ExtendManager.pass("Have successfully ordered payment");
		
	}
	
}
