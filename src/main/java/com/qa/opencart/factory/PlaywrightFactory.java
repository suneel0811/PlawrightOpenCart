package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.qa.opencart.utilities.Logs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser>tlBrowser=new ThreadLocal<>();
    private static ThreadLocal<BrowserContext>tlBrowserContext=new ThreadLocal<>();
    private static ThreadLocal<Page>tlPage=new ThreadLocal<>();
    private static ThreadLocal<Playwright>tlPlaywright=new ThreadLocal<>();

    public static Playwright getPlaywright(){
        return tlPlaywright.get();
    }
    public static Browser getBrowser(){
        return tlBrowser.get();
    }
    public static BrowserContext getBrowserContext(){
        return tlBrowserContext.get();
    }
    public static Page getPage(){
        return tlPage.get();
    }

    public Page initBrowser(Properties prop) {

        String browserName = prop.getProperty("browseType").trim();
        System.out.println("Browser name is " + browserName);

        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
               // browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));

                break;
            case "firefox":
              //  browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome")
                        .setHeadless(false)));

                break;
            default:
                System.out.println(" Please provide the right browser name ");
                break;
        }

       // browserContext = browser.newContext();
        tlBrowserContext.set(getBrowser().newContext());

       // page = browserContext.newPage();
        tlPage.set(getBrowserContext().newPage());

        //page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url"));

        return getPage();
    }

    /**
     * this method is used to initialize properties from the config file
     */

    public Properties init_prop() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return prop;
    }

    /**
     * take screenshot
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }


    /**
     * Clicks on an element after waiting for it to be visible and enabled
     */
    public static void clickElement(String locator, String elementName) {
        getPage().locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        getPage().locator(locator).click();
        Logs.pass("Clicking on " + elementName);
    }

    /**
     * Fills input field after waiting for it to be visible and enabled
     */
    public static void fillInput(String locator, String value, String elementName) {
        getPage().locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        getPage().locator(locator).fill(value);
        Logs.pass("Enter value '" + value + "' in the '" + elementName + "' field");
    }

    /**
     * Waits for element to be visible
     */
    public static void waitForElementVisible(String locator, String elementName) {
        getPage().locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Logs.pass("Waiting for visibility of " + elementName);
    }

    /**
     * Checks if element is visible (returns boolean)
     */
    public static boolean isElementVisible(String locator, String elementName) {
        boolean visible = getPage().locator(locator).isVisible();
        Logs.pass(elementName + " visibility: " + visible);
        return visible;
    }

    /**
     * Gets text content of element after waiting for visibility
     */
    public static String getElementText(String locator, String elementName) {
        waitForElementVisible(locator, elementName);
        String text = getPage().locator(locator).textContent();
        Logs.pass("Text of " + elementName + ": " + text);
        return text;
    }
}
