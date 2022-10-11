package com.cloud.openfeign.log;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import feign.Logger;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

import static feign.Util.decodeOrDefault;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @description:
 * @author: zhou shuai
 * @date: 2022/10/11 16:17
 * @version: v1
 */
@Slf4j
public class MyFeignLogger extends Logger {

    private static final ThreadLocal<Map<String, String>> logContext = new ThreadLocal<>();

    private static final String PATH = "path";

    private static final String METHOD = "method";

    private static final String REQUEST_BODY = "body";

    private static final String HEADER = "header";

    private static final String TIME = "time";


    /**
     * 构建headers字符串
     */
    private String builderHeaders(Map<String, Collection<String>> headersMap) {
        StringBuilder headers = new StringBuilder();
        Iterator<Map.Entry<String, Collection<String>>> iterator = headersMap.entrySet().stream().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Collection<String>> next = iterator.next();
            ArrayList<String> values = new ArrayList<>(next.getValue());
            headers.append(next.getKey())
                    .append(":")
                    .append(values.size() == 1 ? values.get(0) : JSONUtil.toJsonStr(values))
                    .append(iterator.hasNext() ? " | " : "");
        }
        return headers.toString();
    }


    @Override
    protected void logRequest(String configKey, Level logLevel, Request request) {
        Map<String, String> map = new HashMap<>();
        map.put(PATH, request.url());
        map.put(TIME, System.currentTimeMillis() + "");
        map.put(METHOD, request.httpMethod().name());
        map.put(HEADER, builderHeaders(request.headers()));
        String body = request.body() == null ? null : request.charset() == null ? null : new String(request.body(), request.charset());
        map.put(REQUEST_BODY, body);
        logContext.set(map);
    }

    /**
     * 响应拦截
     */
    @Override
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        Map<String, String> request = logContext.get();
        logContext.remove();
        // 返回参数
        byte[] bodyData = IoUtil.readBytes(response.body().asInputStream());
        if (ArrayUtil.isNotEmpty(bodyData)) {
            String responseBody = decodeOrDefault(bodyData, UTF_8, "Binary data");
            log(request, response.status(), responseBody.replaceAll("\\s*|\t|\r|\n", ""));
            return response.toBuilder().body(bodyData).build();
        }
        log(request, response.status(), null);
        return response;
    }


    /**
     * 异常拦截
     */
    @Override
    protected IOException logIOException(String configKey, Level logLevel, IOException ioe, long elapsedTime) {
        Map<String, String> request = logContext.get();
        log.info("Feign-Error -> \npath -> {}\nmethod -> {}\nheaders -> {}\nrequest -> {}\n",
                request.get(PATH), request.get(METHOD), request.get(HEADER), request.get(REQUEST_BODY)
        );
        logContext.remove();
        return ioe;
    }

    /**
     * 日志打印
     *
     * @param request
     * @param responseStatus
     * @param responseBody
     */
    private void log(Map<String, String> request, Integer responseStatus, String responseBody) {
        log.info("\n<Feign>" +
                        "\npath              -> {}" +
                        "\nelapsedTime       -> {}" +
                        "\nmethod            -> {}" +
                        "\nstatus            -> {}" +
                        "\nheaders           -> {}" +
                        "\nrequestBody       -> {}" +
                        "\nresponse          -> {}",
                request.get(PATH),
                (System.currentTimeMillis() - Long.parseLong(request.get(TIME))) + "ms",
                request.get(METHOD),
                responseStatus,
                request.get(HEADER),
                request.get(REQUEST_BODY),
                responseBody
        );
    }

    @Override
    protected void log(String configKey, String format, Object... args) {
        if (log.isInfoEnabled()) {
            log.info(String.format(methodTag(configKey) + format, args));
        }
    }

}
