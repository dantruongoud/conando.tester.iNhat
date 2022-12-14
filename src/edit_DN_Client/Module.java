package edit_DN_Client;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class Module {

    WebDriver driver;

    @FindBy(css = "a[href='/dashboard/business']")
    private WebElement business_navigation;

    @FindBy(css = "a[href='/dashboard/business/edit/']")
    private WebElement edit_navigation;

    @FindBy(css = "button[class='btn profile__btn profile__btn--send order-md-0 order-1 mr-md-1 ml-md-0 ml-1 selectbig']")
    public WebElement requestBtn;

    @FindBy(css = "form#profile-edit.flex-1.px-md-4.px-3")
    public WebElement profile_edit;

    @FindBy(id = "activityProvinceEdit")
    public WebElement activityProvinceEdit;
    String element;

    public Module(String element) {
        this.element = element;
    }

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openEditor() {
        try {
            business_navigation.click();
            Thread.sleep(2000);
            edit_navigation.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextEdit(String name, String add, String phone, String email, String website, String owner,
            String nameContact, String phoneContact) {
        try {
            WebElement name_company = profile_edit.findElement(By.name("vietnamese_name"));
            WebElement address = profile_edit.findElement(By.name("address"));
            WebElement phone_input = profile_edit.findElement(By.name("phone"));
            WebElement email_input = profile_edit.findElement(By.name("email"));
            WebElement website_input = profile_edit.findElement(By.name("website"));
            WebElement owner_input = profile_edit.findElement(By.name("owner"));
            WebElement name_contact = profile_edit.findElement(By.name("contact_person_name"));
            WebElement phone_contact = profile_edit.findElement(By.name("contact_person_phone"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", name_company);
            Thread.sleep(1000);
            name_company.sendKeys(name);
            js.executeScript("arguments[0].scrollIntoView();", address);
            Thread.sleep(1000);
            address.sendKeys(add);
            js.executeScript("arguments[0].scrollIntoView();", phone_input);
            Thread.sleep(1000);
            phone_input.sendKeys(phone);
            js.executeScript("arguments[0].scrollIntoView();", email_input);
            Thread.sleep(1000);
            email_input.sendKeys(email);
            js.executeScript("arguments[0].scrollIntoView();", website_input);
            Thread.sleep(1000);
            website_input.sendKeys(website);
            js.executeScript("arguments[0].scrollIntoView();", owner_input);
            Thread.sleep(1000);
            owner_input.sendKeys(owner);
            js.executeScript("arguments[0].scrollIntoView();", name_contact);
            Thread.sleep(1000);
            name_contact.sendKeys(nameContact);
            js.executeScript("arguments[0].scrollIntoView();", phone_contact);
            Thread.sleep(1000);
            phone_contact.sendKeys(phoneContact);
            js.executeScript("arguments[0].scrollIntoView();", requestBtn);
            Thread.sleep(1000);
            requestBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear_input() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement name_company = profile_edit.findElement(By.name("vietnamese_name"));
            WebElement address = profile_edit.findElement(By.name("address"));
            WebElement phone_input = profile_edit.findElement(By.name("phone"));
            WebElement email_input = profile_edit.findElement(By.name("email"));
            WebElement website_input = profile_edit.findElement(By.name("website"));
            WebElement owner_input = profile_edit.findElement(By.name("owner"));
            WebElement name_contact = profile_edit.findElement(By.name("contact_person_name"));
            WebElement phone_contact = profile_edit.findElement(By.name("contact_person_phone"));
            js.executeScript("arguments[0].scrollIntoView();", name_company);
            Thread.sleep(1000);
            name_company.clear();
            js.executeScript("arguments[0].scrollIntoView();", address);
            Thread.sleep(1000);
            address.clear();
            js.executeScript("arguments[0].scrollIntoView();", phone_input);
            Thread.sleep(1000);
            phone_input.clear();
            js.executeScript("arguments[0].scrollIntoView();", email_input);
            Thread.sleep(1000);
            email_input.clear();
            js.executeScript("arguments[0].scrollIntoView();", website_input);
            Thread.sleep(1000);
            website_input.clear();
            js.executeScript("arguments[0].scrollIntoView();", owner_input);
            Thread.sleep(1000);
            owner_input.clear();
            js.executeScript("arguments[0].scrollIntoView();", name_contact);
            Thread.sleep(1000);
            name_contact.clear();
            js.executeScript("arguments[0].scrollIntoView();", phone_contact);
            Thread.sleep(1000);
            phone_contact.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNoti() {
        Module[] pathElement = {
                new Module("vietnamese_name_error"),
                new Module("address_error"),
                new Module("phone_error"),
                new Module("email_error"),
                new Module("website_error"),
                new Module("owner_error"),
                new Module("contact_person_name_error"),
                new Module("contact_person_phone_error"),
                new Module("provinces_error_edit"),
        };
        String noti = "";
        for (int i = 0; i < pathElement.length; i++) {
            noti = driver.findElement(By.id(pathElement[i].element)).getText().strip();
            if (noti.length() > 0) {
                System.out.println(noti);
                break;
            }
        }
        return noti;
    }

    // G???i y??u c???u ch???nh s???a DN t??? Client
    public static String edit_DN_Client(WebDriver driver) {
        try {

            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Ch??? ????? doanh nghi???p')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//span[contains(text(),'H??? s?? doanh nghi???p')]")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[2]/a[1]")).click();
            Thread.sleep(1000);

            WebElement name_company = driver.findElement(By.name("vietnamese_name"));
            WebElement address = driver.findElement(By.name("address"));
            WebElement phone = driver.findElement(By.name("phone"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement websie = driver.findElement(By.name("website"));
            WebElement owner = driver.findElement(By.name("owner"));
            WebElement name_contact = driver.findElement(By.name("contact_person_name"));
            WebElement phone_contact = driver.findElement(By.name("contact_person_phone"));

            name_company.clear();
            Thread.sleep(1000);
            name_company.click();
            Thread.sleep(1000);
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
            WebElement noti = driver.findElement(By.id("vietnamese_name_error"));
            if (noti != null) {
                System.out.println("Ch???nh s???a khi ????? tr???ng t??n doanh nghi???p: " + noti.getText());
                name_company.sendKeys("C??ng ty TNHH THC Media");
                Thread.sleep(1000);
                address.clear();
                Thread.sleep(1000);
                address.click();
                Thread.sleep(1000);
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(1000);
                noti = driver.findElement(By.id("address_error"));
                if (noti != null) {
                    System.out.println("Ch???nh s???a khi ????? tr???ng ?????a ch??? DN: " + noti.getText());
                    address.sendKeys("23 Tr?????ng Thi 1");
                    Thread.sleep(1000);
                    Select select = new Select(driver.findElement(By.name("province_id")));
                    select.selectByVisibleText("???? N???ng");
                    Thread.sleep(1000);
                    phone.clear();
                    Thread.sleep(1000);
                    phone.click();
                    Thread.sleep(1000);
                    rb.keyPress(KeyEvent.VK_ENTER);
                    rb.keyRelease(KeyEvent.VK_ENTER);
                    Thread.sleep(1000);
                    rb = new Robot();
                    noti = driver.findElement(By.id("phone_error"));
                    if (noti != null) {
                        System.out.println("Ch???nh s???a khi ????? tr???ng SDT: " + noti.getText());
                        phone.sendKeys("0328341092");
                        Thread.sleep(1000);
                        email.clear();
                        Thread.sleep(1000);
                        email.click();
                        Thread.sleep(1000);
                        rb.keyPress(KeyEvent.VK_ENTER);
                        rb.keyRelease(KeyEvent.VK_ENTER);
                        Thread.sleep(1000);
                        noti = driver.findElement(By.id("email_error"));
                        if (noti != null) {
                            System.out.println("Ch???nh s???a khi ????? tr???ng Email DN: " + noti.getText());
                            email.sendKeys("THCMedia@gmail.com");
                            Thread.sleep(1000);
                            websie.clear();
                            Thread.sleep(1000);
                            websie.click();
                            Thread.sleep(1000);
                            rb.keyPress(KeyEvent.VK_ENTER);
                            rb.keyRelease(KeyEvent.VK_ENTER);
                            Thread.sleep(1000);
                            noti = driver.findElement(By.id("website_error"));
                            if (noti != null) {
                                System.out.println("Ch???nh s???a khi ????? tr???ng th??ng tin website: " + noti.getText());
                                websie.sendKeys("facebook.com");
                                Thread.sleep(1000);
                                rb.keyPress(KeyEvent.VK_ENTER);
                                rb.keyRelease(KeyEvent.VK_ENTER);
                                Thread.sleep(1000);
                                rb = new Robot();
                                noti = driver.findElement(By.id("website_error"));
                                if (noti != null) {
                                    System.out.println("Ch???nh s???a khi nh???p website kh??ng h???p l???: " + noti.getText());
                                    websie.clear();
                                    Thread.sleep(1000);
                                    websie.sendKeys("https://www.facebook.com/");
                                    Thread.sleep(1000);
                                    owner.clear();
                                    Thread.sleep(1000);
                                    owner.click();
                                    Thread.sleep(1000);
                                    rb.keyPress(KeyEvent.VK_ENTER);
                                    rb.keyRelease(KeyEvent.VK_ENTER);
                                    Thread.sleep(1000);
                                    rb = new Robot();
                                    noti = driver.findElement(By.id("owner_error"));
                                    if (noti != null) {
                                        System.out.println("Ch???nh s???a khi ????? tr???ng ng?????i s??ng l???p: " + noti.getText());
                                        owner.sendKeys("Nguy???n ??an Tr?????ng");
                                        Thread.sleep(1500);
                                        name_contact.clear();
                                        Thread.sleep(1000);
                                        rb = new Robot();
                                        rb.keyPress(KeyEvent.VK_ENTER);
                                        rb.keyRelease(KeyEvent.VK_ENTER);
                                        noti = driver.findElement(By.id("contact_person_name_error"));
                                        if (noti != null) {
                                            System.out
                                                    .println("Ch???nh s???a khi ????? tr???ng t??n ng?????i LH: " + noti.getText());
                                            name_contact.sendKeys("Tr???n Xu??n T???n");
                                            Thread.sleep(1500);
                                            phone_contact.clear();
                                            Thread.sleep(1000);
                                            rb = new Robot();
                                            rb.keyPress(KeyEvent.VK_ENTER);
                                            rb.keyRelease(KeyEvent.VK_ENTER);
                                            noti = driver.findElement(By.id("contact_person_phone_error"));
                                            if (noti != null) {
                                                System.out.println(
                                                        "Ch???nh s???a khi ????? tr???ng SDT ng?????i LH: " + noti.getText());
                                                phone_contact.sendKeys("03888879");
                                                rb = new Robot();
                                                rb.keyPress(KeyEvent.VK_ENTER);
                                                rb.keyRelease(KeyEvent.VK_ENTER);
                                            } else {
                                                System.out.println("Kh??ng t??m th???y th??ng b??o cho SDT ng?????i LH");
                                                driver.close();
                                            }
                                        } else {
                                            System.out.println("Kh??ng t??m th???y th??ng b??o cho t??n ng?????i li??n h??? r???ng");
                                            driver.close();
                                        }
                                    } else {
                                        System.out.println("Kh??ng t??m th???y th??ng b??o cho Founder r???ng");
                                        driver.close();
                                    }
                                } else {
                                    System.out.println("Kh??ng t??m th???y th??ng b??o cho ???????ng d???n website r???ng");
                                    driver.close();
                                }
                            } else {
                                System.out.println("Kh??ng t??m th???t th??ng b??o cho website kh??ng h???p l???");
                                driver.close();
                            }
                        } else {
                            System.out.println("Kh??ng t??m th???y th??ng b??o cho email r???ng");
                            driver.close();
                        }
                    } else {
                        System.out.println("Kh??ng t??m th???y th??ng b??o cho s??? ??i???n tho???i r???ng");
                        driver.close();
                    }
                } else {
                    System.out.println("Kh??ng t??m th???y th??ng b??o cho ?????a ch??? doanh nghi???p r???ng");
                    driver.close();
                }
            } else {
                System.out.println("Kh??ng t??m th???y th??ng b??o cho t??n doanh nghi???p r???ng");
                driver.close();
            }
            return "Ho??n t???t g???i y??u c???u";
        } catch (Exception e) {
            e.printStackTrace();
            driver.close();
            return "G???i y??u c???u th???t b???i...";
        }
    }
}
