package tests;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;

public class JavaJmeter_actualBrowser_ACCP_UploadFile implements JavaSamplerClient {
    private String username;
    private String password;
    private final String email_field = "//input[contains(@id,'Login_Custom_Web.loginemail')]";
    private final String email_nextButton = "//button[contains(@data-button-id,'Login_Custom_Web.actionButtonNext')]";
    private final String password_field = "//input[contains(@id,'Login_Custom_Web.loginpassword')]";
    private final String login_button = "//button[contains(@data-button-id,'Login_Custom_Web.actionButtonSignIn')]";
    private final String handon_table = "//div[@id='ecoATMTable']";
    private  final String eachRow = "(//table[@class='htCore']//tbody[@role='rowgroup'])[1]/tr[%s]";
    private final String eachRow_bidPrice = eachRow + "/td[10]";
    private final String eachRow_bidQty = eachRow + "/td[11]";
    private final String submitBid_button = "//button[@title='Bidder Submit']";
    private final String submitted_closeModal_button = "//button[contains(@class,'mx-name-actionButtonSubmit')]";
    private final String userAccount_icon = "//a[(@class='mx-link mx-name-actionButton3 circlebase usericon_settings')]";
    private final String userAccount_logout_btn = "//a[(@class='mx-link mx-name-actionButton1 usericon_settings_item')]";
    private static final String export_button = "//button[contains(@class,'mx-name-actionButton1 export')]";
    private static final String import_button = "(//button[contains(@class,'mx-name-actionButton5')])[2]";
    private static final String browseFile_button = "//button[@id='15.EcoATM_BidData.PG_BidData_XMLUpload_BidRound.fileManager1_nmj_181-upload']";
    private static final String fileInput_filed = "//input[@type='file']";
    private static final String importSubmit_button = "//button[contains(@data-button-id,'BidData_XMLUpload_BidRound')]";
    private static final String submit_importModal_button = "(//button[text()='Submit Bids'])[2]";

    @Override
    public void setupTest(JavaSamplerContext context) {
        username = context.getParameter("username");
        password = context.getParameter("password");
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments defaultParameters = new Arguments();
        defaultParameters.addArgument("username", "defaultUser");
        defaultParameters.addArgument("password", "defaultUser");
        return defaultParameters;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        SampleResult result = new SampleResult();
        result.sampleStart();
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\nadia.boonnayanont\\Documents\\Jmeter\\geckodriver.exe");
            driver = new FirefoxDriver();

            //Logging In
            driver.get("https://buy-qa.ecoatmdirect.com/p/login/web");
            Thread.sleep(3000);
            driver.findElement(By.xpath(email_field)).sendKeys(username);
            driver.findElement(By.xpath(email_nextButton)).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath(password_field)).sendKeys(password);
            driver.findElement(By.xpath(login_button)).click();
            Thread.sleep(20000);



            // Import File
            System.out.println("--------  Importing File -------------");
            driver.findElement(By.xpath(import_button)).click();
            Thread.sleep(3000);
            String filePath = "C:\\Users\\nadia.boonnayanont\\Downloads\\AA1WHL_2025_Wk30_Round1.xlsx";
            File fileToUpload = new File(filePath);
            if(!fileToUpload.exists()) {
                System.out.println(" File Not Found : " + fileToUpload.getAbsolutePath());
            }
            driver.findElement(By.xpath(fileInput_filed)).sendKeys(fileToUpload.getAbsolutePath());
            Thread.sleep(3000);
            System.out.println("----------  Input File --------------");
            driver.findElement(By.xpath(importSubmit_button)).click();
            Thread.sleep(60000);
            System.out.println("----------  Clicked Import File -------");
            driver.findElement(By.xpath(submit_importModal_button)).click();
            Thread.sleep(5000);


            //Logging Out
            driver.findElement(By.xpath(userAccount_icon)).click();
            driver.findElement(By.xpath(userAccount_logout_btn)).click();
            Thread.sleep(2000);

        }catch(Exception e) {
            result.setSuccessful(false);
            result.setResponseMessage("Error: " + e.getMessage());
        } finally {
            result.sampleEnd();
            if(driver !=null) {
                driver.quit();
            }
        }
        return result;
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {

    }

    public static String randomBidPrice() {
        double price = 100 + Math.random() * 1000;
        return String.format("%.2f", price);
    }
}
