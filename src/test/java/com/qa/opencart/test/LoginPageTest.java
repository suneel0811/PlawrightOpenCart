package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void navigateToLoginPage() throws InterruptedException {
        loginPage = homePage.navigateToLoginPage();
        String actualPageTittle = loginPage.getPageTitle();
        Assert.assertEquals(actualPageTittle, AppConstants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void forgotPasswordLinkExist() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Test(priority = 3)
    public void logInToCart() throws InterruptedException {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password")));
        Thread.sleep(5000);
    }
}
