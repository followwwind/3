package com.wind.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.wind.dao.ReportDao;
import com.wind.dao.base.BaseDaoImpl;
import com.wind.entity.Report;
import com.wind.entity.base.Page;

/**
 * 报告 dao接口实现
 * @author wind
 */
public class ReportDaoImpl extends BaseDaoImpl<Report, Integer> implements ReportDao{

    @Override
    public int insert(Report r) {
        String sql = "insert into report values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        if(r == null){
            return -1;
        }
        return executeUpdate(sql, ps -> {
            ps.setInt(1, r.getId() != null ? r.getId() : 0);
            ps.setString(2, r.getUnitNumber());
            ps.setString(3, r.getIssueNumber());
            ps.setString(4, r.getTotalIssueNumber());
            ps.setString(5, r.getDeliverUnit());
            ps.setString(6, r.getReportTitle());
            ps.setInt(8, r.getWhetherDeliverSuperior() != null ? r.getWhetherDeliverSuperior() : 0);
            ps.setInt(9, r.getWhetherSameEvent() != null ? r.getWhetherSameEvent() : 0);
            ps.setString(10, r.getRemark());
            ps.setString(11, r.getWorkGroup());
            ps.setString(12, r.getUsername());
        });
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "delete from report where id = ?";
        return executeUpdate(sql, ps -> ps.setInt(1, id));
    }

    @Override
    public int deleteByCondition(Report r){
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        int i = -1;
        if(!"".equals(joinSql)){
            String sql = "delete from report where 1=1" + joinSql;
            return executeUpdate(sql, ps -> {});
        }
        return i;
    }



    @Override
    public Report findById(Integer id) {
        String sql = "select * from report where id = ?";
        List<Report> entitys = executeQuery(sql, ps -> ps.setInt(1, id));
        if(entitys.size() == 1){
            return entitys.get(0);
        }
        return null;
    }

    @Override
    public List<Report> findByCondition(Report r) {
        String sql = "select * from report where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        return executeQuery(sql, ps -> {});
    }

    @Override
    public void findPageList(Report r, Page page){
        String sql = "select count(1) from report where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        int totalCount =  countQuery(sql, ps -> {});
        sql += " limit " + page.getStartRow() + ", " + page.getLineNumber();
        List<Report> entitys =  executeQuery(sql.replace("count(1)", "*"), ps -> {});
        page.setResult(entitys);
    }

    @Override
    public int updateByCondition(Report r) {
        String sql = "update report ";
        if(r == null){
            return -1;
        }
        String setSql = "set " + joinSql(r, "", ", ");
        Integer id = r.getId();
        if(id != null && setSql.contains(", ")){
            sql += setSql.substring(0, setSql.length() - 2);
            sql += " where id = ?";
            return executeUpdate(sql, ps -> ps.setInt(1, id));
        }
        return -1;
    }

    @Override
    public int countByCondition(Report r){
        String sql = "select count(1) from report where 1 = 1 ";
        String joinSql = "";
        if(r != null){
            joinSql += joinSql(r, " and ", "");
        }
        sql += joinSql;
        return countQuery(sql, ps -> {});
    }

    @Override
    public Report parseTable(ResultSet rs) throws SQLException {
        Report entity = null;
        if(rs != null) {
            entity = new Report();
            entity.setId(rs.getInt("id"));
            entity.setUnitNumber(rs.getString("unit_number"));
            entity.setIssueNumber(rs.getString("Issue_number"));
            entity.setTotalIssueNumber(rs.getString("total_Issue_number"));
            entity.setDeliverUnit(rs.getString("deliver_unit"));
            entity.setReportTitle(rs.getString("report_title"));
            entity.setDeliverDate(rs.getTimestamp("deliver_date"));
            entity.setWhetherDeliverSuperior(rs.getInt("whether_deliver_superior"));
            entity.setWhetherSameEvent(rs.getInt("whether_same_event"));
            entity.setRemark(rs.getString("remark"));
            entity.setWorkGroup(rs.getString("work_group"));
            entity.setUsername(rs.getString("username"));
        }
        return entity;
    }

    @Override
    public String joinSql(Report r, String prefix, String suffix){
        String joinSql = "";
        Integer id = r.getId();
        if(id != null){
            joinSql += prefix + "id = " + id + suffix;
        }
        String unitNumber = r.getUnitNumber();
        if(unitNumber != null){
            joinSql += prefix + "unit_number = " + "'" + unitNumber + "'" + suffix;
        }
        String issueNumber = r.getIssueNumber();
        if(issueNumber != null){
            joinSql += prefix + "Issue_number = " + "'" + issueNumber + "'" + suffix;
        }
        String totalIssueNumber = r.getTotalIssueNumber();
        if(totalIssueNumber != null){
            joinSql += prefix + "total_Issue_number = " + "'" + totalIssueNumber + "'" + suffix;
        }
        String deliverUnit = r.getDeliverUnit();
        if(deliverUnit != null){
            joinSql += prefix + "deliver_unit = " + "'" + deliverUnit + "'" + suffix;
        }
        String reportTitle = r.getReportTitle();
        if(reportTitle != null){
            joinSql += prefix + "report_title = " + "'" + reportTitle + "'" + suffix;
        }
        java.util.Date deliverDate = r.getDeliverDate();
        if(deliverDate != null){
        }
        Integer whetherDeliverSuperior = r.getWhetherDeliverSuperior();
        if(whetherDeliverSuperior != null){
        }
        Integer whetherSameEvent = r.getWhetherSameEvent();
        if(whetherSameEvent != null){
        }
        String remark = r.getRemark();
        if(remark != null){
            joinSql += prefix + "remark = " + "'" + remark + "'" + suffix;
        }
        String workGroup = r.getWorkGroup();
        if(workGroup != null){
            joinSql += prefix + "work_group = " + "'" + workGroup + "'" + suffix;
        }
        String username = r.getUsername();
        if(username != null){
            joinSql += prefix + "username = " + "'" + username + "'" + suffix;
        }
        return joinSql;
    }
}

