package com.sp.to;

import lombok.Data;

import java.util.ArrayList;
@Data
public class UserTO {
    private String avatar;
    private String introduction;
    private String name;
    private ArrayList<String> roles;

}