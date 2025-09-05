package com.planit.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver createDriver() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "true"));
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,900");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver drv = new ChromeDriver(options);
        drv.manage().window().maximize();
        drv.get("http://jupiter.cloud.planittesting.com");
        driver.set(drv);
        return drv;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
