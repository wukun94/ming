package com.example.ming.utils;

import java.util.Random;

/**
 * Created by wukun
 * on 2019/8/28
 **/
public class RandomUtils {
    public static String excutorMath0(){
        int x=new Random().nextInt(200);
       // System.out.println(x+"*****");
        String a=null;
        if(x<=190){
            a=0+"";
        }else {
            a=1+"";
        }
        return a;
    }
    public static String excutorMath(){
        int x=new Random().nextInt(10);
        // System.out.println(x+"*****");
        String a=null;
        if(x>=0&&x<=5){
            a=0+"";
        }else {
            a=x+"";
        }
        return a;
    }
    public static Boolean  excutorMathA(){
        Boolean flag=true;
        int x=new Random().nextInt(10);

        String a=null;
        if(x>=0&&x<=5){
            flag=false;
        }
        return flag;
    }

    public static void main(String[] args) {
        for (int i = 0; i <200 ; i++) {
            String a=excutorMath0();
            System.out.println(a);
        }
    }
}

