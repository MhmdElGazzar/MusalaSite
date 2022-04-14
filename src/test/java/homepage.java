import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class homepage {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    MusalaWebsite MSite;

    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("testData.json");
        MSite = new MusalaWebsite(driver);
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
        MSite.openMainPage();
        MSite.ContactUs.click();
        MSite.fillForm("MElgazzar",email,"0100010010" ,"support","call me back!");
        MSite.checkInvalidError("The e-mail address entered is invalid.");
       }

    @Test
    public void testcase2() {
        MSite.openMainPage();
        MSite.Company.click();
        MSite.checkUrlText("https://www.musala.com/company/");
        MSite.containsHeader("Leadership");
        MSite.fbImg.click();
        driver.element().switchToWindow(driver.element().getWindowHandles().get(1));
        MSite.checkUrlText("https://www.facebook.com/MusalaSoft?fref=ts");
        driver.element().switchToWindow(driver.element().getWindowHandles().get(0));
    }


    @Test
    public void testcase3() {
        MSite.openMainPage();
        MSite.Careers.click();
        MSite.CheckOpenPositions.click();
        MSite.checkUrlText("https://www.musala.com/careers/join-us/");
        MSite.openVacancy("Automation QA Engineer");
        MSite.containsHeader("General description");
        MSite.containsHeader("Requirements");
        MSite.containsHeader("Responsibilities");
        MSite.containsHeader("What we offer");
        MSite.Apply.shouldExist();
        MSite.Apply.click();
    }

    @Test
    public void testcase4() {
        MSite.openMainPage();
        MSite.Careers.click();
        MSite.CheckOpenPositions.click();
        MSite.printPositionsInfo("Sofia");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}


