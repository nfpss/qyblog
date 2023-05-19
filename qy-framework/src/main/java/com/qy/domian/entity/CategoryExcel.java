package com.qy.domian.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author: qy
 * @create: 2022/12/17 16:38
 * @description:
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CategoryExcel {
    @TableId
    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("分类名称")
    //分类名
    private String name;

    //父分类id，如果没有父分类为-1
    @ExcelProperty("父类id")
    private Long pid;
    //描述
    @ExcelProperty("描述")
    private String description;
    //状态0:正常,1禁用
    @ExcelProperty("状态")
    private String status;

    @ExcelProperty("创建于")
    private Long createBy;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("更新人")
    private Long updateBy;

    @ExcelProperty("是否更新")
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}
