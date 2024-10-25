package com.sparta.scheduleplus.jwt.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.scheduleplus.exception.customException.HasNotPermissionException;
import com.sparta.scheduleplus.exception.customException.NotFoundEntityException;
import com.sparta.scheduleplus.exception.customException.NotValidCookieException;
import com.sparta.scheduleplus.exception.customException.NotValidTokenException;
import com.sparta.scheduleplus.exception.dto.ResponseExceptionCode;
import com.sparta.scheduleplus.exception.enums.ExceptionCode;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "FilterException")
@Component
@Order(2)
public class ExceptionHandleFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		try {
			chain.doFilter(request, response);

		} catch (NotValidTokenException e) {
			setExceptionToResponse(httpResponse, e.getExceptionCode());

		} catch (NotValidCookieException e) {
			setExceptionToResponse(httpResponse, e.getExceptionCode());

		} catch (HasNotPermissionException e) {
			setExceptionToResponse(httpResponse, e.getExceptionCode());

		} catch (NotFoundEntityException e) {
			setExceptionToResponse(httpResponse, e.getExceptionCode());
		}
	}

	private void setExceptionToResponse(HttpServletResponse httpServletResponse, ExceptionCode exceptionCode) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		httpServletResponse.setStatus(exceptionCode.getHttpStatus().value());
		httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

		ResponseExceptionCode responseExceptionCode = ResponseExceptionCode.builder()
			.code(exceptionCode.name())
			.message(exceptionCode.getMessage())
			.build();

		log.error("{}: {}", exceptionCode, exceptionCode.getMessage());

		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(responseExceptionCode));
	}
}
