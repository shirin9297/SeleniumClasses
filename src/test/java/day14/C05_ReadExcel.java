package day14;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class C05_ReadExcel {
    @Test
    public void readExcelTest1() throws IOException {
        //-Dosya yolunu bir String degiskene atayalim
        String dosyaYolu = "src/resources/ulkeler (1).xlsx";
        //-FileInputStream objesi olusturup,parametre olarak dosya yolunu girelim
        FileInputStream fis = new FileInputStream(dosyaYolu);
        //-Workbook objesi olusturalim,parameter olarak fileInputStream objesini girelim
        //-WorkbookFactory.create(fileInputStream)
        Workbook workbook = WorkbookFactory.create(fis);
        //-sayfa 2'ye gidip satir sayisinin 15, kullanilan satir sayisinin ise 4 oldugunu test edin
        int sonSatir = workbook.getSheet("Sayfa2").getLastRowNum();
        System.out.println(sonSatir);
        int expectedLastRow = 15;
        Assert.assertEquals(expectedLastRow, sonSatir + 1);
        int kullanilanSatir = workbook.getSheet("Sayfa2").getPhysicalNumberOfRows();
        // Excel tablosunda kullanılan satır sayısını bu method ile alırız
        System.out.println(kullanilanSatir);
        int expectedKullanSatir = 4;
        Assert.assertEquals(expectedKullanSatir, kullanilanSatir);

        /*
        odev1:
        1.satirdaki 2.hucreye gidelim ve yazdiralim
1.satirdaki 2.hucreyi bir string degiskene atayalim ve yazdiralim
2.satir 4.cell’in afganistan’in baskenti oldugunu test edelim
Satir sayisini bulalim
Fiziki olarak kullanilan satir sayisini bulun
Ingilizce Ulke isimleri ve baskentleri bir map olarak kaydedelim


      odev2 :

      Yeni bir Class olusturalim WriteExcel
Yeni bir test method olusturalim writeExcelTest()
Adimlari takip ederek 1.satira kadar gidelim
5.hucreye yeni bir cell olusturalim
Olusturdugumuz hucreye “Nufus” yazdiralim
2.satir nufus kolonuna 1500000 yazdiralim
10.satir nufus kolonuna 250000 yazdiralim
15.satir nufus kolonuna 54000 yazdiralim
Dosyayi kaydedelim  10)Dosyayi kapatalim
         */
    }
}
