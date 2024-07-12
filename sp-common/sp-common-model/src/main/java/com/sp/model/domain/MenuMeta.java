package com.sp.model.domain;

import lombok.Data;

import java.util.List;

@Data
public class MenuMeta {

    private int id;
    private String title;
    private String icon;
    private List<String> roles;
    private boolean affix;

}
