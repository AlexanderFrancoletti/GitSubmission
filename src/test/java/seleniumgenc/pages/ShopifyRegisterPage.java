package seleniumgenc.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ShopifyRegisterPage {
	private final String BASE_URL = "https://webapps.tekstac.com/Shopify";
	private WebDriver driver;
	
	public ShopifyRegisterPage(WebDriver driver){
		this.driver = driver;
		this.driver.get(BASE_URL);
	}
	
	public WebDriver getDriver(){return driver;}
	
	public WebElement getFirstNameTextBox(){return driver.findElement(By.id("firstname"));}
	
	public WebElement getLastNameTextBox(){return driver.findElement(By.id("lastname"));}
	
	public WebElement getUserNameTextBox(){return driver.findElement(By.id("username"));}
	
	public WebElement getPasswordNameTextBox(){return driver.findElement(By.id("pass"));}
	
	public Select getSelectCityDropDown(){ return new Select(driver.findElement(By.id("selectcity")));}
	
	public WebElement getGenderRadioMale(){ return driver.findElement(By.xpath("//input[@value='male']"));}
	
	public WebElement getGenderRadioFemale(){ return driver.findElement(By.xpath("//input[@value='female']"));}
	
	public WebElement getGenderRadioOther(){ return driver.findElement(By.xpath("//input[@value='other']"));}
	
	public WebElement getRegisterButton(){return driver.findElement(By.id("reg"));}
	
	public WebElement getCancelButton(){return driver.findElement(By.id("cancel"));}
	
	public WebElement getTable(){return driver.findElement(By.id("ttab"));}
	
	public List<WebElement> getTableRows(){return getTable().findElements(By.tagName("tr"));}
	
	public String getTableLastRowText(){
		List<WebElement> rows = getTableRows();
		return rows.get(rows.size()-1).getText();
	}
	
	
	public String getErrorMessages(){ return driver.findElement(By.id("errfn")).getText();}
	
	public void clickRegisterButton(){getRegisterButton().click();}
	
	
}
