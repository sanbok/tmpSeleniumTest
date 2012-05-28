import com.thoughtworks.selenium.SeleneseTestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class Test1 extends SeleneseTestBase{

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.get("http://google.com");
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test1(){
        WebElement searchInput = driver.findElement(By.id("gbqfq"));
        searchInput.sendKeys("sanbok");
        driver.findElement(By.id("gbqfb")).click();
        waitForResults();

        assertThat(driver.findElement(By.partialLinkText("Oleksandr Andriiets")), is(notNullValue()));
    }

    private void waitForResults() {
        wait.until(new ExpectedCondition<Boolean>(){
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.id("resultStats")).isDisplayed();
            }
        });
    }
}
