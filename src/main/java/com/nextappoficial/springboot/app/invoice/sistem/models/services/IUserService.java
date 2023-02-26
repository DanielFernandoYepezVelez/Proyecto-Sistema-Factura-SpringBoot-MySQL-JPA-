package com.nextappoficial.springboot.app.invoice.sistem.models.services;

import com.nextappoficial.springboot.app.invoice.sistem.models.entity.UserAuth;
public interface IUserService {
    UserAuth findByUsername(String username);
}
