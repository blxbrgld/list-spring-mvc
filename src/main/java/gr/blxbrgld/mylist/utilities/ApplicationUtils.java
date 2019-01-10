package gr.blxbrgld.mylist.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Map;

/**
 * Application's Utils
 * @author blxbrgld
 */
public class ApplicationUtils {

    /**
     * Create Map Of Predefined Cell Styles To Use For XLS Export
     * @param workbook Workbook Object
     * @return Map Of Cell Styles
     */
    public static Map<String, CellStyle> xlsStyles(Workbook workbook) {
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style;

        // Header
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put(Constants.XLS_HEADER_STYLING, style);

        // Alignment
        style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        styles.put(Constants.XLS_ALIGN_STYLING, style);

        return styles;
    }
}
