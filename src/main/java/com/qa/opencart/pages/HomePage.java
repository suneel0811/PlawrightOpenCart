package com.qa.opencart.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.utilities.Logs;

public class HomePage {

    private Page page;

    //1. Private string locators
    private String search = "input[name='search']";
    private String searchIcon = "//button[@class='btn btn-default btn-lg']/i";
    private String searchResultsPageHeader = "//div[@id='content']/h1";
    private String myAccount="//span[text()='My Account']";
    private String login="//ul[@class='dropdown-menu dropdown-menu-right']/li[2]/a";

    //2. Page Constructor
    public HomePage(Page page) {
        this.page = page;
    }

    //3. Page Actions/Methods
    public String getHomePageTitle() {
        String title = page.title();
        System.out.println("Page titile: " + title);
        return title;
    }

    public String getHomePageUrl() {
        String url = page.url();
        System.out.println("Page URL: " + url);
        return url;
    }

    public String searchProduct(String productName) throws InterruptedException {
        Logs.info("Search Product");
        PlaywrightFactory.fillInput(search,productName,"Search");
        PlaywrightFactory.clickElement(searchIcon,"Search icon");
        Thread.sleep(5000);
        String searchHeader = page.textContent(searchResultsPageHeader);
        System.out.println("Search results page title: " + searchHeader);

        return searchHeader;
    }

    public LoginPage navigateToLoginPage(){
        Logs.info("Navigate to Login page");
        PlaywrightFactory.clickElement(myAccount,"My Account");
        PlaywrightFactory.clickElement(login,"LogIn");

        return new LoginPage(page);
    }
}
