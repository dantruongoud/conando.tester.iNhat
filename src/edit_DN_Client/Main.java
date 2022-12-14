package edit_DN_Client;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
        int tescase;
        String name, add, phone, email, website, owner, nameContact, phoneContact;

        public Main(int tescase, String name, String add, String phone, String email, String website, String owner,
                        String nameContact, String phoneContact) {
                this.tescase = tescase;
                this.name = name;
                this.add = add;
                this.phone = phone;
                this.email = email;
                this.website = website;
                this.owner = owner;
                this.nameContact = nameContact;
                this.phoneContact = phoneContact;
        }

        public static void main(String[] args) {
                try {
                        baseSetup init = new baseSetup();
                        WebDriver driver = init.initChromeDriver();
                        login.Module login = new login.Module(driver);
                        indexPage index = new indexPage(driver);
                        Module fileCorp = new Module(driver);

                        index.waitForPageLoaded();
                        login.navigation_login.click();
                        Thread.sleep(2000);

                        index.login();
                        index.waitForPageLoaded();

                        index.openFormCreate();
                        index.waitForPageLoaded();
                        fileCorp.openEditor();
                        index.waitForPageLoaded();

                        Main[] data = {
                                        new Main(2, "", "address", "0328341982", "admin@gmail.com",
                                                        "https://inhat.com.vn",
                                                        "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(3, "Conando Testing Company", "", "0328341982", "admin@gmail.com",
                                                        "https://inhat.com.vn",
                                                        "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(4, "Conando Testing Company", "23 Truong Thi 1", "", "admin@gmail.com",
                                                        "https://inhat.com.vn",
                                                        "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(5, "Conando Testing Company", "23 Truong Thi 1", "0328341092", "",
                                                        "https://inhat.com.vn",
                                                        "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(6, "Conando Testing Company", "23 Truong Thi 1", "0328341092", "admin",
                                                        "https://inhat.com.vn", "nguyen dan truong", "tran xuan tan",
                                                        "0328341092"),
                                        new Main(7, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "", "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(8, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "inhat", "nguyen dan truong", "tran xuan tan", "0328341092"),
                                        new Main(9, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "https://inhat.com.vn", "", "tran xuan tan", "0328341092"),
                                        new Main(10, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "https://inhat.com.vn", "Nguyen Dan Truong", "", "0328341092"),
                                        new Main(11, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "https://inhat.com.vn", "Nguyen Dan Truong", "tran xuan tan",
                                                        ""),
                                        new Main(12, "Conando Testing Company", "23 Truong Thi 1", "0328341092",
                                                        "admin@gmail.com",
                                                        "https://inhat.com.vn", "Nguyen Dan Truong", "tran xuan tan",
                                                        "0328341092"),
                        };

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
                                for (int i = 0; i < data.length; i++) {
                                        System.out.println("=======================");
                                        System.out.println("Testcase: " + data[i].tescase);
                                        fileCorp.setTextEdit(data[i].name, data[i].add, data[i].phone, data[i].email,
                                                        data[i].website,
                                                        data[i].owner,
                                                        data[i].nameContact, data[i].phoneContact);
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
