package com.lionphago.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lionphago.backend.common.dto.GameDTO;
import com.lionphago.backend.common.dto.GamePageQueryDTO;

public interface GameService {
    GameDTO gameAdd(GameDTO gameAddRequest);

    void delete(String name);

    int update(GameDTO gameUpdateRequest);

    IPage<GameDTO> pageQuery(GamePageQueryDTO gamePageQueryDTO);
}
