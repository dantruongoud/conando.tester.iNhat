package edit_del_DV_client;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Module {

    // Chỉnh sửa nhóm dịch vụ
    public static String edit_nhomDV(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý dịch vụ')]")).click();
            Thread.sleep(2000);

            List<WebElement> list_button = driver
                    .findElements(By.xpath("//div[@class='header-list mb-1 head-list']"));
            Thread.sleep(1000);
            for (int i = 0; i < list_button.size(); i++) {
                List<WebElement> pTag = list_button.get(i).findElements(By.tagName("p"));
                if (pTag.size() == 1) {
                    String p = pTag.get(0).getText().strip();
                    if (p.equals("Thi Công Lắp Đặt Bảo Trì Hệ Thống Phòng Cháy Chữa Cháy")) {
                        System.out.println("Bạn đang chỉnh sửa nhóm DV: " + p);
                        WebElement menu_edit = list_button.get(i).findElement(By.tagName("i"));
                        menu_edit.click();
                        Thread.sleep(1000);
                        driver.findElement(By.xpath(
                                "//div[@class='menu--edit active']//a[@class='d-flex align-items-center group-edit group-update']"))
                                .click();
                        Thread.sleep(1000);
                        // Chọn lại nhóm dịch vụ khác
                        WebElement menu_niengiam3 = driver.findElement(By.id("modal-submenu3"));
                        List<WebElement> sub_menu_niengiam3 = menu_niengiam3
                                .findElements(By.xpath(
                                        "//div[@id='modal-submenu3']//div[@class='input-group align-items-center']"));
                        WebElement input3 = sub_menu_niengiam3.get(1).findElement(By.tagName("input"));
                        input3.click();
                        Thread.sleep(1000);
                        driver.findElement(By.id("btn-save-service-group")).click();
                        break;
                    }
                }
            }
            return "Hoàn tất chỉnh sửa nhóm DV";
        } catch (Exception e) {
            e.printStackTrace();
            return "Chỉnh sửa nhóm DV thất bại...";
        }
    }

    // Xóa nhóm DV dư thừa
    public static String del_nhomDV(WebDriver driver) {
        try {
            Thread.sleep(2000);
            List<WebElement> list_button = driver
                    .findElements(By.xpath("//div[@class='header-list mb-1 head-list']"));
            Thread.sleep(1000);
            for (int i = 0; i < list_button.size(); i++) {
                List<WebElement> pTag = list_button.get(i).findElements(By.tagName("p"));
                if (pTag.size() == 1) {
                    String p = pTag.get(0).getText().strip();
                    if (p.equals("Thi Công Lắp Đặt Bảo Trì Hệ Thống Phòng Cháy Chữa Cháy")) {
                        System.out.println("Bạn đang xóa nhóm DV: " + p);
                        WebElement menu_edit = list_button.get(i).findElement(By.tagName("i"));
                        menu_edit.click();
                        Thread.sleep(1000);
                        WebElement del = driver.findElement(By.xpath(
                                "//div[@class='menu--edit active']//a[@class='d-flex align-items-center group-edit delete-group-btn'][normalize-space()='Xóa']"));
                        del.click();
                        Thread.sleep(1000);
                        List<WebElement> del_poup = driver
                                .findElements(
                                        By.xpath("//div[@id='modal-delete-group']//div[@class='modal-body p-0']"));
                        for (int j = 0; j < del_poup.size(); j++) {
                            List<WebElement> name_del = del_poup.get(j).findElements(By.tagName("p"));
                            System.out.println(name_del.get(0).getText());
                            System.out.println(name_del.get(2).getText());
                            driver.findElement(By.id("btn-delete-service-group")).click();
                            System.out.println("Xóa thành công");
                        }
                        break;
                    }
                }
            }
            return "Hoàn tất xóa";
        } catch (Exception e) {
            e.printStackTrace();
            return "Xóa thất bại...";
        }
    }
}