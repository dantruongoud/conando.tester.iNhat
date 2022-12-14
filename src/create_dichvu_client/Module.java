package create_dichvu_client;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import setup.indexPage;

public class Module {

    WebDriver driver;

    @FindBy(css = "a[href='/dashboard/services']")
    public WebElement navigation_services;

    @FindBy(xpath = "//button[@class='btn edit-btn edit-btn--primary ml-auto']")
    public WebElement groupService;

    @FindBy(id = "category-1")
    public WebElement clickCategory;

    @FindBy(id = "cate-1-industry-1")
    public WebElement clickIndustry;

    @FindBy(id = "service-2")
    public WebElement clickService;

    @FindBy(id = "btn-save-service-group")
    public WebElement saveBtn;

    @FindBy(css = "button[class='btn edit-btn edit-btn--primary add-service-btn']")
    public WebElement doneBtn;

    @FindBy(xpath = "//div[@id='create-services-1']//input[@placeholder='Nhập dịch vụ']")
    public WebElement serviceTitle_input;

    @FindBy(css = ".edit-btn.m-auto.px-3.plan-add-btn.w-100")
    public WebElement addBtn;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/div/div[2]/div[1]/div/input")
    public WebElement title_input;

    @FindBy(how = How.CSS, using = ".multi-collapse")
    private List<WebElement> listGroupMenu;

    @FindBy(how = How.CSS, using = ".list-item-dropdwn")
    public WebElement listItem;

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    indexPage index = new indexPage(driver);

    public void openForm() {
        try {
            navigation_services.click();
            index.waitForPageLoaded();
            groupService.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findCreateBtn() {
        try {
            for (int i = 0; i < listGroupMenu.size(); i++) {
                List<WebElement> listCategory = listGroupMenu.get(i).findElements(By.cssSelector(".header-text.mb-0"));
                if (listCategory.size() == 1) {
                    String name = listCategory.get(0).getText().strip();
                    if (name.equals("Viễn Thông - Các Công Ty Viễn Thông")) {
                        WebElement createBtn = listGroupMenu.get(i).findElement(By.tagName("button"));
                        createBtn.click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String nameService, String title_service) {
        try {
            serviceTitle_input.sendKeys(nameService);
            title_input.sendKeys(title_service);
            Thread.sleep(1000);
            doneBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear_input() {
        serviceTitle_input.clear();
        title_input.clear();
    }

    // Tạo mới nhóm dịch vụ, chưa được tách rời
    public static String create_tabdichvu(WebDriver driver) {
        try {

            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý dịch vụ')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[@class='btn edit-btn edit-btn--primary ml-auto'])[1]")).click();
            Thread.sleep(1500);

            // Chỗ này chưa có validation những thông tin bắt buộc * Bổ sung sau
            driver.findElement(By.xpath("(//input[@id='category-1'])[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("cate-1-industry-1")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("service-1")).click();
            Thread.sleep(1000);
            driver.findElement(
                    By.xpath("//div[@id='services-modal']//div[@class='modal__footer d-flex mt-auto']//button[1]"))
                    .click();
            Thread.sleep(1000);
            WebElement tieude = driver
                    .findElement(By.xpath("//div[@id='create-services']//input[@placeholder='Nhập dịch vụ']"));
            WebElement mota = driver
                    .findElement(By.xpath("//div[@id='create-services']//textarea[@placeholder='Mô tả']"));
            WebElement button = driver.findElement(By.xpath("//div[@id='create-services']//i[@class='fal fa-check']"));
            Thread.sleep(1000);
            button.click();
            Thread.sleep(1000);
            Alert noti = driver.switchTo().alert();
            if (noti != null) {
                System.out.println("Tạo mới khi để trống các thông tin bắt buộc: " + noti.getText());
                noti.accept();
                Thread.sleep(1000);
                tieude.sendKeys("Chuyên sửa chữa điện lạnh");
                Thread.sleep(1000);
                mota.sendKeys("Sửa chữa tận nới - dịch vụ cao - giá rẻ");
                Thread.sleep(1000);
                driver.findElement(By.xpath("//div[@id='create-services']//div[@class='d-flex pb-3']")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath(
                        "/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]"))
                        .sendKeys("Sửa chữa tận nhà");
                Thread.sleep(1000);
                driver.findElement(By.xpath(
                        "/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/textarea[1]"))
                        .sendKeys("Chất Lượng - Nhanh Chóng - Uy tín");
                Thread.sleep(1000);
                button.click();
            } else {
                System.out.println("Không tìm thấy thông tin cho mục này");
                driver.close();
            }

            return "Hoàn tất thêm nhóm dịch vụ";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm nhóm dịch vụ thất bại...";
        }
    }

    // Tạo mới nhóm DV
    public static String create_nhom(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý dịch vụ')]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@class='btn edit-btn edit-btn--primary ml-auto']")).click();
            Thread.sleep(1000);

            WebElement button = driver.findElement(By.id("btn-save-service-group"));
            button.click();
            WebElement noti = driver.findElement(By.id("error-industry"));
            if (noti != null) {
                System.out.println("Tạo nhóm DV khi chưa chọn niên giám: " + noti.getText());
                WebElement menu_niengiam = driver.findElement(By.id("modal-submenu1"));
                List<WebElement> sub_menu_niengiam = menu_niengiam
                        .findElements(
                                By.xpath("//div[@id='modal-submenu1']//div[@class='input-group align-items-center']"));
                WebElement input = sub_menu_niengiam.get(0).findElement(By.tagName("input"));
                input.click();
                System.out.println("Niên giám bạn chọn là: " + sub_menu_niengiam.get(0).getText().strip());
                Thread.sleep(1000);
                button.click();
                noti = driver.findElement(By.id("error-industry"));
                if (noti != null) {
                    System.out.println("Tạo nhóm DV khi chưa chọn ngành dịch vụ: " + noti.getText());
                    WebElement menu_niengiam2 = driver.findElement(By.id("modal-submenu2"));
                    List<WebElement> sub_menu_niengiam2 = menu_niengiam2
                            .findElements(By.xpath(
                                    "//div[@id='modal-submenu2']//div[@class='input-group align-items-center']"));
                    WebElement input2 = sub_menu_niengiam2.get(1).findElement(By.tagName("input"));
                    input2.click();
                    System.out.println("Ngành Dịch vụ bạn chọn là: " + sub_menu_niengiam2.get(1).getText().strip());
                    Thread.sleep(1000);
                    WebElement menu_niengiam3 = driver.findElement(By.id("modal-submenu3"));
                    List<WebElement> sub_menu_niengiam3 = menu_niengiam3
                            .findElements(By.xpath(
                                    "//div[@id='modal-submenu3']//div[@class='input-group align-items-center']"));
                    WebElement input3 = sub_menu_niengiam3.get(2).findElement(By.tagName("input"));
                    input3.click();
                    Thread.sleep(1000);
                    System.out.println("Nhóm Dịch vụ bạn chọn là: " + sub_menu_niengiam3.get(2).getText().strip());
                    button.click();
                } else {
                    System.out.println("Không có validation khi để chưa chọn ngành dịch vụ");
                    driver.close();
                }
            } else {
                System.out.println("Không có validation khi để chưa chọn niên giám");
                driver.close();
            }
            return "Hoàn tất thêm mới nhóm DV";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm mới nhóm DV thất bại...";
        }
    }

    // Chọn nhóm DV vừa tạo để add gói dịch vụ
    public static String create_DV(WebDriver driver) {
        try {
            Thread.sleep(2000);

            List<WebElement> list_button = driver
                    .findElements(By.xpath("//div[@class='d-flex align-items-center mb-3']"));
            for (int i = 0; i < list_button.size(); i++) {
                List<WebElement> tagname = list_button.get(i).findElements(By.tagName("p"));
                if (tagname.size() == 1) {
                    String p = tagname.get(0).getText().strip();
                    if (p.equals("Dịch Vụ Bảo Vệ")) {
                        WebElement button = list_button.get(i).findElement(By.tagName("button"));
                        button.click();
                        Thread.sleep(2000);
                        Select select = new Select(
                                driver.findElement(By.xpath("(//select[@name='service_group'])[2]")));
                        select.selectByVisibleText(
                                "An Ninh, Viễn Thông/Phòng Cháy Chữa Cháy/Thi Công Lắp Đặt Bảo Trì Hệ Thống Phòng Cháy Chữa Cháy");
                        WebElement tieude = driver.findElement(By.xpath(
                                "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[1]/div[1]/div/input"));
                        Thread.sleep(1000);
                        WebElement done = driver
                                .findElement(By.xpath(
                                        "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[3]/button[1]"));
                        done.click();
                        WebElement noti = driver
                                .findElement(By.xpath(
                                        "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/p[1]"));
                        if (noti != null) {
                            System.out.println("Thêm mới khi để trống tên Dịch vụ: " + noti.getText());
                            tieude.sendKeys("Dịch vụ lắp đặt thiết bị chữa cháy");
                            Thread.sleep(1000);
                            JavascriptExecutor js = (JavascriptExecutor) driver;
                            js.executeScript("window.scrollBy(0,3050)", "");
                            done.click();
                            noti = driver
                                    .findElement(By.xpath(
                                            "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/p[1]"));
                            if (noti != null) {
                                System.out.println("Thêm mới khi chưa tạo gói Dịch vụ: " + noti.getText());
                                driver.findElement(
                                        By.xpath("(//button[@class='edit-btn m-auto px-3 plan-add-btn w-100'])[1]"))
                                        .click();
                                done.click();
                                noti = driver
                                        .findElement(
                                                By.xpath(
                                                        "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/div/p[1]"));
                                if (noti != null) {
                                    System.out.println("Thêm mới khi để trống tên gói dịch vụ: " + noti.getText());
                                    WebElement tieude_goi = driver
                                            .findElement(By.xpath(
                                                    "/html/body/div[1]/div[2]/div/div[2]/div[2]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/div/div[2]/div[1]/div/input"));
                                    tieude_goi.sendKeys("Gói cơ bản số 1");
                                    Thread.sleep(1000);
                                    done.click();
                                } else {
                                    System.out.println("Không tìm thấy thông báo khi để trống tên gói dịch vụ");
                                    driver.close();
                                }
                            } else {
                                System.out.println("Không tìm thấy thông báo khi chưa thêm gói dịch vụ");
                                driver.close();
                            }
                        } else {
                            System.out.println("Không tìm thấy thông báo khi để trống tên dịch vụ");
                            driver.close();
                        }
                    }
                }
            }
            return "Hoàn tất thêm gói dịch vụ";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thêm gói dịch vụ thất bại...";
        }
    }
}
