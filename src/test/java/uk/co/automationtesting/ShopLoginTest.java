package uk.co.automationtesting;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtendManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.ShopHomePage;
import pageObjects.ShopLoginPage;
import pageObjects.ShopYourAccount;


@Listeners(resources.Listeners.class)
public class ShopLoginTest extends Hooks {

	public ShopLoginTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void addRemoveItem()   throws IOException, InterruptedException {
		
		ExtendManager.log("Starting ShopLoginTest .. ");
		
		HomePage home = new HomePage();
		home.getCookie().click();

		home.getTestStoreLink().click();
	
		ShopHomePage shopHome = new ShopHomePage();
		shopHome.getLoginBtn().click();
		
		FileInputStream workbookLocation = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		/****************************************************************************
		 * Excel Spreadsheet Layout Reminder (teaching purposes only)
		 * 
		 * |Row=0 -->| Email Address (Cell 0) Password (Cell 1) *
		 * -------------------------------------------------------------------- 
		 * |Row=1 -->| test@test.com (Cell 0) test123 (Cell 1) 
		 * |Row=2 -->| john.smith@test.com (Cell 0) test123 (Cell 1)
		 * |Row=3 -->| lucy.jones@test.com (Cell 0) catlover1 (Cell 1) 
		 * |Row=4 -->| martin.brian@test.com (Cell 0) ilovepasta5 (Cell 1) 
		 ****************************************************************************/
		
		Row row1 = sheet.getRow(1);
		Cell cellR1C0 = row1.getCell(0);
		Cell cellR1C1 = row1.getCell(1);	
		String emailRow1 = cellR1C0.toString();
		String passwordRow1 = cellR1C1.toString();
		
		ShopLoginPage shop = new ShopLoginPage();
		shop.getEmail().sendKeys(emailRow1);
		shop.getPassword().sendKeys(passwordRow1);
		shop.getSigninBtn().click();
		
		ShopYourAccount account = new ShopYourAccount();
		try {
			account.getSignOutBtn().click();
			ExtendManager.pass("User has signed in");
			
		} catch (Exception e) {		
			Thread.sleep(500);	
			ExtendManager.fail("User could not sign in");
	}
			
		Row row2 = sheet.getRow(2);
		Cell cellR2C0 = row2.getCell(0);
		Cell cellR2C1 = row2.getCell(1);	
		String emailRow2 = cellR2C0.toString();
		String passwordRow2 = cellR2C1.toString();
		shop.getEmail().sendKeys(emailRow2);
		shop.getPassword().sendKeys(passwordRow2);
		shop.getSigninBtn().click();
		
		try {
			account.getSignOutBtn().click();
			ExtendManager.pass("User has signed in");
			
		} catch (Exception e) {
		
			ExtendManager.fail("User could not sign in");
		}	
	}	
}
