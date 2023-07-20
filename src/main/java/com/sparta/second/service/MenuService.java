package com.sparta.second.service;

import com.sparta.second.dto.MenuListResponseDto;
import com.sparta.second.dto.MenuRequestDto;
import com.sparta.second.dto.MenuResponseDto;
import com.sparta.second.entity.Menu;
import com.sparta.second.entity.Shop;
import com.sparta.second.entity.User;
import com.sparta.second.repository.MenuRepository;
import com.sparta.second.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ShopService shopService;

    // 메뉴 전체 조회
    public MenuListResponseDto getMenus() {
        List<Menu> menuList = menuRepository.findAllByOrderByModifiedAtDesc();
        return new MenuListResponseDto(menuList.stream().map(MenuResponseDto::new).toList());
    }

    // 메뉴 상세 조회
    public MenuResponseDto getMenu(Long menu_id) {
        Menu menu = findMenu(menu_id);

        MenuResponseDto menuResponseDto = new MenuResponseDto(menu);

        return menuResponseDto;
    }

    // 메뉴 생성
    public void createMenu(Long shop_id,MenuRequestDto menurequestDto, User user) {
        Shop shop = shopService.findShop(shop_id);
        if (!shop.getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else {
            menuRepository.save(new Menu(menurequestDto, shop));
        }
    }

    //메뉴 삭제
    @Transactional
    public void deleteMenu(Long menu_id, User user) {
        Menu menu = findMenu(menu_id);

        if (!menu.getShop().getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else {
            menuRepository.delete(menu);
        }
    }

    // 메뉴 수정
    @Transactional
    public void updateMenu(Long menu_id, MenuRequestDto menurequestDto, User user) {
        Menu menu = findMenu(menu_id);

        if (!menu.getShop().getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }
        menu.update(menurequestDto);

    }
    public Menu findMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메뉴가 존재하지 않습니다."));
    }
}
