package com.lionphago.backend.common.dto;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class GamePageQueryDTO {

    private Integer page;

    private Integer pageSize;

    private Long gameId;

    private String gameTitle;
}
