package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Module {

	WebDriver driver;

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
}
