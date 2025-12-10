package com.lionphago.backend.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lionphago.backend.handler.ListLongJsonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 赛事
 * <li>{@code gameId} 赛事id</li>
 * <li>{@code gameTitle} 赛事名称</li>
 * <li>{@code gamers} 参赛名单</li>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "game",autoResultMap = true)
public class GameDTO {
    @TableId(value = "game_id",type = IdType.AUTO)
    private Long gameId; // 赛事id

    @TableField(value = "game_title")
    private String gameTitle; // 赛事名称

    @TableField(value = "gamers",typeHandler = ListLongJsonTypeHandler.class)
    private List<Long> gamers;  // 参赛(学号)名单
}
