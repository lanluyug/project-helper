package kk.lanluyu.projecthelper.core.util.jexl;

/**
 * @author zzh
 * @date 2024-11-05
 */
public class JexlFunctions {

    public static final String NAME = "JexlFunctions";

    private static final JexlHelper JEXL_HELPER = new JexlHelper();

    /**
     * ABS方法集合
     * @param x
     * @return
     */
    public Double abs(Double x){
        return Math.abs(x);
    }

    public Double abs(Integer x){
        return Math.abs(x.doubleValue());
    }

    /**
     * AND方法集合
     * @param b
     * @return
     */
    public Integer and(Boolean... b){
        boolean result = true;
        for (Boolean aBoolean : b) {
            result = result && aBoolean;
        }
        return bool2Int(result);
    }

    public Integer and(String... formulas){
        boolean result = true;
        for (String formula : formulas) {
            Object evaluate = JEXL_HELPER.evaluate(formula);
            result = result && Boolean.TRUE.equals(evaluate);
        }
        return bool2Int(result);
    }

    public Integer and(Integer... b){
        boolean result = true;
        for (Integer item : b) {
            boolean aBoolean = item != 0;
            result = result && aBoolean;
        }
        return bool2Int(result);
    }

    /**
     * or方法集合
     * @param b
     * @return
     */
    public Integer or(Boolean... b){
        boolean result = false;
        for (Boolean aBoolean : b) {
            result = result || aBoolean;
        }
        return bool2Int(result);
    }

    public Integer or(String... formulas){
        boolean result = false;
        for (String formula : formulas) {
            Object evaluate = JEXL_HELPER.evaluate(formula);
            result = result || Boolean.TRUE.equals(evaluate);
        }
        return bool2Int(result);
    }

    public Integer or(Integer... b){
        boolean result = false;
        for (Integer item : b) {
            boolean aBoolean = item != 0;
            result = result || aBoolean;
        }
        return bool2Int(result);
    }

    public Integer bool2Int(Boolean b){
        return Boolean.TRUE.equals(b) ? 1 : 0;
    }

    /**
     * in函数集合
     * @param o1  目标对象
     * @param b 匹配的对象集
     * @return true 1， false 0
     */
    public Integer in(Object o1, Object... b){
        boolean result = false;
        for (Object o : b) {
            result = result || o.equals(o1);
        }
        return bool2Int(result);
    }


    /**
     * switch函数集合
     * @param o 目标对象
     * @param b 奇数为匹配位，偶数位为返回值，最后一位表示default
     * @return
     */
    public String switchFun(Object o, Object... b){
        // b一定是奇数个参数，最后一个是不满足条件返回的数据
        int conditionLength = b.length - 1;
        for (int i = 0; i < conditionLength; i++) {
            if(i % 2 == 0 && b[i].equals(o)){
                return String.valueOf(b[i+1]);
            }
        }
        return String.valueOf(b[b.length - 1]);
    }

    /**
     * if函数集合
     * @param b 奇数为匹配位，偶数位为返回值，最后一位表示else
     * @return
     */
    public String ifFun(Object... b){
        // b一定是奇数个参数，最后一个是不满足条件返回的数据
        int conditionLength = b.length - 1;
        for (int i = 0; i < conditionLength; i++) {
            if(i % 2 == 0) {
                boolean isBoolTrue = b[i] instanceof Boolean && Boolean.TRUE.equals(b[i]);
                boolean isInteger1 = b[i] instanceof Integer && b[i].equals(1);
                if (isBoolTrue || isInteger1) {
                    return String.valueOf(b[i + 1]);
                }
            }
        }
        return String.valueOf(b[b.length - 1]);
    }

}
