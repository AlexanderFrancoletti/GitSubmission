package seleniumgenc;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import seleniumgenc.Utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumgenc.pages.ShopifyRegisterPage;
import seleniumgenc.pages.ShopifyRegisterPageBuilder;

public class SeleniumPracticeTekstacShopifyTests {

	private ShopifyRegisterPageBuilder shopifyRegisterPageBuilder;
	private String excelPath = System.getProperty("user.dir")+"/src/test/java/excelFiles/ShopifyTestData.xlsx";
	public static final String HAPPY_PATH_SHEET = "allFields";
	public static final String MISSING_FIELDS_SHEET = "missingFields";
	private WebDriver driver;
	
	@BeforeTest
	public void beforeTest(){
		WebDriverManager.firefoxdriver().setup();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		shopifyRegisterPageBuilder = new ShopifyRegisterPageBuilder(new ShopifyRegisterPage(new FirefoxDriver()));
	}
	
	@DataProvider(name="happyPath")
	public Object[][] happyPathData() throws IOException{
		Object[][] data = ShopifyExcelUtils.readShopifyFormDataFromExcel(excelPath, HAPPY_PATH_SHEET).stream()
				.map(formData -> new Object[]{formData.getFirstName(), formData.getLastName(), formData.getUserName(),
						formData.getCity().toString(), formData.getGender().toString(), formData.getPassword(),
						formData.getTableText(), formData.getErrorMessage()}).toArray(Object[][]::new);
		return data;
	}
	
	@DataProvider(name="missingFields")
	public Object[][] missingFieldsData() throws IOException{
		Object[][] data = ShopifyExcelUtils.readShopifyFormDataFromExcel(excelPath, MISSING_FIELDS_SHEET).stream()
				.map(formData -> new Object[]{formData.getFirstName(), formData.getLastName(), formData.getUserName(),
						formData.getCity().toString(), formData.getGender().toString(), formData.getPassword(),
						formData.getTableText(), formData.getErrorMessage()}).toArray(Object[][]::new);
		return data;
	}
	
	
	@Test
	public void shopifyRegisterFormContainsAllTextboxes(){
		
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getFirstNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getLastNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getUserNameTextBox().isDisplayed());
		Assert.assertTrue(shopifyRegisterPageBuilder.build().getPasswordNameTextBox().isDisplayed());
		
	}
	
	@Test(dataProvider = "happyPath")
	public void submittingFormAddsInfoToBottomTable(String firstName, String lastName, String userName,
												   String city, String gender, String password, 
												   String tableText, String errorMessage){
		
		//Arrange/Act
		ShopifyRegisterPage page = shopifyRegisterPageBuilder
				.withFirstName(firstName)
				.withLastName(lastName)
				.withUserName(userName)
				.withCity(City.valueOf(city))
				.withGender(Gender.valueOf(gender))
				.withPassword(password).build();
		
		page.clickRegisterButton();
		
		String expected = tableText;
		String actual = page.getTableLastRowText();
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider = "missingFields")
	public void incomplete_form_issues_error(String firstName, String lastName, String userName,
			   								String city, String gender, String password, 
			   								String tableText, String errorMessage){
		
		//Arrange/Act
		ShopifyRegisterPage page = shopifyRegisterPageBuilder
				.withFirstName(firstName)
				.withLastName(lastName)
				.withUserName(userName)
				.withCity(City.valueOf(city))
				.withGender(Gender.valueOf(gender))
				.withPassword(password).build();
		
		page.clickRegisterButton();
		
		String expectedError = errorMessage;
		String actualError = page.getErrorMessages();
		Assert.assertEquals(actualError, expectedError);
		
	}
	
	
	@AfterMethod
	public void afterTest(){
		shopifyRegisterPageBuilder.quitDriver();
	}
}
