package day14;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class C03_ReadExcel {
    @Test
    public void readExcelTest() throws IOException {

       // Belirtilen satirNo ve sutunNo degerlerini parametre olarak alip o cell'deki datayi konsola yazdiralim
        //Sonucun konsolda yazanla ayni oldugunu dogrulayin

        int satir = 12;
        int sutun = 2;

        String dosyaYolu = "src/resources/ulkeler.xlsx";
        FileInputStream fis = new FileInputStream(dosyaYolu);
        Workbook workbook = WorkbookFactory.create(fis);
        String actualData = workbook.
                getSheet("Sayfa1").
                getRow(satir-1).
                getCell(sutun-1).
                toString();
        //Index 0'dan bashladigi icin bizden istenen satira ulasabilmek icin bir eksigini aliriz.
        System.out.println(actualData);

       String konsoldaYazan = "Baku";
       Assert.assertEquals(konsoldaYazan, actualData);
    }
}
