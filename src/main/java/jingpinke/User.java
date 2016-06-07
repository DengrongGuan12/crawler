package jingpinke;

import util.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by I322233 on 6/1/2016.
 */
public class User {
    public void login(){
        Map<String,String> headers = new HashMap<String, String>();
//        password:XMD5:2cf443823bd23e418e858f1a7d518c9f
//        username:DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f
        String url = "http://www.jingpinke.com/xpe/jingpinke/service/security/json/login";
        String params = "password=XMD5:2cf443823bd23e418e858f1a7d518c9f&username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f";
        headers.put("Content-Type","application/x-www-form-urlencoded");
        headers.put("Accept","*/*");
//        headers.put("Accept-Encoding","gzip, deflate, sdch");
        headers.put("Accept-Language","zh-CN,zh;q=0.8");
        headers.put("Connection","keep-alive");
        headers.put("Content-Length","107");
        headers.put("Cookie","Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464577985,1464590103,1464604777,1464785378; Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464785571");
        headers.put("Host","www.jingpinke.com");
        headers.put("Origin","http://www.jingpinke.com");
        headers.put("Referer","http://www.jingpinke.com/account/login");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        headers.put("X-Requested-With","XMLHttpRequest");

        Map<String,List<String>> responseHeader = HttpRequest.postForHeaders(url,params,headers);
        for (String key:responseHeader.keySet()
             ) {
            System.out.print(key+":");
            List<String> list = responseHeader.get(key);
            for (String value:list
                 ) {
                System.out.print(value+" ");

            }
            System.out.println();

        }
    }
}
