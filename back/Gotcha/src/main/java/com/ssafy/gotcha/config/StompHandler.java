package com.ssafy.gotcha.config;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.stereotype.Component;

import com.ssafy.gotcha.api.service.PlayerServiceImpl;
import com.ssafy.gotcha.game.model.Player;
import com.ssafy.gotcha.repository.GameSessionRepository;
import com.ssafy.gotcha.repository.PlayerRepository;

@Component
public class StompHandler extends ChannelInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(StompHandler.class);

	@Autowired
	private PlayerServiceImpl playerService;
	
    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
    	StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        String connectionId = headerAccessor.getSessionId();
        switch (headerAccessor.getCommand()) {
        	// 유저가 구독하면 해당 게임 세션에 플레이어 객체 추가
        	case SUBSCRIBE:
	        	break;
            case CONNECT:
            	logger.info("Client connected with connectionId: {}", connectionId);
            	String roomId = headerAccessor.getFirstNativeHeader("roomId");
            	String userId = headerAccessor.getFirstNativeHeader("userId");
            	playerService.connectPlayer(connectionId, roomId, userId);
                break;
            case DISCONNECT:
            	logger.info("Client disConnected with connectionId: {}", connectionId);
            	playerService.disConnectPlayer(connectionId);            	
                break;
            default:
                break;
        }

    }
}