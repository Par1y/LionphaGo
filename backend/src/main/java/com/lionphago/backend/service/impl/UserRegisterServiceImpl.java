package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param {@code UserDTO}
     * @return {@code UserVO}
     */
    public UserVO Register(UserDTO userDTO) {
        return null;
    }
}
