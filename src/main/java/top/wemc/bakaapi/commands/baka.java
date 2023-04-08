package top.wemc.bakaapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.wemc.bakaapi.Main;
import top.wemc.bakaapi.config.MysqlConfig;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class baka implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            commandSender.sendMessage("======== { BakaAPI } ========");
            commandSender.sendMessage("API信息: BakaAPI");
            commandSender.sendMessage("API版本: null");
            commandSender.sendMessage("插件作者: WYH2004");
        }
        if (strings.length == 1){
            if (strings[0].equals("reload")){
                if (commandSender.hasPermission("baka.admin.core.reload")){
                    Main.reloadConfigs();
                    commandSender.sendMessage("[BakaAPI] 配置文件已重载");
                    commandSender.sendMessage("[BakaAPI-MySql] MySql相关配置需要重启服务器，才可以生效");
                }
            }
            if (strings[0].equals("debug")){
                if (commandSender.hasPermission("baka.admin.core.debug")){
                    commandSender.sendMessage("[BakaAPI] /baka debug <mysql>");
                }
            }
        }
        if (strings.length == 2){
            if (strings[0].equals("debug")){
                if (strings[1].equals("mysql")){
                    if (commandSender.hasPermission("baka.admin.core.debug.mysql")){
                        if (MysqlConfig.debugMode){
                            MysqlConfig.debugMode = false;
                            commandSender.sendMessage("[BakaAPI] Mysql信息输出模式: INFO");
                        }else {
                            MysqlConfig.debugMode = true;
                            commandSender.sendMessage("[BakaAPI] Mysql信息输出模式: DEBUG");
                        }
                        MysqlConfig.config.save();
                    }
                }
            }
        }

        return false;
    }
}
