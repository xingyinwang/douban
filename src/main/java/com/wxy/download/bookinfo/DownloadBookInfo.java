package com.wxy.download.bookinfo;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

/**
 * Created by Kode on 2019/1/14.
 */
public class DownloadBookInfo {
    public static void main(String[] args){
        String userId = "189821590";
        String urll = "https://api.douban.com/v2/book/user/"+userId+"/collections?count=100; ";
        String httpsRequest = httpsRequest(urll, "GET", "null");
        JSONObject jsonObject1 = new JSONObject(httpsRequest);
       // JSONObject jsonObject = new JSONObject().getJSONObject(httpsRequest);
        JSONArray collecObj = (JSONArray)jsonObject1.get("collections");
        for (int i = 0; i < collecObj.length(); i++) {
        //    if (collecObj.get(i))
            JSONObject o = (JSONObject)collecObj.get(i);
            String status = o.getString("status");
            JSONObject book = (JSONObject) o.get("book");
            String title = book.getString("title");
            JSONObject images = (JSONObject)book.get("images");
            String imageUrl = images.getString("small");
            System.out.println("第"+ i + "本书");
            System.out.println("status: " + status);
            System.out.println("imageUrl: " + imageUrl);
            System.out.println("title: " + title);
            System.out.println();
        }

    }
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            //创建SSLContext
            SSLContext sslContext= SSLContext.getInstance("SSL");
            TrustManager[] tm={new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new java.security.SecureRandom());;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf=sslContext.getSocketFactory();
            URL url=new URL(requestUrl);
            HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String getURLContent(String urll, String a) throws Exception {
        String strURL = urll;
        URL url = new URL(strURL);
        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        httpConn.disconnect();

        System.out.println(buffer.toString());
        return buffer.toString();
    }
    public static String getURLContent(String urlStr) {
        /** 网络的url地址 */
        URL url = null;
        /** http连接 */
        HttpURLConnection httpConn = null;
		/**//** 输入流 */
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ex) {

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
        }
        String result = sb.toString();

        return result;
    }
}
