package edit_DN_Client;

import org.openqa.selenium.JavascriptExecutor;
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
                        Module fileCorp = new Module(driver);
                        excelhelpers excel = new excelhelpers();
                        excel.setExcelSheet("editDN - Client");

                        index.waitForPageLoaded();
                        login.navigation_login.click();
                        Thread.sleep(2000);

                        index.login();
                        index.waitForPageLoaded();

                        index.openFormCreate();
                        index.waitForPageLoaded();
                        fileCorp.openEditor();
                        index.waitForPageLoaded();

                        JavascriptExecutor js = (JavascriptExecutor) driver;
                        js.executeScript("arguments[0].scrollIntoView();", fileCorp.activityProvinceEdit);
                        Thread.sleep(2000);

                        fileCorp.activityProvinceEdit.click();
                        fileCorp.requestBtn.click();

                        System.out.println("=======================");
                        System.out.println("Testcase: 1");

                        String noti = fileCorp.getNoti();
                        if (noti.equals("Chưa chọn khu vực hoạt động")) {

                                System.out.println(noti);
                                index.passed();

                                fileCorp.activityProvinceEdit.click();
                                fileCorp.clear_input();
                                Thread.sleep(1000);

                                for (int i = 1; i < 12; i++) {

                                        System.out.println("=======================");
                                        
                                        System.out.println("Testcase: " + excel.getCellData("TCID", i));
                                        fileCorp.setTextEdit(excel.getCellData("name", i), excel.getCellData("add", i),
                                                        excel.getCellData("phone", i), excel.getCellData("email", i),
                                                        excel.getCellData("website", i),
                                                        excel.getCellData("owner", i),
                                                        excel.getCellData("nameContact", i),
                                                        excel.getCellData("phoneContact", i));
                                        Thread.sleep(1200);

                                        noti = fileCorp.getNoti();
                                        switch (noti) {
                                                case "Không được để trống trường này":
                                                        System.out.println(noti);
                                                        index.passed();
                                                        fileCorp.clear_input();
                                                        break;
                                                case "Email không hợp lệ":
                                                        System.out.println(noti);
                                                        index.passed();
                                                        fileCorp.clear_input();
                                                        break;
                                                case "Địa chỉ website không hợp lệ":
                                                        System.out.println(noti);
                                                        index.passed();
                                                        fileCorp.clear_input();
                                                        break;
                                                default:
                                                        noti = fileCorp.getNoti();
                                                        if (noti.length() == 0) {
                                                                System.out.println("Gửi yêu cầu chỉnh sửa thành công");
                                                                index.passed();
                                                        } else {
                                                                index.failed();
                                                        }
                                                        break;
                                        }
                                        Thread.sleep(1200);
                                }
                        } else {
                                index.failed();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
