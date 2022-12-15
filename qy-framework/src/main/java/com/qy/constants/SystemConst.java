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
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章是正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 分类状态正常
     */
    public static final int CATEGORY_STATUS_NORMAL = 0;

    /**
     * 分类状态禁用
     */
    public static final int CATEGORY_STATUS_DISABLE = 1;
    /**
     * 0代表审核通过，1代表审核未通过，2代表未审核
     */
    public static final int LINK_STATUS_PASS = 0;
    public static final int LINK_STATUS_NOT_PASS = 1;
    public static final int LINK_STATUS_NOT_AUDIT = 2;

    /**
     * 博客系统登录的key
     */
    public static final String BLOG_LONG_USER_KEY = "blogLogin:%s";


    public static final String TOKEN = "token";

    /**
     * 评论类型（0代表文章评论，1代表友链评论)
     */
    public static final int COMMENT_ARTICLE_TYPE = 0;
    public static final int COMMENT_LINK_TYPE = 1;
    /**
     * 根评论
     */
    public static final int ROOT_COMMENT = -1;

    /**
     * 注册提示
     */
    public static final String REGISTER_USERNAME = "该用户名太受欢迎，请换一个重试";
    public static final String REGISTER_NICKNAME = "该昵称太受欢迎，请换一个重试";
    public static final String REGISTER_EMAIL = "该邮箱太受欢迎，请换一个重试";

}
