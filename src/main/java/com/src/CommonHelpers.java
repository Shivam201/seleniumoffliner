package com.src;

import java.io.FileWriter;
import java.util.Hashtable;

public class CommonHelpers {
    public static void writetoFile(Hashtable<String,String> ht,String location) {
        StringBuffer sf = new StringBuffer();

        for (String key : ht.keySet()) {
            String KEY = key;
            String VALUE = ht.get(key);
            sf.append(KEY+" : "+VALUE+"\n");
        }
        
        try {
            FileWriter fw = new FileWriter(location);
            fw.write(sf.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Success...");
    }
}
