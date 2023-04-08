package top.wemc.bakaapi.config;

import org.bukkit.plugin.Plugin;
import top.wemc.bakaapi.Main;

/**
 * @author WYH2004
 * @date 2021/11/08
 **/
public class DefaultConfig {
    public static void defaultConfig(){
        Plugin plugin = Main.getInstance();
        plugin.getConfig().options().copyDefaults(true);


    }


}
