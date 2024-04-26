package kk.lanluyu.projecthelper.function.translate.baidu;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.dromara.hutool.crypto.digest.DigestUtil;
import org.dromara.hutool.http.HttpUtil;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author bright
 */
public class TransApi {

    /**
     * 请求api接口
     */
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";
    /**
     * 开发者ID
     */
    private static final String APP_ID = "20240422002031893";
    /**
     * 开发者密钥
     */
    private static final String SECURITY_KEY = "epAM54GFIGgH7UbZa1Fp";

    /**
     * 获取翻译结果
     * 模版:{"from":"zh","to":"en","trans_result":[{"src":"\u963f\u62c9\u65af\u52a0","dst":"Alaska"}]}
     *
     * @param query
     * @param from
     * @param to
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getTransResult(String query, String from, String to) throws Exception {
        /*
         *    百度翻译的标准版 QPS=1
         */
        TimeUnit.SECONDS.sleep(1);
        Map<String, Object> params = buildParams(query, from, to);
        /*
         * 返回的结果是一个JSON字符串,
         * {"error_code":"52003","error_msg":"UNAUTHORIZED USER"}
         * 如{"from":"zh","to":"en","trans_result":[{"src":"\u963f\u62c9\u65af\u52a0","dst":"Alaska"}]}
         */
        String body = HttpUtil.get(TRANS_API_HOST, params);
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONArray transResult = jsonObject.getJSONArray("trans_result");
        return transResult.getJSONObject(0).getString("dst");
    }

    private Map<String, Object> buildParams(String query, String from, String to) {
        Map<String, Object> params = new HashMap<>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", APP_ID);
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = APP_ID + query + salt + SECURITY_KEY;
        params.put("sign", DigestUtil.md5Hex(src));
        return params;
    }
}
