package setup;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class baseSetup {
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	// Khởi tạo cấu hình của các Browser để đưa vào Switch Case
	public WebDriver initChromeDriver() {

		ChromeOptions useragent = new ChromeOptions();
		indexPage index = new indexPage(driver);
		useragent.addArguments("disable-notifications");

		driver = new ChromeDriver(useragent);
		System.out.println("Launching Chrome browser...");
		driver.manage().window().maximize();
		driver.get("https://inhat.com.vn/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

		index.waitForPageLoaded();
		return driver;
	}

}
