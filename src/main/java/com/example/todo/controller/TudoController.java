package com.example.todo.controller;


import com.example.todo.service.TudoService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.io.File;
import java.util.Scanner;


@RestController
public class TudoController {
    File excel = new File("D:\\Tudo.xlsx");
    TudoService tudoService = new TudoService();
    public void Login() throws Exception {
        //创建工作簿
        HSSFWorkbook workbook = null;
        //创建工作表
        HSSFSheet sheet = null;
        if(!excel.exists()){
            //创建excel文件
            excel.createNewFile();
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("待办事项管理系统");

            //创建第一行
            HSSFRow row1 = sheet.createRow(0);
            HSSFCell cell = null;
            String[] title = {"代办事项状态", "代办事项名称", "代办事项内容"};
            //创建表头
            for (int i = 0; i < title.length; i++) {
                cell = row1.createCell(i);
                cell.setCellValue(title[i]);
            }
            FileOutputStream outputStream  = new FileOutputStream(excel);
            workbook.write(outputStream);
            outputStream.close();
        }
        else {
            workbook = new HSSFWorkbook(new FileInputStream(excel));
            sheet = workbook.getSheet("待办事项管理系统");
        }
        System.out.println("#####代办事项管理系统#####");
        System.out.println("1.添加代办事项");
        System.out.println("2.选择已完成的代办事项");
        System.out.println("3.查询未完成的代办事项");
        System.out.println("4.查询所有代办事项");
        System.out.println("5.退出系统");
        System.out.println("#########################");
        boolean flag = true;
        while(flag) {
            System.out.print("输入数字选项选择操作项：（1——5）:");
            Scanner sc = new Scanner(System.in);
            int k = 0;
            try {
                k = sc.nextInt();
            }catch (Exception e) {
                System.out.println("请输入正确的选项！");
                continue;
            }
            switch (k) {
                case 1:
                    tudoService.addtudo(sheet,workbook);
                    break;
                case 2:
                    tudoService.updatetudo(sheet,workbook);
                    break;
                case 3:
                    tudoService.querytudo(sheet,workbook);
                    break;
                case 4:
                    tudoService.queryalltudo(sheet,workbook);
                    break;
                case 5:
                    System.out.println("系统已退出！");
                    flag = false;
                    break;
                default:
                    System.out.println("请输入正确的选项！");
                    break;
            }
        }
    }
}
