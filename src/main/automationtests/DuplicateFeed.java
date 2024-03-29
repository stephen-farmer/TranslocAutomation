package automationtests;

import config.ResourceLocator;
import config.WebDriverWrapper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import cucumber.api.java.en.*;


import java.util.concurrent.TimeUnit;

/**
 * Created by Steve on 6/28/2019.
 */
public class DuplicateFeed extends WebDriverWrapper {
    public int numberOfFeeds;

    @Test(priority = 1)
    @Given("^User is on the Architect home view$")
    public void login() {
        ops.navOps.navigateToURL(ResourceLocator.STAGING_ENV);
        ops.navOps.login("qa_user_3", "n4km@XrhP4MA");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        numberOfFeeds = ops.userOps.getNumberOfFeeds();
    }

    @Test(priority = 2)
    @When("^User selects a feed's overflow menu$")
    public void selectFirstOverflow() {
        driver.findElement(By.xpath(ResourceLocator.FIRST_FEED_OVERFLOW)).click();
    }

    @Test(priority = 3)
    @And("^User selects duplicate$")
    public void selectOverflowDuplicate() {
        driver.findElement(By.xpath(ResourceLocator.OVERFLOW_DUPLICATE)).click();
    }

    @Test(priority = 4)
    @And("^User confirms duplicate$")
    public void confirmDuplicate() {
        ops.navOps.confirmAndClosePopup();
    }

    @Test(priority = 5)
    @Then("^Feed should be duplicated$")
    public void feedDuplicationValidation() {
        Assert.assertEquals(ops.userOps.getNumberOfFeeds(), numberOfFeeds+1);
        Assert.assertEquals(ops.userOps.getFeedName(ResourceLocator.SECOND_FEED), ops.userOps.getFeedName(ResourceLocator.FIRST_FEED) + " Copy");
    }

}

