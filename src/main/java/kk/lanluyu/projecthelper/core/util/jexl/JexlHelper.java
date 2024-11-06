package kk.lanluyu.projecthelper.core.util.jexl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.MapContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author zzh
 * @date 2024-11-05
 */
@Slf4j
public class JexlHelper {
  
    private final JexlEngine jexlEngine;

    private static final JexlFunctions JEXL_FUNCTIONS = new JexlFunctions();

    public JexlHelper() {
        jexlEngine = new JexlBuilder()
                .strict(false)
                .permissions(null)
                .imports(Arrays.asList("java.lang","java.math",
                        "kk.lanluyu.projecthelper.core.util.jexl"
                ))
                .create();
    }  
  
    /**  
     * 评估给定的JEXL表达式  
     *
     * @return 表达式的结果  
     */
    public Object evaluate(String expression, Map<String, Object> context) {
        context.put(JexlFunctions.NAME, JEXL_FUNCTIONS);
        MapContext jexlContext = new MapContext();
        // BigDecimal转成double后支持 = - * /运算
        context.forEach((k,v) -> {
            if(v instanceof BigDecimal) {
                jexlContext.set(k, ((BigDecimal) v).doubleValue());
            }else {
                jexlContext.set(k, v);
            }
        });
        return jexlEngine.createExpression(expression).evaluate(jexlContext);
    }
    /**  
     * 评估给定的JEXL表达式，使用默认的空上下文  
     *  
     * @param expression 要评估的JEXL表达式字符串  
     * @return 表达式的结果  
     */  
    public Object evaluate(String expression) {  
        return evaluate(expression, new HashMap<>());  
    }


    public void formulaCal(String formula, String fieldNameFinal, List<LinkedHashMap> jsonObjects) {
        String trueFormula = JexlFunctionEnum.getTrueFormula(formula);

        jsonObjects.parallelStream().forEach(map -> {
            Object result;
            try {
                result = evaluate(trueFormula, map);
                if (result == null) {
                    map.put(fieldNameFinal, "");
                    return;
                }
                try{
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(result));
                    bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
                    map.put(fieldNameFinal, bigDecimal);
                }catch (Exception ignored){
                    map.put(fieldNameFinal, String.valueOf(result));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                map.put(fieldNameFinal, "");
            }

        });
    }

}