package report_danhgia_client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Driver_JAVA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://inhat-staging.thcmedia.vn/";

        Thread.sleep(3000);
        driver.navigate().to(url);
        driver.manage().window().maximize();

        Thread.sleep(2000);
        String login = Verify_DN_client.Module.login_iNhat(driver);
        if (login.equals("Login Success")) {
            System.out.println("Đăng nhập thành công");

            String report_danhgia = Module.report_danhgia(driver);
            System.out.println(report_danhgia);

        } else {
            System.out.println("Đăng nhập thất bại: " + login);
            driver.close();
        }

    }
}
