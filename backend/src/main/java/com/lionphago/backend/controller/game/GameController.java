package com.lionphago.backend.controller.game;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lionphago.backend.common.dto.GameDTO;
import com.lionphago.backend.common.dto.GamePageQueryDTO;
import com.lionphago.backend.result.Result;
import com.lionphago.backend.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/game")
@Tag(name = "赛事接口")
public class GameController {
    @Autowired
    private GameService gameService;

    /**
     * 赛事添加接口
     * @param {@code GameDTO gameAddRequest}
     * @return {@code GameDTO}
     */
    @Operation(summary = "赛事添加")
    @PostMapping("/add")
    public Result<GameDTO> gameAdd(@RequestBody GameDTO gameAddRequest) {
        // 判空
        if(!StringUtils.hasText(gameAddRequest.getGameTitle())) {
            return Result.error("赛事名称未指定");
        }

        GameDTO r = gameService.gameAdd(gameAddRequest);
        if (r.getGameId() == null) {
            return Result.error("赛事添加失败。");
        }

        return Result.success(r);
    }


    /**
     * 赛事删除接口
     * @param {@code gameDeleteRequest}
     * @return
     */
    @Operation(summary = "赛事删除")
    @DeleteMapping("/delete/{gameName}")
    public Result gameDeleteByName(@PathVariable String gameName){
        if(!StringUtils.hasText(gameName)) return Result.error("名字为空！");
        gameService.delete(gameName);
        return Result.success();
    }

    /**
     * 赛事修改接口
     * @param {@code gameUpdateRequest}
     * @return
     */
    @Operation(summary = "赛事修改")
    @PutMapping("/update")
    public Result gameUpdate(@RequestBody GameDTO gameUpdateRequest){
        if(!StringUtils.hasText(gameUpdateRequest.getGameTitle())
                || gameUpdateRequest.getGamers() == null
                || gameUpdateRequest.getGamers().isEmpty())
            return Result.error("填写参数有误！");
        int update = gameService.update(gameUpdateRequest);
        if(update == 0){
            return Result.error("更新失败");
        }else {
            return Result.success();
        }
    }


    /**
     * 赛事分页查询
     * @param {@code gamePageQueryDTO}
     * @return {@code IPage<GameDTO>}
     */
    @Operation(summary = "赛事分页查询")
    @GetMapping("/page")
    public Result<IPage<GameDTO>> gamePage(GamePageQueryDTO gamePageQueryDTO){
        log.info(gamePageQueryDTO.toString());
        IPage<GameDTO> pageResult = gameService.pageQuery(gamePageQueryDTO);
        return Result.success(pageResult);
    }
}
