package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Item;
import gr.blxbrgld.mylist.utilities.ApplicationUtils;
import gr.blxbrgld.mylist.utilities.Constants;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Export Of Book Items To .XLS Files
 * @author blxbrgld
 */
public class BookExcelView extends AbstractExcelView {

    private static final int NUMBER_OF_COLUMNS = 5;

    /**
     * Build Excel Document For 'items' Model Attribute
     * @param model Model Object
     * @param workbook Workbook Object
     * @param request HttpServletRequest Object
     * @param response HttpServletResponse Object
     * @throws Exception In Case Of An Error
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.XLS_DATE_PATTERN);
        String filename = dateFormat.format(new Date()) + "_" + Constants.XLS_FILENAME;
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        Map<String, CellStyle> styles = ApplicationUtils.xlsStyles(workbook);

        // Sheet
        HSSFSheet sheet = workbook.createSheet("Books");
        sheet.setDefaultColumnWidth(Constants.XLS_COLUMN_WIDTH); // Setting A Default Column Width Due To Title, Artists et.al. Columns Which May Be Too Long

        // Header
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("Title");
        header.createCell(1).setCellValue("Author(s)");
        header.createCell(2).setCellValue("Publisher");
        header.createCell(3).setCellValue("Year");
        header.createCell(4).setCellValue("Pages");
        for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
            header.getCell(i).setCellStyle(styles.get(Constants.XLS_HEADER_STYLING));
        }

        // Data
        int record = 1; // Row Counter Excluding Header
        List<Item> items = (List<Item>) model.get("items");
        for(Item item : items) {
            HSSFRow row = sheet.createRow(record++);
            row.createCell(0).setCellValue(item.getTitleEng());
            row.createCell(1).setCellValue(item.getArtistsString()!=null ? item.getArtistsString() : "None Listed");
            row.createCell(2).setCellValue(item.getPublisher().getTitle());
            row.createCell(3).setCellValue(item.getYear()!=null ? Integer.toString(item.getYear()) : "-");
            row.createCell(4).setCellValue(item.getPages()!=null ? Integer.toString(item.getPages()) : "-");
            for(int i = 2; i < NUMBER_OF_COLUMNS; i++) {
                row.getCell(i).setCellStyle(styles.get(Constants.XLS_ALIGN_STYLING)); // Centered
            }
        }

        // Override DefaultColumnWidth For Columns That Can Not Be Too Long
        for(int i = 2; i < NUMBER_OF_COLUMNS; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
