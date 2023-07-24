package com.sparta.second.controller;

import com.sparta.second.dto.MsgResponseDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.FollowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class FollowController {

    FollowService followService;

    //    @ApiOperation(value = "게시글 즐겨찾기", notes = "사용자가 게시글 즐겨찾기를 누릅니다.")
//    @PostMapping("/boards/{id}/favorites")
//    @ResponseStatus(HttpStatus.OK)
//    public Response favoriteBoard(@ApiParam(value = "게시글 id", required = true) @PathVariable final Long id,
//                                  @JwtAuth Member member) {
//        return Response.success(boardService.updateOfFavoriteBoard(id, member));
//    }
    @PostMapping("/shop/{id}/follow")
    public ResponseEntity<MsgResponseDto> followshop(@PathVariable Long shop_id) {
        followService.followShop(shop_id);
        return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 작성 성공", HttpStatus.OK.value()));
    }
}
