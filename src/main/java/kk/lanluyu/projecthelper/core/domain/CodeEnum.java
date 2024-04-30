package kk.lanluyu.projecthelper.core.domain;

/**
 * @author 刘春发
 * @version 1.0.0
 * @ClassName ErrorEnum.java
 * @Description 返回码枚举类
 * @createTime 2022年03月16日 13:59
 */
public enum CodeEnum {
    SUCCESS("200", "请求成功"),
    ERROR("500", "系统开小差了,请联系管理员"),
    LOGIN_FAIL("10001", "登录失败")
    ;

    private String code;
    private String msg;

    CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
