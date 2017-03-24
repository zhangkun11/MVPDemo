package com.example.retrofitrxjava.mvpdemomine.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017-03-24.
 */

public class Data {
    public Map<String,Object> map;

    public Data() {
        this.map = new HashMap<String,Object>();
    }
    public void setData(String key,Object value){
        map.put(key, value);
    }
    public <T>T getData(String key){
        return (T) map.get(key);
    }
}
