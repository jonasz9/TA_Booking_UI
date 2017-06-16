package com.test.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Janek on 2017-06-15.
 */
public class SkyScannerTest {

    @Test
    public void checkShortestFlight() {
        WebDriver driver = new FirefoxDriver();

        driver.navigate().to("https://www.skyscanner.net");
        Assert.assertTrue("Something goes wrong with loading page.",
                driver.findElement(By.id("culture-info")).isDisplayed());

        WebElement oneWayButton = driver.findElement(By.className("one-way-trip"));
        oneWayButton.click();

//        WebElement oneWayButtonSelected = driver.findElement(By.id("one-way-trip checked"));
//
//        Assert.assertTrue("One Way option wasn't selected.",
//                oneWayButtonSelected.isDisplayed());

        WebElement flightFrom = driver.findElement(By.id("js-origin-input"));
        flightFrom.click();
        flightFrom.sendKeys("Wroclaw");
        flightFrom.sendKeys(Keys.ENTER);

        WebElement flightTo = driver.findElement(By.id("js-destination-input"));
        flightTo.click();
        flightTo.sendKeys("Warsaw");
        flightTo.sendKeys(Keys.ENTER);

        WebElement searchBtn = driver.findElement(By.className("fss-bpk-button"));
        searchBtn.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        WebElement flightPlan = driver.findElement(By.className("search-summary-places"));
        String fromToInfo = flightPlan.getText();
        Assert.assertTrue("Search route aren't displayed", fromToInfo.contains("Wroclaw (WRO)\n" +
                "to\n" +
                " - Warsaw (Any)"));

        List<WebElement> ListOfDurationTimes = driver.findElements(By.className("duration"));
        System.out.println("Number of searched flights: "+ListOfDurationTimes.size());

        if(ListOfDurationTimes.size() == 1) {
            WebElement selectFlight = driver.findElement(By.className("select-action"));
            selectFlight.click();
        }
        else if (ListOfDurationTimes.size() > 1) {
            Select sortByDuration = new Select(driver.findElement(By.id("sort-select")));
            sortByDuration.selectByValue("duration");
            WebElement selectOneFromManyFlights = driver.findElement(By.xpath(".//*[@id='day-section']/div/div[3]/ul/li[1]/article/div[2]/div/div/div/a"));
            selectOneFromManyFlights.click();
        }
        else
            System.out.println("Sorry, there are no flights");

        WebElement flightDetailsPage = driver.findElement(By.className("header-title"));
        String flightDetailsTitle = flightDetailsPage.getText();
        Assert.assertTrue("You are not on the page with details.", flightDetailsTitle.contains("Your flight details"));

        driver.close();
    }
}
