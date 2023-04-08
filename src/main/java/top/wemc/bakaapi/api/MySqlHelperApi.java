package top.wemc.bakaapi.api;

import java.sql.Connection;
import java.util.List;

/**
 * @author WYH2004
 * @date 2021/11/21 22:00
 **/
public interface MySqlHelperApi {
    /**
     * 创建连接
     * @return Connection
     */
    Connection getConnection();

    /**
     * 实现 增 删 改 操作
     * @param sql
     * @param parameters
     * @return boolean
     */
    boolean execute(String sql, Object... parameters);

    /**
     * 实现 查询 操作
     * @param sql
     * @param parameters
     * @return List<Object>
     */
    List<Object> executeQuery(String sql, Object... parameters);
}
