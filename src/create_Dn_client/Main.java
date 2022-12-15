package create_Dn_client;

import org.openqa.selenium.WebDriver;

import excelHelpers.excelhelpers;
import login.Module;
import setup.baseSetup;
import setup.indexPage;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		try {

			baseSetup init = new baseSetup();
			WebDriver driver = init.initChromeDriver();
			Module login = new Module(driver);
			indexPage index = new indexPage(driver);
			formCreatePage formCreate = new formCreatePage(driver);
			excelhelpers excel = new excelhelpers();
			excel.setExcelSheet("Step 1->3 createDN - Client");

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

			for (int i = 1; i < 10; i++) {
				System.out.println("=======================");
				System.out.println("Testcase: " + excel.getCellData("TCID", i));
				formCreate.setText_Step1(excel.getCellData("name", i), excel.getCellData("add", i),
						excel.getCellData("phone", i), excel.getCellData("email", i), excel.getCellData("website", i),
						excel.getCellData("owner", i));
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
				Thread.sleep(1200);
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

							excel.setExcelSheet("Step 4 createDN - Client");

							for (int i = 1; i < 5; i++) {
								System.out.println("=======================");

								System.out.println("Testcase: " + excel.getCellData("TCID", i));
								formCreate.setText_step4(excel.getCellData("name", i), excel.getCellData("phone", i));
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
								Thread.sleep(1200);
							}
						} else {
							System.out.println("Step 4 is not displayed");
							index.failed();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
