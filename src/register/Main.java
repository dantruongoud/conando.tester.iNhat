package register;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import setup.baseSetup;
import setup.indexPage;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		try {
			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module register = new Module(driver);
			indexPage index = new indexPage(driver);
			excelhelpers excel = new excelhelpers();
			excel.setExcelSheet("register");

			index.waitForPageLoaded();
			register.navigationRegister();
			index.waitForPageLoaded();

			for (int i = 1; i < 8; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + excel.getCellData("TCID", i));
				register.setText(excel.getCellData("username", i), excel.getCellData("password", i),
						excel.getCellData("confirmpass", i));
				Thread.sleep(1200);

				String noti = register.getNoti();
				switch (noti) {
					case "Không được để trống trường này":
						System.out.println(noti);
						register.clear_input();
						index.passed();
						break;
					case "Email không hợp lệ":
						System.out.println(noti);
						register.clear_input();
						index.passed();
						break;
					case "Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ và số.":
						System.out.println(noti);
						register.clear_input();
						index.passed();
						break;
					case "Mật khẩu xác nhận không đúng":
						System.out.println(noti);
						index.passed();
						register.checkbox.click();
						register.clear_input();
						break;
					case "Vui lòng xác nhận bạn không phải là robot":
						System.out.println(noti);
						index.passed();
						register.clear_input();
						register.checkbox.click();
						break;
					default:
						noti = register.getNoti();
						if (noti.length() == 0) {
							System.out.println("Đăng ký thành công, xác thực tại email");
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
