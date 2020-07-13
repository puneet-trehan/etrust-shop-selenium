import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EtrustTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void verifyTitle() {
        driver.get("https://qa.trustedshops.com/buyerrating/info_X1C77CF6EE730D2E88A284D7203D1B20F.html");
        driver.manage().window().maximize();
        String actualTitle = driver.getTitle();
        String expectedTitle = "zalando.de Bewertungen & Erfahrungen | Trusted Shops";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void gradeCheck() {
        driver.get("https://qa.trustedshops.com/buyerrating/info_X1C77CF6EE730D2E88A284D7203D1B20F.html");
        driver.findElement(By.xpath("//div[@class='hidden-xs']/a[text()='Bekleidung']")).click();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> totalGrades = driver.findElements(By.className("shop-mark"));
        System.out.println("Total number of grades present in the page: " + totalGrades.size());
        String completeText;
        for (WebElement grade : totalGrades) {
            completeText = grade.getText();
            if (completeText.equals("")) {
                System.out.println("Grade of website: " + completeText + " Grade is zero");
            }
            else {
                System.out.println("Grade of website: " + completeText + " Grade is greater than zero");
            }
        }
        System.out.println("Scenario passed quit browser ");
        driver.quit();
    }

    @Test
    public void withAndWithoutGrades() throws InterruptedException {
        driver.get("https://qa.trustedshops.com/buyerrating/info_X1C77CF6EE730D2E88A284D7203D1B20F.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='hidden-xs']/a[text()='Bekleidung']")).click();
        Thread.sleep(3000);
        List<String> withGrade = new ArrayList<String>();
        List<String> withoutGrade = new ArrayList<String>();
        List<WebElement> webAddress = driver.findElements(By.xpath("//shop-info//div[contains(@class,'shop-name')]/div[2]"));
        for (int i = 1; i <= webAddress.size(); i++) {
            System.out.println("Grades: " + driver.findElement(By.xpath("//shop-info[" + i + "]//*[@class='shop-mark']")).getText());
            if (driver.findElement(By.xpath("//shop-info[" + i + "]//*[@class='shop-mark']")).getText().equals("")) {
                String withoutGrades = driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText();
                withoutGrade.add(withoutGrades);
                System.out.println("Websites with no Grades: " + withoutGrades);
            } else {
                String withGrades = driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText();
                withGrade.add(withGrades);
                System.out.println("Websites with Grades: " + withGrades);
            }
        }
        while (!driver.findElements(By.xpath("//li[@class='pagination-next page-item ng-star-inserted']//a")).isEmpty()) {
            try {
                driver.findElement(By.xpath("//li[contains(@class,'pagination-next')]//a")).click();
                System.out.println("Page number: "+ driver.findElement(By.xpath("//li[contains(@class,'pagination-page page-item active')]//a")).getText());
                Thread.sleep(7000);
                List<WebElement> websites = driver.findElements(By.xpath("//shop-info//div[contains(@class,'shop-name')]/div[2]"));
                for (int i = 1; i <= websites.size(); i++) {
                    System.out.println("Grades: " + driver.findElement(By.xpath("//shop-info[" + i + "]//*[@class='shop-mark']")).getText());
                    if (driver.findElement(By.xpath("//shop-info[" + i + "]//*[@class='shop-mark']")).getText().equals("")) {
                        String zero = driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText();
                        withoutGrade.add(zero);
                        System.out.println("Website without Grades: "+ zero);
                    } else {
                        String nonZero = driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText();
                        withGrade.add(nonZero);
                        System.out.println("Websites with Grades: "+nonZero);
                    }
                }
            } catch (StaleElementReferenceException ex) {
                driver.findElement(By.xpath("//li[contains(@class,'pagination-next')]//a")).click();
                System.out.println("Catch if element is stale: " + driver.findElement(By.xpath("//li[contains(@class,'pagination-page page-item active')]//a")).getText());
            }
        }
        System.out.println("Links with no rating: " + withGrade);
        System.out.println("Links with rating: " + withoutGrade);
    }
}
