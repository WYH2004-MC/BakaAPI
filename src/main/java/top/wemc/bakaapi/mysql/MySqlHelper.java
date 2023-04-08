package top.wemc.bakaapi.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import top.wemc.bakaapi.api.MySqlHelperApi;
import top.wemc.bakaapi.config.MysqlConfig;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WYH2004
 * @date 2021/11/21 20:50
 **/
public class MySqlHelper implements MySqlHelperApi {
    private static DataSource dataSource;

    public MySqlHelper(){
        HikariConfig config = new HikariConfig();
        config.setPoolName("BakaApi-hikari");
        String ip = MysqlConfig.host;
        int port = MysqlConfig.port;
        String database = MysqlConfig.name;
        config.setJdbcUrl("jdbc:mysql://" + ip + ":" + port + "/" + database);
        config.setUsername(MysqlConfig.username);
        config.setPassword(MysqlConfig.password);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        config.addDataSourceProperty("alwaysSendSetIsolation", "false");
        config.addDataSourceProperty("cacheCallableStmts", "true");
        config.addDataSourceProperty("useUnicode", "true");
        config.addDataSourceProperty("characterEncoding", "utf-8");
        config.addDataSourceProperty("useSSL", "false");
        config.setMaximumPoolSize(MysqlConfig.maximumPoolSize);
        config.setMinimumIdle(MysqlConfig.minimumIdle);
        config.setMaxLifetime(MysqlConfig.maxLifetime);
        config.setConnectionTimeout(MysqlConfig.connectionTimeout);
        dataSource = new HikariDataSource(config);
    }

    /**
     * 创建连接
     * @return Connection
     */
    @Override
    public Connection getConnection(){
        try(Connection conn = dataSource.getConnection()){
            return conn;
        }catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 实现 增 删 改 操作
     * @param sql
     * @param parameters
     * @return boolean
     */
    @Override
    public boolean execute(String sql, Object... parameters) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                if (parameters.length <= 0){
                    conn.setAutoCommit(false);
                    pst.execute(sql);
                    conn.commit();
                } else {
                    conn.setAutoCommit(false);
                    for (int i=0; i<parameters.length; i++){
                        pst.setObject(i+1,parameters[i]);
                    }
                    pst.execute();
                    conn.commit();
                }
                if(pst != null){
                    pst.close();
                }
                if(conn != null) {
                    conn.close();
                }
                return true;
            }catch (SQLException ex) {
                conn.rollback();
                System.out.println("[BakaAPI-MySQL] 操作发生错误，操作已回滚");
                System.out.println(ex.getMessage());
                if(conn != null) {
                    conn.close();
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 实现 查询 操作
     * @param sql
     * @param parameters
     * @return List<Object>
     */
    @Override
    public List<Object> executeQuery(String sql, Object... parameters) {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                ResultSet rs;
                if(parameters.length <= 0) {
                    rs = pst.executeQuery();
                } else {
                    for (int i=0; i<parameters.length; i++){
                        pst.setObject(i+1,parameters[i]);
                    }
                    rs = pst.executeQuery();
                }
                List<Object> result = new ArrayList<>();
                ResultSetMetaData rsm = rs.getMetaData();
                while (rs.next()){
                    Map<String, Object> tempMap = new HashMap<String ,Object>();
                    for (int i = 0; i < rsm.getColumnCount(); i++){
                        tempMap.put(rsm.getColumnName(i+1),rs.getObject(i+1));
                    }
                    result.add(tempMap);
                }
                if(pst != null){
                    pst.close();
                }
                if(rs != null){
                    rs.close();
                }
                if(conn != null) {
                    conn.close();
                }
                return result;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

