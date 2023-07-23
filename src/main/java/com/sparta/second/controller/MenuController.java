package com.sparta.second.controller;

import com.sparta.second.dto.*;
import com.sparta.second.entity.MenuCategory;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.ShopCategory;
import com.sparta.second.entity.User;
import com.sparta.second.security.UserDetailsImpl;
import com.sparta.second.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api/{shopId}")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    //해당 가게 메뉴판 리스트 조회
    @GetMapping("/menus")
    public ResponseEntity<MenuListResponseDto> getMenus() {
        MenuListResponseDto menuListResponseDto = menuService.getMenus();

        System.out.println("ddddddd");
        return ResponseEntity.ok().body(menuListResponseDto);
    }

    //해당 가게 메뉴판 개별 조회
    @GetMapping("/menu/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menuId) {
        MenuResponseDto menuResponseDto = menuService.getMenu(menuId);
        return ResponseEntity.ok().body(menuResponseDto);
    }

    @GetMapping("/menu/category")
    public ResponseEntity<MenuListResponseDto> getShopCategory(@PathVariable Long shopId ,@RequestParam MenuCategory menuCategory) {
        MenuListResponseDto menuListResponseDto=menuService.getMenuCategory(shopId,menuCategory);
        return ResponseEntity.ok().body(menuListResponseDto);
    }

    // 가게 메뉴판 작성
    @PostMapping("/menu")
    public ResponseEntity<MsgResponseDto> createMenu(@PathVariable Long shopId,@RequestBody MenuRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        menuService.createMenu(shopId ,requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 작성 완료 !", HttpStatus.OK.value()));
    }

    //가게 메뉴판 수정
    @PutMapping("/menu/{menuId}")
    public ResponseEntity<MsgResponseDto> updateMenu(@PathVariable Long menuId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MenuRequestDto requestDto) {
        try {
            menuService.updateMenu(menuId, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 수정 완료 !", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    //가게 메뉴판 삭제
    @DeleteMapping("/menu/{menuId}")
    public ResponseEntity<MsgResponseDto> deleteMenu(@PathVariable Long menuId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            menuService.deleteMenu(menuId, userDetails.getUser());
            return ResponseEntity.ok().body(new MsgResponseDto("메뉴판 삭제 완료 !", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new MsgResponseDto("작성자만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
