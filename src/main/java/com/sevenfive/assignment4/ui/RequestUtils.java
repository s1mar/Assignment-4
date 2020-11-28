package com.sevenfive.assignment4.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestUtils {

    public static Map<String,String> valuesFromRequest(String body){

         String[] splitted = body.split("&",0);
         Map<String,String> asl = new HashMap<>();
         for(String s : splitted){
             String[] split =   s.split("=");
             asl.put(split[0],split[1]);
         }
         return asl;
    }

    public static void main(String[] args){
        valuesFromRequest("uname=simar&psw=safasd");
    }

}
