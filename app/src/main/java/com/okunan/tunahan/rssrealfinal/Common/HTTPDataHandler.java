package com.okunan.tunahan.rssrealfinal.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by TUNAHAN on 05.01.2018.
 */

public class HTTPDataHandler {
    static String stream=null;

    public HTTPDataHandler(){

    }

    public String GetHttpData(String urlString){
        try {
            URL url=new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            if (urlConnection.getResponseCode()==HttpURLConnection.HTTP_OK){
                InputStream inputStream=new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder=new StringBuilder();

                String line;
                while ((line=r.readLine())!=null){
                    stringBuilder.append(line);
                    stream=stringBuilder.toString();
                    urlConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;//stream olmayabilir kontrol et

    }
}
