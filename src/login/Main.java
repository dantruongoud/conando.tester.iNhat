package login;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import setup.baseSetup;
import setup.indexPage;

public class Main {

	public static void main(String[] args) {
		try {
			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module login = new Module(driver);
			indexPage index = new indexPage(driver);
			excelhelpers excel = new excelhelpers();
			excel.setExcelSheet("login");

			index.waitForPageLoaded();
			login.navigation_login.click();
			Thread.sleep(2000);

			for (int i = 1; i < 6; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + excel.getCellData("TCID", i));
				login.setText(excel.getCellData("username", i), excel.getCellData("password", i));
				Thread.sleep(1200);

				String noti = login.getNotify();
				switch (noti) {
					case "Không được để trống trường này":
						System.out.println(noti);
						index.passed();
						login.clear_input();
						break;
					case "Email không hợp lệ":
						System.out.println(noti);
						index.passed();
						login.clear_input();
						break;
					case "Tài khoản hoặc mật khẩu của bạn vừa nhập không chính xác":
						System.out.println(noti);
						index.passed();
						login.clear_input();
						break;
					default:
						if (login.loginsuccess.isDisplayed()) {
							System.out.println("Đăng nhập thành công");
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
