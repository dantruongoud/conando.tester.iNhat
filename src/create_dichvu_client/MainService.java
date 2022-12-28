package create_dichvu_client;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import setup.baseSetup;
import setup.indexPage;

public class MainService {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            create_dichvu_client.Module servicePage = new create_dichvu_client.Module(driver);
            excelhelpers excel = new excelhelpers();
            excel.setExcelSheet("Tạo Dịch vụ");

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

                for (int i = 1; i < 4; i++) {
                    System.out.println("=======================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    servicePage.setText(excel.getCellData("nameService", i), excel.getCellData("titleService", i));
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
