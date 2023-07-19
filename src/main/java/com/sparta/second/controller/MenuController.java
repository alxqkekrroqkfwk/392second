package com.sparta.second.controller;

import com.sparta.second.dto.MenuListResponseDto;
import com.sparta.second.dto.MenuRequestDto;
import com.sparta.second.dto.MenuResponseDto;
import com.sparta.second.dto.MsgResponseDto;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    //해당 가게 메뉴판 리스트 조회
    @GetMapping("/menus")
    public ResponseEntity<MenuListResponseDto> getMenus() {
        MenuListResponseDto menuListResponseDto = menuService.getMenus();
        return ResponseEntity.ok().body(menuListResponseDto);
    }

    //해당 가게 메뉴판 개별 조회
    @GetMapping("/menu/{menu_id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menu_id) {
        MenuResponseDto menuResponseDto = new MenuResponseDto(menuService.findMenu(menu_id));
        return ResponseEntity.ok().body(menuResponseDto);
    }

    // 가게 메뉴판 작성
    @PostMapping("/menu")
    public ResponseEntity<MsgResponseDto> createMenu(@RequestBody MenuRequestDto requestDto) {
        menuService.createMenu(requestDto);
        return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 작성 성공", HttpStatus.OK.value()));
    }

    //가게 메뉴판 수정
    @PutMapping("/menu/{menu_id}")
    public ResponseEntity<MsgResponseDto> updateMenu(@PathVariable Long menu_id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MenuRequestDto requestDto) {
        try {
            menuService.updateMenu(menu_id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 수정 성공.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    //가게 메뉴판 삭제
    @DeleteMapping("/menu/{menu_id}")
    public ResponseEntity<MsgResponseDto> deleteMenu(@PathVariable Long menu_id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MenuRequestDto requestDto) {
        try {
            menuService.deleteMenu(menu_id, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 삭제 성공.", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
