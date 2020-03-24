package com.adc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private String logFile = "run.log";
    private static Map<String, List<Pattern>> parsePattern = new HashMap<>();

    static {
        parsePattern.put("sql", Lists.newArrayList(Pattern.compile("执行Hive导入Palo操作SQL:(?s)(.*)执行pre_cmd", Pattern.MULTILINE)));
        parsePattern.put("hive", Lists.newArrayList(Pattern.compile("执行pre_cmd: (.*)", Pattern.MULTILINE), Pattern.compile("执行post_cmd: (.*)", Pattern.MULTILINE)));
        parsePattern.put("job", Lists.newArrayList(Pattern.compile("(^JobId:.*)", Pattern.MULTILINE)));
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        LogParser parser = new LogParser();
        Map<String, LinkedHashSet<String>> result = parser.extractVarsFromLogFile();

        System.out.println("耗时" + (System.currentTimeMillis() - start));
        System.out.println(JSON.toJSONString(result));

//        String str = "执行post_cmd: hive -e \"1drop table default.deleteme_sync_ng_part_20200317174111\" 执行cmd命令：hive -e \"2drop table default.deleteme_sync_ng_part_20200317174111\"";
//
//        Pattern p = Pattern.compile("执行post_cmd: (.*) 执行cmd命令：(.*)");
//        Matcher matcher = p.matcher(str);
//        while (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//        }
    }

    public Map<String, LinkedHashSet<String>> extractVarsFromLogFile() {
        if (parsePattern == null || parsePattern.isEmpty()) {
            return null;
        }

        Map<String, LinkedHashSet<String>> result = new HashMap<>();

        try {
            String content = FileUtils.readFileToString(new File("run.txt"), "UTF-8");
            parsePattern.entrySet().stream()
                    .forEach(entry -> {
                        String key = entry.getKey();
                        entry.getValue().forEach(pattern -> {
                            Matcher m = pattern.matcher(content);
                            while (m.find()) {
                                if (!result.containsKey(key)) {
                                    result.put(key, new LinkedHashSet<>());
                                }
                                result.get(key).add(m.group(1));
                            }
                        });
                    });
        } catch (Exception e) {

        }

        return result;
    }
}
