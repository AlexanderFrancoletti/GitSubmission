package seleniumgenc.pages;

import seleniumgenc.Utils.City;
import seleniumgenc.Utils.Gender;

public class ShopifyRegisterPageBuilder {
	private ShopifyRegisterPage shopifyRegisterPage;

	public ShopifyRegisterPageBuilder(ShopifyRegisterPage shopifyRegisterPage) {
		super();
		this.shopifyRegisterPage = shopifyRegisterPage;
	}
	
	public ShopifyRegisterPageBuilder withFirstName(String firstName){
		shopifyRegisterPage.getFirstNameTextBox().sendKeys(firstName);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withLastName(String lastName){
		shopifyRegisterPage.getLastNameTextBox().sendKeys(lastName);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withUserName(String userName){
		shopifyRegisterPage.getUserNameTextBox().sendKeys(userName);
		return this;
	}
	
	public ShopifyRegisterPageBuilder withCity(City city){
		shopifyRegisterPage.getSelectCityDropDown().selectByVisibleText(city.toString());
		return this;
	}
	
	public ShopifyRegisterPageBuilder withGender(Gender gender){
		if(gender.equals(Gender.Male)){
			shopifyRegisterPage.getGenderRadioMale().click();
		}
		else if(gender.equals(Gender.Female)){
			shopifyRegisterPage.getGenderRadioFemale().click();
		}
		else{
			shopifyRegisterPage.getGenderRadioOther().click();
		}
		return this;
	}
	
	public ShopifyRegisterPageBuilder withPassword(String password){
		shopifyRegisterPage.getPasswordNameTextBox().sendKeys(password);
		return this;
	}
	
	public void quitDriver(){
		shopifyRegisterPage.getDriver().quit();
	}
	
	public ShopifyRegisterPage build(){ return shopifyRegisterPage;}
	
}
