package com.qa.opencart.base;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginFailPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utilities.ExcelUtil;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    protected HomePage homePage;
    protected Properties prop;
    protected LoginPage loginPage;
    protected LoginFailPage loginFailPage;
   // protected ExcelUtil excel;
    @BeforeMethod
    public void setUp() {
        playwrightFactory = new PlaywrightFactory();
        prop=playwrightFactory.init_prop();
        page = playwrightFactory.initBrowser(prop);
        homePage = new HomePage(page);
//        excel = new ExcelUtil("testdata/LoginData.xlsx", "Sheet1");
    }
/*    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return excel.getSheetData();
    }*/

    @AfterMethod
    public void tearDown() {
   //     excel.closeWorkbook();
        page.context().browser().close();
    }
}
