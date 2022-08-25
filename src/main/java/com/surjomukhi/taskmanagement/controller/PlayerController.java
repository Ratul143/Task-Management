package com.surjomukhi.taskmanagement.controller;

import com.surjomukhi.taskmanagement.entity.PlayerEntity;
import com.surjomukhi.taskmanagement.request.PaginationRequest;
import com.surjomukhi.taskmanagement.service.PlayerService;
import com.surjomukhi.taskmanagement.utils.API;
import com.surjomukhi.taskmanagement.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(API.BASE_API + API.PLAYER_SERVICE_API)
@Api(tags = "Player Controller API")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping(API.SAVE)
    @ApiOperation("Enroll the Players in the system.")
    public ResponseEntity<BaseResponse> createPlayer(@Valid @RequestBody PlayerEntity playerEntity) {
        BaseResponse response = playerService.createPlayer(playerEntity);
        return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping(API.PLAYER_LIST)
    @ApiOperation("Returns list of all Players in the system.")
    public ResponseEntity<Page<PlayerEntity>> getPlayerListWithPagination(@RequestBody PaginationRequest request){
        Pageable pageable = PageRequest.of(request.getPageNo(), request.getSize());
        Page<PlayerEntity> response = playerService.getPlayerListWithPagination(pageable);
        return new ResponseEntity<Page<PlayerEntity>>(response, HttpStatus.OK);
    }

}
