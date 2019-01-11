package com.ds.mall.auth.server.controller;

import com.ds.mall.auth.server.config.AuthConfig;
import com.ds.mall.auth.server.service.IClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户认证
 * @author tb
 * @date 2019/1/10 15:22
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientsController {

    private final AuthConfig authConfig;
    private final IClientsService clientsService;


}
