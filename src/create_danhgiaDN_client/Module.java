package create_danhgiaDN_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class Module {

    WebDriver driver;

    @FindBy(css = ".btn.edit-btn.edit-btn--small.secondary-color.report-review-js")
    public WebElement button_report;

    @FindBy(css = "button[class='btn edit-btn edit-btn--primary submit-report-business-btn']")
    private WebElement button_sent;

    @FindBy(css = "div[id='report-busines'] p[class='invalid-block']")
    private WebElement noti;

    @FindBy(css = "div[id='report-busines'] textarea[name='report_content']")
    public WebElement report_content;

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setText(String text) {
        report_content.sendKeys(text);
        button_sent.click();
    }

    public String getNoti() {
        return noti.getText().strip();
    }

    // Tạo báo cáo DN bất thường
    public static String report_DN_client(WebDriver driver) {
        try {
            driver.findElement(By.linkText("Xem tất cả")).click();
            Thread.sleep(4000);
            driver.findElement(By.name("key")).sendKeys("Công ty 4");
            // Khởi tạo Robot class
            Robot rb = new Robot();
            rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            driver.findElement(By.className("cards-company")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//button[@data-target='#report-busines']")).click();
            Thread.sleep(1000);
            WebElement noidung = driver
                    .findElement(By.xpath("//div[@id='report-busines']//textarea[@name='report_content']"));
            WebElement button = driver.findElement(
                    By.xpath("//button[@class='btn edit-btn edit-btn--primary submit-report-business-btn']"));
            button.click();
            Alert noti = driver.switchTo().alert();
            if (noti.getText() != null) {
                System.out.println(noti.getText());
                Thread.sleep(1000);
                noti.accept();
                Thread.sleep(1000);
                noidung.sendKeys("Tôi thích là tôi báo cáo để tôi test");
                Thread.sleep(1000);
                button.click();
                Thread.sleep(1000);
                noti = driver.switchTo().alert();
                if (noti.getText() != null) {
                    System.out.println(noti.getText());
                    Thread.sleep(1000);
                    noti.accept();
                }
            } else {
                System.out.println("Không tìm thấy thông báo khi để rỗng thông tin báo cáo");
                driver.close();
            }
            return "Hoàn tất gửi báo cáo";
        } catch (Exception e) {
            e.printStackTrace();
            return "Gửi báo cáo thất bại...";
        }
    }
}
