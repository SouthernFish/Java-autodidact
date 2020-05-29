package com.ImageProcess;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @Author TR
 * @Create 2020年01月02日
 * @Title: ImageHsvRedis
 * @Description: 缩略图Hsv缓存
 */
public class ImageHsvRedis {

    // 保存图库缩略图 Hsv
    public void saveHsvClub(float clubHsv[][], String fileName, String sheetName){
        try{
            File excel = new File(fileName);  // 读取文件
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook wb = null;
            if(!excel.exists()){
                wb = new HSSFWorkbook();
            }else{
                FileInputStream inFile = new FileInputStream(excel); // 转换为流
                wb = new HSSFWorkbook(inFile);
            }
            // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            HSSFRow row = null;
            for(int i=0; i< clubHsv.length; i++){
                // 第三步，在sheet中添加第i行
                row = sheet.createRow(i);
                // 第四步，创建单元格，并设置值
                row.createCell(0).setCellValue(clubHsv[i][0]);
                row.createCell(1).setCellValue(clubHsv[i][1]);
                row.createCell(2).setCellValue(clubHsv[i][2]);
            }
            // 第六步，将文件存到指定位置

            FileOutputStream out = new FileOutputStream(fileName);
            wb.write(out);
            out.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    // 读取图库缩略图 Hsv
    public float[][] readHsvClub(String fileName, String sheetName){
        try{
            HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(fileName)));
            HSSFSheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getPhysicalNumberOfRows();
            float clubHsv[][] = new float[rows][3];
            for(int i = 0; i < rows; i++){
                HSSFRow row = sheet.getRow(i);
                clubHsv[i][0] = (float)row.getCell(0).getNumericCellValue();
                clubHsv[i][1] = (float)row.getCell(1).getNumericCellValue();
                clubHsv[i][2] = (float)row.getCell(2).getNumericCellValue();
            }
            return clubHsv;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // 读取图库缩略图 Hsv
    public BufferedImage[] readImageClub(File fileName) throws Exception{
        File imagePath[] = fileName.listFiles();
        BufferedImage imageClub[] = new BufferedImage[imagePath.length];
        for(int i = 0; i < imagePath.length; i++){
            imageClub[i] = ImageIO.read(imagePath[i]);
        }
        return imageClub;
    }
}
