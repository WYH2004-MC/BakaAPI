package top.wemc.bakaapi.mysql.entity;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class TableEntity {
    ColumnEntity[] columnEntities;
    String tableName;
    String primaryKey = null;
    String charSet = "utf8";
    String engine = "InnoDB";

    public TableEntity(String tableName, ColumnEntity... columnEntities) {
        this.columnEntities = columnEntities;
        this.tableName = tableName;
    }

    public TableEntity(String tableName, String primaryKey, ColumnEntity... columnEntities) {
        this.columnEntities = columnEntities;
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public String getSql() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS `").append(tableName).append("`(");
        for (ColumnEntity columnEntity : columnEntities) {
            stringBuilder.append(columnEntity.toString());
        }
        if (primaryKey != null) {
            stringBuilder.append("PRIMARY KEY ( `").append(primaryKey).append("` ))");
        } else {
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
        }
        stringBuilder.append("ENGINE=").append(engine).append(" DEFAULT CHARSET=").append(charSet).append(";");
        return stringBuilder.toString();
    }
}
