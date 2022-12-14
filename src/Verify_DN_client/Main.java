package Verify_DN_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
    int testcase;
    String name, phone, email;

    public Main(int testcase, String name, String phone, String email) {
        this.testcase = testcase;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_danhgia_client.Module valuePage = new create_danhgia_client.Module(driver);
            Verify_DN_client.Module verifyPage = new Verify_DN_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            index.search("Công ty Testing ");
            index.waitForPageLoaded();

            valuePage.cards_company.click();
            index.waitForPageLoaded();

            verifyPage.btnVerify.click();
            Thread.sleep(1000);
            Main[] data = {
                    new Main(1, "", "0328341092", "ndtruong.conando@gmail.com"),
                    new Main(2, "Truong", "", "ndtruong.conando@gmail.com"),
                    new Main(3, "Truong", "0328341092", ""),
                    new Main(4, "Truong", "0328341092", "ndtruong"),
                    new Main(5, "Truong", "0328341092", "ndtruong.conando@gmail.com"),
            };
            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                verifyPage.setText(data[i].name, data[i].phone, data[i].email);
                Thread.sleep(1200);

                String noti = verifyPage.getNoti();
                switch (noti) {
                    case "Vui lòng điền đầy đủ thông tin":
                        System.out.println(noti);
                        index.passed();
                        Alert alert = driver.switchTo().alert();
                        Thread.sleep(1000);
                        alert.accept();
                        verifyPage.clear();
                        break;
                    case "Email không đúng định dạng":
                        System.out.println(noti);
                        index.passed();
                        alert = driver.switchTo().alert();
                        Thread.sleep(1000);
                        alert.accept();
                        verifyPage.clear();
                        break;
                    default:
                        if (noti.equals("Đã gửi yêu cầu xác thực thành công!")) {
                            System.out.println(noti);
                            index.passed();
                            alert = driver.switchTo().alert();
                            alert.accept();
                        } else {
                            index.failed();
                        }
                        break;
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
