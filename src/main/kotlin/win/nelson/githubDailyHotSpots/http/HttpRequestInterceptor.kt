package com.wc.amazon.http

import cn.hutool.http.HttpInterceptor
import cn.hutool.http.HttpRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory


/**
 * hutool http请求拦截器
 */
class HttpRequestInterceptor : HttpInterceptor<HttpRequest> {

    val logger : Logger = LoggerFactory.getLogger(HttpRequestInterceptor::class.java)

    override fun process(httpObj: HttpRequest?) {
        if (httpObj == null){
            return
        }
        logger.info("请求信息：[{}]", httpObj.toString())
    }
}