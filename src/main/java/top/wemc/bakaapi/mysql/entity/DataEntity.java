package top.wemc.bakaapi.mysql.entity;

import top.wemc.bakaapi.mysql.enums.DataType;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class DataEntity {
    DataType dataType;
    String suffix = "";

    public DataEntity(DataType dataType) {
        this.dataType = dataType;
    }

    public DataEntity(DataType dataType, String suffix) {
        this.dataType = dataType;
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return dataType.toString() + suffix;
    }
}
