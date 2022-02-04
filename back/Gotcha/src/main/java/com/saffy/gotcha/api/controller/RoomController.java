package com.saffy.gotcha.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saffy.gotcha.api.request.RoomRegisterPostReq;
import com.saffy.gotcha.api.service.RoomService;
import com.saffy.gotcha.common.model.response.BaseResponseBody;
import com.saffy.gotcha.entity.Room;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "room 컨트롤러 API")
@RequestMapping("/api/rooms")
public class RoomController {

	@Autowired
	RoomService roomService;

	@ApiOperation(value = "roomInfo", notes = "새로운 room 만들기")
	@PostMapping()
	public ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody RoomRegisterPostReq roomInfo) {
		roomService.createRoom(roomInfo);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	
	@ApiOperation(value = "",notes = "생성된 모든 room의 정보를 반환한다.")
	@GetMapping
	public ResponseEntity<List<Room>> roomList() {
		return ResponseEntity.status(200).body(roomService.getRooms());
	}
}
