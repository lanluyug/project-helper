package kk.lanluyu.projecthelper.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户端异常
 *
 * @author zzh
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -3852465600337L;
    /**
     * 错误枚举
     */
    private final String errorCode;
    /**
     * 错误描述
     */
    private final String errorMsg;

    public CommonException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommonException(String errorMsg) {
        this.errorCode = "600000";
        this.errorMsg = errorMsg;
    }
}
