package seleniumgenc.Utils;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ShopifyExcelUtils {
		
	public static List<ShopifyFormData> readShopifyFormDataFromExcel(String excelFilePath, String sheetName) throws IOException{
		List<ShopifyFormData> listShopifyFormData = new ArrayList<ShopifyFormData>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		//Apache POI
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = firstSheet.iterator();
		
		while(iterator.hasNext()){
			Row row = iterator.next();
			if(row.getRowNum()==0){
				continue; //skip header row
			}
			Iterator<Cell> cellIterator = row.cellIterator();
			ShopifyFormData shopifyFormData = new ShopifyFormData();
			
			while(cellIterator.hasNext()){
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				
				switch(columnIndex){
					case 0:
						shopifyFormData.setFirstName(nextCell.getStringCellValue());
						break;
					case 1:
						shopifyFormData.setLastName(nextCell.getStringCellValue());
						break;
					case 2:
						shopifyFormData.setUserName(nextCell.getStringCellValue());
						break;
					case 3:
						shopifyFormData.setCity(City.valueOf(nextCell.getStringCellValue()));
						break;
					case 4:
						shopifyFormData.setGender(Gender.valueOf(nextCell.getStringCellValue()));
						break;
					case 5:
						shopifyFormData.setPassword(nextCell.getStringCellValue());
						break;
					case 6:
						shopifyFormData.setTableText(nextCell.getStringCellValue());
						break;
					case 7:
						shopifyFormData.setErrorMessage(nextCell.getStringCellValue());
						break;
				}//switch
			}//inner while
			listShopifyFormData.add(shopifyFormData);
		}//outer while
		
		inputStream.close();
		return listShopifyFormData;
	}

}
