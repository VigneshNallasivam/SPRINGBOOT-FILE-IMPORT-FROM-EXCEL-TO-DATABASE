package com.example.demo.extras;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Import;

public class ExcelHelper
{
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "Serial", "Name", "Place", "Cell","Mail" };
	static String SHEET = "Sheet1";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Import> excelToDatas(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();

			List<Import> imports = new ArrayList<Import>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				if (rowNumber == 0) 
				{
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Import imported = new Import();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						imported.setSerial((int) currentCell.getNumericCellValue());
						break;

					case 1:
						imported.setName(currentCell.getStringCellValue());
						break;

					case 2:
						imported.setPlace(currentCell.getStringCellValue());
						break;

					case 3:
						imported.setCell((long) currentCell.getNumericCellValue());
						break;
					
					case 4:
						imported.setMail(currentCell.getStringCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

			  imports.add(imported);
			}

			workbook.close();

			return imports;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}


}
