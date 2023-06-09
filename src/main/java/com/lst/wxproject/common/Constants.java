package com.lst.wxproject.common;

public class Constants {

    public static final class STATUS_CODE{

        /*手机号码*/
        public static final int PHONENUM_ERR_CODE = 1001;
        public static final String PHONENUM_ERR_MSG="手机号码有误！";

        /*数据新增失败*/
        public static final int DATA_ADD_FAIL_CODE = 1002;
        public static final String DATA_ADD_FAIL_MSG="新增数据失败！";

        /*数据更新失败*/
        public static final int DATA_UPDATE_FAIL_CODE = 1003;
        public static final String DATA_UPDATE_FAIL_MSG="更新数据失败！";

        /*数据删除失败*/
        public static final int DATA_DELETE_FAIL_CODE = 1004;
        public static final String DATA_DELETE_FAIL_MSG="删除数据失败！";

        //用户登录失败
        public static final int USER_LOGIN_FAIL_CODE = 1005;
        public static final String USER_LOGIN_FAIL_MSG ="用户登录失败！";

        //状态变更失败
        public static final int STATUS_CHANGE_FAIL_CODE=1006;
        public static final String STATUS_CHANGE_FAIL_MSG="状态变更失败！";
    }
}
