package kk.lanluyu.projecthelper.core.util.jexl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 方法全部放在JexlFunctions类中
 * @see JexlFunctions
 * @author zzh
 * @date 2024-11-05
 */
@Getter
@RequiredArgsConstructor
public enum JexlFunctionEnum {

    ABS("ABS", "abs"),
    AND("AND", "and"),
    OR("OR", "or"),
    IF("IF", "ifFun"),
    SWITCH("SWITCH", "switchFun"),
    IN("IN", "in"),
    ;

    private final String functionName;

    private final String methodName;

    public static String getTrueFormula(String formula) {
        for (JexlFunctionEnum jexlFunctionEnum : JexlFunctionEnum.values()) {
            formula = formula.replace(jexlFunctionEnum.functionName + "(",
                    JexlFunctions.NAME + "." + jexlFunctionEnum.methodName + "(");
        }
        return formula;
    }
}
