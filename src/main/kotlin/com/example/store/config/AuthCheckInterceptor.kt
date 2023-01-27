package com.example.store.config

import com.example.store.common.exception.ErrorResponse
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.web.servlet.HandlerInterceptor

class AuthCheckInterceptor: HandlerInterceptor {

    @Throws(Exception::class)
    fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val session: HttpSession = request.session
        val url: String = request.requestURI
        if (url.contains("swagger") || url.contains("api-docs") || url.contains("webjars")) {
            return true
        }
        if (session.getAttribute("loginSession") == null) {
            val objectMapper = ObjectMapper()
            val message = ErrorResponse("로그인이 필요한 서비스 입니다.")
            val json = objectMapper.writeValueAsString(message)
            response.contentType = "application/json;charset=UTF-8"
            response.characterEncoding = "utf-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.print(json)
            return false
        }
        return true
    }
}