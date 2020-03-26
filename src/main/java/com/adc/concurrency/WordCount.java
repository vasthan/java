package com.adc.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join模拟MapReduce词频统计
 */
public class WordCount {

    public static void main(String[] args) {
        // 每个元素代表一行文本
        String[] lines = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};
        ForkJoinPool fjp = new ForkJoinPool(3);
        MR mr = new MR(lines, 0, lines.length - 1);
        Map<String, Integer> result = fjp.invoke(mr);
        System.out.println(result);
    }

    static class MR extends RecursiveTask<Map<String, Integer>> {

        String[] lines;
        int l, r;

        // 统计lines[l, r]的词频
        public MR(String[] lines, int l, int r) {
            this.lines = lines;
            this.l = l;
            this.r = r;
        }

        @Override
        protected Map<String, Integer> compute() {
            if (l == r) {
                return wc(lines[l]);
            }
            int mid = l + (r - l) / 2;
            MR mr1 = new MR(lines, l, mid);
            mr1.fork();
            MR mr2 = new MR(lines, mid + 1, r);
            return merge(mr1.join(), mr2.compute());
        }

        private Map<String, Integer> merge(Map<String, Integer> r1, Map<String, Integer> r2) {
            Map<String, Integer> result = new HashMap<>(r1);
            for (Map.Entry<String, Integer> entry : r2.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (!result.containsKey(key)) {
                    result.put(key, value);
                } else {
                    result.put(key, result.get(key) + value);
                }
            }
            return result;
        }

        private Map<String, Integer> wc(String line) {
            Map<String, Integer> result = new HashMap<>();
            for (String word : line.split(" ")) {
                if (!result.containsKey(word)) {
                    result.put(word, 1);
                } else {
                    result.put(word, result.get(word) + 1);
                }
            }
            return result;
        }
    }
}
