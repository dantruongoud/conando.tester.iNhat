package create_danhgiaDN_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
    int testcase;
    String text;

    public Main(int testcase, String text) {
        this.testcase = testcase;
        this.text = text;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_danhgia_client.Module valuePage = new create_danhgia_client.Module(driver);
            create_danhgiaDN_client.Module reportPage = new create_danhgiaDN_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            index.search("Công ty Testing ");
            index.waitForPageLoaded();

            valuePage.cards_company.click();
            index.waitForPageLoaded();

            reportPage.button_report.click();
            index.waitForPageLoaded();

            Main[] data = {
                    new Main(1, ""),
                    new Main(2, "Cho Test phát nha")
            };
            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                reportPage.setText(data[i].text);
                Thread.sleep(1000);

                String noti = reportPage.getNoti();
                switch (noti) {
                    case "Không thể để trống trường này":
                        System.out.println(noti);
                        index.passed();
                        reportPage.report_content.clear();
                        break;
                    default:
                        if (noti.length() == 0) {
                            System.out.println("Gửi báo cáo thành công");
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
