package create_Dn_client;

import org.openqa.selenium.WebDriver;

import login.Module;
import setup.baseSetup;
import setup.indexPage;

public class Main {
	int testcase;
	String name, add, phone, email, website, owner;

	public Main(int testcase, String name, String add, String phone, String email, String website, String owner) {
		this.testcase = testcase;
		this.name = name;
		this.add = add;
		this.phone = phone;
		this.email = email;
		this.website = website;
		this.owner = owner;
	}

	public Main(int testcase, String name, String phone) {
		this.testcase = testcase;
		this.name = name;
		this.phone = phone;
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			Main[] data = {
					new Main(1, "", "23 Trường thi 1", "0328341092", "admin@gmail.com", "https://inhat.com.vn",
							"Nguyễn Đan Trường"),
					new Main(2, "Công ty Testing", "", "0328341092", "admin@gmail.com", "https://inhat.com.vn",
							"Nguyễn Đan Trường"),
					new Main(3, "Công ty Testing", "23 Trường thi 1", "", "admin@gmail.com", "https://inhat.com.vn",
							"Nguyễn Đan Trường"),
					new Main(4, "Công ty Testing", "23 Trường thi 1", "0328341092", "", "https://inhat.com.vn",
							"Nguyễn Đan Trường"),
					new Main(5, "Công ty Testing", "23 Trường thi 1", "0328341092", "admin", "https://inhat.com.vn",
							"Nguyễn Đan Trường"),
					new Main(6, "Công ty Testing", "23 Trường thi 1", "0328341092", "admin@gmail.com", "inhat",
							"Nguyễn Đan Trường"),
					new Main(7, "Công ty Testing", "23 Trường thi 1", "0328341092", "admin@gmail.com", "",
							"Nguyễn Đan Trường"),
					new Main(8, "Công ty Testing", "23 Trường thi 1", "0328341092", "admin@gmail.com",
							"https://inhat.com.vn",
							""),
					new Main(9, "Công ty Testing", "23 Trường thi 1", "0328341092", "admin@gmail.com",
							"https://inhat.com.vn",
							"Nguyễn Đan Trường"),
			};

			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module login = new Module(driver);
			indexPage index = new indexPage(driver);
			formCreatePage formCreate = new formCreatePage(driver);

			index.waitForPageLoaded();
			login.navigation_login.click();
			Thread.sleep(2000);

			index.login();
			index.waitForPageLoaded();

			index.openFormCreate();
			index.waitForPageLoaded();
			formCreate.createCorpBtn.click();

			Thread.sleep(2000);
			// Step 1
			// up hình logo trước khi test
			formCreate.upfile("css", ".name-file__edit");

			for (int i = 0; i < data.length; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + data[i].testcase);
				formCreate.setText_Step1(data[i].name, data[i].add, data[i].phone, data[i].email, data[i].website,
						data[i].owner);
				Thread.sleep(1200);

				String noti = index.getNoti();
				switch (noti) {
					case "Không được để trống trường này":
						System.out.println(noti);
						index.passed();
						formCreate.clear_input();
						break;
					case "Email không hợp lệ":
						System.out.println(noti);
						index.passed();
						formCreate.clear_input();
						break;

					case "Địa chỉ website không hợp lệ":
						System.out.println(noti);
						index.passed();
						formCreate.clear_input();
						break;
					default:
						if (formCreate.step2.isDisplayed()) {
							System.out.println("Hoàn thành Step 1");
							index.passed();
						} else {
							index.failed();
						}
						break;
				}
				Thread.sleep(1000);
			}

			// Step 3
			formCreate.nextBtn.click();
			if (formCreate.step3.isDisplayed()) {
				formCreate.nextBtn.click();
				Thread.sleep(1000);

				formCreate.infoCompany.sendKeys("This's infomation my Company");
				System.out.println("=======================");
				System.out.println("Testcase: 10");
				String noti = index.getNoti();
				if (noti.equals("Không được để trống trường này")) {
					System.out.println(noti);
					index.upfile("css", ".fw-400.font-14.lh-18");
					index.passed();
					formCreate.infoCompany.clear();
					formCreate.nextBtn.click();
					Thread.sleep(1000);

					System.out.println("=======================");
					System.out.println("Testcase: 11");
					noti = index.getNoti();
					if (noti.equals("Không được để trống trường này")) {
						System.out.println(noti);
						index.passed();
						formCreate.infoCompany.sendKeys("This's my company");
						formCreate.nextBtn.click();

						// Step 4
						if (formCreate.step4.isDisplayed()) {

							Main[] data4 = {
									new Main(12, "Tran Xuan Tan", "0328341092"),
									new Main(13, "Tran Xuan Tan", ""),
									new Main(14, "", "0328341092"),
									new Main(15, "Tran Xuan Tan", "0328341092")
							};

							for (int i = 0; i < data4.length; i++) {
								System.out.println("=======================");
								System.out.println("Testcase: " + data4[i].testcase);
								formCreate.setText_step4(data4[i].name, data4[i].phone);
								Thread.sleep(1200);

								noti = index.getNoti();
								switch (noti) {
									case "Không được để trống trường này":
										System.out.println(noti);
										index.passed();
										formCreate.clear_input4();
										break;
									case "Chưa chọn khu vực hoạt động":
										System.out.println(noti);
										index.passed();
										formCreate.country.click();
										Thread.sleep(1000);
										formCreate.clear_input4();
										break;
									default:
										noti = index.getNoti();
										if (noti.length() == 0) {
											Thread.sleep(8000);
											System.out.println(formCreate.getComplete());
											index.passed();
											formCreate.nextBtn.click();
										} else {
											index.failed();
										}
										break;
								}
								Thread.sleep(1000);
							}
						} else {
							System.out.println("Step 4 is not displayed");
						}

					} else {
						index.failed();
					}
				} else {
					index.failed();
				}
			} else {
				System.out.println("Step 3 is not displayed");
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
