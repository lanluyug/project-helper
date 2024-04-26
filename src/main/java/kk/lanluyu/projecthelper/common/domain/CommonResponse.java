package kk.lanluyu.projecthelper.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * @author zzh
 * @date 2024-04-26
 */
@Getter
@Setter
public class CommonResponse<T>  implements Serializable{
    /**
     * 返回代码
     */
    private String code;
    /**
     * 失败消息
     */
    private String message;
    /**
     * 时间戳
     */
    private long timestamp;
    /**
     * 成功标志
     */
    private boolean success;
    /**
     * 结果对象
     */
    private T data;

    /**
     * 屏蔽无参构造参数
     */
    private CommonResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 返回成功
     */
    public static <T> CommonResponse<T> success(T resp, String msg){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = CodeEnum.SUCCESS.getCode();
        commonResponse.data = resp;
        commonResponse.success = true;
        commonResponse.message = msg;

        return commonResponse;
    }

    /**
     * 返回成功
     */
    public static <T> CommonResponse<T> success(T resp){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = CodeEnum.SUCCESS.getCode();
        commonResponse.data = resp;
        commonResponse.success = true;

        return commonResponse;
    }

    /**
     * 返回成功
     */
    public static <T> CommonResponse<T> success(){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = CodeEnum.SUCCESS.getCode();
        commonResponse.success = true;

        return commonResponse;
    }

    /**
     * 返回成功
     */
    public static <T> CommonResponse<T> success(String msg){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = CodeEnum.SUCCESS.getCode();
        commonResponse.success = true;
        commonResponse.message = msg;

        return commonResponse;
    }

    /**
     * 返回失败
     */
    public static <T> CommonResponse<T> fail(String errorCode, String error){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = errorCode;
        commonResponse.success = false;
        commonResponse.message = error;

        return commonResponse;
    }

    /**
     * 返回失败
     */
    public static <T> CommonResponse<T> fail(CodeEnum codeEnum){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = codeEnum.getCode();
        commonResponse.success = false;
        commonResponse.message = codeEnum.getMsg();

        return commonResponse;
    }

    /**
     * 返回失败
     */
    public static <T> CommonResponse<T> simpleFail(String error){
        CommonResponse<T> commonResponse = new CommonResponse<>();
        commonResponse.code = CodeEnum.ERROR.getCode();
        commonResponse.success = false;
        commonResponse.message = error;

        return commonResponse;
    }
}
