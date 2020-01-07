package cn.infomany;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.text.SimpleDateFormat;

/**
 * @author DELL
 * <p>
 * now + "|" + elapsed + "|" + category + "|connection " + connectionId + "|url " + url + "|" + P6Util.singleLine(prepared) + "|" + P6Util.singleLine(sql);
 * #01-07-20 14:37:38:871 | took 1ms | statement | connection 0| url jdbc:p6spy:mysql://localhost:3306/data?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=true
 */
public class P6SpyLogger implements MessageFormattingStrategy {
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("=====================================================\n")
                .append("连接id：").append(connectionId).append("\n")
                .append("当前时间：").append(now).append("\n")
                .append("类别：").append(category).append("\n")
                .append("花费时间：").append(elapsed).append("\n")
//                .append("url：").append(url).append("\n")
                .append("预编译sql：").append(P6Util.singleLine(prepared)).append("\n")
                .append("最终执行的sql：").append(P6Util.singleLine(sql))
                .append("\n=====================================================\n");
        return sb.toString();
    }
}
