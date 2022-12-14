package edit_del_DV_client;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Driver_JAVA\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://inhat-staging.thcmedia.vn/";
        driver.navigate().to(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        Thread.sleep(2000);

        String login = Verify_DN_client.Module.login_iNhat(driver);
        if (login.equals("Login Success")) {
            System.out.println("Đăng nhập thành công");

            String edit_nhom = Module.edit_nhomDV(driver);
            System.out.println(edit_nhom);

            String del_nhom = Module.del_nhomDV(driver);
            System.out.println(del_nhom);

        } else {
            System.out.println("Đăng nhập thất bại: " + login);
            driver.close();
        }
    }
}
