package com.surjomukhi.taskmanagement.service.implementation;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.repository.PlayerRepository;
import com.surjomukhi.taskmanagement.service.PlayerService;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import com.surjomukhi.taskmanagement.utils.CustomCodeGenerator;
import com.surjomukhi.taskmanagement.utils.CustomMessage;
import com.surjomukhi.taskmanagement.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CustomCodeGenerator customCodeGenerator;

    @Override
    public BaseResponse createPlayer(PlayerEntity playerEntity) {
        try {
            playerEntity.setUuid(customCodeGenerator.getGeneratedUuid());
            playerRepository.save(playerEntity);
            log.info("Player" + CustomMessage.SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info("Player Save Error Message" + e.getCause().getCause().getMessage());
            return new BaseResponse(StatusCode.BAD_REQUEST, "Player" + CustomMessage.SAVE_FAILED_MESSAGE);
        }
        return new BaseResponse(StatusCode.CREATED, "Player" + CustomMessage.SAVE_SUCCESS_MESSAGE);
    }

    @Override
    public Page<PlayerEntity> getPlayerListWithPagination(Pageable pageable) {
        return playerRepository.findByIsActiveOrderByCreatedAtDesc(false, pageable);
    }
}
