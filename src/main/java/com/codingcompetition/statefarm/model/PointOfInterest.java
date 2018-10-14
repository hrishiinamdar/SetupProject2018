package com.codingcompetition.statefarm.model;

import java.util.HashMap;
import java.util.Map;

public class PointOfInterest {

	String Latitude = "";
	String Longitude = "";
	String k = "";
	

	String v = "";
	
	/** To check what below function does need to see test case !!*/
    public Map<Object,String> getDescriptors() {
    return new HashMap<>();
    }

    public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	
    public String getLatitude() {
        return "";
    }

    public String getLongitude() {
        return "";
    }
    public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}
}
