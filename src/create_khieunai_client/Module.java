package create_khieunai_client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class Module {

    WebDriver driver;

    @FindBy(css = "a[href='/dashboard/complains']")
    public WebElement complains_navigation;

    @FindBy(css = "a[href='/dashboard/complains/add/']")
    public WebElement add_navigation;

    @FindBy(name = "title")
    public WebElement title_input;

    @FindBy(css = ".complain__send-icon.mr-2")
    private WebElement sentBtn;

    @FindBy(xpath = "(//p[@id='title_error'])[1]")
    private WebElement noti;

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setText(String title) {
        try {
            title_input.sendKeys(title);
            sentBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNoti() {
        return noti.getText().strip();
    }

    // Tạo khiếu nại và gửi về admin
    public static String add_khieunai(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//a[@href='/dashboard/complains']//span[contains(text(),'Khiếu nại')]"))
                    .click();
            Thread.sleep(2000);

            driver.findElement(By.xpath(
                    "//a[@class='d-flex justify-content-center align-items-center btn edit-btn edit-btn--small add-new-quotasion']"))
                    .click();
            Thread.sleep(1500);

            WebElement tieude = driver.findElement(By.name("title"));
            WebElement button = driver.findElement(By.xpath("//button[@type='submit']//span[contains(text(),'Gửi')]"));
            button.click();
            Thread.sleep(1000);
            WebElement noti = driver.findElement(By.xpath("(//p[@id='title_error'])[1]"));
            if (noti != null) {
                System.out.println("Chưa nhập tiêu đề: " + noti.getText());
                tieude.sendKeys("Tôi muốn góp ý");
                Thread.sleep(1000);
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                rb.keyPress(KeyEvent.VK_TAB);
                rb.keyRelease(KeyEvent.VK_TAB);
                String copy = "Tôi là Nguyễn Đan Trường";

                StringSelection str = new StringSelection(copy);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str,
                        null);

                Thread.sleep(2000);

                // Nhấn Control+V để dán
                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);
                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);
                Thread.sleep(1000);
                button.click();
            } else {
                System.out.println("Không có validation cho tiêu đề");
                driver.close();
            }
            return "Hoàn tất thêm mới khiếu nại";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm mới khiếu nại thất bại";
        }
    }

    // Xử lý khiếu nại ở phân hệ Admin
    public static String repply_khieunai_admin(WebDriver driver) {
        try {
            driver.navigate().to("https://inhat-staging.thcmedia.vn/admincp");
            Thread.sleep(2500);
            driver.findElement(By.xpath("//span[contains(text(),'Chăm sóc khách hàng')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//h4[contains(text(),'Quản lí khiếu nại')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Tôi muốn");
            Thread.sleep(3000);
            List<WebElement> trList = driver.findElements(By.tagName("tr"));
            for (int i = 0; i < trList.size(); i++) {
                List<WebElement> tdList = trList.get(i).findElements(By.tagName("td"));
                if (tdList.size() == 6) {
                    WebElement active = tdList.get(4);
                    String active_text = active.getText().strip();
                    if (active_text.equals("Chưa xử lý")) {
                        WebElement aTag = tdList.get(0).findElement(By.xpath("//a[@class='badge bg-primary']"));
                        Thread.sleep(1000);
                        aTag.click();
                        Thread.sleep(1000);
                        break;
                    }
                }
            }
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            WebElement name_company = driver
                    .findElement(By.xpath("//p[@class='complain__company-name mb-md-2 mb-0']"));
            System.out.println("Bạn đang phản hồi với Doanh nghiệp " + name_company.getText());
            driver.findElement(By.xpath("//p[@class='complain__send-info']//img[@class='complain__sender-img']"))
                    .click();
            rb.keyPress(KeyEvent.VK_TAB);
            rb.keyRelease(KeyEvent.VK_TAB);
            String copy = "Tôi rất mệt mỏi rồi";
            StringSelection str = new StringSelection(copy);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str,
                    null);
            Thread.sleep(2000);
            // Nhấn Control+V để dán
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);
            driver.findElement(By.xpath("//span[@type='button']")).click();

            driver.findElement(By.xpath("//a[contains(text(),'Đánh dấu đã xử lý')]")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Tôi muốn");
            Thread.sleep(3000);
            trList = driver.findElements(By.tagName("tr"));
            for (int i = 0; i < trList.size(); i++) {
                List<WebElement> tdList = trList.get(i).findElements(By.tagName("td"));
                if (tdList.size() == 6) {
                    WebElement active = tdList.get(4);
                    String active_text = active.getText().strip();
                    if (active_text.equals("Đã xử lý")) {
                        System.out.println("Đã xử lý khiếu nại");
                        break;
                    } else {
                        System.out.println("Chưa xử lý khiếu nại");
                    }
                }
            }
            return "Hoàn tất phản hồi khiếu nại";
        } catch (Exception e) {
            e.printStackTrace();
            return "Phản hồi khiếu nại thất bại...";
        }
    }
}
