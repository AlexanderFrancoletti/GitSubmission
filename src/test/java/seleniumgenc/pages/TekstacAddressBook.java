package seleniumgenc.pages;


import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import seleniumgenc.Utils.Address;
import seleniumgenc.Utils.AddressCover;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;


public class TekstacAddressBook {

    private final String url = "https://webapps.tekstac.com/AddressBook/";
    private String filePath = System.getProperty("user.dir") +"/src/test/java/dataFiles/Addresses.json";
    private WebDriver driver;


    @Before
    public void setUP(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @Test
    public void myTest(){

        String fName = driver.findElement(By.xpath("//td[contains(text(),'NickName')]//ancestor::div")).getAttribute(
                "id");
        Assert.assertEquals(fName,"t");
    }

    @Test
    public void JSONParsing_1() throws IOException, ParseException {


        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        AddressCover addressCover = new Gson().fromJson(reader, AddressCover.class);
        Optional<Address> address =Arrays.stream(addressCover.getAddresses()).filter(a->a.getId()==1).findFirst();
        System.out.println(address.get().getNickName());

//        JSONParser jsonParser = new JSONParser();
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
//        AddressCover addressCover = mapper.readValue(new File(filePath),AddressCover.class);
//
//
//        Optional<Address> address =
//                Arrays.stream(addressCover.getAddresses()).filter(a->a.getId()==1).findFirst();
//        System.out.println(address.get().getNickName());

    }


}
