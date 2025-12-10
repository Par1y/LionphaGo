package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lionphago.backend.common.dto.GameDTO;
import com.lionphago.backend.common.dto.GamePageQueryDTO;
import com.lionphago.backend.exception.GameAlreadyExsistException;
import com.lionphago.backend.exception.GameDoesNotExsistException;
import com.lionphago.backend.mapper.GameMapper;
import com.lionphago.backend.service.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    /**
     * 添加赛事
     * @param {@code gameAddRequest}
     * @return
     */
    public GameDTO gameAdd(GameDTO gameAddRequest) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("game_title",gameAddRequest.getGameTitle());
        GameDTO gameDTO = gameMapper.selectOne(queryWrapper);
        if(gameDTO != null) {
            // 如果发现有重名的赛事
            throw new GameAlreadyExsistException("存在相同姓名的赛事，请重新输入");
        }
        // 直接添加然后返回 dto字段已添加id自动填充
        gameMapper.insert(gameAddRequest);
        return gameAddRequest;
    }

    /**
     * 删除赛事
     * @param {@code gameName}
     */
    public void delete(String gameName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("game_title",gameName);
        GameDTO gameDTO = gameMapper.selectOne(queryWrapper);
        if(gameDTO == null){
            throw new GameDoesNotExsistException("不存在该赛事！");
        }
        gameMapper.deleteById(gameDTO.getGameId());
    }

    /**
     * 修改赛事
     * @param {@code gameUpdateRequest}
     */
    public int update(GameDTO gameUpdateRequest) {
        return gameMapper.updateById(gameUpdateRequest);
    }

    /**
     * 赛事分页查询
     * @param {@code gamePageQueryDTO}
     * @return {@code IPage<GameDTO>}
     */
    public IPage<GameDTO> pageQuery(GamePageQueryDTO gamePageQueryDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("game_title",gamePageQueryDTO.getGameTitle());
        IPage<GameDTO> page = new Page<>(gamePageQueryDTO.getPage(),gamePageQueryDTO.getPageSize());
        IPage<GameDTO> pageResult = gameMapper.selectPage(page,queryWrapper);
        return pageResult;
    }
}
