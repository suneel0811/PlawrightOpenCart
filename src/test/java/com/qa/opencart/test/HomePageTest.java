package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.AssertActions;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {


    @Test
    public void searchProduct() throws InterruptedException {
        String actualTitle = homePage.getHomePageTitle();
        AssertActions.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);

        String actualUrl = homePage.getHomePageUrl();
        AssertActions.assertEquals(actualUrl, prop.getProperty("url"));
        String actualSearchResultPageTitle = homePage.searchProduct("MacBook");
        AssertActions.assertEquals(actualSearchResultPageTitle,"Search - MacBook");
        AssertActions.assertEquals(actualSearchResultPageTitle,"Search - MacBook");
    }


}
