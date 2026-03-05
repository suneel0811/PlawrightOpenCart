package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.AssertActions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginFailTest extends BaseTest {

    @Test(priority = 1)
    public void navigateToLoginPage() throws InterruptedException {
        loginPage = homePage.navigateToLoginPage();

        String actualPageTittle = loginPage.getPageTitle();
        AssertActions.assertEquals(actualPageTittle, AppConstants.LOGIN_PAGE_TITLE);

        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
        //Assert.assertTrue(loginFailPage.doLogin(prop.getProperty("userName"), prop.getProperty("password")));
        loginFailPage.doLogin("Suneel","redd");
    }
}
