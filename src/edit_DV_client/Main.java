package edit_DV_client;

import org.openqa.selenium.WebDriver;

import setup.baseSetup;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        baseSetup inti = new baseSetup();
        WebDriver driver = inti.initChromeDriver();

        Thread.sleep(2000);
        String login = Verify_DN_client.Module.login_iNhat(driver);
        if (login.equals("Login Success")) {
            System.out.println("Đăng nhập thành công");

            String edit_DV = Module.edit_DV(driver);
            System.out.println(edit_DV);

        } else {
            System.out.println("Đăng nhập thất bại: " + login);
            driver.close();
        }
    }
}
