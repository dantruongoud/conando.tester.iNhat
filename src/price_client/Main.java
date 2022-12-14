package price_client;



import org.openqa.selenium.WebDriver;

import setup.baseSetup;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        baseSetup init = new baseSetup();
        WebDriver driver = init.initChromeDriver();

        Thread.sleep(2000);
        String login = Verify_DN_client.Module.login_iNhat(driver);
        if (login.equals("Login Success")) {
            System.out.println("Đăng nhập thành công");

            String create_price = Module.create_price(driver);
            System.out.println(create_price);

        } else {
            System.out.println("Đăng nhập thất bại: " + login);
            driver.close();
        }

    }
}
