import PluginServices.Plugin;
import PluginServices.PluginManager;

public class Main {
    public static void main(String[] args){
        PluginManager manager = new PluginManager("C:\\Users\\Игорь\\IdeaProjects\\javahw-classloader");
        Plugin p1 = manager.load("Plugin1", "NewPlugin.CoolPlugin");
        Plugin p2 = manager.load("Plugin2", "NewPlugin.CoolPlugin");
        p1.doSmthVeryVeryUseful();
        p2.doSmthVeryVeryUseful();
    }
}
