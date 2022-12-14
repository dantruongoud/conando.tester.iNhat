package register;

import org.openqa.selenium.WebDriver;
import setup.baseSetup;
import setup.indexPage;

public class Main {
	int testcase;
	String username, password, confirmpass;

	public Main(int testcase, String username, String password, String confirmpass) {
		this.testcase = testcase;
		this.username = username;
		this.password = password;
		this.confirmpass = confirmpass;
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module register = new Module(driver);
			indexPage index = new indexPage(driver);

			index.waitForPageLoaded();
			register.navigationRegister();
			index.waitForPageLoaded();

			Main[] data = {
					new Main(1, "", "dantruong2410", "dantruong2410"),
					new Main(2, "ndtruong.conando", "dantruong2410", "dantruong2410"),
					new Main(3, "email1@gmail.com", "", ""),
					new Main(5, "email1@gmail.com", "dantruong2410", "123456"),
					new Main(6, "email1@gmail.com", "123456", "123456"),
					new Main(7, "email1@gmail.com", "dantruong2410", "dantruong2410"),
					new Main(8, "mail123@gmail.com", "dantruong2410", "dantruong2410"),
			};

			for (int i = 0; i < data.length; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + data[i].testcase);
				register.setText(data[i].username, data[i].password, data[i].confirmpass);
				Thread.sleep(1200);

				String noti = index.getNoti();
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
						noti = index.getNoti();
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
