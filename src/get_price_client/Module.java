package get_price_client;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Module {

    // Chọn Dv cần lấy mẫu báo giá
    public static String chose_DV(WebDriver driver) {
        try {
            driver.findElement(By.linkText("Xem tất cả")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='cards-company'])[1]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//a[contains(text(),'Dịch vụ cung cấp')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//div[contains(text(),'An Ninh, Viễn Thông')]//i")).click();
            Thread.sleep(1000);
            driver.findElement(By
                    .xpath("//ul[@class='sub-sub-content']//span[@data-toggle='modal'][contains(text(),'Dịch vụ C')]"))
                    .click();
            Thread.sleep(1500);
            driver.findElement(By.cssSelector("div[id='service-47'] a[type='button']")).click();
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-1350)", "");
            return "Hoàn tất nhận báo giá";
        } catch (Exception e) {
            e.printStackTrace();
            return "Nhận báo giá thất bại...";
        }
    }
}
