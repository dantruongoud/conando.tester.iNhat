package User_transferPass;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
    int testcase;
    String name, phone;

    public Main(int testcase, String name, String phone) {
        this.testcase = testcase;
        this.name = name;
        this.phone = phone;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
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

            accountPage.clear();

            Main[] data = {
                    new Main(1, "", "123-456-7890"),
                    new Main(2, "Mary", ""),
                    new Main(3, "Peter", "123-456-7890"),
            };
            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                accountPage.setText(data[i].name, data[i].phone);
                Thread.sleep(1200);

                String noti = accountPage.getNoti();
                switch (noti) {
                    case "Không được để trống trường này":
                        System.out.println(noti);
                        index.passed();
                        accountPage.clear();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
