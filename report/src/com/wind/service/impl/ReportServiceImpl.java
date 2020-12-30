package com.wind.service.impl;

import java.util.List;
import com.wind.dao.ReportDao;
import com.wind.dao.impl.ReportDaoImpl;
import com.wind.entity.Report;
import com.wind.service.ReportService;
import com.wind.service.base.BaseServiceImpl;
import com.wind.entity.base.Page;

/**
 * 报告 service接口实现
 * @author wind
 */
public class ReportServiceImpl extends BaseServiceImpl<Report, Integer> implements ReportService{

    private ReportDao dao = new ReportDaoImpl();


    @Override
    public int insert(Report r) {
        int i = dao.insert(r);
        return i;
    }

    @Override
    public int delete(Report r) {
        return dao.deleteByCondition(r);
    }

    @Override
    public Report findEntity(Report r) {
        List<Report> entitys = dao.findByCondition(r);
        return entitys.size() == 1 ? entitys.get(0) : null;
    }

    @Override
    public List<Report> findList(Report r) {
        return dao.findByCondition(r);
    }

    @Override
    public void findPageList(Report r, Page page){
        dao.findPageList(r, page);
    }

    @Override
    public int update(Report r) {
        return dao.updateByCondition(r);
    }

    @Override
    public int count(Report r){
        return dao.countByCondition(r);
    }
}

