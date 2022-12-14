package create_danhgia_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
	int testcase;
	String content;

	public Main(int testcase, String content) {
		this.testcase = testcase;
		this.content = content;
	}

	public static void main(String[] args) {
		try {
			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			login.Module login = new login.Module(driver);
			indexPage index = new indexPage(driver);
			create_danhgia_client.Module valuePage = new create_danhgia_client.Module(driver);

			index.waitForPageLoaded();
			login.navigation_login.click();
			Thread.sleep(2000);

			index.login();
			index.waitForPageLoaded();

			index.search("Công ty Testing ");
			index.waitForPageLoaded();

			valuePage.cards_company.click();
			index.waitForPageLoaded();

			valuePage.openForm();
			index.waitForPageLoaded();

			Main[] data = {
					new Main(1, ""),
					new Main(2, "Tôi cần đánh giá"),
			};

			for (int i = 0; i < data.length; i++) {
				System.out.println("=======================");
				System.out.println("Testcase:" + data[i].testcase);
				valuePage.setText(data[i].content);
				Thread.sleep(1000);

				String noti = valuePage.getNoti();
				switch (noti) {
					case "Không thể để trống trường này":
						System.out.println(noti);
						index.passed();
						valuePage.review_content.clear();
						valuePage.rangePoint();
						break;
					default:
						noti = valuePage.getNoti();
						if (noti.length() == 0) {
							System.out.println("Gửi đánh giá thành công");
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
