package User_transferPass;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Module {

    public WebDriver driver;

    private String element;

    public Module(String element) {
        this.element = element;
    }

    @FindBy(css = "a[href='/account']")
    public WebElement navigation_account;

    @FindBy(xpath = "(//input[@name='fullname'])[1]")
    private WebElement name_input;

    @FindBy(xpath = "(//input[@name='phone'])[1]")
    private WebElement phone_input;

    @FindBy(css = "form[id='nav-home'] button[role='button']")
    private WebElement save_button;

    @FindBy(id = "nav-password-tab")
    public WebElement password_tab;

    @FindBy(css = "form[id='nav-password'] button[role='button']")
    private WebElement password_button;

    @FindBy(name = "old_password")
    private WebElement old_password;

    @FindBy(id = "new_password")
    private WebElement new_password;

    @FindBy(name = "confirm_new_password")
    private WebElement confirm_new_password;

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setText(String name, String phone) {
        try {
            name_input.sendKeys(name);
            phone_input.sendKeys(phone);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", save_button);
            Thread.sleep(500);
            save_button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextPassword(String oldpass, String newpassword, String confirmPass) {
        try {
            old_password.sendKeys(oldpass);
            new_password.sendKeys(newpassword);
            confirm_new_password.sendKeys(confirmPass);
            password_button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear_input() {
        try {
            old_password.clear();
            new_password.clear();
            confirm_new_password.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        name_input.clear();
        phone_input.clear();
    }

    public String getNoti() {
        String noti = "";
        Module[] validation = {
                new Module("fullname_error"),
                new Module("phone_error"),
                new Module("old_password_error"),
                new Module("new_password_error"),
                new Module("confirm_new_password_error"),
                new Module("changepw-error"),
        };
        for (int i = 0; i < validation.length; i++) {
            noti = driver.findElement(By.id(validation[i].element)).getText().strip();
            if (noti.length() > 0) {
                System.out.println(noti);
                break;
            }
        }
        return noti;

    }

    // Thay ?????i th??ng tin c?? nh??n c???a ng?????i d??ng
    public static String edit_infoUser(WebDriver driver) {
        try {

            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//a[@href='/account']")).click();
            Thread.sleep(2000);

            WebElement fullname = driver.findElement(By.name("fullname"));
            WebElement phone = driver.findElement(By.name("phone"));
            WebElement birthday = driver.findElement(By.name("birthday"));
            Select gioitinh = new Select(driver.findElement(By.xpath("//section[@id='account']//select[1]")));
            Select city = new Select(driver.findElement(By.name("province_id")));
            WebElement address = driver.findElement(By.name("address"));
            WebElement button = driver.findElement(By.xpath("//form[@id='nav-home']//button[@role='button']"));

            fullname.clear();
            Thread.sleep(1000);
            button.click();
            Thread.sleep(1000);
            WebElement noti = driver.findElement(By.id("fullname_error"));
            if (noti != null) {
                System.out.println("Ch???nh s???a khi ????? tr???ng h??? v?? t??n: " + noti.getText());
                fullname.sendKeys("I'm Dan Truong");
                Thread.sleep(1000);
                phone.clear();
                Thread.sleep(1000);
                button.click();
                Thread.sleep(1000);
                noti = driver.findElement(By.id("phone_error"));
                if (noti != null) {
                    System.out.println("Ch???nh s???a khi ????? tr???ng s??? ??i???n tho???i: " + noti.getText());
                    phone.sendKeys("0909686868");
                    Thread.sleep(1000);
                    birthday.sendKeys("24/10/2000");
                    Thread.sleep(1000);
                    gioitinh.selectByVisibleText("Nam");
                    Thread.sleep(1000);
                    city.selectByVisibleText("???? N???ng");
                    Thread.sleep(1000);
                    address.sendKeys("K14/H22/43 Nguy???n Ho??ng, V??nh Trung, Thanh Kh??");
                    Thread.sleep(1000);
                    button.click();
                } else {
                    System.out.println("Kh??ng t??m th???y th??ng b??o cho m???c n??y");
                    driver.close();
                }
            } else {
                System.out.println("Kh??ng t??m th???y th??ng b??o cho m???c n??y");
                driver.close();
            }
            return "Ho??n t???t ch???nh s???a th??ng tin";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ch???nh s???a th??ng tin th???t b???i...";
        }
    }

    // Thay ?????i ???nh ?????i di???n
    public static String edit_logo(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//a[@href='/account']")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("account-upload-avatar")).click();
            Thread.sleep(3000);

            // Kh???i t???o Robot class
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Copy File path v??o Clipboard
            String filePath = "C:\\Users\\Admin\\Downloads\\test1.webp";
            StringSelection str = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

            Thread.sleep(2000);

            // Nh???n Control+V ????? d??n
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            Thread.sleep(2000);

            // Nh???n Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_CONTROL);

            Thread.sleep(5000);
            driver.findElement(By.xpath("//form[@id='nav-home']//button[@role='button']")).click();
            Thread.sleep(1000);
            return "Ho??n t???t thay ?????i h??nh ???nh";
        } catch (Exception e) {
            e.printStackTrace();
            return "Thay ?????i h??nh ???nh th???t b???i...";
        }
    }

    // Thay ?????i m???t kh???u
    public static String edit_pass(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//a[@href='/account']")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("nav-password-tab")).click();
            Thread.sleep(1000);
            WebElement old_password = driver.findElement(By.name("old_password"));
            WebElement new_password = driver.findElement(By.name("new_password"));
            WebElement Renew_password = driver.findElement(By.name("confirm_new_password"));
            WebElement button = driver.findElement(By.xpath("//form[@id='nav-password']//button[@role='button']"));

            button.click();
            Thread.sleep(1000);
            WebElement noti = driver.findElement(By.id("old_password_error"));
            if (noti != null) {
                System.out.println("?????i m???t kh???u khi ????? tr???ng c??c th??ng tin b???t bu???c: " + noti.getText());
                old_password.sendKeys("12345678");
                Thread.sleep(1000);
                button.click();
                Thread.sleep(1000);
                noti = driver.findElement(By.id("new_password_error"));
                if (noti != null) {
                    System.out.println("?????i m???t kh???u khi ????? tr???ng m???t kh???u m???i: " + noti.getText());
                    new_password.sendKeys("000");
                    Thread.sleep(1000);
                    noti = driver.findElement(By.id("new_password_error"));
                    if (noti != null) {
                        System.out.println("?????i m???t kh???u khi nh???p sai ?????nh d???ng password: " + noti.getText());
                        new_password.clear();
                        Thread.sleep(1000);
                        new_password.sendKeys("dantruong2410");
                        Renew_password.sendKeys("10000");
                        Thread.sleep(1000);
                        button.click();
                        Thread.sleep(1000);
                        noti = driver.findElement(By.id("confirm_new_password_error"));
                        if (noti != null) {
                            System.out.println("?????i m???t kh???u khi x??c nh???n m???t kh???u m???i sai: " + noti.getText());
                            Renew_password.clear();
                            Thread.sleep(1000);
                            Renew_password.sendKeys("dantruong2410");
                            Thread.sleep(1000);
                            button.click();
                            Thread.sleep(1000);
                            noti = driver.findElement(By.id("changepw-error"));
                            if (noti != null) {
                                System.out.println("?????i m???t kh???u khi nh???p sai m???t kh???u c??: " + noti.getText());
                                old_password.clear();
                                Thread.sleep(1000);
                                old_password.sendKeys("dantruong2410");
                                Thread.sleep(1000);
                                button.click();
                                Thread.sleep(1000);
                                noti = driver.findElement(By.id("changepw-error"));
                                if (noti != null) {
                                    System.out.println(noti.getText());
                                    new_password.clear();
                                    Thread.sleep(1000);
                                    Renew_password.clear();
                                    Thread.sleep(1000);
                                    new_password.sendKeys("nguyendantruong2410");
                                    Thread.sleep(1000);
                                    Renew_password.sendKeys("nguyendantruong2410");
                                    Thread.sleep(1000);
                                    button.click();
                                } else {
                                    System.out.println("Kh??ng t??m th???y th??ng b??o cho m???c n??y");
                                    driver.close();
                                }
                            } else {
                                System.out.println("Kh??ng t??m th???y th??ng b??o cho m???c n??y");
                                driver.close();
                            }
                        } else {
                            System.out.println("Kh??ng t??m th???y th??ng b??o m???c n??y");
                            driver.close();
                        }
                    } else {
                        System.out.println("Kh??ng t??m th???y th??ng b??o m???c n??y");
                        driver.close();
                    }
                } else {
                    System.out.println("Kh??ng t??m th???y th??ng b??o m???c n??y");
                    driver.close();
                }
            } else {
                System.out.println("Kh??ng t??m th???y th??ng b??o m???c n??y");
                driver.close();
            }
            return "Ho??n t???t ?????i m???t kh???u";
        } catch (Exception e) {
            e.printStackTrace();
            return "?????i m???t kh???u th???t b???i...";
        }
    }

    // ????ng nh???p sau khi ?????i m???t kh???u
    public static String login_again(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'????ng xu???t')]")).click();
            Thread.sleep(3000);
            driver.findElement(By.className("header-login")).click();
            Thread.sleep(2000);
            driver.findElement(By.name("email")).sendKeys("ndtruong.conando@gmail.com");
            Thread.sleep(1000);
            driver.findElement(By.name("password")).sendKeys("nguyendantruong2410");
            driver.findElement(By.id("login-btn")).click();
            Thread.sleep(4000);
            String title = driver.getTitle();
            System.out.println("???? v??o h??? th???ng: " + title);
            return "Ho??n t???t ????ng nh???p sau khi ?????i m???t kh???u";
        } catch (Exception e) {
            e.printStackTrace();
            return "????ng nh???p th???t b???i sau khi ?????i m???t kh???u";
        }
    }
}
