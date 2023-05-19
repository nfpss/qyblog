package com.qy.domian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author pengxiaoxi
 * @Description
 * @date 2022/12/16 17:00
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Accessors(chain = true)
public class RouterVO implements Serializable {

    private static final long serialVersionUID = 3146486325300517632L;

    private List<MenuVO> menus;
}
