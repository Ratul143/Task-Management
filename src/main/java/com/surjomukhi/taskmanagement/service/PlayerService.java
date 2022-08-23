package com.surjomukhi.taskmanagement.service;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.utils.BaseResponse;

public interface PlayerService {

    BaseResponse createPlayer(PlayerEntity playerEntity);

}
