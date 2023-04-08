package top.wemc.bakaapi.config;

/**
 * @author WYH2004
 * @date 2021/11/20
 */
public class PushConfig extends ConfigManager{
    public static PushConfig config;

    public PushConfig() {
        super("PushConfig");
        config = this;
    }

    @Config(path = "Qmsg.Enabled")
    public static boolean qmsgEnabled = false;

    @Config(path = "Qmsg.Token")
    public static String qmsgToken = "YourToken";
}
