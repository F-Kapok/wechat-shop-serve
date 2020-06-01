package com.fans.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.fans.annotation.ScopeLevel;
import com.fans.core.exception.http.ForbiddenException;
import com.fans.core.exception.http.UnAuthenticatedException;
import com.fans.utils.JwtTokenUtils;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * className: PermissionInterceptor
 *
 * @author k
 * @version 1.0
 * @description
 * @date 2020-06-01 21:26
 **/
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            return true;
        }
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(bearerToken)) {
            throw new UnAuthenticatedException(10004);
        }
        //Bearer
        if (!bearerToken.startsWith("Bearer")) {
            throw new UnAuthenticatedException(10004);
        }
        List<String> tokenList = Splitter.on(StringUtils.SPACE).trimResults().omitEmptyStrings().splitToList(bearerToken);
        if (tokenList.size() != 2) {
            throw new UnAuthenticatedException(10004);
        }
        String token = tokenList.get(1);
        Optional<Map<String, Claim>> optionalMap = JwtTokenUtils.getClaims(token);
        Map<String, Claim> claimMap = optionalMap.orElseThrow(() -> new UnAuthenticatedException(10004));
        return hasPermission(scopeLevel.get(), claimMap);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel == null) {
                return Optional.empty();
            }
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        int level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }
}
