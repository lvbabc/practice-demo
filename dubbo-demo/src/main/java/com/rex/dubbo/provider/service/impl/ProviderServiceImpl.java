package com.rex.dubbo.provider.service.impl;

import com.rex.dubbo.provider.service.ProviderService;

public class ProviderServiceImpl implements ProviderService {

    public String sayHello(String word) {
        return word;
    }
}
