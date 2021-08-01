package com.src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Finder {
    public static WebDriver getdriver(String Browser, String Path) {
        Browser = Browser.toLowerCase();
        WebDriver driver = null;
        switch (Browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", Path);
                driver = new ChromeDriver();
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
        return driver;
    }

    /**
     * Find all the div with class
     * 
     * @param driver
     * @param location
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static Hashtable<String, String> returnAllDiv(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div"));
        System.out.println("All Div Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("class").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "class").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String className = listOfElements.get(i).getAttribute("class");
                        String key = "driver.findElement(By.className(\"" + className + "\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 2 - if element has id > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("id").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "id").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String idName = listOfElements.get(i).getAttribute("id");
                        String key = "driver.findElement(By.id(\"" + idName + "\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 3 - if element has name.length > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("name").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "name").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String ByName = listOfElements.get(i).getAttribute("name");
                        String key = "driver.findElement(By.name(\"" + ByName + "\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 4 - if element has value > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("value").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "value").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String ByValue = listOfElements.get(i).getAttribute("value");
                        String key = "driver.findElement(By.xpath(\"//*[text()='" + ByValue + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }

        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    /**
     * Search for Tag Input - class and Id
     * 
     * @param driver
     * @param location
     * @param Counter
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static Hashtable<String, String> returnAllInputTag(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//input"));
        System.out.println("All Div Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("class").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "class").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String className = listOfElements.get(i).getAttribute("class");
                        String key = "driver.findElement(By.xpath(\"//input[@class='" + className + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 2 - if element has id > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("id").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "id").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String idName = listOfElements.get(i).getAttribute("id");
                        String key = "driver.findElement(By.xpath(\"//input[@id='" + idName + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }

        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    /**
     * Search for Tag Button - class and Id and text
     * 
     * @param driver
     * @param location
     * @param Counter
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static Hashtable<String, String> returnAllButtonTag(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//button"));
        System.out.println("All Div Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("class").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "class").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String className = listOfElements.get(i).getAttribute("class");
                        String key = "driver.findElement(By.xpath(\"//button[@class='" + className + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 2 - if element has id > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("id").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "id").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String idName = listOfElements.get(i).getAttribute("id");
                        String key = "driver.findElement(By.xpath(\"//button[@id='" + idName + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 3 - if element has value > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("value").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "value").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String ByValue = listOfElements.get(i).getAttribute("value");
                        String key = "driver.findElement(By.xpath(\"//button[text()='" + ByValue + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }

        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    /**
     * Search for Tag Img - class and Id
     * 
     * @param driver
     * @param location
     * @param Counter
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public static Hashtable<String, String> returnAllImgTag(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//img"));
        System.out.println("All Div Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("class").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "class").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String className = listOfElements.get(i).getAttribute("class");
                        String key = "driver.findElement(By.xpath(\"//img[@class='" + className + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
            /***
             * Logic 2 - if element has id > 2 means some value than pick it
             */
            else if (listOfElements.get(i).getAttribute("id").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "id").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String idName = listOfElements.get(i).getAttribute("id");
                        String key = "driver.findElement(By.xpath(\"//img[@id='" + idName + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }

        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    /***
     * 
     * @param driver
     * @param location
     * @param Counter
     * @return all href
     * @throws InterruptedException
     * @throws IOException
     */
    public static Hashtable<String, String> returnAllLinks(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.tagName("a"));
        System.out.println("All Div Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("href").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "href").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String BytagAHref = listOfElements.get(i).getAttribute("href");
                        String key = "driver.findElement(By.xpath(\"//a[@href='" + BytagAHref + "']\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }
        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    public static Hashtable<String, String> returnAllIds(WebDriver driver, String location, int Counter)
            throws InterruptedException, IOException {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//*[@id]"));
        System.out.println("All Id Count" + listOfElements.size());
        Hashtable<String, String> ht = new Hashtable<String, String>();
        int count = Counter;
        for (int i = 0; i < listOfElements.size(); i++) {
            /***
             * Logic 1 - if element has only class attribute than we will skip that
             */
            if (listOfElements.get(i).getAttribute("id").length() > 1) {
                if (findallAttributes(driver, listOfElements.get(i), "id").toString().length() > 5) {
                    try {
                        HighlightElement(driver, listOfElements.get(i));
                        Thread.sleep(1000); // Because highlight and screenshot is taking some time //
                        if (System.getProperty("os.name").toLowerCase().contains("window")) {
                            takescreenshot(driver, location + "\\" + count + ".png");
                        } else {
                            takescreenshot(driver, location + "/" + count + ".png");
                        }

                        unHighlightElement(driver, listOfElements.get(i));
                        String ById = listOfElements.get(i).getAttribute("id");
                        String key = "driver.findElement(By.id(\"" + ById + "\"));";
                        String value = location + "_" + count + ".png";
                        if (!ht.containsKey(key)) {
                            ht.put(key, value);
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println("Error !!! continuing without this element" + e);
                    }
                }
            }
        }

        System.out.println("Total HT = " + count);
        Counter = count;
        return ht;
    }

    /**
     * Highlighting the element
     * 
     * @param driver
     * @param element
     */
    public static void HighlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        movetoElement(driver, element);
        // js.executeScript("arguments[0].setAttribute('style', 'background: yellow;
        // border: 1px solid red;');", element);
        js.executeScript("arguments[0].setAttribute('style', 'border: 1px solid red;');", element);

    }

    public static void unHighlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", element);
    }

    /**
     * Find all the attributes of Elements
     * 
     * @param driver
     * @param element
     * @param tagmusthave this tag is already there.. if there is any other tag
     *                    apart from this than only element need to be checked.
     */
    public static ArrayList<String> findallAttributes(WebDriver driver, WebElement element, String tagmusthave) {

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object elementAttributes = executor.executeScript(
                "var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;",
                element);
        String allexistAttributes[] = elementAttributes.toString().split(",");
        ArrayList<String> allAttributes = new ArrayList<String>();
        for (int i = 0; i < allexistAttributes.length; i++) {
            String currentValue = allexistAttributes[i];
            if (!currentValue.toLowerCase().contains(tagmusthave.toLowerCase())) {
                allAttributes.add(currentValue);
            }
        }

        return allAttributes;
    }

    /**
     * Takking Screenshot
     * 
     * @param driver
     * @param location
     * @throws IOException
     */
    public static void takescreenshot(WebDriver driver, String location) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(location + ".png");
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public String getlocation(String location) {
        if (System.getProperty("os.name").toLowerCase().contains("window")) {
            return location + "\\";
        } else
            return location + "/";
    }

    public static void movetoElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        // Call moveToElement() method of actions class to move mouse cursor to a
        // WebElement A.
        actions.moveToElement(element).build().perform();
    }
}
