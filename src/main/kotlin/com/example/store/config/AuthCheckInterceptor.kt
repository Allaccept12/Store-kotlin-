package com.example.store.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.web.ErrorResponse
import org.springframework.web.servlet.HandlerInterceptor

class AuthCheckInterceptor: HandlerInterceptor {

    @Throws(Exception::class)
    fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any?): Boolean {
        val session: HttpSession = request.getSession()
        val url: String = request.getRequestURI()
        if (url.contains("swagger") || url.contains("api-docs") || url.contains("webjars")) {
            return true
        }
        if (session.getAttribute("loginSession") == null) {
            val objectMapper = ObjectMapper()
            val message: ErrorResponse = ErrorResponse.builder().message("로그인이 필요한 서비스 입니다.").build()
            val json = objectMapper.writeValueAsString(message)
            response.setContentType("application/json;charset=UTF-8")
            response.setCharacterEncoding("utf-8")
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
            response.getWriter().print(json)
            return false
        }
        return true
    }
}