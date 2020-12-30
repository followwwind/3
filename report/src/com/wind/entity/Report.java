package com.wind.entity;

import java.util.Date;

/**
 * 报告
 * @author wind
 */
public class Report{

    private Integer id;

    /**
     * 显示编号
     */
    private String unitNumber;

    /**
     * 期号
     */
    private String issueNumber;

    /**
     * 总期号
     */
    private String totalIssueNumber;

    /**
     * 报送单位
     */
    private String deliverUnit;

    /**
     * 报告标题
     */
    private String reportTitle;

    /**
     * 日期
     */
    private Date deliverDate;

    /**
     * 是否上报
     */
    private Integer whetherDeliverSuperior;

    /**
     * 是否为相同事件
     */
    private Integer whetherSameEvent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报告来源工作组
     */
    private String workGroup;

    /**
     * 当前登录用户名
     */
    private String username;


    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }
    
    public void setUnitNumber(String unitNumber){
        this.unitNumber = unitNumber;
    }

    public String getUnitNumber(){
        return this.unitNumber;
    }
    
    public void setIssueNumber(String issueNumber){
        this.issueNumber = issueNumber;
    }

    public String getIssueNumber(){
        return this.issueNumber;
    }
    
    public void setTotalIssueNumber(String totalIssueNumber){
        this.totalIssueNumber = totalIssueNumber;
    }

    public String getTotalIssueNumber(){
        return this.totalIssueNumber;
    }
    
    public void setDeliverUnit(String deliverUnit){
        this.deliverUnit = deliverUnit;
    }

    public String getDeliverUnit(){
        return this.deliverUnit;
    }
    
    public void setReportTitle(String reportTitle){
        this.reportTitle = reportTitle;
    }

    public String getReportTitle(){
        return this.reportTitle;
    }
    
    public void setDeliverDate(Date deliverDate){
        this.deliverDate = deliverDate;
    }

    public Date getDeliverDate(){
        return this.deliverDate;
    }
    
    public void setWhetherDeliverSuperior(Integer whetherDeliverSuperior){
        this.whetherDeliverSuperior = whetherDeliverSuperior;
    }

    public Integer getWhetherDeliverSuperior(){
        return this.whetherDeliverSuperior;
    }
    
    public void setWhetherSameEvent(Integer whetherSameEvent){
        this.whetherSameEvent = whetherSameEvent;
    }

    public Integer getWhetherSameEvent(){
        return this.whetherSameEvent;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return this.remark;
    }
    
    public void setWorkGroup(String workGroup){
        this.workGroup = workGroup;
    }

    public String getWorkGroup(){
        return this.workGroup;
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }
    

}

