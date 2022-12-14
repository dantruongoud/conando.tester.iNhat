package create_danhgia_client;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Module {
	private WebDriver driver;
	String element;

	public Module(String element) {
		this.element = element;
	}

	@FindBy(css = "a[href='#danh-gia']")
	private WebElement danh_giaNavigation;

	@FindBy(css = "a[href='#danh-gia']")
	private WebElement danh_gia;

	@FindBy(css = "a[data-toggle='modal'] span")
	private WebElement create_review;

	@FindBy(name = "needed")
	private WebElement needed;

	@FindBy(name = "price")
	private WebElement price;

	@FindBy(name = "response")
	private WebElement response;

	@FindBy(name = "professional")
	private WebElement professional;

	@FindBy(name = "trust")
	private WebElement trust;

	@FindBy(css = "button[class='btn edit-btn edit-btn--primary submit-rating-btn']")
	private WebElement send_btn;

	@FindBy(css = ".cards-company")
	public WebElement cards_company;

	@FindBy(id = "review-content")
	public WebElement review_content;

	public Module(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void openForm() {
		try {
			danh_gia.click();
			Thread.sleep(2000);
			JavascriptExecutor ex = (JavascriptExecutor) driver;
			ex.executeScript("arguments[0].click();", create_review);
			// create_review.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void rangePoint() {
		try {
			needed.sendKeys(Keys.RIGHT);
			needed.sendKeys(Keys.RIGHT);
			needed.sendKeys(Keys.RIGHT);
			needed.sendKeys(Keys.RIGHT);
			needed.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			price.sendKeys(Keys.RIGHT);
			price.sendKeys(Keys.RIGHT);
			price.sendKeys(Keys.RIGHT);
			price.sendKeys(Keys.RIGHT);
			price.sendKeys(Keys.RIGHT);
			price.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			response.sendKeys(Keys.RIGHT);
			response.sendKeys(Keys.RIGHT);
			response.sendKeys(Keys.RIGHT);
			response.sendKeys(Keys.RIGHT);
			response.sendKeys(Keys.RIGHT);
			response.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			professional.sendKeys(Keys.RIGHT);
			professional.sendKeys(Keys.RIGHT);
			professional.sendKeys(Keys.RIGHT);
			professional.sendKeys(Keys.RIGHT);
			professional.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			trust.sendKeys(Keys.RIGHT);
			trust.sendKeys(Keys.RIGHT);
			trust.sendKeys(Keys.RIGHT);
			trust.sendKeys(Keys.RIGHT);
			trust.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setText(String content) {
		try {
			review_content.sendKeys(content);
			send_btn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getNoti() {
		String noti = "";
		Module[] validation = {
				new Module("review-content-error")
		};
		for (int i = 0; i < validation.length; i++) {
			noti = driver.findElement(By.id(validation[i].element)).getText().strip();
			if (noti.length() > 0) {
				System.out.println(noti);
				break;
			}
		}
		return noti;
	}

	// Tạo mới đánh giá đối với doanh nghiệp
	public static String create_danhgia(WebDriver driver) {
		try {

			driver.findElement(By.xpath("//a[@href='/search']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@onclick=\"location.href='/thc-media'\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[contains(text(),'Đánh giá')]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@data-toggle='modal']")).click();

			WebElement danhgia1 = driver.findElement(By.name("needed"));
			WebElement danhgia2 = driver.findElement(By.name("price"));
			WebElement danhgia3 = driver.findElement(By.name("response"));
			WebElement danhgia4 = driver.findElement(By.name("professional"));
			WebElement danhgia5 = driver.findElement(By.name("trust"));

			danhgia1.sendKeys(Keys.RIGHT);
			danhgia1.sendKeys(Keys.RIGHT);
			danhgia1.sendKeys(Keys.RIGHT);
			danhgia1.sendKeys(Keys.RIGHT);
			danhgia1.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			danhgia2.sendKeys(Keys.RIGHT);
			danhgia2.sendKeys(Keys.RIGHT);
			danhgia2.sendKeys(Keys.RIGHT);
			danhgia2.sendKeys(Keys.RIGHT);
			danhgia2.sendKeys(Keys.RIGHT);
			danhgia2.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			danhgia3.sendKeys(Keys.RIGHT);
			danhgia3.sendKeys(Keys.RIGHT);
			danhgia3.sendKeys(Keys.RIGHT);
			danhgia3.sendKeys(Keys.RIGHT);
			danhgia3.sendKeys(Keys.RIGHT);
			danhgia3.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			danhgia4.sendKeys(Keys.RIGHT);
			danhgia4.sendKeys(Keys.RIGHT);
			danhgia4.sendKeys(Keys.RIGHT);
			danhgia4.sendKeys(Keys.RIGHT);
			danhgia4.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);
			danhgia5.sendKeys(Keys.RIGHT);
			danhgia5.sendKeys(Keys.RIGHT);
			danhgia5.sendKeys(Keys.RIGHT);
			danhgia5.sendKeys(Keys.RIGHT);
			danhgia5.sendKeys(Keys.RIGHT);
			Thread.sleep(1000);

			WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Gửi đánh giá')]"));
			Select select = new Select(driver.findElement(By.name("service_id")));
			WebElement noidung = driver.findElement(By.name("content"));

			button.click();
			Thread.sleep(1000);
			Alert noti = driver.switchTo().alert();
			if (noti != null) {
				System.out.println("Gửi đánh giá khi chưa nhập nội dung: " + noti.getText());
				noti.accept();
				Thread.sleep(1000);
				select.selectByVisibleText("Cung cấp gas");
				Thread.sleep(1500);
				noidung.sendKeys("Tôi là nguyễn đan trường");
				Thread.sleep(1000);
				button.click();
			} else {
				System.out.println("Không tìm thấy thông báo");
			}
			return "Đã gửi đánh giá lên doanh nghiệp";
		} catch (Exception e) {
			e.printStackTrace();
			return "Gửi đánh giá thất bại...";
		}
	}

}
