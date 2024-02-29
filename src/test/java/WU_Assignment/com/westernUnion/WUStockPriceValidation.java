package WU_Assignment.com.westernUnion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WUStockPriceValidation {

	public static boolean validateHashMaps(Map<String, List<String>> map1, Map<String, List<String>> map2) {
		// Check if the maps have the same size
		if (map1.size() != map2.size()) {
			return false;
		}

		// Check each key in map1
		for (String key : map1.keySet()) {
			// If map2 doesn't contain the key, return false value
			if (!map2.containsKey(key)) {
				return false;
			}
			// If the lists associated with the key are different, return false value
			if (!map1.get(key).equals(map2.get(key))) {
				return false;
			}
		}

		// If all key-value pairs are the same, return true value
		return true;
	}

	public static void main(String[] args) {
		Map<String, List<String>> hashmap1 = new HashMap<String, List<String>>();
		Map<String, List<String>> hashmap2 = new HashMap<String, List<String>>();
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://money.rediff.com/losers/bse/daily/groupall");
		try {

			WebElement table = driver.findElement(By.className("dataTable"));

			List<WebElement> rows = table.findElements(By.tagName("tr"));

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Stock Data");

			int rowNum = 0;
			for (WebElement row : rows) {
				List<String> rowData = new ArrayList<String>();
				List<WebElement> cells = row.findElements(By.tagName("td"));
				for (WebElement cell : cells) {
					rowData.add(cell.getText());
				}
				if (rowData.size() != 0) {
					hashmap2.put(rowData.get(0), rowData);
				}

				Row excelRow = sheet.createRow(rowNum++);
				for (int i = 0; i < rowData.size(); i++) {
					Cell cell = excelRow.createCell(i);
					cell.setCellValue(rowData.get(i));
				}
			}

			FileOutputStream fileOut = new FileOutputStream("web_table_data.xlsx");
			workbook.write(fileOut);
			fileOut.close();

			workbook.close();
			driver.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {

			String filePath = "web_table_data.xlsx";

			InputStream inputStream = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				List<String> tmp = new ArrayList<String>();

				for (Cell cell : row) {
					tmp.add(cell.toString());
				}
				if (tmp.size() != 0) {
					hashmap1.put(tmp.get(0), tmp);
				}

			}

			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean isEqual = validateHashMaps(hashmap1, hashmap2);
		System.out.println("HashMaps are equal: " + isEqual);

	}

}
