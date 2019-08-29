package com.example.ming.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by wukun
 * on 2019/8/29
 **/
public final class ReadSQLUtil {
    public static void main(String[] args) {
        System.err.println("begin");
        long start = System.currentTimeMillis();
        String path = "C:/Users/成就系列/Desktop/测试文件.sql";
        getData(path);
        System.err.print((System.currentTimeMillis() - start) / 1000);
    }

    public static void getData(String path) {
        //读取文件
        BufferedReader reader;
        Connection conn = null;
        Statement pst = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection(
             //       "jdbc:mysql://47.94.241.154:3306/nikekun?useUnicode=yes&characterEncoding=UTF8", "root", "1234");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@36.32.163.29:1521:fengkdb", "wf_bank", "wf_bank");

            pst = conn.createStatement();
            conn.setAutoCommit(false);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                //如果连接的是ORACLE数据库,要将分号去掉
                line=line.replaceAll(";","");
                System.out.println(line);
                pst.addBatch(line);
                if (i % 100 == 0) {
                    System.out.println("执行了：" + i);
                    pst.executeBatch();
                }
                i += 1;
            }
            reader.close();
            // 执行批量更新
            pst.executeBatch();
            pst.clearBatch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*public void runSqlByScriptRunner(String sqlPath) throws Exception {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection conn = sqlSession.getConnection();
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setEscapeProcessing(false);
            runner.setSendFullScript(true);
            runner.runScript(new InputStreamReader(new FileInputStream(sqlPath), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }*/
}
