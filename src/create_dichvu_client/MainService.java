package create_dichvu_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class MainService {

    int testcase;
    String nameService, title_service;

    public MainService(int testcase, String nameService, String title_service) {
        this.testcase = testcase;
        this.nameService = nameService;
        this.title_service = title_service;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_dichvu_client.Module servicePage = new create_dichvu_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            index.openFormCreate();
            index.waitForPageLoaded();
            servicePage.navigation_services.click();
            index.waitForPageLoaded();

            servicePage.findCreateBtn();
            index.waitForPageLoaded();

            System.out.println("=======================");
            System.out.println("Testcase: 1");
            servicePage.serviceTitle_input.sendKeys("123123");
            servicePage.doneBtn.click();
            Thread.sleep(1000);
            String noti = index.getNotiClass();
            if (noti.equals("*Vui lòng thêm gói dịch vụ")) {
                System.out.println(noti);
                index.passed();
                servicePage.serviceTitle_input.clear();
                servicePage.addBtn.click();

                MainService[] data = {
                        new MainService(2, "", "noti"),
                        new MainService(3, "Dịch vụ kiểm thử tự động", ""),
                        new MainService(4, "Dịch vụ kiểm thử tự động", "Gói định kỳ theo tháng")
                };

                for (int i = 0; i < data.length; i++) {
                    System.out.println("=======================");
                    System.out.println("Testcase: " + data[i].testcase);
                    servicePage.setText(data[i].nameService, data[i].title_service);
                    Thread.sleep(1000);

                    noti = index.getNotiClass();

                    switch (noti) {
                        case "*Vui lòng điền đầy đủ thông tin":
                            System.out.println(noti);
                            index.passed();
                            servicePage.clear_input();
                            break;
                        default:
                            if (servicePage.listItem.isDisplayed()) {
                                System.out.println("Tạo mới thành công");
                                index.passed();
                            } else {
                                index.failed();
                            }
                            break;
                    }
                    Thread.sleep(1000);

                }
            } else {
                index.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
