package create_Dn_client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class formCreatePage {

	WebDriver driver;

	@FindBy(xpath = "//img[@alt='Avatar']")
	private WebElement menuUser;

	@FindBy(xpath = "//li[contains(text(),'Chế độ doanh nghiệp')]")
	private WebElement siteCorp;

	@FindBy(xpath = "//p[contains(text(),'Tạo doanh nghiệp mới')]")
	public WebElement createCorpBtn;

	@FindBy(id = "step-1")
	public WebElement step1;

	@FindBy(xpath = "//span[contains(text(),'Ngành nghề - Dịch vụ')]")
	public WebElement step2;

	@FindBy(id = "step-3")
	public WebElement step3;

	@FindBy(id = "step-4")
	public WebElement step4;

	@FindBy(id = "step-5")
	public WebElement step5;

	// Listelement form tạo doanh nghiệp
	@FindBy(name = "vietnamese_name")
	private WebElement nameCompany;

	@FindBy(css = "div[class='form-group'] input[name='address']")
	private WebElement addressCompany;

	@FindBy(css = "div[class='form-group'] select[name='province_id']")
	public Select provinceCompany;

	@FindBy(css = "div[class='form-group'] input[name='phone']")
	private WebElement phoneCompany;

	@FindBy(css = "input[name='email'][type='text']")
	private WebElement emailCompany;

	@FindBy(name = "website")
	private WebElement websiteCompany;

	@FindBy(name = "owner")
	private WebElement ownerCompany;

	@FindBy(css = ".name-file__edit")
	private WebElement logoCompany;

	@FindBy(id = "next-step-btn")
	public WebElement nextBtn;

	@FindBy(id = "introduce__field")
	public WebElement infoCompany;

	@FindBy(name = "contact_person_name")
	private WebElement contactPersonName;

	@FindBy(name = "contact_person_phone")
	private WebElement contactPersonPhone;

	@FindBy(id = "activityProvince")
	public WebElement country;

	@FindBy(xpath = "/html/body/form/div/div/div[2]/div[5]/p")
	private WebElement complete_noti;

	public formCreatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void upfile(String typeFind, String path) {
		try {
			WebElement upBtn;
			if (typeFind == "id") {
				upBtn = driver.findElement(By.id(path));
			} else if (typeFind == "class") {
				upBtn = driver.findElement(By.className(path));
			} else if (typeFind == "css") {
				upBtn = driver.findElement(By.cssSelector(path));
			} else {
				upBtn = driver.findElement(By.xpath(path));
			}
			String filePath = "C:\\Users\\Admin\\Downloads\\test3.jpg";

			// Click để mở form upload
			upBtn.click();
			Thread.sleep(3000);

			// Khởi tạo Robot class
			Robot rb = null;
			try {
				rb = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}

			// Copy File path vào Clipboard
			StringSelection str = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

			Thread.sleep(2000);

			// Nhấn Control+V để dán
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);

			Thread.sleep(2000);

			// Nhấn Enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);

			rb.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setText_Step1(String name, String add, String phone, String email, String website, String owner) {
		try {
			nameCompany.sendKeys(name);
			addressCompany.sendKeys(add);
			phoneCompany.sendKeys(phone);
			emailCompany.sendKeys(email);
			websiteCompany.sendKeys(website);
			ownerCompany.sendKeys(owner);
			nextBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setText_step4(String name, String phone) {
		try {
			contactPersonName.sendKeys(name);
			contactPersonPhone.sendKeys(phone);
			nextBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear_input4() {
		contactPersonName.clear();
		contactPersonPhone.clear();
	}

	public String getComplete() {
		return complete_noti.getText().strip();
	}

	public boolean verifyComplete() {
		String completenoti = "Quá trình tạo mới doanh nghiệp đã hoàn tất. iNhat sẽ xử lý yêu cầu của bạn trong thời gian sớm nhất";
		return getComplete().equals(completenoti);
	}

	public void clear_input() {
		try {
			nameCompany.clear();
			addressCompany.clear();
			phoneCompany.clear();
			emailCompany.clear();
			websiteCompany.clear();
			ownerCompany.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Tạo mới doanh nghiệp_client
	public static String create_DN(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//img[@alt='Avatar']")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//p[contains(text(),'Tạo doanh nghiệp mới')]")).click();
			Thread.sleep(1000);

			WebElement name_company = driver.findElement(By.name("vietnamese_name"));
			WebElement address = driver.findElement(By.xpath("//div[@class='form-group']//input[@name='address']"));
			WebElement phone = driver.findElement(By.xpath("//div[@class='form-group']//input[@name='phone']"));
			WebElement email = driver.findElement(By.xpath("//div[@class='form-group']//input[@name='email']"));
			WebElement website = driver.findElement(By.xpath("//div[@class='form-group']//input[@name='website']"));
			WebElement owner = driver.findElement(By.name("owner"));
			WebElement upload = driver.findElement(By.className("name-file__edit"));
			WebElement button = driver.findElement(By.id("next-step-btn"));
			Thread.sleep(1500);
			button.click();

			WebElement noti = driver.findElement(By.id("vietnamese_name_add_error"));
			if (noti != null) {
				System.out.println("Tạo mới khi để trống tên công ty: " + noti.getText());
				name_company.sendKeys("Công ty Martech");
				Thread.sleep(1000);
				button.click();
				Thread.sleep(1000);
				noti = driver.findElement(By.id("address_add_error"));
				if (noti != null) {
					System.out.println("Tạo mới khi để trống địa chỉ công ty: " + noti.getText());
					address.sendKeys("23 Trường Thi 1, Đà Nẵng");
					Thread.sleep(1000);
					button.click();
					Thread.sleep(1000);
					noti = driver.findElement(By.id("phone_add_error"));
					if (noti != null) {
						System.out.println("Tạo mới khi để trống SDT doanh nghiệp: " + noti.getText());
						phone.sendKeys("0328341092");
						Thread.sleep(1000);
						button.click();
						Thread.sleep(1000);
						noti = driver.findElement(By.id("email_add_error"));
						if (noti != null) {
							System.out.println("Tạo mớI khi chưa nhập email: " + noti.getText());
							email.sendKeys("ndtruong.conando");
							Thread.sleep(1000);
							button.click();
							Thread.sleep(1000);
							noti = driver.findElement(By.id("email_add_error"));
							if (noti != null) {
								System.out.println("Tạo mới khi nhập email sai định dạng: " + noti.getText());
								email.clear();
								Thread.sleep(1000);
								email.sendKeys("ndtruong.conando@gmail.com");
								Thread.sleep(1000);
								button.click();
								Thread.sleep(1000);
								noti = driver.findElement(By.id("website_add_error"));
								if (noti != null) {
									System.out.println("Tạo mới khi chưa nhập địa chỉ Website: " + noti.getText());
									website.sendKeys("facebook.com");
									Thread.sleep(1000);
									button.click();
									Thread.sleep(1000);
									noti = driver.findElement(By.id("website_add_error"));
									if (noti != null) {
										System.out.println("Tạo mới khi nhập sai định dạng website: " + noti.getText());
										website.clear();
										Thread.sleep(1000);
										website.sendKeys("https://www.facebook.com");
										Thread.sleep(1000);
										button.click();
										Thread.sleep(1000);
										noti = driver.findElement(By.id("owner_add_error"));
										if (noti != null) {
											System.out
													.println("Tạo mới khi để trống tên chủ sở hữu: " + noti.getText());
											owner.sendKeys("Nguyễn Đan Trường");
											Thread.sleep(1000);
											button.click();
											Thread.sleep(1000);
											noti = driver.findElement(By.id("logo_add_error"));
											if (noti != null) {
												System.out.println(
														"Tạo mới khi chưa up logo doanh nghiệp: " + noti.getText());
												upload.click();
												Thread.sleep(1500);
												// Khởi tạo Robot class
												Robot rb = null;
												try {
													rb = new Robot();
												} catch (AWTException e) {
													e.printStackTrace();
												}

												// Copy File path vào Clipboard
												String filePath = "C:\\Users\\Admin\\Downloads\\test1.webp";
												StringSelection str = new StringSelection(filePath);
												Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str,
														null);

												Thread.sleep(2000);

												// Nhấn Control+V để dán
												rb.keyPress(KeyEvent.VK_CONTROL);
												rb.keyPress(KeyEvent.VK_V);

												Thread.sleep(2000);

												// Nhấn Enter
												rb.keyPress(KeyEvent.VK_ENTER);
												rb.keyRelease(KeyEvent.VK_ENTER);
												rb.keyRelease(KeyEvent.VK_CONTROL);

												Thread.sleep(5000);
												button.click();
											} else {
												System.out.println("Chưa có thông báo khi không up logo doanh nghiệp");
												driver.close();
											}
										} else {
											System.out.println("Chưa có thông báo khi để trống chủ sở hữu");
											driver.close();
										}
									} else {
										System.out.println("Chưa có thông báo khi nhập sai định dạng website");
										driver.close();
									}
								} else {
									System.out.println("Chưa có thông báo khi để trống địa chỉ website");
									driver.close();
								}
							} else {
								System.out.println("Chưa có thông báo khi email sai định dạng");
								driver.close();
							}
						} else {
							System.out.println("Chưa có thông báo khi để trống email doanh nghiệp");
							driver.close();
						}
					} else {
						System.out.println("Chưa có thông báo khi để trống SDT doanh nghiệp");
						driver.close();
					}
				} else {
					System.out.println("Chưa có thông báo khi để trống địa chỉ công ty");
					driver.close();
				}
			} else {
				System.out.println("Chưa có thông báo khi để trống tên công ty");
				driver.close();
			}
			return "Hoàn tất bước 1";
		} catch (Exception e) {
			e.printStackTrace();
			return "Bước 1 thất bại...";
		}
	}

	// Bước 2-3-4 tạo DN
	public static String add_DN(WebDriver driver) {
		try {
			WebElement button = driver.findElement(By.id("next-step-btn"));
			driver.findElement(By.xpath("//input[@placeholder='Nhập tên dịch vụ']"))
					.sendKeys("Chuyên lập trình automation");
			Thread.sleep(1000);
			button.click();
			Thread.sleep(1000);
			button.click();
			Thread.sleep(1000);
			WebElement noti = driver.findElement(By.id("info_add_error"));
			if (noti != null) {
				System.out.println("Tạo mới khi chưa giới thiệu công ty: " + noti.getText());
				driver.findElement(By.id("introduce__field")).sendKeys("Công ty số 1 Đà nẵng");
				Thread.sleep(1000);
				button.click();
				Thread.sleep(1000);
				noti = driver.findElement(By.id("business_license_add_error"));
				if (noti != null) {
					System.out.println("Tạo mới khi chưa có giấy phép kinh doanh: " + noti.getText());
					driver.findElement(By.xpath("(//span[@class='fw-400 font-14 lh-18'])[1]")).click();
					Thread.sleep(1500);
					// Khởi tạo Robot class
					Robot rb = null;
					try {
						rb = new Robot();
					} catch (AWTException e) {
						e.printStackTrace();
					}

					// Copy File path vào Clipboard
					String filePath = "C:\\Users\\Admin\\Downloads\\test2.jpg";
					StringSelection str = new StringSelection(filePath);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str,
							null);
					Thread.sleep(2000);

					// Nhấn Control+V để dán
					rb.keyPress(KeyEvent.VK_CONTROL);
					rb.keyPress(KeyEvent.VK_V);

					Thread.sleep(2000);

					// Nhấn Enter
					rb.keyPress(KeyEvent.VK_ENTER);
					rb.keyRelease(KeyEvent.VK_ENTER);
					rb.keyRelease(KeyEvent.VK_CONTROL);

					Thread.sleep(5000);
					button.click();

					Thread.sleep(1000);
					Select select = new Select(driver.findElement(By.name("business_type")));
					select.selectByValue("2");
					Thread.sleep(1000);
					WebElement checkbox = driver.findElement(By.name("activiy_area_all"));
					Boolean checkbox_active = checkbox.isSelected();
					if (checkbox_active == false) {
						checkbox.click();
						Thread.sleep(1000);
						select = new Select(driver.findElement(By.name("company_size")));
						select.selectByVisibleText("Từ 51 đến 100 người");
						Thread.sleep(1000);
						button.click();
						Thread.sleep(1000);
						noti = driver.findElement(By.id("contact_person_name_add_error"));
						if (noti != null) {
							System.out.println("Tạo mới khi chưa nhập họ tên người liên hệ: " + noti.getText());
							driver.findElement(By.name("contact_person_name")).sendKeys("Nguyễn Đan Trường");
							Thread.sleep(1000);
							driver.findElement(By.name("contact_person_position")).sendKeys("Trưởng Phòng");
							Thread.sleep(1000);
							button.click();
							Thread.sleep(1000);
							noti = driver.findElement(By.id("contact_person_phone_add_error"));
							if (noti != null) {
								System.out.println("Tạo mới khi chưa nhập sdt người liên hệ");
								Thread.sleep(1000);
								driver.findElement(By.name("contact_person_phone")).sendKeys("036465856");
								Thread.sleep(1000);
								button.click();
							} else {
								System.out.println("Chưa có thông báo khi để trống SDT người liên hệ");
								driver.close();
							}
						} else {
							System.out.println("Chưa có thông báo khi để trống họ tên người liên hệ");
							driver.close();
						}
					} else {
						System.out.println("Không thể chọn khu vực hoạt động");
						driver.close();
					}
				} else {
					System.out.println("Chưa có thông báo khi để trống mục giới thiệu công ty");
					driver.close();
				}
			} else {
				System.out.println("Chưa có thông báo khi để trống mục giới thiệu công ty");
				driver.close();
			}
			return "Hoàn tất tạo mới Doanh Nghiệp";
		} catch (Exception e) {
			e.printStackTrace();
			return "Tạo mới Doanh Nghiệp thất bại...";
		}
	}
}
