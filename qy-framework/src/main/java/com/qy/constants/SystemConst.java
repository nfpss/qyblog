package com.qy.constants;

/**
 * @author: qy
 * @create: 2022/12/13 0:27
 * @description:
 **/
public class SystemConst {

    /**
     * 文章是草稿
     */
    public static final String ARTICLE_STATUS_DRAFT = "1";

    /**
     * 文章是正常发布状态
     */
    public static final String ARTICLE_STATUS_NORMAL = "0";

    /**
     * 分类状态正常
     */
    public static final int CATEGORY_STATUS_NORMAL = 0;

    /**
     * 分类状态禁用
     */
    public static final String CATEGORY_STATUS_DISABLE = "1";
    /**
     * 0代表审核通过，1代表审核未通过，2代表未审核
     */
    public static final String LINK_STATUS_PASS = "0";
    public static final String LINK_STATUS_NOT_PASS = "1";
    public static final String LINK_STATUS_NOT_AUDIT = "2";

    /**
     * 博客系统登录的key
     */
    public static final String BLOG_LONG_USER_KEY = "blogLogin:%s";

    /**
     * 博客系统后台系统登录的key
     */
    public static final String ADMIN_LONG_USER_KEY = "adminLogin:%s";

    public static final String TOKEN = "token";

    /**
     * 评论类型（0代表文章评论，1代表友链评论)
     */
    public static final String COMMENT_ARTICLE_TYPE = "0";
    public static final String COMMENT_LINK_TYPE = "1";
    /**
     * 根评论
     */
    public static final Long ROOT_COMMENT = -1L;

    /**
     * 注册提示
     */
    public static final String REGISTER_USERNAME = "该用户名太受欢迎，请换一个重试";
    public static final String REGISTER_NICKNAME = "该昵称太受欢迎，请换一个重试";
    public static final String REGISTER_EMAIL = "该邮箱太受欢迎，请换一个重试";

    /**
     * 文章浏览次数
     */
    public static final String ARTICLE_COUNT_KEY = "articleCount";

    /**
     * 菜单类型（M目录 C菜单 F按钮)
     */
    public static final String MENU_TYPE_M = "M";
    public static final String MENU_TYPE_C = "C";
    public static final String MENU_TYPE_F = "F";

    /**
     * '是否为外链（0是 1否）'
     */
    public static final int MENU_IS_FRAME = 0;
    public static final int MENU_NOT_FRAME = 1;

    /**
     * 菜单状态（0显示 1隐藏）',
     */
    public static final int MENU_VIS_DIS = 0;
    public static final int MENU_VIS_HIDDEN = 1;

    /**
     * '菜单状态（0正常 1停用）'
     */
    public static final String MENU_STATUS_NORMAL = "0";
    public static final String MENU_STATUS_START = "1";

    /**
     * 角色分类 1超级管理员 2 普通角色  11 嘎嘎嘎  12 友链审核员
     */
    public static final Long ROLE_BY_ADMIN = 1L;
    public static final Long ROLE_BY_COMMON = 2L;
    public static final Long ROLE_BY_AGGAG = 11L;
    public static final Long ROLE_BY_LINK = 12L;
    public static final String USER_ROLE_KEY = "userRoleKey";

    /**
     * 顶级父类菜单
     */
    public static final Long ROOT_MENU = 0L;

    /**
     * 是否删除 （0没删除，1已经删除）
     */
    public static final Integer NOT_DELETE = 0;
    public static final Integer IS_DELETE = 1;
    /**
     * 账号状态（0正常 1停用）
     */
    public static final String NORMAL_ROLE_STATUS = "0";
    public static final String DISABLE_ROLE_STATUS = "1";
    public static final String USER_STATUS = "status";
    public static final String USER_ID = "userId";
}
