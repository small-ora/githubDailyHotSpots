package com.wc.amazon.http

import cn.hutool.http.HttpInterceptor
import cn.hutool.http.HttpResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HttpResponseInterceptor: HttpInterceptor<HttpResponse> {

    val logger : Logger = LoggerFactory.getLogger(HttpResponseInterceptor::class.java)

    override fun process(httpObj: HttpResponse?) {
        if (httpObj == null){
            return
        }
        logger.info("响应信息：[{}]", httpObj.toString())
    }
}