# BakaAPI

## 安装&使用
### 前往[Actions](https://github.com/WYH2004-MC/BakaAPI/actions)下载最后编译的版本,安装到spigot中,让你的插件调用它。

## 功能
### Qmsg酱
#### 调用方法

```java
import top.wemc.bakaapi.api.QmsgApi;

public class Main() {
    public static void main(String[] args) {
        // 发送私聊消息
        QmsgApi.sendMessage("消息内容");
        // 指定QQ发送私聊消息(多个QQ号用 , 分开)
        QmsgApi.sendMessage("消息内容","QQ号1,QQ号2");
        // 发送群聊消息
        QmsgApi.sendGroupMessage("消息内容");
        // 发送群聊消息，并@QQ(多个QQ号用 , 分开,-1为@全体成员)
        QmsgApi.sendGroupMessage("消息内容","QQ号1,QQ号2");
    }
}
```

### MySQL通信
#### MySql Manager 方案(使用简单)
> MySql Manager已实现基本增删改查功能  
> 本项目Entity相关代码取自 [wisdommen](https://github.com/wisdommen/) 开发的 [MySqlForSpigot](https://github.com/wisdommen/MySqlForSpigot)
##### 创建表
```java
import top.wemc.bakaapi.mysql.MySqlManager;
import top.wemc.bakaapi.mysql.entity.ColumnEntity;

public class Main() {
    public static void main(String[] args) {
        // 新建一个列对象，传入列名称、数据类型
        ColumnEntity uuid = new ColumnEntity("uuid", new DataEntity(DataType.VARCHAR, "(200)"));
        // 另一个新建列对象的方法，可以只传入列名称，数据类型默认是TEXT
        ColumnEntity playerName = new ColumnEntity("player_name");
        // 新建一个表对象，传入表名称、id列名称（可选）、所有的列对象（可无限添加）
        TableEntity playerDataTable = new TableEntity("lottery_player_data", "uuid", uuid, playerName);
        // 使用api创建表，直接传入表对象
        MySqlManager.createTable(playerDataTable);
    }
}
```
##### 其他方法

```java
import top.wemc.bakaapi.mysql.MySqlManager;
import java.util.*;

public class Main() {
    public static void main(String[] args) {
        // 向已有表中插入新数据
        Map<String, String> datamap = new HashMap<String, String>(); // 创建一个Map用来写入数据
        datamap.put("ID", "123"); // Key:列 Value:数据内容
        datamap.put("Name", "WYH2004");
        datamap.put("Password", "test");
        MySqlManager.insertData("test", datamap); // 第一个参数为表名，第二个导入之前创建的Map

        //删除表
        MySqlManager.dropTable("test"); //参数填写需要删除的表名称

        //删除行
        //第一个参数为表名,第二个参数填写数据唯一的列名称,第三个即是对应的数据内容
        MySqlManager.deleteLine("test","ID","123"); 
        
        //更新数据
        //参数1: 表名
        //参数2: 修改列名称
        //参数3: 修改数据内容
        //参数4: 搜索依据列名称
        //参数5: 搜索依据数据内容
        MySqlManager.updateData("test","Password","12345678","Name","WYH2004");
        
        //查询数据(返回值为Map<String, Object>)
        //参数1: 表名
        //参数2: 搜索依据列名称
        //参数3: 搜索依据数据内容
        Map<String,Object> data = MySqlManager.getRecordFromTable("test","Password","12345678");
    }
}
```
#### MySql Helper 方案(自由度更高)

```java


import top.wemc.bakaapi.api.MySqlHelperApi;

import java.sql.Connection;
import java.util.List;

public class Main() {
    public static void main(String[] args) {
        // 建立API
        MySqlHelperApi mySqlHelperApi = new MySqlHelper();
        
        // 执行增 删 改操作
        mySqlHelperApi.execute("sql语句");

        // 查询操作(有返回值)
        List<Object> result = mySqlHelperApi.executeQuery("sql语句");
    }
}
```
