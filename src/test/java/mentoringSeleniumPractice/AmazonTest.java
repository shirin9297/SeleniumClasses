package mentoringSeleniumPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class AmazonTest {

    WebDriver driver;

    @Before
    public void AmazonSearch(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void Test(){

        //    1. https://www.amazon.com.tr sayfasını açalım
        driver.navigate().to("https://www.amazon.com.tr");

        //    2. açılan sayfanın dogru oldugunu  doğrulayalım
        WebElement logo= driver.findElement(By.cssSelector("[id=\"nav-logo-sprites\"]"));
        Assert.assertTrue(logo.isDisplayed());

        //    3. çerezleri kabul et butonuna tıklayalım.

        driver.findElement(By.xpath("//input[@name=\"accept\"]")).click();

        //    4. login olalım
        driver.findElement(By.xpath("//span[text()='Merhaba, Giriş yapın']")).click();
        driver.findElement(By.cssSelector("[id=\"ap_email\"]")).sendKeys("suat_oruc@yahoo.com");
        driver.findElement(By.xpath("//input[@id=\"continue\" and @type=\"submit\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"ap_password\" or @name=\"password\"]")).sendKeys("Amazon115436-");
        driver.findElement(By.cssSelector("[id=\"signInSubmit\"]")).click();

        //    5. başarılı bir şekilde login olduğumuz doğrulayalım.

        Assert.assertTrue(driver.findElement(By.cssSelector("[id=\"nav-link-accountList-nav-line-1\"]")).isDisplayed());

        //    6. team11 adına yeni bir liste oluşturalım

        //    7. listemizin başarılı bir şekilde oluştugunu doğrulayalım

        //    8. Kategori menusunden "Bilgisayarlar" secilir.
        WebElement kategoriBox= driver.findElement(By.xpath("//select[@id=\"searchDropdownBox\"]"));
        Select select=new Select(kategoriBox);
        select.selectByIndex(3);

        //    9. seçimin doğru yapıldıgı doğrulanır.
        String actual=select.getFirstSelectedOption().getText();
        String expected="Bilgisayarlar";
        Assert.assertTrue(actual.equals(expected));

        //    10.Arama kutusuna "lenovo" yazalım ve enter tuşuna basıp arama işleminiyapalım
        driver.findElement(By.cssSelector("[id='twotabsearchtextbox']")).sendKeys("lenovo"+ Keys.ENTER);

        //    11. arama sonuçlarının "lenovo" içerdigi bdoğrulayalım
        String actualArama=driver.findElement(By.cssSelector("[class=\"a-color-state a-text-bold\"]")).getText();
        String expectedArama="\"lenovo\"";
        Assert.assertEquals(expectedArama,actualArama);

        //    12. gelen ürün sayfalarında 3. sayfaya gidelim

        String url=driver.getCurrentUrl();
        url=url.replace("computers&","computers&page=3&");
        driver.get(url);

        //    13. 3. sayfada oldugunumuzu doğrulayalım
        String actualUrl=driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("page=3"));

        //    14. sayfadaki 3. ürünü secelim.

        String secilenUrunTitle=secilenUrunTitleAl(3);

        urunuSec(3);

        //    15. açılan sayfada 3. ürünü seçdiğimiz doğrulayalım
        String actualTitle=driver.findElement(By.cssSelector("[id=\"productTitle\"]")).getText();
        Assert.assertEquals(secilenUrunTitle,actualTitle);

        //    16. seçtiğimiz ürünü sepete atalım.
        driver.findElement(By.cssSelector("[id=\"add-to-cart-button\"]")).click();

        //    17. ürünümüzü listemize attığımızı doğrulayalım.

        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Sepete Eklendi')]")).isDisplayed());

        //    18. sepetimiz açalım
        driver.findElement(By.cssSelector("[id=\"nav-cart\"]")).click();

        //    19. listemizden ürünümüz silelim.
        driver.findElement(By.xpath("//input[@value=\"Sil\" and @data-action=\"delete\"]")).click();

        //    20. listemizden ürünümüzn silindigini doğrulayalım.
        Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

        driver.close();
    }

    private String secilenUrunTitleAl(int uruntitle) {
        String xpath="(//div[@data-index="+uruntitle+"]//a)[4]";
        String title=driver.findElement(By.xpath(xpath)).getText();

        return title;
    }

    private void urunuSec(int urun) {
        String xpath="(//div[@data-index="+urun+"]//a)[1]";
        driver.findElement(By.xpath(xpath)).click();

    }
}
