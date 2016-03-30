/**
 * @Author Gaurav
 */

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

//import org.junit.Before;
//import java.util.List;
//import static org.junit.Assert.assertFalse;


public class CodeWarsSubmitCodeTest {
    WebDriver driver = new FirefoxDriver(); // Creating WebDriver
    @Test
    public void codewarsSubmitCodeSuccess() {
        // 1. Go To Code Wars (https://www.codewars.com)
        driver.get("https://www.codewars.com/"); // Going to Code Wars
        //driver.manage().window().maximize();
        // 2. Verify Sign In Page is displayed
        WebDriverWait wait = new WebDriverWait(driver, 500);
        // Click on LogIn
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log In")));
        WebElement logIn = driver.findElement(By.linkText("Log In"));
        logIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        // Validating that we are in signIn page by looking at static content @ Sign Page
        String response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"GitHub Log In\" Not found ", response.contains("GitHub Log In"));
        // 3. Enter email
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"email\" Not found ", response.contains("email"));
        WebElement emailTextBox = driver.findElement(By.id("user_email"));
        emailTextBox.clear();
        emailTextBox.sendKeys("gk_71189@yahoo.co.in");
        // 6. Enter Password
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"password\" Not found ", response.contains("password"));
        WebElement passwordTextBox = driver.findElement(By.id("user_password"));
        passwordTextBox.clear();
        passwordTextBox.sendKeys("testatcodewars");
        // 7. Log In
        assertTrue("Validation Falied - Text \"Log In\" Not found ", response.contains("Log In"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"new_user\"]/button")));
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"new_user\"]/button"));
        logInButton.click();
        // 8. Verify User did sign in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_profile_link\"]/div[1]/img")));
        response = driver.getPageSource();
        assertTrue("Validation Falied - Text \"Kata\" Not found ", response.contains("Kata"));
        driver.get("http://www.codewars.com/kata/function-1-hello-world");
        response = driver.getPageSource();
        assertTrue("Validation Falied -  \"Train or Train Again\" Not found ", response.contains("play_btn"));
        WebElement trainButton;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Train")));
        if(response.contains("replay_btn")){
            trainButton = driver.findElement(By.id("replay_btn"));
        }
        else{
            trainButton = driver.findElement(By.id("play_btn"));
        }

        trainButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Submit")));
        response = driver.getPageSource();
        assertTrue("Validation Falied -  \"Submit\" Not found ", response.contains("Submit"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("CodeMirror")));
        WebElement codeBox= driver.findElement(By.className("CodeMirror"));
        WebElement nextDiv = codeBox.findElement(By.tagName("div"));
        codeBox.click();
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'overflow: visible;');",nextDiv);
        WebElement textarea = nextDiv.findElement(By.tagName("textarea"));
        // String foo = "1\n "; // till this string is visible in codebox

        textarea.sendKeys(Keys.CONTROL + "a");
        textarea.sendKeys(Keys.DELETE);
       /*if(!(codeBox.getText().contentEquals(foo) || codeBox.getText().contentEquals(foo+" "))){
           while(!(codeBox.getText().contentEquals(foo+" "))){
               textarea.sendKeys(Keys.DELETE); // send delete
               textarea.sendKeys(Keys.BACK_SPACE);// send backspace
           }
       }*/

        // foo = "1\n ";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attempt_btn")));
        WebElement submitButton = driver.findElement(By.id("attempt_btn"));
        //if(codeBox.getText().contentEquals(foo)){
        textarea.sendKeys("public class HelloWorld{ \n public static String greet");
        textarea.sendKeys(("\u0028")+("\u0029")+"{\n");
        textarea.sendKeys(" return "+"\"hello world"+("\u0021")+"\";}\n}");

        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Submit Final")));
        response = driver.getPageSource();
        assertTrue("validation Failed. test Failed",response.contains("Submit Final"));
        submitButton = driver.findElement(By.linkText("Submit Final"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("solutions")));
        Actions actions = new Actions(driver);
        WebElement profile = driver.findElement(By.xpath("//*[@id=\"header_profile_link\"]/div[2]/div[2]"));
        actions.moveToElement(profile).build().perform();

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