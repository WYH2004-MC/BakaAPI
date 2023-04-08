package top.wemc.bakaapi;

import org.bukkit.plugin.java.JavaPlugin;
import top.wemc.bakaapi.api.MySqlHelperApi;
import top.wemc.bakaapi.commands.baka;
import top.wemc.bakaapi.config.MysqlConfig;
import top.wemc.bakaapi.config.PushConfig;
import top.wemc.bakaapi.mysql.MySqlHelper;

/**
 * @author WYH2004
 * @date 2021/11/01
 **/
public class Main extends JavaPlugin {
    private static Main instance;
    private MySqlHelperApi mySqlHelperApi;

    public static Main getInstance(){
        return instance;
    }

    public static MySqlHelperApi getMySqlHelperApi(){
        return instance.mySqlHelperApi;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        instance = this;
        regConfig();
        regCommands();
        if(MysqlConfig.mysqlEnabled){
            mySqlHelperApi = new MySqlHelper();
        }
        getLogger().info("§e插件已启用");
    }

    @Override
    public void onDisable() {
        getLogger().info("§e插件已关闭");
    }

    public void regConfig(){
        // DefaultConfig.defaultConfig();
        new MysqlConfig();
        new PushConfig();
        saveConfig();
    }

    public static void regCommands(){
        getInstance().getCommand("baka").setExecutor(new baka());
    }

    public static void reloadConfigs(){
        MysqlConfig.config.reload();
        PushConfig.config.reload();
        if(MysqlConfig.mysqlEnabled){

        }
        // Main.getInstance().reloadConfig();
    }

}
