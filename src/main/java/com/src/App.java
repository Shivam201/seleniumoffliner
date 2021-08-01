package com.src;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

/**
 * Hello world!
 *
 */
public class App extends Finder {
    static WebDriver driver = null;
    static int Counter = 0;

    public static void main(String[] args) throws InterruptedException, IOException {
        driver = getdriver("Chrome", "C:\\Users\\Home\\eclipse-workspace\\seleniumfirst\\driver\\chromedriver.exe");
        driver.navigate().to("https://www.google.com/search?q=shivam+maralay");
        driver.manage().window().maximize();
        String location = "C:\\Users\\Home\\eclipse-workspace\\seleniumfirst\\output";

        Hashtable<String, String> allcontents = new Hashtable<String, String>();

        Hashtable<String, String> divcontents = new Hashtable<String, String>();
        Hashtable<String, String> idcontents = new Hashtable<String, String>();
        Hashtable<String, String> tagahrefcontents = new Hashtable<String, String>();
        Hashtable<String, String> taginputcontents = new Hashtable<String, String>();
        Hashtable<String, String> tagbuttoncontents = new Hashtable<String, String>();
        Hashtable<String, String> tagimgcontents = new Hashtable<String, String>();

        // // take all Div - class , id , value , name
        divcontents = returnAllDiv(driver, location, Counter);

        // // take all Ids
        idcontents = returnAllIds(driver, location, Counter);

        // // take all by tag name
        tagahrefcontents = returnAllLinks(driver, location, Counter);
        taginputcontents = returnAllInputTag(driver, location, Counter);
        tagbuttoncontents = returnAllButtonTag(driver, location, Counter);
        tagimgcontents = returnAllImgTag(driver, location, Counter);

        // // Adding All contents //
        allcontents.putAll(divcontents);
        allcontents.putAll(idcontents);
        allcontents.putAll(tagahrefcontents);
        allcontents.putAll(taginputcontents);
        allcontents.putAll(tagbuttoncontents);
        allcontents.putAll(tagimgcontents);

        // // Create OR file //
        CommonHelpers.writetoFile(allcontents, location + "\\OR.txt");
        Thread.sleep(5000);
        driver.quit();
    }

}
