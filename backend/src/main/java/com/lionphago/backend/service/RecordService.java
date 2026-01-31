package com.lionphago.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lionphago.backend.common.dto.RecordDTO;

public interface RecordService {
    RecordDTO add(RecordDTO recordAddRequest);

    void delete(RecordDTO recordDelRequest);

    int update(RecordDTO recordUpdateRequest);
}
