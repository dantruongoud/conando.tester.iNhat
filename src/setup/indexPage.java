package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.time.Duration;

import login.Module;

public class indexPage {
    WebDriver driver;
    String idElement;

    @FindBy(xpath = "//img[@alt='Avatar']")
    public WebElement menuUser;

    @FindBy(css = "a[href='/dashboard']")
    public WebElement siteCorp;

    @FindBy(id = "search-key")
    private WebElement searchKey;

    @FindBy(css = "button[class='btn-search']")
    private WebElement searchButton;

    public indexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public indexPage(String idElement) {
        this.idElement = idElement;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean verifyTitle(String condition) {
        return driver.getTitle().equals(condition);
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void failed() {
        System.out.println("Status: FAILED");
        System.out.println("=======================");
    }

    public void passed() {
        System.out.println("Status: PASSED");
        System.out.println("=======================");
    }

    public String getNoti() {

        indexPage[] validation = {
                new indexPage("emailErr"),
                new indexPage("pwErr"),
                new indexPage("login-error-msg"),
                new indexPage("emailRegisterErr"),
                new indexPage("pwRegisterErr"),
                new indexPage("pwConfirmRegisterErr"),
                new indexPage("robotRegisterErr"),
                new indexPage("register-error"),
                new indexPage("vietnamese_name_add_error"),
                new indexPage("address_add_error"),
                new indexPage("phone_add_error"),
                new indexPage("email_add_error"),
                new indexPage("website_add_error"),
                new indexPage("owner_add_error"),
                new indexPage("info_add_error"),
                new indexPage("business_license_add_error"),
                new indexPage("contact_person_name_add_error"),
                new indexPage("contact_person_phone_add_error"),
                new indexPage("provinces_error"),
                
        };
        String notify_text = "";
        for (int i = 0; i < validation.length; i++) {
            notify_text = driver.findElement(By.id(validation[i].idElement)).getText().strip();
            if (notify_text.length() > 0) {
                System.out.println(notify_text);
                break;
            }

        }
        return notify_text;
    }

    public String getNotiClass() {
        try {
            String notify_text = "";
            indexPage[] validation = {
                    new indexPage("//p[@id='error-industry']"),
                    new indexPage("//p[@class='service-error']"),
            };

            for (int i = 0; i < validation.length; i++) {
                Thread.sleep(1000);
                notify_text = driver.findElement(By.xpath(validation[i].idElement)).getText().strip();
                if (notify_text.length() > 0) {
                    System.out.println(notify_text);
                    break;
                }
            }
            return notify_text;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void login() {
        try {
            login.Module login = new Module(driver);
            login.setText("admin@gmail.com", "123456789");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(String condition) {
        try {
            searchKey.sendKeys(condition);
            Thread.sleep(1000);
            searchButton.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upfile(String typeFind, String path) {
        try {
            WebElement upBtn;
            if (typeFind == "id") {
                upBtn = driver.findElement(By.id(path));
            } else if (typeFind == "class") {
                upBtn = driver.findElement(By.className(path));
            } else if (typeFind == "css") {
                upBtn = driver.findElement(By.cssSelector(path));
            } else {
                upBtn = driver.findElement(By.xpath(path));
            }
            String filePath = "C:\\Users\\Admin\\Downloads\\test3.jpg";

            // Click để mở form upload
            upBtn.click();
            Thread.sleep(3000);

            // Khởi tạo Robot class
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Copy File path vào Clipboard
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            Thread.sleep(2000);

            // Nhấn Control+V để dán
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            Thread.sleep(2000);

            // Nhấn Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);

            rb.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openFormCreate() {
        try {
            menuUser.click();
            Thread.sleep(1000);
            siteCorp.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
