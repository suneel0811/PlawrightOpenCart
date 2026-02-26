package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {


    @Test
    public void homePageTitleTest() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
    }

    @Test
    public void homePageUrlTest() {
        String actualUrl = homePage.getHomePageUrl();
        Assert.assertEquals(actualUrl, prop.getProperty("url"));
    }

    @Test
    public void searchResultPageTitleTest() {
        String actualSearchResultPageTitle = homePage.searchProduct("MacBook");
        Assert.assertEquals(actualSearchResultPageTitle, "Search - MacBook");
    }


}
