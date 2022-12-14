package User_transferPass;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class MainPass {

    int testcase;
    String oldpass, newpasswork, confirmPass;

    public MainPass(int testcase, String oldpass, String newpasswork, String confirmPass) {
        this.testcase = testcase;
        this.oldpass = oldpass;
        this.newpasswork = newpasswork;
        this.confirmPass = confirmPass;
    }

    public static void main(String[] args) {
        try {
            MainPass[] data = {
                    new MainPass(1, "", "123456789", "123456789"),
                    new MainPass(2, "123", "123456789", "123456789"),
                    new MainPass(3, "123456", "", ""),
                    new MainPass(4, "123456", "123", "123"),
                    new MainPass(5, "123456", "123456789", ""),
                    new MainPass(6, "123456", "123456789", "123"),
                    new MainPass(7, "123456", "123456789", "123456789"),
            };
            
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            User_transferPass.Module accountPage = new User_transferPass.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);
            index.login();
            index.waitForPageLoaded();

            index.menuUser.click();
            accountPage.navigation_account.click();
            index.waitForPageLoaded();

            accountPage.password_tab.click();
            index.waitForPageLoaded();

            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                accountPage.setTextPassword(data[i].oldpass, data[i].newpasswork, data[i].confirmPass);
                Thread.sleep(1200);

                String noti = accountPage.getNoti();
                switch (noti) {
                    case "Không được để trống trường này":
                        System.out.println(noti);
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "* Mật khẩu cũ không đúng.":
                        System.out.println(noti);
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "Mật khẩu xác nhận không đúng":
                        System.out.println(noti);
                        index.passed();
                        accountPage.clear_input();
                        break;
                    case "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ và số.":
                        System.out.println(noti);
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
