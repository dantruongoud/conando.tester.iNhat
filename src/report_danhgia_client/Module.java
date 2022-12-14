package report_danhgia_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Module {

    // Báo cáo đánh giá bất thường
    public static String report_danhgia(WebDriver driver) {
        try {

            driver.findElement(By.xpath("//input[@placeholder='What are you looking for today?']")).sendKeys("THC");
            driver.findElement(By.xpath("//button[@class='btn-search']")).click();
            Thread.sleep(2500);
            driver.findElement(By.className("cards-company")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(text(),'Đánh giá')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("report-1")).click();
            Thread.sleep(1000);

            WebElement noidung = driver.findElement(By.name("report_content"));
            WebElement button = driver
                    .findElement(By.xpath("//button[@class='btn edit-btn edit-btn--primary submit-report-btn']"));
            button.click();
            Thread.sleep(1000);
            Alert noti = driver.switchTo().alert();
            if (noti != null) {
                System.out.println("Báo cáo đánh giá khi chưa nhập nội dung: " + noti.getText());
                noti.accept();
                Thread.sleep(1000);
                noidung.sendKeys("Tôi thích thì tôi báo cáo được không?");
                button.click();
                Thread.sleep(1000);
                noti = driver.switchTo().alert();
                if (noti != null) {
                    System.out.println(noti.getText());
                    noti.accept();
                }
            } else {
                System.out.println("Không tìm thấy thông báo cho mục này");
                driver.close();
            }
            return "Hoàn thành báo cáo đánh giá bất thường";
        } catch (Exception e) {
            e.printStackTrace();
            return "Báo cáo đánh giá thất bại...";
        }
    }
}
