package com.jang.overcharge.singleton;

import com.jang.overcharge.domain.SearchItem;
import com.skp.Tmap.TMapPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jang on 2016-03-26.
 */
public class InfoSingleton {

    private static InfoSingleton infoSingleton;
    private String clientId;
    private String cliendPassword;
    private String ipAddress;
    private String daumApiKey;
    private List<SearchItem> points;


    private InfoSingleton(){
        this.ipAddress = "192.168.0.8";
        this.clientId = "dBPDkOlS_gq9dLoSMaxF";
        this.daumApiKey = "37bb67f541cdcd8129f2c3033cd0d955";
        this.points = new ArrayList<SearchItem>();

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

    public String getDaumApiKey(){
       return this.daumApiKey;
    }

    public List<SearchItem> getPoints() {
        return points;
    }

    public void setPoints(List<SearchItem> points) {
        this.points = points;
    }
}
