package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.exception.UserAlreadyExsistException;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        // 设置身份
        // userDTO.setRoleName();
        QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userDTO.getUserId())
                .or()
                .eq("username",userDTO.getUsername());
        // 如果存在该学号对应的用户 或 存在相同用户名
        if(userMapper.selectOne(queryWrapper) != null){
            throw new UserAlreadyExsistException("已经存在对应的用户");
        }
        userMapper.insert(userDTO);
        return UserVO.builder().id(userDTO.getUserId())
                .grade(userDTO.getGrade())
                .major(userDTO.getMajor())
                ._class(userDTO.get_class())
                .school(userDTO.getSchool())
                .username(userDTO.getUsername())
                .build();
    }
}
