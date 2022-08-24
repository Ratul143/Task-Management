package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    BaseResponse createPlayer(PlayerEntity playerEntity);

    Page<PlayerEntity> getPlayerListWithPagination(Pageable pageable);
}
