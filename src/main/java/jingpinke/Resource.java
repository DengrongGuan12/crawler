package jingpinke;

import org.json.JSONObject;
import util.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by I322233 on 6/1/2016.
 */
public class Resource {
    private String url;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String uuid;

    public String getDownloadUrl(){
        String url = "http://resource.jingpinke.com/xpe/portal/serviceJson/8eb3d955-1223-1000-899d-d1978b4f37ef";
        String params = "flag=tip&uuid=38c3faec-144e-1000-aea3-91d231a9332a";
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Content-Type","text/html; charset=utf-8");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Language","zh-CN,zh;q=0.8");
        headers.put("Cache-Control","max-age=0");
        headers.put("Connection","keep-alive");
//        headers.put("Cookie","user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\";Path=/;Domain=.jingpinke.com xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==;Path=/;Domain=.jingpinke.com username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f;Path=/;Domain=.jingpinke.com xpeSessionId=7e60b97859c35ce6;Path=/");
//        headers.put("Cookie","bdshare_firstime=1464425564120; " +
//                "username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f; " +
//                "xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==; " +
//                "user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\"; " +
//                "Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464590103,1464604777,1464785378,1464856442; " +
//                "Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464856588");
        headers.put("Cookie","bdshare_firstime=1464425564120; " +
                "username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f; " +
                "xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==; " +
                "user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\"; " +
                "Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464590103,1464604777,1464785378,1464856442; " +
                "Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464856903");
        headers.put("Host","resource.jingpinke.com");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        String aca = HttpRequest.sendGet(url,
                params,
                headers);
//        System.out.println(aca);
        JSONObject result = new JSONObject(aca);
        System.out.println(result.optString("body"));
        return result.optString("body");
    }

    public void downloadFile(){
        String url = "http://files.jingpinke.com/down/resource/6c/8b/38c86c48-144e-1000-a6f5-59b78b968456?st=XeN0J66efasWDxe838pOqQ&e=1464858608&n=2008%E5%B9%B4%E5%8D%97%E4%BA%AC%E5%A4%A7%E5%AD%A6%E7%9F%BF%E7%89%A9%E5%AD%A6%E8%80%83%E7%A0%94%E8%AF%95%E9%A2%98.pdf";
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Content-Type","text/html; charset=utf-8");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Language","zh-CN,zh;q=0.8");
        headers.put("Cache-Control","max-age=0");
        headers.put("Connection","keep-alive");
//        headers.put("Cookie","user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\";Path=/;Domain=.jingpinke.com xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==;Path=/;Domain=.jingpinke.com username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f;Path=/;Domain=.jingpinke.com xpeSessionId=7e60b97859c35ce6;Path=/");
//        headers.put("Cookie","bdshare_firstime=1464425564120; " +
//                "username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f; " +
//                "xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==; " +
//                "user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\"; " +
//                "Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464590103,1464604777,1464785378,1464856442; " +
//                "Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464856588");
        headers.put("Cookie","bdshare_firstime=1464425564120; " +
                "username=DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f; " +
                "xpeSessionId=auth:YzNlZjIwY2MyNDBjMDhjYTczMmU5ZjU0NjliN2Q1MmU6RGVuZ3JvbmdHdWFuXzc2MjA4OWI2LTExZGQtMTAwMC05M2QyLTRkYzIzYTNlMWMzZg==; " +
                "user=\"DengrongGuan_762089b6-11dd-1000-93d2-4dc23a3e1c3f|%E7%AE%A1%E7%99%BB%E8%8D%A3|normal\"; " +
                "Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464590103,1464604777,1464785378,1464856442; " +
                "Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464856903");
        headers.put("Host","files.jingpinke.com");
        headers.put("Referer","http://resource.jingpinke.com/download?uuid=38c86c48-144e-1000-a6f5-59b78b968456&objectId=oid:38c86c51-144e-1000-a6f6-59b78b968456");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        HttpRequest.downloadFile(url,headers);
    }
}
