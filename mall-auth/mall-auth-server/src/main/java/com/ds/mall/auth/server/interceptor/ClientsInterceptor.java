package com.ds.mall.auth.server.interceptor;

import com.ds.mall.auth.common.jwt.IJwtData;
import com.ds.mall.auth.server.config.AuthConfig;
import com.ds.mall.auth.server.service.ClientService;
import com.ds.mall.auth.server.service.JwtService;
import com.ds.mall.common.context.DsMallContext;
import com.ds.mall.common.exception.ClientForbiddenException;
import com.ds.mall.common.exception.ClientInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author tb
 * @date 2019/1/11 14:21
 */
public class ClientsInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthConfig authConfig;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ClientService clientService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(authConfig.getTokenHeader());
        IJwtData jwtData = jwtService.getClientFromToken(token);
        String clientId = jwtData.getClientId();
        List<String> allows = clientService.getAllowedClients(authConfig.getClient().getCode());
        if(allows == null || allows.size() == 0){
            throw new ClientInvalidException("Client is Forbidden!");
        }
        for(String client: allows){
            if(client.equals(clientId)){
                DsMallContext.set(clientId,jwtData.getClientName(),token);
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        DsMallContext.clear();
        super.afterCompletion(request, response, handler, ex);
    }
}
