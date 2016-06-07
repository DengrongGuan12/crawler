package jingpinke;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by I322233 on 6/1/2016.
 */
public class Resources {
    public static String url = "http://resource.jingpinke.com/advanced_search/result";
    public void requestResources(){
        HttpRequest httpRequest = new HttpRequest();
        Map<String,String> headers = new HashMap<String, String>();
        headers.put("Content-Type","text/html; charset=utf-8");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        headers.put("Accept-Language","zh-CN,zh;q=0.8");
        headers.put("Cache-Control","max-age=0");
        headers.put("Connection","keep-alive");
        headers.put("Cookie","bdshare_firstime=1464425564120; Hm_lvt_30c43732c76b9f372baa51b918b0fc4e=1464425505,1464577985; Hm_lpvt_30c43732c76b9f372baa51b918b0fc4e=1464577985");
        headers.put("Host","resource.jingpinke.com");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
        String params = "keywords=%E5%8D%97%E4%BA%AC%E5%A4%A7%E5%AD%A6&queryTemplate=contentType%3A%22jingpinke%2Fresource%22+AND+status%3A%22published%22+AND+title_Search%3D%22%E5%8D%97%E4%BA%AC%E5%A4%A7%E5%AD%A6%22&submit=%E6%90%9C%20%E7%B4%A2";
        String aca = httpRequest.sendGet(url,
                params,
                headers);
        Document doc = Jsoup.parse(aca,"utf-8");

        Elements elements = doc.select("a[title~=^\"]:has(img)");
        for (Element node:elements) {
            System.out.println(node.attr("href"));
        }
    }
}
