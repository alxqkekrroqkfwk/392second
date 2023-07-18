package com.sparta.second.dto;

import lombok.Getter;
import lombok.Setter;
import java.net.URL;
@Getter
@Setter
public class MenuRequestDto {

    private String menuTitle;
    private String menuCategory;
    private String content;
    private URL menuImage;
}
