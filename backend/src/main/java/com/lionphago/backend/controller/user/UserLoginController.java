package com.lionphago.backend.controller.user;

import com.lionphago.backend.common.dto.UserDTO;
import com.lionphago.backend.common.vo.UserVO;
import com.lionphago.backend.result.Result;
import com.lionphago.backend.service.UserLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "用户相关接口")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户登录接口
     * @params id username password
     * @return
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result userLogin(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "username", required = false) String username, @RequestParam("password") String password) {
        // 判空
        if (username == null && id == null) {
            return Result.error("用户名或用户名为空");
        }
        if (password == null) {
            return Result.error("口令为空");
        }

        UserDTO userDTO = UserDTO.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
        // 验证
        UserVO userVO = userLoginService.login(userDTO);
        if (userVO.getId() == null) {
            return Result.error("账户或密码错误");
        }

        return Result.success(userVO);
    }
}
