package top.wemc.bakaapi.config;

/**
 * @author WYH2004
 * @date 2021/11/08
 **/
public class MysqlConfig extends ConfigManager {
    public static MysqlConfig config;

    public MysqlConfig() {
        super("MysqlConfig");
        config = this;
    }

    @Config(path = "Mysql")
    public static boolean mysqlEnabled = false;

    @Config(path = "DebugMode")
    public static boolean debugMode = false;

    @Config(path = "database.name")
    public static String name = "database";

    @Config(path = "database.host")
    public static String host = "localhost";

    @Config(path = "database.port")
    public static int port = 3306;

    @Config(path = "database.username")
    public static String username = "root";

    @Config(path = "database.password")
    public static String password = "password";

    @Config(path = "datasourse.connectionTimeout")
    public static int connectionTimeout = 5000;

    @Config(path = "datasourse.minimumIdle")
    public static int minimumIdle = 60000;

    @Config(path = "datasourse.maxLifetime")
    public static int maxLifetime = 1800000;

    @Config(path = "datasourse.maximumPoolSize")
    public static int maximumPoolSize = 10;
}
