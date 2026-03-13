package com.vti.vti_champion.service.interfaces;

import com.vti.vti_champion.entity.User;

public interface IUserService {
    public User getUserByUsername(String username);
}
