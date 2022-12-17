package com.qy.service.impl;

import com.qy.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: qy
 * @create: 2022/12/17 16:54
 * @description:
 **/
@Service("per")
public class PermissionService {

    public boolean hasPermission(String per) {
//        if (SecurityUtils.isAdmin()) {
//            return true;
//        }
        List<String> permList = SecurityUtils.getLoginUser().getPermList();
        return permList.contains(per);
    }
}
