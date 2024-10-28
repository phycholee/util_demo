package com.llf.demo.log.domain;

import com.llf.demo.common.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FansGatherExport {

    @ExcelColumn(name = "日期", width = 30)
    private String date;

    @ExcelColumn(name = "主播数量", width = 30)
    private int anchorCount;

    @ExcelColumn(name = "投钱主播数量", width = 30)
    private int anchorRubyCount;

    @ExcelColumn(name = "中途投钱主播数量", width = 30)
    private int anchorAddRubyCount;

    @ExcelColumn(name = "投钱总额", width = 30)
    private int rubyAmount;

    @ExcelColumn(name = "用户数量", width = 30)
    private int userCount;

    @ExcelColumn(name = "奖励用户数量", width = 30)
    private int userRewardCount;

    @ExcelColumn(name = "奖励用户总额", width = 30)
    private int userRewardRubyAmount;

}
