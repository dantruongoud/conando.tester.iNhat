package create_khieunai_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
    int testcase;
    String title;

    public Main(int testcase, String title) {
        this.testcase = testcase;
        this.title = title;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_khieunai_client.Module complainsPage = new create_khieunai_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            index.openFormCreate();
            index.waitForPageLoaded();

            complainsPage.complains_navigation.click();
            index.waitForPageLoaded();
            complainsPage.add_navigation.click();
            index.waitForPageLoaded();

            Main[] data = {
                    new Main(1, ""),
                    new Main(2, "Test Phát nha"),
            };
            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                complainsPage.setText(data[i].title);

                Thread.sleep(1200);

                String noti = complainsPage.getNoti();
                switch (noti) {
                    case "Không được để trống trường này":
                        System.out.println(noti);
                        index.passed();
                        complainsPage.title_input.clear();
                        break;

                    default:
                        if (noti.length() == 0) {
                            System.out.println("Khiếu nại thành công");
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
