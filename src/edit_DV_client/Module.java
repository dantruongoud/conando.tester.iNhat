package edit_DV_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Module {

    // Chỉnh sửa Dịch vụ của DN
    public static String edit_DV(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý dịch vụ')]")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//li/div/i")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath(
                    "//div[@class='menu--edit active']//a[@class='d-flex align-items-center']"))
                    .click();
            Thread.sleep(1000);
            WebElement tieude = driver.findElement(By.xpath("//input[@value='Dịch vụ C']"));
            WebElement mota = driver
                    .findElement(By.xpath("//textarea[@name='service_description'][contains(text(),'Dịch vụ C')]"));
            WebElement tieude_goiDV = driver.findElement(By.xpath("//input[@value='Dịch vụ C2']"));
            WebElement mota_DV = driver
                    .findElement(By.xpath("//textarea[@name='plan_description[]'][contains(text(),'Dịch vụ C')]"));
            WebElement button = driver.findElement(By.cssSelector(
                    "div[id='update-service-47'] div[class='modal-footer justify-content-start p-0'] button:nth-child(1)"));
            tieude.clear();
            Thread.sleep(1000);
            button.click();
            Thread.sleep(1000);
            Alert noti = driver.switchTo().alert();
            if (noti.getText() != null) {
                System.out.println("Chỉnh sửa khi để trống tên dịch vụ: " + noti.getText());
                noti.accept();
                Thread.sleep(1000);
                tieude.sendKeys("Dịch vụ lái xe thuê");
                Thread.sleep(1000);
                mota.clear();
                Thread.sleep(1000);
                button.click();
                if (noti.getText() != null) {
                    System.out.println("Chỉnh sửa khi để trống mô tả dịch vụ: " + noti.getText());
                    noti.accept();
                    Thread.sleep(1000);
                    mota.sendKeys("Đây là dịch vụ đăng cấp nhất Việt Nam");
                    Thread.sleep(1000);
                    tieude_goiDV.clear();
                    Thread.sleep(1000);
                    button.click();
                    noti = driver.switchTo().alert();
                    if (noti.getText() != null) {
                        System.out.println("Chỉnh sửa khi để trống tiêu đề gói dịch vụ: " + noti.getText());
                        noti.accept();
                        Thread.sleep(1000);
                        tieude_goiDV.sendKeys("Xe tự động");
                        Thread.sleep(1000);
                        mota_DV.clear();
                        Thread.sleep(1000);
                        button.click();
                        if (noti.getText() != null) {
                            System.out.println("Chỉnh sửa khi để trống mô tả gói dịch vụ: " + noti.getText());
                            noti.accept();
                            Thread.sleep(1000);
                            mota_DV.sendKeys("Xe tự động lái đó cưng");
                            Thread.sleep(1000);
                            button.click();
                        } else {
                            System.out.println("Không có thông báo khi để trống mô tả của gói dịch vụ");
                            driver.close();
                        }
                    } else {
                        System.out.println("Không có thông báo khi để trống tiêu đề của gói dịch vụ");
                        driver.close();
                    }
                } else {
                    System.out.println("Không có thông báo khi để trống mô tả dịch vụ");
                    driver.close();
                }
            } else {
                System.out.println("Không có thông báo khi để trống tên dịch vụ");
                driver.close();
            }
            return "Hoàn tất chỉnh sửa DV";
        } catch (Exception e) {
            e.printStackTrace();
            return "chỉnh sửa DV thất bại...";
        }
    }
}
