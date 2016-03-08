/**
 * Created by Gaurav on 3/7/2016.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

public class CodeWarsSignInTest {
    WebDriver driver = new FirefoxDriver(); // Creating WebDriver

    @Test
    public void codewarsSignInShouldBeSuccessful(){
        // 1. Go To Code Wars (https://www.codewars.com)
        driver.get("https://www.codewars.com/"); // Going to Code Wars
        // 2. Verify Sign In Page is displayed
        WebDriverWait wait = new WebDriverWait(driver,100);
        // Click on LogIn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log In")));
        WebElement logIn = driver.findElement(By.linkText("Log In"));
        logIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        // Validating that we are in signIn page by looking at static content @ Sign Page
        String response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"GitHub Log In\" Not found ",response.contains("GitHub Log In"));
        // 3. Enter email
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"email\" Not found ",response.contains("email"));
        WebElement emailTextBox = driver.findElement(By.id("user_email"));
        emailTextBox.clear();
        emailTextBox.sendKeys("gk_71189@yahoo.co.in");
        // 6. Enter Password
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"password\" Not found ",response.contains("password"));
        WebElement passwordTextBox = driver.findElement(By.id("user_password"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("testatcodewars");
        // 7. Log In
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"new_user\"]/button"));
        logInButton.click();
        // 8. Verify User did sign in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_profile_link\"]/div[1]/img")));
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"Kata\" Not found ",response.contains("Kata"));
        // 9. Sign Out
        WebElement profile = driver.findElement(By.xpath("//*[@id=\"header_profile_link\"]/div[2]/div[2]"));
        profile.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
        WebElement signOut = driver.findElement(By.linkText("Sign out"));
        signOut.click();
        // 10. Verify User did sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"GitHub Log In\" Not found ",response.contains("GitHub Log In"));
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
