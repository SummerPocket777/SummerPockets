package com.sp.dto;

import lombok.Data;

import java.util.ArrayList;
@Data
public class UserDTO {
    private String avatar;
    private String introduction;
    private String name;
    private ArrayList<String> roles;

}
