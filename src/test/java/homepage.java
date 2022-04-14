import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class homepage {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    MusalaWebsite musalaWebsite;

    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("testData.json");
        musalaWebsite= new MusalaWebsite(driver);
    }



    @DataProvider(name = "data-provider")
    public Object[][] dpMethod() {
        return new Object[][]{{"email.ee"}, {"email@d@ss"}, {"emaild.ss@s"}, {"email@ddd"}, {"ema.il@d"}};
    }

    @Test(dataProvider = "data-provider")
    public void testcase1(String email) {
        musalaWebsite.openMainPage();
        driver.element().click(By.xpath("//*[text()='Contact us']"));
        driver.element().type(By.name("your-name"), "MElgazzar");
        driver.element().type(By.name("your-email"), email);
        driver.element().type(By.name("mobile-number"), "0100010010");
        driver.element().type(By.name("your-subject"), "support");
        driver.element().type(By.name("your-message"), "call me back!");
        driver.element().click(By.className("btn-cf-submit"));
        driver.element().waitForElementToBePresent(By.xpath("//*[@name=\"your-email\"]/following::span[1]"), 10, true);
        driver.assertThat().element(By.xpath("//*[@name=\"your-email\"]/following::span[1]")).text().isEqualTo("The e-mail address entered is invalid.").perform();
    }

    @Test
    public void testcase2() {
        musalaWebsite.openMainPage();
        driver.element().click(By.xpath("//*[@id='navbar']/..//a[text()='Company'][1]"));
        driver.assertThat().browser().url().isEqualTo("https://www.musala.com/company/").perform();
        driver.assertThat().element(By.xpath("//*[text()='Leadership']")).exists().perform();
        driver.element().click(By.className("musala-icon-facebook"));
        driver.element().switchToWindow(driver.element().getWindowHandles().get(1));
        driver.assertThat().browser().url().isEqualTo("https://www.facebook.com/MusalaSoft?fref=ts").perform();
        driver.element().switchToWindow(driver.element().getWindowHandles().get(0));
    }


    @Test
    public void testcase3() {
        musalaWebsite.openMainPage();
        musalaWebsite.Careers.click();
        musalaWebsite.CheckOpenPositions.click();
        musalaWebsite.checkUrlText("https://www.musala.com/careers/join-us/");
        musalaWebsite.openVacancy("Automation QA Engineer");
        musalaWebsite.containsHeader("General description");
        musalaWebsite.containsHeader("Requirements");
        musalaWebsite.containsHeader("Responsibilities");
        musalaWebsite.containsHeader("What we offer");
        musalaWebsite.Apply.shouldExist();
        musalaWebsite.Apply.click();
    }

    @Test
    public void testcase4() {
        musalaWebsite.openMainPage();
        musalaWebsite.Careers.click();
        musalaWebsite.CheckOpenPositions.click();
        musalaWebsite.printPositionsInfo("Sofia");
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}


