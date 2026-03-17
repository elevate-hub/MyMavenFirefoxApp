package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();

        try {
            driver.manage().window().maximize();

            // ---------------- TAB 1 (SauceDemo) ----------------
            driver.get("https://www.saucedemo.com/");
            Thread.sleep(2000);

            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            Thread.sleep(1000);
            driver.findElement(By.id("login-button")).click();

            // ---------------- TAB 2 (Practice Test Login) ----------------
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://practicetestautomation.com/practice-test-login/");
            Thread.sleep(2000);

            driver.findElement(By.id("username")).sendKeys("student");
            driver.findElement(By.id("password")).sendKeys("Password123");
            Thread.sleep(1000);
            driver.findElement(By.id("submit")).click();

            // ---------------- TAB 3 (Automation Exercise) ----------------
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://automationexercise.com/");
            Thread.sleep(2000);

            // store all tabs
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            // switch to TAB 3 (automationexercise)
            driver.switchTo().window(tabs.get(2));

            // ---------------- SAFE CLICK for first add-to-cart button ----------------
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Optional: hide overlay iframe (Google Ads)
            try {
                WebElement iframe = driver.findElement(By.id("aswift_4"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", iframe);
            } catch (Exception ignored) {
                // If iframe not found, ignore
            }

            // Wait for the button to be clickable
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.className("add-to-cart"))
            );

            // Scroll into view just in case
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);

            // Click using JS to avoid any remaining obstruction
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

            Thread.sleep(2000);

            // ---------------- SWITCH BETWEEN TABS (optional demo) ----------------
            driver.switchTo().window(tabs.get(0)); // back to SauceDemo
            Thread.sleep(1000);

            driver.switchTo().window(tabs.get(1)); // back to Practice site
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
