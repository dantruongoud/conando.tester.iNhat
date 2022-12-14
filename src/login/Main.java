package login;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
	int testcase;
	String username, password;

	public Main(int testcase, String username, String password) {
		this.testcase = testcase;
		this.username = username;
		this.password = password;
	}

	public static void main(String[] args) {
		try {
			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module login = new Module(driver);
			indexPage index = new indexPage(driver);

			index.waitForPageLoaded();
			login.navigation_login.click();
			Thread.sleep(2000);

			Main[] data = {
					new Main(1, "", "password"),
					new Main(2, "username", "password"),
					new Main(3, "username@gmail.com", ""),
					new Main(4, "ndtruong.conando@gmail.com", "dantruong2410"),
					new Main(5, "admin@gmail.com", "123456789"),
			};
			for (int i = 0; i < data.length; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + data[i].testcase);
				login.setText(data[i].username, data[i].password);
				Thread.sleep(1200);

				String noti = index.getNoti();
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
