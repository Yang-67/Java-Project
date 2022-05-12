package com.example.ojtest.util;

import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class PhoneUtil {
    public static String getRandomPhone(){
        Random random=new Random();
        String[] SuPhone= {"133","149","153","173","177","180","181","189","199","130","131","132","145","155","156","166","171","175","176","185","186","166"};
        int index=random.nextInt(SuPhone.length-1);
        String phone ="";
        phone = SuPhone[index];
        return phone + ((int)(Math.random()*(9999-1000+1))+1000) +((int)(Math.random()*(9999-1000+1))+1000) ;
    }
}
