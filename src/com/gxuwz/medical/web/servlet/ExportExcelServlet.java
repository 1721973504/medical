package com.gxuwz.medical.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.gxuwz.medical.database.DbUtil;

/**
 * 普通导出数据
 * 
 * @author admin
 * 
 */
public class ExportExcelServlet extends HttpServlet {

    public ExportExcelServlet() {
        super();
    }

    public void init() throws ServletException {
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        DbUtil dbUtil = new DbUtil();
        Connection conn = null;
        try {
            conn = dbUtil.getConn();// 获得数据库连接
            Workbook wb = new HSSFWorkbook();
            String headers[] = { "参合证号",	"行政区域","参合发票号","缴费人","缴费金额","缴费年度","缴费时间"};// 标题
            ResultSet rs = userList(conn);// 得到结果集
            fillExcelData(rs, wb, headers);
            export(response, wb, "缴费凭证数据.xls");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询数据库
     * 
     * @param con
     * @return ResultSet 返回结果集
     * @throws Exception
     */
    public ResultSet userList(Connection con) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_pay");
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * 导出
     * 
     * @throws Exception
     */
    public void fillExcelData(ResultSet rs, Workbook wb, String[] headers)
            throws Exception {
        int rowIndex = 0; // 第一行
        Sheet sheet = wb.createSheet(); // 创建sheet页
        Row row = sheet.createRow(rowIndex++);
        // 创建标题
        for (int i = 0; i < headers.length; i++) {
            row.createCell(i).setCellValue(headers[i]);
        }
        // 导出数据库中的数据
        while (rs.next()) {
            row = sheet.createRow(rowIndex++);
            for (int i = 0; i < headers.length; i++) {
                row.createCell(i).setCellValue(rs.getObject(i + 1).toString());
                //rs.getObject(i + 1)得到一个对象，即数据库中一行的结果，每一列就是属性凑成一行变成对象。因为id是从1开始，所以要+1。
            }
        }
    }

    /**
     * 把数据放入到.xls文件中并下载到本地
     * 
     * @param response
     * @param wb
     * @param fileName
     * @throws Exception
     */
    public void export(HttpServletResponse response, Workbook wb,
            String fileName) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes("utf-8"), "iso8859-1"));// 设置头信息
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);// 进行输出，下载到本地
        out.flush();
        out.close();
    }
}