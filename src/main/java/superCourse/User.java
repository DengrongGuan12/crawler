package superCourse;

import util.HttpRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by I322233 on 5/11/2016.
 */
public class User {
    public static String cookie = "FA1913266509AE7938AA625F6F85DF7F-memcached1";
    public void login(){
        System.out.println("Login...");
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
//        headers.put("Cookie","JSESSIONID=B3E8F934973A72860FBFB9CD1CBA4772-memcached1");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","248");
        String params = "platform=1&password="+Secret.passwd+"&phoneVersion=23&phoneBrand=Android&account="+Secret.account+"&versionNumber=7.5.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&deviceCode=000000000000000&channel=OfficialMarket&";
        Map<String, List<String>> map = httpRequest.postForHeaders("http://120.55.151.61/V2/StudentSkip/loginCheckV4.action",
                params,
                headers);
        Outter:
        for (String key : map.keySet()) {
            List<String> values = map.get(key);
            for(String value:values){
                String[] vList = value.split("=");
                if(vList[0].equals("JSESSIONID")){
                    cookie = vList[1];
                    break Outter;
                }
            }
        }
    }
    public static void main(String[] args){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
//        headers.put("Cookie","JSESSIONID=B3E8F934973A72860FBFB9CD1CBA4772-memcached1");
        headers.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent","Dalvik/2.1.0 (Linux; U; Android 6.0; Custom Phone - 6.0.0 - API 23 - 768x1280 Build/MRA58K)");
        headers.put("Host","120.55.151.61");
        headers.put("Connection","Keep-Alive");
        headers.put("Accept-Encoding","gzip");
        headers.put("Content-Length","248");
        String params = "platform=1&password=3ABFB37CEAEC834B38E048ABC85D701C&phoneVersion=23&phoneBrand=Android&account=9861D883CF5E443B0FB0B82D7E8E2056&versionNumber=7.3.0&phoneModel=Custom+Phone+-+6.0.0+-+API+23+-+768x1280&deviceCode=000000000000000&channel=advertising&";
        Map<String, List<String>> map = httpRequest.postForHeaders("http://120.55.151.61/V2/StudentSkip/backstageLoginV5.action",
                params,
                headers);
        Outter:
        for (String key : map.keySet()) {
            List<String> values = map.get(key);
            for(String value:values){
                String[] vList = value.split("=");
                if(vList[0].equals("JSESSIONID")){
                    cookie = vList[1];
                    break Outter;
                }
            }
        }
    }

}
