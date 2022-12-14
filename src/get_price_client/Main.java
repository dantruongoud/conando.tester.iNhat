package get_price_client;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Driver_JAVA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://inhat-staging.thcmedia.vn/";
        driver.navigate().to(url);
        driver.manage().window().maximize();

        Thread.sleep(2000);
        String login = Verify_DN_client.Module.login_iNhat(driver);
        if (login.equals("Login Success")) {
            System.out.println("Đăng nhập thành công");

            String chose_DV = Module.chose_DV(driver);
            System.out.println(chose_DV);

        } else {
            System.out.println("Đăng nhập thất bại: " + login);
            driver.close();
        }

    }
}
