package top.wemc.bakaapi.api;

import com.google.gson.JsonElement;
import top.wemc.bakaapi.config.PushConfig;
import top.wemc.bakaapi.utils.JsonUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author WYH2004
 * @date 2021/11/22 12:05
 **/
public class QmsgApi {
    /**
     * Qmsg酱 发送私聊消息
     * @param msg
     * @param qq
     * @return boolean
     * @throws IOException
     */
    public static boolean sendMassage(String msg,String ...qq) throws IOException {
        if (PushConfig.qmsgEnabled){
            String url = "https://qmsg.zendee.cn/send/" + PushConfig.qmsgToken + "?msg=" + msg;
            if(qq.length > 0){
                url = url + "&qq=" +Arrays.toString(qq);
            }
            JsonElement rawData = JsonUtils.getJsonURL(url,"success");
            return rawData.getAsBoolean();
        }
        return false;
    }

    /**
     * Qmsg酱 发送群聊消息(qq参数填入'-1'，则为群发)
     * @param msg
     * @param qq
     * @return boolean
     * @throws IOException
     */
    public static boolean sendGroupMassage(String msg,String ...qq) throws IOException {
        if (PushConfig.qmsgEnabled){
            String url = "https://qmsg.zendee.cn/group/" + PushConfig.qmsgToken + "?msg=" + msg;
            if(qq.length > 0){
                url = url + "&at=" + Arrays.toString(qq);
            }
            JsonElement rawData = JsonUtils.getJsonURL(url,"success");
            return rawData.getAsBoolean();
        }
        return false;
    }
}
