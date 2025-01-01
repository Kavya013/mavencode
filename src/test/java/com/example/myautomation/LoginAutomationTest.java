package com.example.myautomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class LoginAutomationTest {
    @Test
    public void testLogin() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\prabh\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.saucedemo.com/");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername("standard_user");
            loginPage.enterPassword("secret_sauce");
            loginPage.clickLogin();
            String expectedTitle = "Swag Labs";
            String actualTitle = loginPage.getPageTitle();
            assertEquals(expectedTitle, actualTitle);
        } finally {
            driver.quit();
        }
    }
}
