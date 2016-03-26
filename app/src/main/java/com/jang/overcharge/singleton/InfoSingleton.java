package com.jang.overcharge.singleton;

/**
 * Created by Jang on 2016-03-26.
 */
public class InfoSingleton {

    private static InfoSingleton infoSingleton;
    private String clientId;
    private String cliendPassword;
    private String ipAddress;


    private InfoSingleton(){
        this.ipAddress = "192.168.0.8";
        this.clientId = "dBPDkOlS_gq9dLoSMaxF";

    }

    public static InfoSingleton getInstance(){
        if(infoSingleton == null){
            infoSingleton = new InfoSingleton();
        }

        return infoSingleton;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
