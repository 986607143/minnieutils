package com.sunshine.archer;

import com.sunshine.utils.FileUtils;
import com.sunshine.utils.poi.ExcelUtil;
import com.sunshine.utils.poi.Word2Html;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    private static final String targetPath = "D://5//WORD//";
        //"D://Resource//192.168.17.192//KBMS2.4版本相关文档//开发//临床路径//WORD//";

    private static final String sourcePath = "D://5//#2000//";
        //"D://Resource//192.168.17.192//KBMS2.4版本相关文档//产品//临床路径";

    private static final String htmlPath = "D://5//HTML//";
        //"D://Resource//192.168.17.192//KBMS2.4版本相关文档//开发//临床路径//HTML//";

    public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
        logger.info(Test.class.getResource("/").toString());
        System.out.println(Test.class.getResource("/").toString());
        /*删除文件*/
//        FileUtils.deleteFile(targetPath);
//        FileUtils.createDirectory(targetPath);
//        FileUtils.deleteFile(htmlPath);
//        FileUtils.createDirectory(htmlPath);

        /*copy word文档*/
        getFile();
        /*word转html*/
        word2Html();
//        testSearchStr("502.放射性口腔粘膜炎临床路径_2016_1499219419473.doc");
//        List<File> listFile = FileUtils.getFile(htmlPath);
//        for(File f : listFile){
//                System.out.println(UUID.randomUUID().toString());
//        }
    }

    public static List<File> getFile(){

        List<File> listFile = FileUtils.getFile(sourcePath);
        List<File> newFile = new ArrayList<>();

        String fileName = null;
        int i = 0;
        for(File f : listFile){
            fileName = f.getName();
            if(fileName.endsWith("docx")
                    || fileName.endsWith("doc")){
                i++;
                FileUtils.copy(f, targetPath);
            }else{
                logger.info(f.getPath());
            }
        }

        return listFile;
    }

    public static void word2Html(){

        List<File> listFile = FileUtils.getFile(targetPath);

        int i = 0;
        for(File f : listFile){
            String fileName = f.getName();

//            logger.info(fileName + " >> " + i++);

            try{
                if(fileName.endsWith("docx")){
                    Word2Html.Word2007ToHtml(f, htmlPath);
                }else if(fileName.endsWith("doc")){
                    Word2Html.Word2003ToHtml(f, htmlPath);
                }else{
                    logger.warn(fileName);
                }
            }catch(Exception e){
                e.printStackTrace();
                logger.error(e);
                logger.error(">>>>" + fileName + "<<<<");
            }

        }
    }

    public static void copy(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
             File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    public static void test2() throws IOException {
        Workbook workbookSXSSF = new SXSSFWorkbook();
        Sheet sheetSXSSF = (SXSSFSheet) workbookSXSSF.createSheet();

        // sheetSXSSF.setDefaultColumnWidth(30);
        // sheetSXSSF.setDefaultRowHeightInPoints(30);

        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(new File("D://2.xlsx"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 14; i++) {
            // 写表头
            String title = "测试测试测试测试测试测试测试测试";
            String value = "小明\n敏敏";
            ExcelUtil.setContent(sheetSXSSF, 1, i, HSSFCell.CELL_TYPE_STRING,
                    title, null);
            ExcelUtil.setContent(sheetSXSSF, 0, i, HSSFCell.CELL_TYPE_STRING,
                    value, getCellStyle(workbookSXSSF));
            /* 设置自适应宽度 */
            sheetSXSSF.autoSizeColumn(i);
        }

        // for(Integer[] index : cellIndex2){
        // CellRangeAddress region =
        // new CellRangeAddress(index[0], index[1], index[2], index[3]); //
        // 参数都是从O开始
        // sheetSXSSF.addMergedRegion(region);
        // }

        workbookSXSSF.write(outStream);

        /* 刷新流和关闭流 */
        outStream.flush();
        outStream.close();

    }


    private static CellStyle getCellStyle(Workbook workbook) {

        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

        /* 自动换行 */
        style.setWrapText(true);

        return style;

    }


    public static void test1() throws IOException {
        InputStreamReader inputReader =
                new InputStreamReader(new FileInputStream("D:\\MinNie.txt"));
        BufferedReader reader = new BufferedReader(inputReader);
        String resultLine;
        while ((resultLine = reader.readLine()) != null) {
            System.out.println(resultLine);
        }

    }


    private static List<String[]> readerExcel(String path, String sheetName,
        int minColumns)
        throws IOException, OpenXML4JException, ParserConfigurationException,
        SAXException {
        OPCPackage p = OPCPackage.open(path, PackageAccess.READ);
        p.close();
        return null;
    }
}
