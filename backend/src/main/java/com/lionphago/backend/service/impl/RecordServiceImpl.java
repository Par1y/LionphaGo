package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lionphago.backend.common.dto.RecordDTO;
import com.lionphago.backend.exception.RecordAlreadyExistException;
import com.lionphago.backend.exception.RecordDoesNotExistException;
import com.lionphago.backend.mapper.RecordMapper;
import com.lionphago.backend.service.RecordService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 添加获奖记录
     * @param {@code recordAddRequest}
     * @return
     */
    public RecordDTO add(RecordDTO recordAddRequest) {
        // 检索记录
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("game_id", recordAddRequest.getGameId())
            .eq("gamer_id", recordAddRequest.getGamerId())
            .apply("awards = {0}::jsonb", recordAddRequest.getAwards());
        RecordDTO recordDTO = recordMapper.selectOne(queryWrapper);
        if(recordDTO != null) throw new RecordAlreadyExsistException("奖项已经存在。");

        // 添加记录
        recordMapper.insert(recordAddRequest);
        return recordAddRequest;
    }

    /**
     * 删除获奖记录
     * @param {@code recordDelRequest}
     */
    public void delete(RecordDTO recordDelRequest) {
        // 检索记录
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("game_id", recordDelRequest.getGameId())
            .eq("gamer_id", recordDelRequest.getGamerId())
            .apply("awards = {0}::jsonb", recordDelRequest.getAwards());
        RecordDTO recordDTO = recordMapper.selectOne(queryWrapper);
        if(recordDTO == null) throw new RecordDoesNotExsistException("奖项不存在。");
        // 删除记录
        recordMapper.delete(queryWrapper);
    }

    public int update(RecordDTO recordUpdateRequest) {
        // 检索记录
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("game_id", recordUpdateRequest.getGameId())
            .eq("gamer_id", recordUpdateRequest.getGamerId());
        RecordDTO recordDTO = recordMapper.selectOne(queryWrapper);
        if(recordDTO == null) throw new RecordDoesNotExistException("奖项不存在。");

        // 修改记录
        return recordMapper.update(recordUpdateRequest, queryWrapper);
    }

    /**
     * 分页查询获奖记录
     */
    public IPage<RecordDTO> pageQuery(RecordPageQueryDTO dto) {
        // 任意 gameId 或 gamerId
        QueryWrapper<RecordDTO> wrapper = new QueryWrapper<>();
        if(dto.getGameId() != null) wrapper.eq("game_id", dto.getGameId());
        if(dto.getGamerId() != null) wrapper.eq("gamer_id", dto.getGamerId());
        
        // 返回分页查询结果
        IPage<RecordDTO> page = new Page<>(dto.getPage(), dto.getPageSize());
        return recordMapper.selectPage(page, wrapper);
    }
}
