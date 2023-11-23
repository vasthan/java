package com.adc.dataframe;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import joinery.DataFrame;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author 拓破
 */
public class DataFrameTest {

    private final DataFrame<Object> df = new DataFrame<>();

    @Before
    public void before() {
        df.add("商品名称", Lists.newArrayList("水果", "蔬菜", "面包"));
        df.add("销售渠道", Lists.newArrayList("大润发", "永辉", "盒马"));
        df.add("价格", Lists.newArrayList(100, 200, 300));
    }

    @Test
    public void testAppendRow() {
        // 增加一行数据
        df.append(Lists.newArrayList("烤鸡", "盒马", 50));
        System.out.println(df);
    }

    @Test
    public void testAddCol() {
        // 增加一列数据
        df.add("数量", Lists.newArrayList(10, 10, 10));
        System.out.println(df);
    }

    @Test
    public void testReshape() {
        // 行列裁剪，取两行两列的数据
        DataFrame<Object> df2 = df.reshape(2, 2);
        System.out.println(df2);
    }

    @Test
    public void testJoin() {
        DataFrame<Object> df2 = new DataFrame<>();
        df2.add("商品名称", Lists.newArrayList("水果", "蔬菜", "面包1"));
        df2.add("销售渠道", Lists.newArrayList("大润发", "永辉", "盒马"));
        df2.add("价格", Lists.newArrayList(5000, 8000, 20000));

        df.rename("价格", "价格1");
        df2.rename("价格", "价格2");

        DataFrame<Object> joinedDf = df.joinOn(df2, DataFrame.JoinType.INNER, "商品名称", "销售渠道");
        System.out.println(joinedDf);

        joinedDf = joinedDf.drop("商品名称_right", "销售渠道_right");
        System.out.println(joinedDf);

        Map<Object, Object> map = ImmutableMap.of("商品名称_left", "商品名称", "销售渠道_left", "销售渠道");
        joinedDf = joinedDf.rename(map);
        System.out.println(joinedDf);

        DataFrame<Object> df3 = new DataFrame<>();
        df3.add("商品名称", Lists.newArrayList("水果"));
        df3.add("销售渠道", Lists.newArrayList("大润发"));
        df3.add("价格", Lists.newArrayList(3000));

        joinedDf = joinedDf.joinOn(df3, DataFrame.JoinType.INNER, "商品名称", "销售渠道");
        System.out.println(joinedDf);
    }
}
