package question_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;
import setup.indexPage;

public class Main {
    int testcase;
    String question, answer;

    public Main(int testcase, String question, String answer) {
        this.testcase = testcase;
        this.question = question;
        this.answer = answer;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            login.Module login = new login.Module(driver);
            indexPage index = new indexPage(driver);
            question_client.Module newQuestion = new question_client.Module(driver);

            index.waitForPageLoaded();
            login.navigation_login.click();
            Thread.sleep(2000);

            index.login();
            index.waitForPageLoaded();

            // Mở chế độ doanh nghiệp
            index.openFormCreate();
            index.waitForPageLoaded();

            newQuestion.openQuestionForm();
            index.waitForPageLoaded();

            Main[] data = {
                    new Main(1, "", "Đây là câu trả lời"),
                    new Main(2, "Đây là câu hỏi?", ""),
                    new Main(3, "Đây là câu hỏi?", "Đây là câu trả lời"),
            };
            for (int i = 0; i < data.length; i++) {
                System.out.println("=======================");
                System.out.println("Testcase: " + data[i].testcase);
                newQuestion.setText(data[i].question, data[i].answer);
                Thread.sleep(1000);

                String noti = newQuestion.getNoti();
                switch (noti) {
                    case "Không được bỏ trống trường nào":
                        System.out.println(noti);
                        index.passed();
                        newQuestion.clear_input();
                        break;
                    default:
                        if (newQuestion.verifyQuestion("Đây là câu hỏi?")) {
                            System.out.println("Tạo câu hỏi thường gặp thành công");
                            index.passed();
                        } else {
                            index.failed();
                        }
                        break;
                }
                Thread.sleep(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
