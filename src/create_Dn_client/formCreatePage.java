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
			Thread.sleep(500);
			nextBtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setText_step4(String name, String phone) {
		try {
			contactPersonName.sendKeys(name);
			contactPersonPhone.sendKeys(phone);
			Thread.sleep(500);
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

}
