package tests;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class JavaJmeter_Main {

    // Define XPaths
    private static final String email_field = "//input[contains(@id,'Login_Custom_Web.loginemail')]";
    private static final String email_nextButton = "//button[contains(@data-button-id,'Login_Custom_Web.actionButtonNext')]";
    private static final String password_field = "//input[contains(@id,'Login_Custom_Web.loginpassword')]";
    private static final String login_button = "//button[contains(@data-button-id,'Login_Custom_Web.actionButtonSignIn')]";
    private static final String handon_table = "//div[@id='ecoATMTable']";
    private static final String eachRow = "(//table[@class='htCore']//tbody[@role='rowgroup'])[1]/tr[%s]";
    private static final String eachRow_bidPrice = eachRow + "/td[10]";
    private static final String eachRow_bidQty = eachRow + "/td[11]";
    private static final String submitBid_button = "//button[@title='Bidder Submit']";
    private static final String submitted_closeModal_button = "//button[contains(@class,'mx-name-actionButtonSubmit')]";
    private static final String userAccount_icon = "//a[(@class='mx-link mx-name-actionButton3 circlebase usericon_settings')]";
    private static final String userAccount_logout_btn = "//a[(@class='mx-link mx-name-actionButton1 usericon_settings_item')]";
    private static final String export_button = "//button[contains(@class,'mx-name-actionButton1 export')]";
    private static final String import_button = "(//button[contains(@class,'mx-name-actionButton5')])[2]";
    private static final String browseFile_button = "//button[@id='15.EcoATM_BidData.PG_BidData_XMLUpload_BidRound.fileManager1_nmj_181-upload']";
    private static final String fileInput_filed = "//input[@type='file']";
    private static final String importSubmit_button = "//button[contains(@data-button-id,'BidData_XMLUpload_BidRound')]";
    private static final String submit_importModal_button = "(//button[text()='Submit Bids'])[2]";

    public static void main(String[] args) {
        String username = "ecoauc.automation1@anything.com";
        String password = "ecoATM123$";

        WebDriver driver = null;

        try {
            // 🔧 Set the path to geckodriver
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\nadia.boonnayanont\\Documents\\Jmeter\\geckodriver.exe");
            driver = new FirefoxDriver();
            WebDriverWait wait = new WebDriverWait(driver, 30);

            // 🔐 Login
            driver.get("https://buy-uat.ecoatmdirect.com/p/login/web");
            Thread.sleep(3000);

            driver.findElement(By.xpath(email_field)).sendKeys(username);
            driver.findElement(By.xpath(email_nextButton)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(password_field)).sendKeys(password);
            driver.findElement(By.xpath(login_button)).click();
            Thread.sleep(15000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(handon_table)));
            System.out.println("✅ Login and table loaded.");

            // 🛠 Place bids
//            for (int i = 1; i < 7; i++) {
//                driver.findElement(By.xpath(String.format(eachRow_bidPrice, i))).click();
//                driver.findElement(By.xpath(String.format(eachRow_bidPrice, i)))
//                        .sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
//                driver.findElement(By.xpath(String.format(eachRow_bidPrice, i)))
//                        .sendKeys(randomBidPrice());
//                Thread.sleep(2000);
//
//                driver.findElement(By.xpath(String.format(eachRow_bidQty, i))).click();
//                driver.findElement(By.xpath(String.format(eachRow_bidQty, i))).sendKeys("1");
//                Thread.sleep(2000);
//            }

            // 📤 Submit bid
//            driver.findElement(By.xpath(submitBid_button)).click();
//            Thread.sleep(5000);
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(submitted_closeModal_button))).click();
//            System.out.println("✅ Bid submitted.");

            // Export File
//            driver.findElement(By.xpath(export_button)).click();
//            System.out.println("--------  Exported File -------------");
//            Thread.sleep(90000);


            // Import File
            System.out.println("--------  Importing File -------------");
            driver.findElement(By.xpath(import_button)).click();
            String filePath = "C:\\Users\\nadia.boonnayanont\\Downloads\\AA1WHL_2025_Wk32_Round1.xlsx";
            File fileToUpload = new File(filePath);
            if(!fileToUpload.exists()) {
                System.out.println(" File Not Found : " + fileToUpload.getAbsolutePath());
                return;
            }
            driver.findElement(By.xpath(fileInput_filed)).sendKeys(fileToUpload.getAbsolutePath());
            Thread.sleep(90000);
            System.out.println("----------  Input File --------------");
            driver.findElement(By.xpath(importSubmit_button)).click();
            Thread.sleep(90000);
            System.out.println("----------  Clicked Import File -------");
            driver.findElement(By.xpath(submit_importModal_button)).click();
            Thread.sleep(90000);
            //driver.findElement(By.xpath(submitBid_button)).click();


            // 🚪 Log out
            driver.findElement(By.xpath(userAccount_icon)).click();
            driver.findElement(By.xpath(userAccount_logout_btn)).click();
            Thread.sleep(2000);

            System.out.println("✅ Test finished successfully!");

        } catch (Exception e) {
            System.out.println("❌ Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static String randomBidPrice() {
        double price = 100 + Math.random() * 1000;
        return String.format("%.2f", price);
    }

}
