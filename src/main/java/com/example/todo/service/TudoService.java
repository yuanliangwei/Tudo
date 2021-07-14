package com.example.todo.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.todo.controller.TudoController;
import com.example.todo.daos.TodoModel;
import com.example.todo.controller.TudoController.*;

import net.sf.json.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.*;

@Service
public class TudoService {
    File excel = new File("D:\\Tudo.xlsx");
    public Scanner name = new Scanner(System.in);

    public void addtudo(Sheet sheet,Workbook workbook) throws IOException {

        //捕获输入数据
        System.out.println("请输入新增代办项目名称:");
        String tudoname = name.nextLine();
        System.out.println("请输入新增代办项目内容:");
        String tudocontent = name.nextLine();

        //遍历表格
        for (int i = 1; i < sheet.getLastRowNum()+3; i++) {
            Row row = sheet.getRow(i);
            if(row == null){
                for (int j = 0; ; ) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(j);
                    cell.setCellValue("未完成");
                    cell = row.createCell(++j);
                    cell.setCellValue(tudoname);
                    cell = row.createCell(++j);
                    cell.setCellValue(tudocontent);
                    //创建并写入数据流
                    FileOutputStream outputStream = new FileOutputStream(excel);
                    workbook.write(outputStream);
                    outputStream.close();
                    System.out.println("添加代办事项成功！");
                    return;
                }
            }
        }
    }
    //更新代办事项完成状态
    public void updatetudo(Sheet sheet,Workbook workbook) throws IOException {
        System.out.print("请输入已完成的项目名称:");
        String tudoname = name.nextLine();
        //更新完成状态
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            for (int j = 1 ; ;) {
                Cell cell = row.getCell(j);
                if (cell.getStringCellValue().equals(tudoname)){
                    Cell cell1 = row.getCell(--j);
                    cell1.setCellValue("已完成");
                    //通过IO流更新状态
                    FileOutputStream outputStream  = new FileOutputStream(excel);
                    workbook.write(outputStream);
                    outputStream.close();
                    System.out.println("更新状态成功!");
                    return;
                }
                break;
            }
        }
    }
    //查询未完成代办事项
    public void querytudo (Sheet sheet,Workbook workbook) throws IOException {
        System.out.println("  未完成代办事项名称 "+"未完成代办事项内容 ");
        //查询数据
        int m = 1;
        for(int i = 1;i < sheet.getLastRowNum()+1;i++){
            Row row = sheet.getRow(i);
            for (int j = 0;;) {
                Cell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                if(value.equals("未完成")){
                    System.out.print(m);
                    cell = row.getCell(++j);
                    value = cell.getStringCellValue();
                    System.out.print("       "+value+"     ");
                    cell = row.getCell(++j);
                    value = cell.getStringCellValue();
                    System.out.println("       "+value+"     ");
                    m++;
                }
                break;
            }
        }
        System.out.println("查询未完成待办事项完毕！");
    }
    //查询所有代办事项
    public void queryalltudo (Sheet sheet,Workbook workbook) throws IOException {
        System.out.println("  代办事项状态 "+"代办事项名称 "+"代办事项内容 ");
        int m = 1;
        //查询数据
        for(int i = 1;i < sheet.getLastRowNum()+1;i++){
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                String value = cell.getStringCellValue();
                if(value != null){
                    System.out.print(m);
                    System.out.print("  "+value+"  ");
                    cell = row.getCell(++j);
                    value = cell.getStringCellValue();
                    System.out.print("     "+value+"  ");
                    cell = row.getCell(++j);
                    value = cell.getStringCellValue();
                    System.out.println("     "+value+"  ");
                    m++;
                }
            }
        }
        System.out.println("查询所有代办事项完毕!");
    }
}
