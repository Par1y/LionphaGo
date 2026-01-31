package com.lionphago.backend.common.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RecordPageQueryDTO {

    private Integer page;

    private Integer pageSize;

    private Long gameId;

    private Long gamerId;

    private List<String> awards;
}
