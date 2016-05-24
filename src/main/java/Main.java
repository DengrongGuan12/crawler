import util.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by I322233 on 5/10/2016.
 */
public class Main {
    public static void main(String[] args){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Cookie","JSESSIONID=B3E8F934973A72860FBFB9CD1CBA4772-memcached1");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","174");
        String params = "platform=1&beginYear=2015&phoneVersion=23&phoneBrand=Android&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId=8006&channel=advertising&term=2&";
        String aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseTagsV2.action",
                params,
                headers);
        //8006 南航
        //8007 南财
        //80014 南中医
        //8005 东南
        //8003 南师
        //南京林业科技大学 没有
        //8004 南理工
        //80016 金陵科技学院
        //80057 江苏经贸职业技术学院
        //8009 南京工业大学
        //80018 南京审计大学
        //8005 东南大学


        System.out.println(aca);

        params = "platform=1&tagId=7970017&beginYear=2015&phoneVersion=23&phoneBrand=Android&index=&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&schoolId=8006&type=0&page=0&channel=advertising&term=1&";
        headers.put("Content-Length","209");
        aca = httpRequest.sendPost("http://120.55.151.61/V2/Course/getCourseNameListByTagV2.action",params,headers);
        System.out.println(aca);
    }
}
