package com.qy.domian.entity;

import lombok.*;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/15 10:45
 **/
@Data
@NoArgsConstructor
@ToString
@Builder
public class PageParameterHelper {

    private Integer currentPage;

    private Integer pageSize;

    public PageParameterHelper(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage == null || currentPage <= 0 ? 1 : currentPage;
        this.pageSize = pageSize == null || pageSize <= 0 ? 10 : pageSize;
    }
}
