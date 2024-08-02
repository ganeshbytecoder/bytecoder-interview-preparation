package com.gschoudhary.open2api.restcontroller;

import com.gschoudhary.open2api.domain.ApiStatsEntity;
import com.gschoudhary.open2api.service.APIStatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class ApiRequestInterceptor implements HandlerInterceptor {

    @Autowired
    APIStatsServiceImpl apiStatsService;


    private String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = request.getReader();
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Request Method: " + request.getMethod());

        // Example: Check for a custom header
        String apiCode = request.getHeader("code");
        if (apiCode == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing or invalid api code");
            return false;
        }
        ApiStatsEntity apiStatsEntity = apiStatsService.accept(apiCode, request.toString());
        request.setAttribute("id", apiStatsEntity.getId());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws IOException {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
      System.out.println(request.getAttribute("id"));
        long id = (long) request.getAttribute("id");
        apiStatsService.update(id, "response", response.getStatus());
    }

}
