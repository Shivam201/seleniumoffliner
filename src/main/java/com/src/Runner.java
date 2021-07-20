package com.src;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

/**
 * Hello world!
 *
 */
public class Runner extends Finder {
    static int Counter;

    Runner() {
        Counter = 0;
    }

    public static String createOR(String outputlocation, String urlToSearch, WebDriver driver)
            throws InterruptedException, IOException {
        try {
            driver.manage().window().maximize();
            driver.navigate().to(urlToSearch);
            String location = outputlocation;
            Hashtable<String, String> allcontents = new Hashtable<String, String>();
            Hashtable<String, String> divcontents = new Hashtable<String, String>();
            Hashtable<String, String> idcontents = new Hashtable<String, String>();
            Hashtable<String, String> tagahrefcontents = new Hashtable<String, String>();
            Hashtable<String, String> taginputcontents = new Hashtable<String, String>();
            Hashtable<String, String> tagbuttoncontents = new Hashtable<String, String>();
            Hashtable<String, String> tagimgcontents = new Hashtable<String, String>();
            // take all Div - class , id , value , name
            divcontents = returnAllDiv(driver, location, Counter);

            // take all Ids
            idcontents = returnAllIds(driver, location, Counter);

            // take all by tag name
            tagahrefcontents = returnAllLinks(driver, location, Counter);
            taginputcontents = returnAllInputTag(driver, location, Counter);
            tagbuttoncontents = returnAllButtonTag(driver, location, Counter);
            tagimgcontents = returnAllImgTag(driver, location, Counter);

            // Adding All contents //
            allcontents.putAll(divcontents);
            allcontents.putAll(idcontents);
            allcontents.putAll(tagahrefcontents);
            allcontents.putAll(taginputcontents);
            allcontents.putAll(tagbuttoncontents);
            allcontents.putAll(tagimgcontents);

            // Create OR file //
            CommonHelpers.writetoFile(allcontents, location + "\\OR.txt");
        } catch (Exception e) {
            return "Error in execution and creating File \n " + e;
        }

        return "OR.txt and its linked Images are created in this location - " + outputlocation;

    }
}
