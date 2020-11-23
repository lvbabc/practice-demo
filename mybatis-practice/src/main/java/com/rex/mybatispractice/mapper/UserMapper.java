package com.rex.mybatispractice.mapper;

import com.rex.mybatispractice.entity.User;

public interface UserMapper {
    User selectUser(Long id);
}
