package gr.blxbrgld.myList.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import gr.blxbrgld.myList.model.Item;

/**
 * Export Of Items To .XLS Files
 */
public class ItemExcelView extends AbstractExcelView {

	/**
	 * Build Excel Document for itemList Model Attribute
	 * @param model Model
	 * @param workbook Workbook
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    List<Item> itemList = (List<Item>) model.get("itemList");
		int record = 1; //Row Counter Excluding Header
	    
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String today = dateFormat.format(new Date());
		String filename = today.toString() + "_" + "Export.xls"; 
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		
		Map<String, CellStyle> styles = createStyles(workbook);
		/*
		 * Sheet
		 */
		HSSFSheet excelSheet = workbook.createSheet("Items");
		excelSheet.setDefaultColumnWidth(40);
		/*
		 * Header
		 */
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Artist(s)");
	    excelHeader.createCell(1).setCellValue("Title");
	    excelHeader.createCell(2).setCellValue("Category");
	    excelHeader.createCell(3).setCellValue("Comments");
	    excelHeader.createCell(4).setCellValue("Place");
	    excelHeader.createCell(5).setCellValue("Discs");
	    for(int i = 0; i < 6; i++) excelHeader.getCell(i).setCellStyle(styles.get("header")); //Colored
	    /*
	     * Data
	     */
		for(Item item : itemList) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(item.getArtistsString()!=null ? item.getArtistsString() : "None Listed");
			excelRow.createCell(1).setCellValue(item.getTitleEng());
		    excelRow.createCell(2).setCellValue(item.getCategory().getTitle());
		    excelRow.createCell(3).setCellValue(item.getCommentsString()!=null ? item.getCommentsString() : "-");
		    excelRow.createCell(4).setCellValue(item.getPlace()!=null ? Integer.toString(item.getPlace()) : "-");
		    excelRow.createCell(5).setCellValue(item.getDiscs()!=null ? Integer.toString(item.getDiscs()) : "-");
		    for(int i = 2; i < 6; i++) excelRow.getCell(i).setCellStyle(styles.get("centered")); //Centered
		}
		for(int i = 2; i < 6; i++) excelSheet.autoSizeColumn(i); //Override DefaultColumnWidth
	}
	
	/**
	 * Create Map Of Cell Styles To Use In buildExcelDocument Method
	 * @param workbook Workbook
	 * @return Map Of Cell Styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook workbook) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		CellStyle style;
		/*
		 * Colored
		 */
		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 11);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);
        /*
         * Centered
         */
        style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put("centered", style);

        return styles;
	}
}
