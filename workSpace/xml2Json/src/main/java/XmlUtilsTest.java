import com.alibaba.fastjson.JSONArray;


import com.alibaba.fastjson.JSONObject;
import com.test.XmlUtils;

import java.util.Iterator;


public class XmlUtilsTest {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n" +
                "<sitemap>\n" +
                "　　<loc><![CDATA[http://qq.com/xml/_1.xml]]></loc>\n" +
                "　　<lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "<sitemap>\n" +
                "　　<loc><![CDATA[http://qq.com/xml/_2.xml]]></loc>\n" +
                "　　<lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "<sitemaps>\n" +
                "   <sitemap1>\n" +
                "　　     <loc><![CDATA[http://qq.com/xml/_46.xml]]></loc>\n" +
                "　　     <lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap1>\n" +
                "   <sitemap>\n" +
                "　　     <loc><![CDATA[http://qq.com/xml/_47.xml]]></loc>\n" +
                "　　     <lastmod><![CDATA[2018-06-25 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "</sitemaps>\n" +
                "</sitemapindex>\n";
//
//        System.out.println(xml);
//
//        JSONObject json = XmlUtils.xml2Json(xml);
//        System.out.println(json.toJSONString());
//     JSONObject js = json.getJSONObject("sitemapindex");
//        System.out.println("--------------------------");
//        System.out.println(js.toString());
//        JSONArray array = js.getJSONArray("sitemaps");
//        System.out.println(array.getJSONObject(0).toString());
//        System.out.println(array.getJSONObject(0).getJSONArray("sitemap").getJSONObject(0).get("loc"));
//
        String str = "{\"code\":\"10000\",\"msg\":\"success\",\"time\":\"2022-10-21 16:15:37\",\"requestId\":\"8a43bbfc-02ed-4e11-8016-d9324ce6e2e9\",\"data\":{\"access_token\":\"eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NjYzNDA0MzcsInN1YiI6ImYwNTU2ZjNkLThjYzMtNDYyYi04MzMxLTA1ZjE3NDZmZWJiYiIsImNyZWF0ZWQiOjE2NjYzNDAxMzc4OTF9.7AVmwsxSblcYBsvyxZztrLRQmQfODeD-HMj6KtUKZWbQPPDSVoffIB657QvT1P6nyXqFdxvMYM47i9mMTwF94Q\",\"needChoose\":0}}";

        String jsonString = "{\"name\":\"张三\",\"sex\":\"男\",\"age\":25}";


        JSONObject jsonObject = JSONObject.parseObject(str);
       String access_token =jsonObject.getJSONObject("data").getString("access_token");
       System.out.println(access_token);

    }
}