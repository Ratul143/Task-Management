package com.surjomukhi.taskmanagement.controller;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.request.PaginationRequest;
import com.surjomukhi.taskmanagement.service.PlayerService;
import com.surjomukhi.taskmanagement.utils.Api;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Api.BASE_API + Api.PLAYER_SERVICE_API)
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping(Api.SAVE)
    public ResponseEntity<BaseResponse> createPlayer(@Valid @RequestBody PlayerEntity playerEntity) {
        BaseResponse response = playerService.createPlayer(playerEntity);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping(Api.PLAYER_LIST)
    public ResponseEntity<Page<PlayerEntity>> getPlayerListWithPagination(@RequestBody PaginationRequest request){
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        Page<PlayerEntity> response = playerService.getPlayerListWithPagination(pageable);
        return new ResponseEntity<Page<PlayerEntity>>(response, HttpStatus.OK);
    }

}
