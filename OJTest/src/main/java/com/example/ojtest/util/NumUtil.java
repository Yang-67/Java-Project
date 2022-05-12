package com.example.ojtest.util;

import com.example.ojtest.entity.titleInfo;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Random;
@Configuration
public class NumUtil {

    public titleInfo TitleInfo(){
        Random random=new Random();
        String[] Title= {"+","-","*","/"};
        int index=random.nextInt(Title.length-1);
        String title ="简单的四则运算";
        String fh = Title[index];
        int num1 = ((int)(Math.random()*(999-100+1))+100);
        int num2 = ((int)(Math.random()*(999-500+1))+500);
        String ss = num2+fh+num1;
        String result = String.valueOf(eval(ss));
//        System.out.println(ss+"="+result);
        return new titleInfo(title,ss,result);
    }

    public static int eval(String str) {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine se = manager.getEngineByName("js");
        int result = 0;

        try {
            result = (int) se.eval(str);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

}
