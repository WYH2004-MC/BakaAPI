package top.wemc.bakaapi.mysql;

import top.wemc.bakaapi.Main;
import top.wemc.bakaapi.api.MySqlHelperApi;
import top.wemc.bakaapi.config.MysqlConfig;
import top.wemc.bakaapi.mysql.entity.TableEntity;
import top.wemc.bakaapi.utils.DataInsert;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class MySqlManager {
    private static MySqlHelperApi mySqlHelperApi = Main.getMySqlHelperApi();
    /**
     * 创建表
     * @param tableEntity
     * @throws SQLException
     */
    public static void createTable(TableEntity tableEntity) throws SQLException {
        String sql = tableEntity.getSql();
        if (MysqlConfig.debugMode) {
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
        }
        mySqlHelperApi.execute(sql);
    }

    /**
     * 向已有表中插入新数据
     * @param tableName
     * @param dataMap
     * @throws SQLException
     */
    public static void insertData(String tableName,Map<String, String> dataMap) throws SQLException {
        String[] keys = dataMap.keySet().toArray(new String[0]);
        String[] values = dataMap.values().toArray(new String[0]);
        String sql = "insert into " + tableName + " (" + DataInsert.insertFields(keys) + ") values (" + DataInsert.insertValues(values) + ")";
        if (MysqlConfig.debugMode){
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
            System.out.println("[BakaAPI-MySQL] 以下为新添数据");
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                System.out.println("[BakaAPI-MySQL] key= " + entry.getKey() + " | value= " + entry.getValue());
            }
        }
        mySqlHelperApi.execute(sql);
    }

    /**
     * 删除表(危险操作,正常情况不使用)
     * @param tableName
     * @throws SQLException
     */
    public static void dropTable(String tableName) throws SQLException {
        String sql = "drop table " + tableName + ";";
        if (MysqlConfig.debugMode){
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
        }
        mySqlHelperApi.execute(sql);
    }

    /**
     * 删除行
     * @param tableName
     * @param primaryColumnName
     * @param primaryValue
     * @throws SQLException
     */
    public static void deleteLine(String tableName,String primaryColumnName,String primaryValue) throws SQLException {
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryColumnName + "=" + primaryValue + ";";
        if (MysqlConfig.debugMode) {
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
            System.out.println("[BakaAPI-MySQL] 目标表位置: " + tableName);
            System.out.println("[BakaAPI-MySQL] 搜索依据列名称: " + primaryColumnName);
            System.out.println("[BakaAPI-MySQL] 搜索依据数据内容: " + primaryValue);
        }
        mySqlHelperApi.execute(sql);
    }

    /**
     * 更新数据
     * @param tableName
     * @param pivotColumnName
     * @param primaryColumnName
     * @param primaryValue
     * @param pivotValue
     * @throws SQLException
     */
    public static void updateData(String tableName,String pivotColumnName, String pivotValue, String primaryColumnName, String primaryValue) throws SQLException {
        String sql = "update " + tableName + " set " + pivotColumnName + "=" + pivotValue  + " where " + primaryColumnName + "=" + primaryValue;
        if (MysqlConfig.debugMode) {
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
            System.out.println("[BakaAPI-MySQL] 目标表位置: " + tableName);
            System.out.println("[BakaAPI-MySQL] 修改列名称: " + pivotColumnName);
            System.out.println("[BakaAPI-MySQL] 修改数据内容: " + pivotValue);
            System.out.println("[BakaAPI-MySQL] 搜索依据列名称: " + primaryColumnName);
            System.out.println("[BakaAPI-MySQL] 搜索依据数据内容: " + primaryValue);
        }
        mySqlHelperApi.execute(sql);
    }

    /**
     * 查询数据
     * @param tableName
     * @param columnName
     * @param value
     * @return
     * @throws SQLException
     */
    public static Map<String,Object> getRecordFromTable(String tableName, String columnName, String value) throws SQLException {
        String sql = "select * from " + tableName + " where " + columnName + "=" + value;
        if (MysqlConfig.debugMode){
            System.out.println("[BakaAPI-MySQL] SQL语句: " + sql);
            System.out.println("[BakaAPI-MySQL] =======返回值=======");
        }
        List<Object> result = mySqlHelperApi.executeQuery(sql);
        Iterator it = result.iterator();
        while (it.hasNext()){
            Object temp = it.next();
            Map<String ,Object> map = (Map<String, Object>) temp;
            if (MysqlConfig.debugMode){
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println("[BakaAPI-MySQL] key= " + entry.getKey() + " | value= " + entry.getValue());
                }
            }
            return map;
        }
        if (MysqlConfig.debugMode){
            System.out.println("[BakaAPI-MySQL] null");
        }
        return null;
    }
}
