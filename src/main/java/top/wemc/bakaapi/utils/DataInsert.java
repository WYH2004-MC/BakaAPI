package top.wemc.bakaapi.utils;

/**
 * @author WYH2004
 * @date 2021/11/08
 **/
public class DataInsert {
    /**
     * 将一个String[]转换为mysql插入语句的fields
     *
     * @param fields 需要转换的fields
     * @return 一个MySQL语句
     */
    public static String insertFields(String[] fields) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String arg : fields) {
            ++i;
            builder.append(arg + (i == fields.length ? "" : ","));
        }
        return builder.toString();
    }

    /**
     * 将一个String[]转换为mysql插入语句的values
     *
     * @param values 需要转换的fields
     * @return 一个MySQL语句
     */
    public static String insertValues(String[] values) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String arg : values) {
            ++i;
            builder.append("'" + arg + "'" + (i == values.length ? "" : ","));
        }
        return builder.toString();
    }
}
