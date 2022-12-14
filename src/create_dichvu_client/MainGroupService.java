package create_dichvu_client;

import org.openqa.selenium.WebDriver;

import login.Module;
import setup.baseSetup;
import setup.indexPage;

public class MainGroupService {
    public static void main(String[] args) throws InterruptedException {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            Module login = new Module(driver);
            indexPage index = new indexPage(driver);
            create_dichvu_client.Module servicePage = new create_dichvu_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            index.openFormCreate();
            index.waitForPageLoaded();

            servicePage.openForm();
            index.waitForPageLoaded();

            System.out.println("=======================");
            System.out.println("Testcase: 1");
            servicePage.saveBtn.click();
            Thread.sleep(1000);
            String noti = index.getNotiClass();
            if (noti.equals("*Vui lòng chọn một niên giám/Ngành dịch vụ")) {
                System.out.println(noti);
                index.passed();
                servicePage.clickCategory.click();
                Thread.sleep(1000);
                servicePage.saveBtn.click();
                System.out.println("=======================");
                System.out.println("Testcase: 2");
                noti = index.getNotiClass();
                if (noti.equals("*Vui lòng chọn một niên giám/Ngành dịch vụ")) {
                    System.out.println(noti);
                    index.passed();
                    servicePage.clickIndustry.click();
                    Thread.sleep(1000);
                    servicePage.clickService.click();
                    Thread.sleep(1000);
                    System.out.println("=======================");
                    System.out.println("Testcase: 3");
                    servicePage.saveBtn.click();
                    index.waitForPageLoaded();
                    noti = index.getNoti();
                    if (noti.length() == 0) {
                        System.out.println("Tạo nhóm DV thành công");
                        index.passed();
                    } else {
                        index.failed();
                    }
                } else {
                    index.failed();
                }
            } else {
                index.failed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
