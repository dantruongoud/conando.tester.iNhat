package price_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
// import java.awt.Robot;
// import java.awt.AWTException;
// import java.awt.datatransfer.StringSelection;
// import java.awt.Toolkit;
// import java.awt.event.KeyEvent;
import org.openqa.selenium.support.PageFactory;

public class Module {

    WebDriver driver;

    @FindBy(css = "a[href='/dashboard/quotasions']")
    public WebElement quotasion_navigation;

    @FindBy(css = "a[href='/dashboard/quotasions/add/']")
    public WebElement quotasion_add;

    @FindBy(css = ".input-wrap.input-invalid")
    private WebElement borderhighlight;

    @FindBy(id = "quotasion-name")
    private WebElement quotasion_name;

    @FindBy(id = "quotasion-title")
    private WebElement quotasion_title;

    @FindBy(id = "quotasion-title-content")
    private WebElement quotasion_title_content;

    @FindBy(css = "textarea[placeholder='Nhập mô tả'][name='content[]']")
    private WebElement quotasion_content;

    @FindBy(name = "price_name[]")
    private WebElement price_name;

    @FindBy(name = "price_quantity[]")
    private WebElement price_quantity;

    @FindBy(name = "price_price[]")
    private WebElement price_price;

    
    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCss() {
        String css = borderhighlight.getCssValue(".input-invalid").strip();
        System.out.println(css);
        return css;
    }

    // Tạo mới mẫu báo giá
    public static String create_price(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý mẫu báo giá')]")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/a")).click();
            Thread.sleep(1000);

            WebElement tieude = driver.findElement(By.id("quotasion-name"));
            WebElement name_title = driver.findElement(By.id("quotasion-title"));
            WebElement content = driver.findElement(By.name("content[]"));
            WebElement product = driver.findElement(By.xpath("(//input[@name='price_name[]'])[1]"));
            WebElement quantity = driver.findElement(By.xpath("(//input[@name='price_quantity[]'])[1]"));
            WebElement price = driver.findElement(By.xpath("(//input[@name='price_price[]'])[1]"));
            WebElement button = driver.findElement(By.id("submit-add-quotasion"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", button);
            Thread.sleep(2000);
            button.click();
            Thread.sleep(1000);
            Alert alert = driver.switchTo().alert();
            if (alert.getText() != null) {
                System.out.println("Tạo mới khi để rỗng tiêu đề lớn: " + alert.getText());
                alert.accept();
                Thread.sleep(1000);
                js.executeScript("arguments[0].scrollIntoView();", tieude);
                Thread.sleep(1000);
                tieude.sendKeys("Mẫu báo giá cho dịch vụ phòng chữa cháy");
                Thread.sleep(1500);
                js.executeScript("arguments[0].scrollIntoView();", button);
                Thread.sleep(1000);
                button.click();
                Thread.sleep(1000);
                if (alert.getText() != null) {
                    System.out.println("Tạo mới khi để rỗng tiêu đề nhỏ: " + alert.getText());
                    alert.accept();
                    Thread.sleep(1500);

                    // upload hình ảnh chưa được, Bổ sung sau
                    // driver.findElement(By.id("upload-quotasion-btn")).click();
                    // Thread.sleep(1500);
                    // // Khởi tạo Robot class
                    // Robot rb = null;
                    // try {
                    // rb = new Robot();
                    // } catch (AWTException e) {
                    // e.printStackTrace();
                    // }

                    // // Copy File path vào Clipboard
                    // String filePath = "C:\\Users\\Admin\\Downloads\\test1.webp";
                    // StringSelection str = new StringSelection(filePath);
                    // Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

                    // Thread.sleep(2000);

                    // // Nhấn Control+V để dán
                    // rb.keyPress(KeyEvent.VK_CONTROL);
                    // rb.keyPress(KeyEvent.VK_V);

                    // Thread.sleep(2000);

                    // // Nhấn Enter
                    // rb.keyPress(KeyEvent.VK_ENTER);
                    // rb.keyRelease(KeyEvent.VK_ENTER);
                    // rb.keyRelease(KeyEvent.VK_CONTROL);

                    // Thread.sleep(5000);

                    name_title.sendKeys("Số 1");
                    Thread.sleep(1000);
                    js.executeScript("arguments[0].scrollIntoView();", button);
                    button.click();
                    Thread.sleep(1000);
                    alert = driver.switchTo().alert();
                    if (alert.getText() != null) {
                        System.out.println("Tạo mới khi để rỗng mô tả mẫu báo giá: " + alert.getText());
                        alert.accept();
                        Thread.sleep(1500);
                        content.sendKeys(
                                "Đây mẫu báo giá dành cho 1 người dùng lần đầu tiên, nếu bạn hợp tác lâu dài và cần giá tốt hơn hãy liên hệ tôi trực tiếp");
                        Thread.sleep(1000);
                        js.executeScript("arguments[0].scrollIntoView();", button);
                        button.click();
                        Thread.sleep(1000);
                        alert = driver.switchTo().alert();
                        if (alert.getText() != null) {
                            System.out.println("Tạo mới khi chưa nhập sản phẩm và giá: " + alert.getText());
                            alert.accept();
                            Thread.sleep(1000);
                            product.sendKeys("Bình chữa cháy");
                            Thread.sleep(1000);
                            quantity.sendKeys("5");
                            Thread.sleep(1000);
                            price.sendKeys("150000");
                            Thread.sleep(1000);
                            button.click();
                        } else {
                            System.out.println("Không tìm thấy thông báo khi nhập rỗng sản phẩm báo giá");
                            driver.close();
                        }
                    } else {
                        System.out.println("Không tìm thấy thông báo khi nhập rỗng mô tả của mẫu báo giá");
                        driver.close();
                    }
                } else {
                    System.out.println("Không tìm thấy thông báo khi nhập rỗng tiêu đề nhỏ");
                    driver.close();
                }
            } else {
                System.out.println("Không tìm thấy thông báo khi nhập rỗng tiêu đề lớn");
                driver.close();
            }
            return "Hoàn tất thêm mới mẫu báo giá";
        } catch (Exception e) {
            e.printStackTrace();
            return "Tạo mẫu báo giá thất bại...";
        }
    }
}
