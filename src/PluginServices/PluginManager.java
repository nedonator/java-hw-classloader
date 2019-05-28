package PluginServices;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;

public class PluginManager {
    private final String pluginRootDirectory;
    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }
    public Plugin load(String pluginName, String pluginClassName){
        Object o = null;
        try {
            ClassLoader cl = new URLClassLoader(new URL[]{new File(pluginRootDirectory + File.separator + pluginName).toURI().toURL()});
            String str = pluginRootDirectory + File.separator + pluginName + File.separator + pluginClassName.replace('.', File.separatorChar) + ".class";
            File file = new File(str);
            //todo: make it more legal
            Method method = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
            method.setAccessible(true);
            byte[] array = Files.readAllBytes(file.toPath());
            Class<?> clazz = (Class<?>) method.invoke(cl, pluginClassName, array, 0, array.length);
            o = clazz.getDeclaredConstructor().newInstance();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return o instanceof Plugin ? (Plugin)o : null;
    }
}
