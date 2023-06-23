package qtriptest.Utility;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelOperation {
    private XSSFWorkbook workbook=null;
    private XSSFSheet sheet = null;
    private XSSFRow row= null;
    private XSSFCell cell= null;
    private String filePath="/home/crio-user/workspace/pronoysunny-47-ME_QTRIP_QA/app/src/test/resources/DatasetsforQTrip.xlsx";
    Object obj[][]=null;
    int lastRow= 0;
    int lastColumn=0;

    public  void setupExcel(){
        File file = new File(filePath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
             workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }       
    
}

public Object[][] readDataFromExcel(String sheetName){
    sheet=workbook.getSheet(sheetName);
    lastRow=sheet.getLastRowNum();
    lastColumn = sheet.getRow(0).getLastCellNum();
    obj = new Object[lastRow][lastColumn-1]; 
   for (int i=0 ; i<=obj.length-1;i++){
        row=sheet.getRow(i+1);
       for (int j =0 ; j<=obj[0].length-1;j++){
            cell=row.getCell(j+1);
           obj[i][j]=cell.getStringCellValue();
       }
   }
   return obj;
}
}
