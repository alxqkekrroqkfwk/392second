package com.sparta.second.service;

import com.sparta.second.dto.MenuListResponseDto;
import com.sparta.second.dto.MenuRequestDto;
import com.sparta.second.dto.MenuResponseDto;
import com.sparta.second.entity.Menu;
import com.sparta.second.entity.User;
import com.sparta.second.repository.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

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
    @Transactional
    public void createMenu(MenuRequestDto menurequestDto) {
        menuRepository.save(new Menu(menurequestDto));
    }

    //메뉴 삭제
    @Transactional
    public void deleteMenu(Long menu_id, User user) {
        String id = findMenu(menu_id).getUser().getUserName();
        Menu menu = findMenu(menu_id);

        if (!(id.equals(user.getUserName()))) {
            throw new RejectedExecutionException();
        } else {
            menuRepository.delete(menu);
        }
    }

    // 메뉴 수정
    @Transactional
    public MenuResponseDto updateMenu(Long menu_id, MenuRequestDto menurequestDto, User user) {
        String id = findMenu(menu_id).getUser().getUserName();
        Menu menu = findMenu(menu_id);

        if (!(id.equals(user.getUserName()))) {
            throw new RejectedExecutionException();
        }
        menu.setMenuCategory(menurequestDto.getMenuCategory());
        menu.setMenuTitle(menurequestDto.getMenuTitle());
        menu.setMenuContent(menurequestDto.getContent());
        menu.setMenuImage(menurequestDto.getMenuImage());

        return new MenuResponseDto(menu);
    }
    public Menu findMenu(Long id) {
        return menuRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메뉴가 존재하지 않습니다."));
    }
}
