package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}
