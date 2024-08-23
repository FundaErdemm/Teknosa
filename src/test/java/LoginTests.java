import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import org.apache.log4j.Logger;

public class LoginTests extends BaseTest {

    private static final Logger logger = Logger.getLogger(LoginTests.class);

    @Test
    void teknosagiristesti() throws InterruptedException {
        logger.info("Test başlıyor: teknosagiristesti");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        logger.info("Arama kutusuna 'saat' kelimesi yazılıyor.");
        driver.findElement(By.id("search-input")).sendKeys("saat");
        Thread.sleep(1000);

        logger.info("Arama butonuna çift tıklanıyor.");
        WebElement ara = driver.findElement(By.className("sbx-search"));
        Actions actions = new Actions(driver);
        actions.doubleClick(ara).perform();

        logger.info("Arama sonuçları bekleniyor.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("prd-inner")));
        WebElement firstProduct = driver.findElements(By.className("prd-inner")).get(0);
        WebElement parent = firstProduct.findElement(By.xpath(".."));
        WebElement link = driver.findElement(By.className("prd-link"));
        String hrefValue = driver.findElement(By.className("prd-link")).getAttribute("href");

        // "Watch" kelimesi geçiyor mu kontrol et
        logger.info("'watch' kelimesi href değerinde aranıyor.");
        if (hrefValue.contains("watch")) {
            js.executeScript("alert('Kelime bulundu!');");
            Thread.sleep(1500);
            driver.switchTo().alert().accept();

            logger.info("Ürün linkine tıklanıyor.");
            link.click();
            Thread.sleep(500);

            try {
                logger.info("Sepete ekle butonu aranıyor.");
                WebElement button = driver.findElement(By.className("AddToCart-AddToCartAction"));
                if (button.isDisplayed()) {
                    logger.info("Sepete ekle butonu bulundu.");
                    js.executeScript("alert('Sepete ekle Butonu bulundu!');");
                    Thread.sleep(1500);
                    driver.switchTo().alert().accept();

                 }
            } catch (org.openqa.selenium.NoSuchElementException e) {
                logger.warn("Sepete ekle butonu bulunamadı.");
                js.executeScript("alert('Sepete ekle Butonu bulunamadı!');");
                Thread.sleep(1500);
                driver.switchTo().alert().accept();
            }

           /* Thread.sleep(2500);
            logger.info("Sepet butonuna tıklanıyorrrrrrr.");
            WebElement sepet = driver.findElement(By.id("addToCartButton"));
            sepet.click();
            */





        } else {
            logger.info("'watch' kelimesi bulunamadı.");
            js.executeScript("alert('Kelime bulunamadı :(!');");
            Thread.sleep(1500);
            driver.switchTo().alert().accept();
            driver.quit();
        }


        logger.info("Test tamamlandı: teknosagiristesti");
            }
        }

