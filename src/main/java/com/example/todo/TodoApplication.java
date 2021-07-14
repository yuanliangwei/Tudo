package com.example.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.todo.controller.TudoController;

import java.io.File;


/*
 * @版本：tudo_1.0
 * @作者: yuan liangwei
 * @日期: 2021-7-6
 * @功能：
    实现一个待办事项管理软件，是以命令行应用的方式存在
    ############第一阶段需求 基本功能##############
    1. 添加Todo项
    2. 完成Todo项
    3. 查看Todo列表，缺省情况下，只列出未完成的Todo项
    4. 使用all参数，查看所有的Todo项目
    #############################################
*/
@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) throws Exception {
        new TudoController().Login();
    }

}
