package PluginServices;

import java.util.Random;

public class Encryptor {
    public static byte[] encrypt(byte[] data, int key){
        Random random = new Random(key);
        for(int i = 0; i < data.length; i++)
            data[i] += random.nextInt();
        return data;
    }
    public static byte[] decrypt(byte[] data, int key){
        Random random = new Random(key);
        for(int i = 0; i < data.length; i++)
            data[i] -= random.nextInt();
        return data;
    }
}
