package top.wemc.bakaapi.mysql.entity;

import top.wemc.bakaapi.mysql.enums.DataType;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class ColumnEntity {
    String columnName;
    boolean isAutoIncrement = false;
    DataEntity dataEntity = new DataEntity(DataType.TEXT);
    boolean isNotNullable = false;

    public ColumnEntity(String columnName) {
        this.columnName = columnName;
    }

    public ColumnEntity(String columnName, DataEntity dataEntity) {
        this.columnName = columnName;
        this.dataEntity = dataEntity;
    }

    public ColumnEntity(String columnName, boolean isAutoIncrement, DataEntity dataEntity, boolean isNotNullable) {
        this.columnName = columnName;
        this.isAutoIncrement = isAutoIncrement;
        this.dataEntity = dataEntity;
        this.isNotNullable = isNotNullable;
    }

    @Override
    public String toString() {
        return "`" + columnName + "` " + dataEntity.toString() + (isNotNullable ? " NOT NULL" : "") + (isAutoIncrement ? " AUTO_INCREMENT," : ",");
    }
}
