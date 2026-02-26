package com.qa.opencart.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

    private Page page;

    //1. Private string locators
    private String search = "input[name='search']";
    private String searchIcon = "div#search button";
    private String searchResultsPageHeader = "div#content h1";
    private String myAccount="//span[text()='My Account'] ";
    private String login="Login";

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

    public String searchProduct(String productName) {
        System.out.println("Search a product");
        page.locator(search).fill(productName);
        page.locator(searchIcon).click();
        String searchHeader = page.textContent(searchResultsPageHeader);
        System.out.println("Search results page title: " + searchHeader);
        return searchHeader;
    }

    public LoginPage navigateToLoginPage(){
        page.locator(myAccount).click();
        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName(login)).click();

        return new LoginPage(page);
    }
}
