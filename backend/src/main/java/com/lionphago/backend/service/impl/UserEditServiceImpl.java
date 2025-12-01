package com.lionphago.backend.service.impl;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.mapper.UserMapper;
import com.lionphago.backend.service.UserEditService;
import com.lionphago.backend.utils.DOUtil;
import com.lionphago.backend.utils.ShiroSHAUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UserEditServiceImpl implements UserEditService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户修改
     * @param {@code user}
     * @return @{code UserVO}
     */
    public UserVO Edit(UserDTO user) {
        // 偷懒，用修改部分替换已有的部分，然后使用整个对象update
        UserDTO userFromDatabase = userMapper.selectById(user.getUserId());

        // 要改的项目就改一下
        if(!user.getUsername().isEmpty()) {
            userFromDatabase.setUsername(user.getUsername());
        }
        if(!user.getPassword().isEmpty()) {
            String encryptedPassword = ShiroSHAUtil.encrypt(user.getPassword());
            userFromDatabase.setPassword(encryptedPassword);
        }
        if(!user.getGrade().describeConstable().isEmpty()) {
            userFromDatabase.setGrade(user.getGrade());
        }
        if(!user.getClassNumber().describeConstable().isEmpty()) {
            userFromDatabase.setClassNumber(user.getClassNumber());
        }
        if(!user.getMajor().isBlank()) {
            userFromDatabase.setMajor(user.getMajor());
        }
        if(!user.getSchool().isBlank()) {
            userFromDatabase.setSchool(user.getSchool());
        }

        // 写入数据库
        int r = userMapper.updateById(userFromDatabase);

        if(r != 1) {
            log.error("编辑用户 {} 数据库写入失败，写入 {} 行", user.getUserId(), r);
            return UserVO.builder().build();
        }

        return DOUtil.dtoToVo(userFromDatabase);
    }
}
