package org.hehaoming.user.config.web;

import org.hehaoming.user.exception.BizErrorResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;
import static org.hehaoming.user.constant.Constant.MY_TIPS;

@Component
public class ApplicationErrorAttributes extends DefaultErrorAttributes {


    @Autowired
    public ApplicationErrorAttributes(ServerProperties serverProperties) {
        super(serverProperties.getError().isIncludeException());
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(webRequest, includeStackTrace);

        if (webRequest.getHeader("Accept").matches(".*text/html.*")) {
            return attributes;
        }

        Map<String, Object> resultAttributes = new LinkedHashMap<>();
        resultAttributes.put("success", false);
        resultAttributes.put("message", attributes.get("message"));

        int status = Integer.parseInt(attributes.get("status").toString());
        if (status >= 400 && status < 500) {
            Throwable error = getError(webRequest);
            if (error != null) {
                BizErrorResponseStatus annotation = AnnotationUtils.findAnnotation(error.getClass(), BizErrorResponseStatus.class);
                if (annotation != null) {
                    resultAttributes.put("code", annotation.value());
                } else {
                    resultAttributes.put("code", "-1");
                }
            } else {
                resultAttributes.put("code", "-1");
            }
        } else {
            resultAttributes.put("code", "-2");
        }

        //校验失败不打印stack信息
        if (attributes.get("message") != null) {
            String mes = attributes.get("message").toString();
            if (MY_TIPS.equals(mes.substring(0, 4))) {
                return resultAttributes;
            }
            //截取stack中的提示返回前端
            if ("Validation".equals(mes.substring(0, 10))) {
                String[] strs = attributes.get("trace").toString().split("]] ")[0].split("\\[");
                resultAttributes.put("message", MY_TIPS + strs[strs.length - 1]);
                return resultAttributes;
            }
        }

        if (includeStackTrace) {
            resultAttributes.put("stack", attributes.get("trace"));
        }

        return resultAttributes;
    }
}