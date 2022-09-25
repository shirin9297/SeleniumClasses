package day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C02_Alerts {

    /*
    Bir web sitesine girdigimizde karsimiza bir uyari mesaji yada bir buttona tikladigimizda bir
     uyari (alert) cikabilir. Eger bu uyariya incele(mause sag tikla-inspect) yapabiliyorsak bu tur alertlere HTML alert denir
     ve istedigimiz locate'i alabilir. Ama gelen uyari kutusuna mudahele(sag tik-incele) edemiyorsak bu tur alert'lere js(java script) alert denir.
     Js alert'lere mudahele edebilmek icin
     -tamam yada ok icin driver.switchTo().alert().accept() kullanilir
     -iptal icin driver.switchTo().alert().dismiss() methodu kullanilir
     -Alert icindeki mesaji almak icin driver.switchTo().alert().getText() kullanilir
     -Alert bizden bir metin istiyorsa driver.switchTo().alert().sendKeys("") kullanilir
     */

    /*
    Bir class olusturun: Alerts
    https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
    Bir metod olusturun: acceptAlert
    1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının  “You successfully clicked an alert” oldugunu test edin.
    Bir metod olusturun: dismissAlert
    2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    “successfuly” icermedigini test edin.
    Bir metod olusturun: sendKeysAlert
    3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.
     */
    WebDriver driver;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @After
    public void tearDown() {
        //driver.close();
    }
    @Test
    public void test1() throws InterruptedException {
        //https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //Bir metod olusturun: acceptAlert
        //1. butona tıklayın, uyarıdaki OK(Tamam) butonuna tıklayın ve result mesajının
        // “You successfully clicked an alert” oldugunu test edin.
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        WebElement mesaj = driver.findElement(By.xpath("//*[@id='result']"));
        String actualMesaj = mesaj.getText();
        String expectedMesaj = "You successfully clicked an alert";
        Assert.assertEquals(expectedMesaj,actualMesaj);
    }
    @Test
    public void test2() throws InterruptedException {
        //2. butona tıklayın, uyarıdaki Cancel(iptal) butonuna tıklayın ve result mesajının
        //“successfuly” icermedigini test edin.
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        //Alert üzerindeki mesajı yazdırın
        System.out.println("2.Buttonun Alert Mesajı = "+driver.switchTo().alert().getText());
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();
        String actualMesaj = driver.findElement(By.xpath("//*[@id='result']")).getText();
        String expectedMesaj = "successfuly";
        Assert.assertFalse(actualMesaj.contains(expectedMesaj));
    }
    @Test
    public void test3() throws InterruptedException {
        //3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK(Tamam) butonuna
        //tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        //Alert üzerindeki mesajı yazdırınız
        Thread.sleep(2000);
        System.out.println("3. Butonun alert mesaji : "+driver.switchTo().alert().getText());
        driver.switchTo().alert().sendKeys("Erol Evren");
        driver.switchTo().alert().accept();
        String actualMesaj = driver.findElement(By.xpath("//*[@id='result']")).getText();
        String expectedMesaj = "Erol Evren";
        Assert.assertTrue(actualMesaj.contains(expectedMesaj));
    }
}
