package register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Module {

	WebDriver driver;
	String idElement;

	public Module(String idElement) {
		this.idElement = idElement;
	}

	@FindBy(css = ".btn-user-singup")
	private WebElement navigation_register;

	@FindBy(name = "robotRegister")
	public WebElement checkbox;

	@FindBy(name = "emailRegister")
	private WebElement username_input;

	@FindBy(name = "passwordRegister")
	private WebElement password_input;

	@FindBy(name = "confirmPasswordRegister")
	private WebElement confirm_password_input;

	@FindBy(xpath = "//button[contains(text(),'Đăng kí')]")
	private WebElement register_button;

	@FindBy(css = "button[class='btn btn-primary btn-block verify__btn']")
	public WebElement confirmBtn;

	@FindBy(css = "div[id='register-error'] div[class='notification__close-btn']")
	public WebElement closeNotify;

	@FindBy(css = "form[id='verifyForm'] label[class='verify__input-label']")
	private WebElement verify_mail;

	public Module(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigationRegister() {
		navigation_register.click();
		checkbox.click();
	}

	public void setText(String username, String password, String confirmpass) {
		username_input.sendKeys(username);
		password_input.sendKeys(password);
		confirm_password_input.sendKeys(confirmpass);
		register_button.click();
	}

	public void clear_input() {
		username_input.clear();
		password_input.clear();
		confirm_password_input.clear();
	}

	public boolean verify_login() {
		String text = verify_mail.getText().strip();
		return text.equals("Nhập mã xác thực");
	}

	public String getNoti() throws Exception {
		Module[] listnoti = {
				new Module("emailRegisterErr"),
				new Module("pwRegisterErr"),
				new Module("pwConfirmRegisterErr"),
				new Module("robotRegisterErr")
		};
		String notify = "";
		for (int i = 0; i < listnoti.length; i++) {
			WebElement noti = driver.findElement(By.id(listnoti[i].idElement));
			Thread.sleep(1000);
			notify = noti.getText().strip();
			if (notify.length() > 0) {
				System.out.println(notify);
				break;
			}
		}
		return notify;
	}

	public static String register(WebDriver driver) {
		try {
			driver.findElement(By.xpath("//i[@class='fas fa-smile-plus']")).click();
			Thread.sleep(1500);
			WebElement username = driver.findElement(By.name("emailRegister"));
			WebElement password = driver.findElement(By.name("passwordRegister"));
			WebElement confirmpass = driver.findElement(By.name("confirmPasswordRegister"));
			WebElement checkbox = driver.findElement(By.name("robotRegister"));
			WebElement register = driver.findElement(By.xpath("//button[contains(text(),'Đăng kí')]"));

			password.sendKeys("truong2410");
			Thread.sleep(1000);
			confirmpass.sendKeys("truong2410");
			Thread.sleep(1000);
			checkbox.click();
			Thread.sleep(1000);
			register.click();
			WebElement noti = driver.findElement(By.id("emailRegisterErr"));
			if (noti != null) {
				System.out.println("Đăng ký khi để trống Username: " + noti.getText());
				username.sendKeys("nguyendantruongg");
				Thread.sleep(1000);
				password.sendKeys("truong2410");
				Thread.sleep(1000);
				confirmpass.sendKeys("truong2410");
				Thread.sleep(1000);
				register.click();

				noti = driver.findElement(By.id("emailRegisterErr"));
				if (noti != null) {
					System.out.println("Đăng ký khi nhập sai định dạng Email: " + noti.getText());
					username.clear();
					Thread.sleep(1000);
					username.sendKeys("nguyendantruongg@gmail.com");
					Thread.sleep(1000);
					password.clear();
					Thread.sleep(1000);
					confirmpass.clear();
					Thread.sleep(1000);
					register.click();
					noti = driver.findElement(By.id("pwRegisterErr"));

					if (noti != null) {
						System.out.println("Đăng ký khi để trống password: " + noti.getText());
						password.sendKeys("truong");
						Thread.sleep(1000);
						confirmpass.sendKeys("truong");
						Thread.sleep(1000);
						register.click();
						noti = driver.findElement(By.id("pwRegisterErr"));

						if (noti != null) {
							System.out.println("Đăng ký khi nhập sai định dạng password: " + noti.getText());
							password.clear();
							Thread.sleep(1000);
							password.sendKeys("dantruong2410");
							Thread.sleep(1000);
							register.click();

							noti = driver.findElement(By.id("pwConfirmRegisterErr"));
							if (noti != null) {
								System.out.println("Đăng ký khi xác nhận sai password: " + noti.getText());
								confirmpass.clear();
								Thread.sleep(1000);
								confirmpass.sendKeys("dantruong2410");
								Thread.sleep(1000);
								checkbox.click();
								Thread.sleep(1000);
								register.click();

								noti = driver.findElement(By.id("robotRegisterErr"));
								if (noti != null) {
									System.out.println("Đăng ký khi chưa xác nhận Robot: " + noti.getText());
									checkbox.click();
									Thread.sleep(1000);
									register.click();
								} else {
									System.out.println("Không tìm thấy thông báo lỗi khi chưa xác nhận robot");
								}
							} else {
								System.out.println("Không tìm thấy thông báo lỗi khi xác nhận sai password");
							}
						} else {
							System.out.println("Không tìm thấy thông báo lỗi khi nhập sai định dạng Email");
						}
					} else {
						System.out.println("Không tìm thấy thông báo lỗi khi chưa nhập password");
					}
				} else {
					System.out.println("Không tìm thấy thông báo lỗi khi nhập sai định dạng Email");
				}
			} else {
				System.out.println("Không tìm thấy thông báo lỗi khi chưa nhập Username");
			}
			return "Hoàn tất đăng ký";
		} catch (Exception e) {
			e.printStackTrace();
			return "Đăng ký thất bại...";
		}
	}

}
