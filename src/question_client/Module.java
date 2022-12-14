package question_client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.indexPage;

public class Module {

    WebDriver driver;

    @FindBy(css = "a[href='/dashboard/faqs']")
    private WebElement faqsLink;

    @FindBy(css = "a[href='/dashboard/faqs-edit']")
    private WebElement faqsEditLink;

    @FindBy(css = ".btn.edit-btn.add_new_faqs")
    private WebElement newQuestion;

    @FindBy(css = "input[placeholder='Nhập tiêu đề cho câu hỏi']")
    private WebElement question_title;

    @FindBy(css = "textarea[placeholder='Nhập câu trả lời']")
    private WebElement question_content;

    @FindBy(css = ".btn.edit-btn.edit-btn--primary.mr-1.updateFaq")
    private WebElement saveQuestion;

    @FindBy(xpath = "(//input[@placeholder='Nhập tiêu đề cho câu hỏi'])[1]")
    private WebElement question_id;

    @FindBy(id = "error-faq")
    private WebElement error;

    public Module(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    indexPage index = new indexPage(driver);

    public void openQuestionForm() {
        try {
            faqsLink.click();
            index.waitForPageLoaded();
            faqsEditLink.click();
            Thread.sleep(1000);
            newQuestion.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String question, String answer) {
        try {
            question_title.sendKeys(question);
            question_content.sendKeys(answer);
            saveQuestion.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clear_input() {
        question_title.clear();
        question_content.clear();
    }

    public boolean verifyQuestion(String condition) {
        try {
            String question_title = question_id.getAttribute("value");
            return question_title.equals(condition);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getNoti() {
        String nameError = error.getText().strip();
        if (nameError.length() > 0) {
            System.out.println(nameError);
        }
        return nameError;
    }

    // Tạo mới câu hỏi thường gặp
    public static String create_question(WebDriver driver) {
        try {
            driver.findElement(By.className("logo-user")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//li[contains(text(),'Chế độ doanh nghiệp')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("//span[contains(text(),'Quản lý câu hỏi thường gặp')]")).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/a")).click();
            Thread.sleep(1500);

            WebElement add = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[1]/button"));
            WebElement done = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/button"));

            add.click();
            Thread.sleep(1000);
            WebElement tieude = driver.findElement(By.xpath("//input[@placeholder='Nhập tiêu đề cho câu hỏi']"));
            WebElement repply = driver.findElement(By.xpath("//textarea[@placeholder='Nhập câu trả lời']"));
            done.click();
            Thread.sleep(1000);
            WebElement noti = driver.findElement(By.id("error-faq"));
            if (noti != null) {
                System.out.println("Tạo mới khi để trống tiêu đề: " + noti.getText());
                tieude.sendKeys("Làm thế nào để tôi nhận được báo giá cho dịch vụ?");
                Thread.sleep(1000);
                done.click();
                Thread.sleep(1000);
                noti = driver.findElement(By.id("error-faq"));
                if (noti != null) {
                    System.out.println("Tạo mới khi để trống câu trả lời: " + noti.getText());
                    repply.sendKeys("Bạn hãy chọn dịch vụ cần lấy giá và ấn vào nó nhé");
                    Thread.sleep(1000);
                    add.click();
                    Thread.sleep(1000);
                    WebElement tieude2 = driver
                            .findElement(By.xpath("(//input[@placeholder='Nhập tiêu đề cho câu hỏi'])[2]"));
                    tieude2.sendKeys("Tôi không tìm thấy phần đánh giá để xem cảm nhận của các khách hàng khác");
                    Thread.sleep(1000);
                    WebElement repply2 = driver
                            .findElement(By.xpath("(//textarea[@placeholder='Nhập câu trả lời'])[2]"));
                    repply2.sendKeys("Bạn hãy chọn mục Đánh Giá tại mục điều hướng");
                    Thread.sleep(1000);
                    done.click();
                } else {
                    System.out.println("Không tìm thấy thông báo khi câu trả lời rỗng");
                    driver.close();
                }
            } else {
                System.out.println("Không tìm thấy thông báo khi tiêu đề rỗng");
                driver.close();
            }
            return "Hoàn tất tạo mới câu hỏi thường gặp";
        } catch (Exception e) {
            e.printStackTrace();

            return "Tạo mới câu hỏi thường gặp thất bại...";
        }
    }

    // Sắp xếp câu hỏi thường gặp
    public static String up_down_del(WebDriver driver) {
        try {

            driver.findElement(By.xpath("//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[2]/button[1]"))
                    .click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//body[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/button[3]"))
                    .click();
            return "Hoàn tất sắp xếp và xóa";
        } catch (Exception e) {
            e.printStackTrace();
            return "Sắp xếp và xóa thất bại...";
        }
    }
}
