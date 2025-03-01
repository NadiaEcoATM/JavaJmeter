package tests;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaJmeter_actualBrowser implements JavaSamplerClient {
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
            Thread.sleep(3000);

            //Placing Bids Hand-on Table
            for(int i=1; i<7; i++) {
                driver.findElement(By.xpath(String.format(eachRow_bidPrice,String.valueOf(i)))).click();
                driver.findElement(By.xpath(String.format(eachRow_bidPrice,String.valueOf(i)))).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
                driver.findElement(By.xpath(String.format(eachRow_bidPrice,String.valueOf(i)))).sendKeys(randomBidPrice());
                Thread.sleep(2000);
                driver.findElement(By.xpath(String.format(eachRow_bidQty,String.valueOf(i)))).click();
                driver.findElement(By.xpath(String.format(eachRow_bidQty,String.valueOf(i)))).sendKeys("1");
                Thread.sleep(2000);
            }

            //Submitting Bids
            driver.findElement(By.xpath(submitBid_button)).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath(submitted_closeModal_button)).click();

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
