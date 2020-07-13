import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        String actualTitle = driver.getTitle();
        String expectedTitle = "zalando.de Bewertungen & Erfahrungen | Trusted Shops";
        Assert.assertEquals(actualTitle, expectedTitle);
    }


   @Test
    public void gradeCheck() {
        driver.get("https://qa.trustedshops.com/buyerrating/info_X1C77CF6EE730D2E88A284D7203D1B20F.html");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//div[@class='hidden-xs']/a[text()='Bekleidung']")).click();
        waitForPageToLoad();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        List<WebElement> totalGrades = driver.findElements(By.className("shop-mark"));
        System.out.println("Total number of grades in a page : " + totalGrades.size());
        for (WebElement allGrades : totalGrades) {
         String grade = allGrades.getText();
            if (grade.equals("")) {
                System.out.println("Grade of website: " + grade + " Grade is zero");
            } else {
                System.out.println("Grade of website: " + grade + " Grade is greater than zero ");
            }
        }
    }

    @Test
    public void withAndWithoutGrades() {
        driver.get("https://qa.trustedshops.com/buyerrating/info_X1C77CF6EE730D2E88A284D7203D1B20F.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='hidden-xs']/a[text()='Koffer, Taschen & Lederwaren']")).click();
        waitForPageToLoad();
        List<String> withGrade = new ArrayList<String>();
        List<String> withoutGrade = new ArrayList<String>();
        while (!driver.findElements(By.xpath("//li[@class='pagination-next page-item ng-star-inserted']//a")).isEmpty()){
            getGradeInfo(withGrade, withoutGrade);
            driver.findElement(By.xpath("//li[contains(@class,'pagination-next')]//a")).click();
        }
        getGradeInfo(withGrade, withoutGrade);
        System.out.println("Links with grade = " + withGrade.size());
        printList(withGrade);
        System.out.println("Links with no grade = " + withoutGrade.size());
        printList(withoutGrade);
    }

    private void printList(List<String> grade) {
        for (String result: grade) {
            System.out.println(result);
        }
    }

    private void getGradeInfo(List<String> withGrade, List<String> withoutGrade) {
        try {
            waitForPageToLoad();
            List<WebElement> websites = driver.findElements(By.xpath("//shop-info//div[contains(@class,'shop-name')]/div[2]"));
            for (int i = 1; i <= websites.size(); i++) {
                if (driver.findElement(By.xpath("//shop-info[" + i + "]//*[@class='shop-mark']")).getText().equals("")) {
                    withoutGrade.add(driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText());
                } else {
                    withGrade.add(driver.findElement(By.xpath("//shop-info[" + i + "]//div[contains(@class,'shop-name')]/div[2]")).getText());
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            driver.findElement(By.xpath("//li[contains(@class,'pagination-next')]//a")).click();
            System.out.println("In catch: " + driver.findElement(By.xpath("//li[contains(@class,'pagination-page page-item active')]//a")).getText());
        }
    }


    public void waitForPageToLoad(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        int i=0;

        while(i!=10){
            String state = (String)js.executeScript("return document.readyState;");
            if(state.equals("complete"))
                break;
            else
                wait(2);
            i++;
        }
        wait(2);// wait of 2 sec between page status and jquery
        // check for jquery status
        i=0;
        while(i!=10){

            Boolean result= (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
            if(result )
                break;
            else
                wait(2);
            i++;
        }

    }
    public void wait(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
