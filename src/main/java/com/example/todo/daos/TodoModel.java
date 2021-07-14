package com.example.todo.daos;


import cn.afterturn.easypoi.excel.annotation.Excel;



public class TodoModel {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTudostate() {
        return tudostate;
    }

    public void setTudostate(String tudostate) {
        this.tudostate = tudostate;
    }

    public String getTudoname() {
        return tudoname;
    }

    public void setTudoname(String tudoname) {
        this.tudoname = tudoname;
    }

    public String getTudocontent() {
        return tudocontent;
    }

    public void setTudocontent(String tudocontent) {
        this.tudocontent = tudocontent;
    }

    private Long id;

    @Excel(name = "代办事项状态")

    private String tudostate;

    @Excel(name = "代办事项名称")

    private String tudoname;

    @Excel(name = "代办事项内容")

    private String tudocontent;
}
