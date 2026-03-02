package com.qa.opencart.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.utilities.Logs;

public class LoginPage {

    private Page page;
    //1. String locators
    private String userEmail = "input[id='input-email']";
    private String userPassword = "input[id='input-password']";
    private String forgotPwd = "div[class='form-group'] a";
    private String logIn = "//input[@value='Login']";
    private String logOut="//a[@class='list-group-item'][normalize-space()='Logout']";

    //2.Page constructor

    public LoginPage(Page page) {
        this.page = page;
    }

    //3.Page actions/methods
    public String getPageTitle(){
        return page.title();
    }

    public boolean isForgotPwdLinkExist(){
        return page.isVisible(forgotPwd);
    }

    public boolean doLogin(String email, String password){
        Logs.info("Login to Cart");

        PlaywrightFactory.fillInput(userEmail,email,"EmailId");
        PlaywrightFactory.fillInput(userPassword,password,"Password");
        PlaywrightFactory.clickElement(logIn,"LoginBtn");
        if (page.isVisible(logOut)){
            System.out.println("user logged in successfully.....");
            return true;
        }
        return false;
    }
}
