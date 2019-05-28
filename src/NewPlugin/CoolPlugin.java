package NewPlugin;

import PluginServices.Plugin;

import java.util.concurrent.TimeUnit;

public class CoolPlugin implements Plugin {
    @Override
    public void doSmthVeryVeryUseful(){
        new Thread(()-> {
            for (int i = 0; i < 100; i++) {
                System.out.println("doing useful: " + i + "%");
                try {
                    TimeUnit.MILLISECONDS.sleep(10000 / (100 - i));
                } catch (InterruptedException ignore) {
                }
            }
        }).start();
    }
}
