package com.sparta.second.service;

import com.sparta.second.dto.*;
import com.sparta.second.entity.Menu;
import com.sparta.second.entity.MenuCategory;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final ShopService shopService;



    // 메뉴 전체 조회
    public MenuListResponseDto getMenus() {
        List<MenuResponseDto> menuList = menuRepository.findAllByOrderByModifiedAtDesc()
                .stream().map(MenuResponseDto::new).collect(Collectors.toList());
        return new MenuListResponseDto(menuList);
    }

    // 메뉴 상세 조회
    public MenuResponseDto getMenu(Long menu_id) {
        Menu menu = findMenu(menu_id);

        MenuResponseDto menuResponseDto = new MenuResponseDto(menu);

        return menuResponseDto;
    }

    public MenuListResponseDto getMenuCategory(Long shopId , MenuCategory menuCategory) {
        List<MenuResponseDto> menuResponseDtoList = menuRepository.findByShop_ShopIdAndMenuCategory(shopId,menuCategory)
                .stream().map(MenuResponseDto::new).collect(Collectors.toList());
        return new MenuListResponseDto(menuResponseDtoList);
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

    // 메뉴 수정
    @Transactional
    public void updateMenu(Long menuId, MenuRequestDto menurequestDto, User user) {
        Menu menu = findMenu(menuId);

        if (!menu.getShop().getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        }
        menu.update(menurequestDto);
    }

    //메뉴 삭제
    @Transactional
    public void deleteMenu(Long menuId, User user) {
        Menu menu = findMenu(menuId);

        if (!menu.getShop().getUser().getUserName().equals(user.getUserName())) {
            throw new RejectedExecutionException();
        } else {
            menuRepository.delete(menu);
        }
    }


    public Menu findMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메뉴가 존재하지 않습니다."));
    }


}
