package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppOrder {
    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\KoteBone\\QA\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestAllFormPos() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Екатерина Федосеева");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String resultMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedMessage, resultMessage, "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");
    }

    @Test
    void shouldTestNameFormPos() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Жан-Клод Вандам");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String resultMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expectedMessage, resultMessage, "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.");
    }

    @Test
    void shouldTestNameFormNeg() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ekaterina Fedoseeva");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79999999999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String resultMessage = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().strip();
        String expectedMessage = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expectedMessage, resultMessage, "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");
    }
}