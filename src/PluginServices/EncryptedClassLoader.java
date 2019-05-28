package PluginServices;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class EncryptedClassLoader extends ClassLoader {
    private final String key;

    private final File dir;
    public EncryptedClassLoader(String key, File dir, ClassLoader parent) {
        super(parent);
        this.key = key;
        this.dir = dir;
    }

    @Override
    protected Class<?> findClass(String name){
        try {
            FileInputStream fin = new FileInputStream(dir.getAbsolutePath() + File.separator + name + ".class");
            List<Byte> data = new ArrayList<>();
            int b;
            while((b = fin.read()) != -1)
                data.add((byte)(b));
            byte[] arr = new byte[data.size()];
            for(int i = 0; i < data.size(); i++)
                arr[i] = data.get(i);
            return defineClass(name, Encryptor.decrypt(arr, key.hashCode()), 0, arr.length);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
