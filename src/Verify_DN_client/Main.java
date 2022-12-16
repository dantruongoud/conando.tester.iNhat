package Verify_DN_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import setup.baseSetup;
import setup.indexPage;

public class Main {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_danhgia_client.Module valuePage = new create_danhgia_client.Module(driver);
            Verify_DN_client.Module verifyPage = new Verify_DN_client.Module(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("Xác thực DN");

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

            for (int i = 1; i < 6; i++) {

                System.out.println("=======================");

                System.out.println("Testcase: " + excel.getCellData("TCID", i));
                verifyPage.setText(excel.getCellData("name", i), excel.getCellData("phone", i),
                        excel.getCellData("email", i));
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
                            index.passed();
                            alert = driver.switchTo().alert();
                            System.out.println(noti);
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
