package Verify_DN_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Module {

	WebDriver driver;

	@FindBy(css = ".btn.edit-btn.edit-btn--primary.ml-auto.verify-btn")
	public WebElement btnVerify;

	@FindBy(name = "verify_name")
	private WebElement name_input;

	@FindBy(name = "verify_phone")
	private WebElement phone_input;

	@FindBy(name = "verify_email")
	private WebElement email_input;

	@FindBy(css = "button[class='btn edit-btn edit-btn--primary verify-business-btn']")
	private WebElement send_btn;

	public Module(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setText(String name, String phone, String email) {
		try {
			name_input.sendKeys(name);
			phone_input.sendKeys(phone);
			email_input.sendKeys(email);
			send_btn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		name_input.clear();
		phone_input.clear();
		email_input.clear();
	}

	public String getNoti() {
		Alert alert = driver.switchTo().alert();
		return alert.getText().strip();
	}

	public static String login_iNhat(WebDriver driver) {
		try {
			driver.findElement(By.className("header-login")).click();
			Thread.sleep(2000);
			driver.findElement(By.name("email")).sendKeys("admin@gmail.com");
			Thread.sleep(1000);
			driver.findElement(By.name("password")).sendKeys("123456789");
			driver.findElement(By.id("login-btn")).click();
			Thread.sleep(4000);
			return "Login Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Login Failed";
		}
	}

	// Tìm Doanh nghiệp và thực hiện xác thực
	public static String verify_DN(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//div[@class='item-service-list-one']//div[@class='img-wrap']")).click();
			Thread.sleep(1500);

			Select select = new Select(driver.findElement(By.xpath("//select[@onchange='onChangeCategory(this)']")));
			select.selectByVisibleText("An Ninh, Viễn Thông");
			Thread.sleep(1000);

			driver.findElement(By.xpath("//div[@onclick=\"location.href='/thc-media'\"]")).click();
			Thread.sleep(1500);
			driver.findElement(By.xpath("//button[@class='btn edit-btn edit-btn--primary ml-auto verify-btn']"))
					.click();
			Thread.sleep(1500);

			WebElement modal = driver.findElement(By.id("verify-business"));
			Boolean modal_verify = modal.isDisplayed();
			if (modal_verify) {
				System.out.println("Có popup xác thực doanh nghiệp");
				WebElement name = driver.findElement(By.name("verify_name"));
				WebElement phone = driver.findElement(By.name("verify_phone"));
				WebElement email = driver.findElement(By.name("verify_email"));
				Select position = new Select(driver.findElement(By.name("verify_position")));
				WebElement button = driver
						.findElement(By.xpath("//button[@class='btn edit-btn edit-btn--primary verify-business-btn']"));

				button.click();
				Thread.sleep(1000);
				Alert alert = driver.switchTo().alert();
				if (alert.getText() != null) {
					System.out.println("Gửi xác thực khi chưa nhập đầy đủ thông tin bắt buộc: " + alert.getText());
					alert.accept();
					Thread.sleep(1500);
					name.sendKeys("Tôi cần lấy doanh nghiệp này");
					Thread.sleep(1500);
					button.click();
					Thread.sleep(1000);
					if (alert.getText() != null) {
						System.out.println("Gửi xác thực khi chưa nhập số điện thoại: " + alert.getText());
						alert.accept();
						Thread.sleep(1000);
						phone.sendKeys("0328341092");
						Thread.sleep(1500);
						button.click();
						Thread.sleep(1000);
						alert = driver.switchTo().alert();
						if (alert.getText() != null) {
							System.out.println("Gửi xác thực khi chưa nhập email: " + alert.getText());
							alert.accept();
							Thread.sleep(1000);
							email.sendKeys("ndtruong");
							Thread.sleep(1500);
							button.click();
							Thread.sleep(1000);
							if (alert.getText() != null) {
								System.out.println(
										"Gửi xác thực khi nhập email không đúng định dạng: " + alert.getText());
								alert.accept();
								Thread.sleep(1000);
								email.clear();
								Thread.sleep(1500);
								email.sendKeys("ndtruong.conando@gmail.com");
								Thread.sleep(1200);
								position.selectByVisibleText("Trưởng phòng");
								Thread.sleep(1000);
								button.click();
								Thread.sleep(1000);
								alert = driver.switchTo().alert();
								if (alert.getText() != null) {
									System.out.println("Xác nhận gửi xác thực thành công: " + alert.getText());
									alert.accept();
								} else {
									System.out.println("Không tìm thấy popup xác nhận");
								}
							} else {
								System.out.println("Không tìm thấy validation cho mục này");
							}
						} else {
							System.out.println("Không tìm thấy validation cho mục này");
						}
					} else {
						System.out.println("Không tìm thấy validation cho mục này");
					}
				} else {
					System.out.println("Không tìm thấy validation cho mục này");
				}
			} else {
				System.out.println("Chưa có popup xác thực doanh nghiệp");
			}
			return "Hoàn tất xác thực Doanh Nghiệp";
		} catch (Exception e) {
			e.printStackTrace();
			return "Xác thực Doanh Nghiệp thất bại...";
		}
	}
}
