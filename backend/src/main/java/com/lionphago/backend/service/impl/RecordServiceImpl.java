package com.lionphago.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lionphago.backend.common.dto.RecordDTO;
import com.lionphago.backend.exception.RecordAlreadyExsistException;
import com.lionphago.backend.exception.RecordDoesNotExsistException;
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
}
