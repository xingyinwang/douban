package com.wxy.download.bookinfo;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Kode on 2019/1/14.
 */
public class MyX509TrustManager implements X509TrustManager {


    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }


    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        // TODO Auto-generated method stub

    }


    public X509Certificate[] getAcceptedIssuers() {
        // TODO Auto-generated method stub
        return null;
    }

}
