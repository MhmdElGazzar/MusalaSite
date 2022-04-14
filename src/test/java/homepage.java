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
        return new Object[][]{
                {testData.getTestData("invalidEmail1")},
                {testData.getTestData("invalidEmail2")},
                {testData.getTestData("invalidEmail3")},
                {testData.getTestData("invalidEmail4")},
                {testData.getTestData("invalidEmail5")}};
    }

    @Test(dataProvider = "data-provider")
    public void testcase1(String email) {
        musalaWebsite.openMainPage();
        musalaWebsite.ContactUs.click();
        musalaWebsite.fillForm("MElgazzar",email,"0100010010" ,"support","call me back!");
        musalaWebsite.checkInvalidError("The e-mail address entered is invalid.");
       }

    @Test
    public void testcase2() {
        musalaWebsite.openMainPage();
        musalaWebsite.Company.click();
        musalaWebsite.checkUrlText("https://www.musala.com/company/");
        musalaWebsite.containsHeader("Leadership");
        musalaWebsite.fbImg.click();
        driver.element().switchToWindow(driver.element().getWindowHandles().get(1));
        musalaWebsite.checkUrlText("https://www.facebook.com/MusalaSoft?fref=ts");
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


