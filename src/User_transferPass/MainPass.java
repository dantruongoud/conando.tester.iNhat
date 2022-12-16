package User_transferPass;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import setup.baseSetup;
import setup.indexPage;

public class MainPass {

    public static void main(String[] args) {
        try {

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            User_transferPass.Module accountPage = new User_transferPass.Module(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("transferPass");

            login.navigation_login.click();
            Thread.sleep(2000);
            index.login();
            index.waitForPageLoaded();

            index.menuUser.click();
            accountPage.navigation_account.click();
            index.waitForPageLoaded();

            accountPage.password_tab.click();
            index.waitForPageLoaded();

            for (int i = 1; i < 9; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + excel.getCellData("TCID", i));
                accountPage.setTextPassword(excel.getCellData("passold", i), excel.getCellData("passnew", i),
                        excel.getCellData("confirmpass", i));
                Thread.sleep(1200);

                String noti = accountPage.getNoti();
                switch (noti) {
                    case "Không được để trống trường này":
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "* Mật khẩu cũ không đúng.":
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "Mật khẩu xác nhận không đúng":
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ và số.":
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "* Mật khẩu mới không được trùng với mật khẩu hiện tại.":
                        index.passed();
                        accountPage.clear_input();
                        break;
                    default:
                        noti = accountPage.getNoti();
                        if (noti.length() == 0) {
                            System.out.println("Cập nhật thành công");
                            index.passed();
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
