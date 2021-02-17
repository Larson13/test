package top.auntie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.threads.JMeterVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Input {
    private static final Logger log = LoggerFactory.getLogger(Input.class);

    public static String input2JSONString(JMeterVariables vars) {
        Map<String, String> varMap = new HashMap<>();
        Iterator<Map.Entry<String, Object>> var2 = vars.entrySet().iterator();
        while (var2.hasNext()) {
            Map.Entry<String, Object> entry = var2.next();
            if (entry.getValue() != null)
                varMap.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        log.info(varMap.toString());
        return JSON.toJSONString(varMap);
    }

    public static String doEncrypt(String dataJson) {
        try {
            String data = encryptAesWithAlgorithm(dataJson, Constant.AES_KEY, Constant.ALGORITHM);
            return JSON.toJSONString(new RequestModel(data));
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    private static String encryptAesWithAlgorithm(String data, String privateKey, String algorithm) throws Exception {
        if (!StringUtils.isBlank(privateKey) && !StringUtils.isBlank(data)) {
            SecretKeySpec key = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(1, key);
            return Base64.encodeBase64String(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        }
        return data;
    }

    public static String doDecrypt(String dataJson) {
        try {
            JSONObject jsonObject = JSON.parseObject(dataJson);
            String data = jsonObject.getString("data");
            return decryptAesWithAlgorithm(data, Constant.AES_KEY, Constant.ALGORITHM);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }

    private static String decryptAesWithAlgorithm(String cipherText, String privateKey, String algorithm) throws Exception {
        byte[] dataBytes = Base64.decodeBase64(cipherText);
        SecretKeySpec key = new SecretKeySpec(privateKey.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(2, key);
        return new String(cipher.doFinal(dataBytes), StandardCharsets.UTF_8);
    }

    public static String encryptVars(JMeterVariables vars) {
        String dataJson = input2JSONString(vars);
        return doEncrypt(dataJson);
    }

    public static String decryptAndTake(JMeterVariables vars, String key, Integer... index) {
        String decryptStr = decryptVars(vars);
        return StringUtils.isBlank(decryptStr) ? "" : getString(key, decryptStr, index, 0);
    }

    private static String decryptVars(JMeterVariables vars) {
        String dataJson = input2JSONString(vars);
        return doDecrypt(dataJson);
    }

    public static void encryptAndSetVars(JMeterVariables vars) {
        vars.put("parameterBase64", encryptVars(vars));
    }

    public static void encryptParamsAndSetVars(Params params, JMeterVariables vars) {
        vars.put("parameterBase64", doEncrypt(params.toJSONString()));
    }

    public static Params buildParams(JMeterVariables vars) {
        Map<String, Object> varMap = new HashMap<>();
        if (vars != null) {
            Iterator<Map.Entry<String, Object>> var2 = vars.entrySet().iterator();
            while (var2.hasNext()) {
                Map.Entry<String, Object> entry = var2.next();
                varMap.put(entry.getKey(), entry.getValue());
            }
        }
        return new Params(varMap);
    }

    public static Params buildParams() {
        return new Params(new HashMap<>());
    }

    public static List<Object> buildList(Object... params) {
        return ArrayUtils.isNotEmpty(params) ? Lists.newArrayList(params) : Lists.newArrayList();
    }

    public static String decryptPrev(SampleResult prev) {
        String responseStr = prev.getResponseDataAsString();
        return doDecrypt(responseStr);
    }

    public static String decryptAndTake(SampleResult prev, String key, Integer... index) {
        String decryptStr = decryptPrev(prev);
        return StringUtils.isBlank(decryptStr) ? "" : getString(key, decryptStr, index, 0);
    }

    private static String getString(String key, String decryptStr, Integer[] index, int i) {
        if (decryptStr.startsWith("{")) {
            JSONObject jsonObject = JSON.parseObject(decryptStr);
            return jsonObject.getString(key);
        }
        if (decryptStr.startsWith("[") && CollectionUtils.isNotEmpty(Lists.newArrayList((Object[]) index))) {
            JSONArray jsonArray = JSON.parseArray(decryptStr);
            String var10001 = jsonArray.getString(arrayObjOrDefault(index, i, 0));
            i++;
            return getString(key, var10001, index, i);
        }
        return "";
    }

    private static int arrayObjOrDefault(Integer[] index, int i, int defaultValue) {
        return (index.length > i) ? index[i].intValue() : defaultValue;
    }

    public static String getStringFromObject(String jsonStr, String key) {
        if (jsonStr.startsWith("{")) {
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            return jsonObject.getString(key);
        }
        throw new RuntimeException("數據格式錯誤");
    }

    public static String getObjectStringFromArray(String jsonStr, Integer i) {
        if (jsonStr.startsWith("{"))
            throw new RuntimeException("數據格式錯誤");
        JSONArray jsonArray = JSON.parseArray(jsonStr);
        return jsonArray.getString(i.intValue());
    }

    public static void main(String[] args) {
        JMeterVariables vars = new JMeterVariables();
        vars.put("mobile", "17501671036");
        vars.put("area", "86");
        encryptAndSetVars(vars);
        System.out.println(JSON.toJSONString(vars));
        SampleResult prev = new SampleResult();
        decryptAndTake(prev, "code", new Integer[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
    }
}
