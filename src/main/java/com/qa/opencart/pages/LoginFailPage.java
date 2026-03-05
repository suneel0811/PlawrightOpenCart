package com.qa.opencart.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.utilities.Logs;

public class LoginFailPage {

    private Page page;
    //1. String locators
    private String userEmail = "input[id='input-email']";
    private String userPassword = "input[id='input-password']";
    private String forgotPwd = "div[class='form-group'] a";
    private String logIn = "//input[@value='Login']";
    private String logOut="//a[@class='list-group-item'][normalize-space()='Logout']";
    private String loginFail="//div[text()=' Warning: No match for E-Mail Address and/or Password.']";

    //2.Page constructor

    public LoginFailPage(Page page) {
        this.page = page;
    }


    public boolean doLogin(String email, String password){
        Logs.info("Login fail");

        PlaywrightFactory.fillInput(userEmail,email,"EmailId");
        PlaywrightFactory.fillInput(userPassword,password,"Password");
        //PlaywrightFactory.clickElement(logIn,"LoginBtn");
        if (page.isVisible(loginFail)){
            System.out.println("user login failed successfully.....");
            return true;
        }
        return false;
    }
}
