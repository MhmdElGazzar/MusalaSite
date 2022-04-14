import com.shaft.driver.SHAFT;
import com.shaft.dsl.gui.Button;
import com.shaft.dsl.gui.Element;
import org.openqa.selenium.By;

public class MusalaWebsite {
    SHAFT.GUI.WebDriver driver;
    public Button Careers = new Button(By.xpath("//*[@id='navbar']/..//a[text()='Careers'][1]"));
    public Button CheckOpenPositions = new Button(By.xpath("//*[text()='Check our open positions']"));
    public Button Apply= new Button(By.className("btn-apply"));
    public MusalaWebsite(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
        Element.setDriver(driver.getDriver());
        openMainPage();
        driver.element().click(By.id("wt-cli-accept-all-btn"));
    }

    public void openMainPage() {
        driver.browser().navigateToURL(System.getProperty("SITE_URL"));
    }
    public void containsHeader(String Text) {
        driver.assertThat().element(By.xpath("//h2[text()='"+Text+"']")).exists().perform();    }
    public void openVacancy(String Text) {
        driver.element().click(By.xpath("//div[2]/img[@alt='"+Text+"']"));
    }
    public void checkUrlText(String Text) {
        driver.assertThat().browser().url().isEqualTo(Text).perform();
    }

    public void printPositionsInfo(String city)
    {
        System.out.println(city);
        driver.element().select(By.id("get_location"),city);
        int count=driver.getDriver().findElements(By.xpath("//article//h2")).size();
        for (int i=1;i<=count;i++)
        {
            String position= driver.element().getText(By.xpath("//article["+i+"]//h2"));
            System.out.println("Position: "+ position);
            String link = driver.element().getAttribute(By.xpath("//article["+i+"]//a"),"href");
            System.out.println("For More Info: "+ link);
        }

    }
}
