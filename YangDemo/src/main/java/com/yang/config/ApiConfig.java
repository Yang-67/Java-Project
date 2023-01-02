package com.yang.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author yang
 * @Date 2022/11/29 21:03
 * @Description
 */
@Slf4j
@Configuration
public class ApiConfig {

    @Resource
    private WebApplicationContext applicationContext;

//    @PostConstruct
//    void contextLoads() {
//        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        // 拿到Handler适配器中的全部方法
//        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
////        for (RequestMappingInfo info : methodMap.keySet()){
////            Set<String> urlSet = info.getPatternsCondition().getPatterns();
////            // 获取全部请求方式
////            Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
////            if(!Methods.isEmpty()) {
////                log.info("method: {}", Methods);
////                for (String url : urlSet){
////                    log.info("url : {}",url);
////                }
////            }
////
////        }
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methodMap.entrySet()) {
//            RequestMappingInfo info = entry.getKey();
//            Set<String> urlSet = info.getPatternsCondition().getPatterns();
//            // 获取全部请求方式
//            Set<RequestMethod> Methods = info.getMethodsCondition().getMethods();
//            if(!Methods.isEmpty()) {
//                log.info("method: {}", Methods);
//                for (String url : urlSet){
//                    log.info("url : {}",url);
//                }
//            }
//            // 获取api名称
//            HandlerMethod method = entry.getValue();
//            Annotation[] annotations = method.getMethod().getDeclaredAnnotations();
//            if (annotations != null) {
//                for (Annotation annotation : annotations) {
//                    if(annotation instanceof ApiOperation) {
//                        ApiOperation methodDesc = (ApiOperation) annotation;
//                        String desc = methodDesc.value();
//                        log.info("doc: {}", desc);
//                    }
//                }
//            }
//        }
//    }


    @PostConstruct
    void contextLoads() {
        List<Map<String, String>> resultList = new ArrayList<>();

        Set<String> classApi = new HashSet<>();

        String classDesc = null;
        String classURL = null;
        String methodName = null;
        String methodDesc1 = null;
        String methodURL = null;
        String requestType = null;

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();

            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();

            String className = handlerMethod.getMethod().getDeclaringClass().getName();

            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    classDesc = api.tags()[0];
                } else if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    if (null != requestMapping.value() && requestMapping.value().length > 0) {
                        classURL = requestMapping.value()[0]; //类URL
                    }
                }
            }
            methodName = handlerMethod.getMethod().getName(); // 方法名
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            if (annotations != null) {
                // 处理具体的方法信息
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ApiOperation) {
                        ApiOperation methodDesc = (ApiOperation) annotation;
                        methodDesc1 = methodDesc.value();//接口描述
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                methodURL = url;//请求URL
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                requestType = requestMethod.toString();//请求方式：POST/PUT/GET/DELETE
            }

            // 赋值
            if (!methodsCondition.isEmpty()) {
                if (!classApi.contains(className)) {
                    log.info("第一次进入: {}", className);
                    classApi.add(className);
                }
                resultMap.put("classDesc", classDesc);
                resultMap.put("classURL", classURL);
                resultMap.put("methodName", methodName);
                resultMap.put("methodDesc1", methodDesc1);
                resultMap.put("methodURL", methodURL);
                resultMap.put("requestType", requestType);

                resultList.add(resultMap);
            }
        }
        log.info("doc : {}", resultList);
    }
}
