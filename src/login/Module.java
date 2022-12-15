package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Module {

	WebDriver driver;

	String idelement;

	public Module(String idelement) {
		this.idelement = idelement;
	}

	@FindBy(css = ".group-user")
	public WebElement loginsuccess;

	@FindBy(className = "header-login")
	public WebElement navigation_login;

	@FindBy(name = "email")
	private WebElement username_input;

	@FindBy(name = "password")
	private WebElement password_input;

	@FindBy(id = "login-btn")
	private WebElement loginBtn;

	public Module(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setText(String username, String password) {
		try {
			username_input.sendKeys(username);
			password_input.sendKeys(password);
			Thread.sleep(1000);
			loginBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear_input() {
		username_input.clear();
		password_input.clear();
	}

	public String getNotify() {
		Module[] listNoti = {
				new Module("emailErr"),
				new Module("pwErr"),
				new Module("login-error-msg")
		};
		String notify = "";
		for (int i = 0; i < listNoti.length; i++) {
			WebElement noti = driver.findElement(By.id(listNoti[i].idelement));
			notify = noti.getText().strip();
			if (notify.length() > 0) {
				System.out.println(notify);
				break;
			}
		}
		return notify;
	}
}
