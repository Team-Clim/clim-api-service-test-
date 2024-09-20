package com.teamclim.climapiservice.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )throws IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ClimException e) {
            ErrorCode errorCode = e.getErrorCode();
            writeErrorResponse(response, errorCode);
            logger.error("ClimException 발생", e);
        } catch (Exception e) {
            e.printStackTrace();
            writeErrorResponse(response, ErrorCode.INTERNAL_SERVER_ERROR);
            logger.error("예상치 못한 예외 발생(레전드 상황 발생)", e);
        }
    }



    private void writeErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatusCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), ErrorResponse.of(errorCode, errorCode.getErrorMessage()));
        }
}
