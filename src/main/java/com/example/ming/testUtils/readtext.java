package com.example.ming.testUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.ming.dto.applyNoDto;
import com.example.ming.utils.HttpUtil;
import com.example.ming.utils.RandomUtils;
import com.example.ming.utils.ReadSQLUtil;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by wukun
 * on 2019/8/28
 **/
public class readtext {

    public static void main(String args[]) throws IOException, SQLException, ClassNotFoundException {
        //手机号码路径
        String pathname = "C:/Users/成就系列/Desktop/手机号码.txt";
        //产生的文件路径
        String pathsql="C:/Users/成就系列/Desktop/测试文件.sql";
        System.out.println("开始生成SQL和POST文件");
        readFile(pathname,pathsql);
        System.out.println("生成SQL和POST文件结束");
        System.out.println("开始运行sql文件");
        ReadSQLUtil.getData(pathsql);
        System.out.println("SQL生成结束");
        System.out.println("开始发送数据进行风控");
        post (pathname);
        System.out.println("数据发送完成");

    }

    /**
     * 读入TXT文件
     */
    public static void readFile(String pathname,String pathsql) throws IOException, ClassNotFoundException, SQLException {
        File writeName = new File(pathsql); // 相对路径，如果没有则要建立一个新的output.txt文件
        writeName.createNewFile();
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader);
        FileWriter writer = new FileWriter(writeName);
        BufferedWriter out = new BufferedWriter(writer);
        // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null) {
            // 一次读入一行数据
            String[] a = line.split("-");
            applyNoDto applyNoDto = new applyNoDto();
            applyNoDto.setName(a[0]);
            applyNoDto.setIdcrad(a[1]);
            applyNoDto.setPhone(a[2]);
            String m1, m2, m3, m4, m5;
            m1 = RandomUtils.excutorMath0();
            m2 = RandomUtils.excutorMath0();
            m3 = RandomUtils.excutorMath0();
            m4 = RandomUtils.excutorMath0();
            m5 = RandomUtils.excutorMath0();
            JSONObject objectNH = JSON.parseObject("{\"ice_blacklist\":" + m1 + ",\"idcard_discredit_court\":" + m2 + ",\"idcard_crime\":" + m3 + ",\"idcard_execute_court\":" + m4 + ",\"idcard_overdue\":" + m5 + "}");
            String insertsqlNH = "insert into data_ysd_test (DATASOURCE_NAME, USER_DATA, ID_CARD) values ('NH'," + "'" + objectNH.toString() + "'" + "," + "'" + applyNoDto.getIdcrad() + "'" + ");\r\n";
            out.write(insertsqlNH);
            if (m1.equals("0") && m2.equals("0") && m3.equals("0") && m4.equals("0") && m5.equals("0")) {
                if (RandomUtils.excutorMathA()) {
                    JSONObject objectNB = JSON.parseObject("{\"nz_d7_platform_loan3\":" + RandomUtils.excutorMath() + ",\"nz_m1_platform_loan3\":" + RandomUtils.excutorMath() + ",\"nz_m3_platform_loan3\":" + RandomUtils.excutorMath() + ",\"nz_m3_platform_lending\":" + RandomUtils.excutorMath() + ",\"nz_last_15d_p2ps_num\":" + RandomUtils.excutorMath() + ",\"nz_last_1m_p2ps_num\":" + RandomUtils.excutorMath() + ",\"nz_last_3m_p2ps_num\":" + RandomUtils.excutorMath() + ",\"nz_last_6m_p2ps_num\":" + RandomUtils.excutorMath() + ",\"nz_last_9m_p2ps_num\":" + RandomUtils.excutorMath() + ",}");
                    String insertsqlNB = "insert into data_ysd_test (DATASOURCE_NAME, USER_DATA, ID_CARD) values ('NB'," + "'" + objectNB.toString() + "'" + "," + "'" + applyNoDto.getIdcrad() + "'" + ");\r\n";
                    out.write(insertsqlNB);
                }
                if(RandomUtils.excutorMathA()) {
                    JSONObject objectNG = JSON.parseObject("{\"m3_phone_idcard\":" + RandomUtils.excutorMath() + ",\"idcard_high_risk_attention\":" + RandomUtils.excutorMath() + ",\"idcard_tax\":" + RandomUtils.excutorMath() + ",\"idcard_tax_entity\":" + RandomUtils.excutorMath() + ",\"idcard_case_court\":" + RandomUtils.excutorMath() + ",\"phone_high_risk_attention\":1,\"phone_overdue\":" + RandomUtils.excutorMath() + ",\"phone_car_rent\":" + RandomUtils.excutorMath() + ",\"idcard_debit_entity\":" + RandomUtils.excutorMath() + ",\"phone_debit_entity\":" + RandomUtils.excutorMath() + "}");
                    String insertsqlNG = "insert into data_ysd_test (DATASOURCE_NAME, USER_DATA, ID_CARD) values ('NG'," + "'" + objectNG.toString() + "'" + "," + "'" + applyNoDto.getIdcrad() + "'" + ");\r\n";
                    out.write(insertsqlNG);
                }
            }
            out.flush();
        }
    }

    /**
     * 发送申请,并将申请信息写入到文件
     * @throws IOException
     */
    public static void post(String pathname) throws IOException {
        File writeName = new File("C:/Users/成就系列/Desktop/post文件.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
        writeName.createNewFile();
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) ;
             FileWriter writer = new FileWriter(writeName);
             BufferedWriter out = new BufferedWriter(writer)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String [] a=line.split("-");
                applyNoDto applyNoDto=new applyNoDto();
                applyNoDto.setName(a[0].trim());
                applyNoDto.setIdcrad(a[1]);
                applyNoDto.setPhone(a[2]);
                String b=UUID.randomUUID().toString().substring(0,2);
                SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
                sdf.applyPattern("yyyyMMddHHmmss");// a为am/pm的标记
                Date date = new Date();// 获取当前时间
                String datetime="test"+sdf.format(date)+b;
                JSONObject object= JSON.parseObject("{\"ice_blacklist\":0,\"idcard_discredit_court\":0,\"idcard_crime\":0,\"idcard_execute_court\":1,\"idcard_overdue\":0}");
                String postman="{\"param\": {\"idCard\":"+"\""+applyNoDto.getIdcrad()+"\""+",\"applyNo\":"+"\""+datetime+"\","+"\"channel\": \"21\",\"mobile\":"+"\""+applyNoDto.getPhone()+"\""+",\"userName\":"+"\""+applyNoDto.getName()+"\""+"},\"bizCode\": \"pratt_bank_flow\",\"showdetail\": \"true\"}\r\n";
                out.write(postman);
                //String response=HttpUtil.request("http://localhost:18080/api/apply",HttpUtil.METHOD_POST, JSON.parse(postman), HttpUtil.PARAM_TYPE_JSON);
                String response=HttpUtil.request("http://36.32.163.28:18080/api/apply",HttpUtil.METHOD_POST, JSON.parse(postman), HttpUtil.PARAM_TYPE_JSON);
                System.out.println(response);
                out.flush();
            }
            out.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



