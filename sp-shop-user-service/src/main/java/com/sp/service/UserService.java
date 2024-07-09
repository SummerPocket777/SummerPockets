package com.sp.service;

import com.sp.dto.UserDTO;

public interface UserService {

    UserDTO getInfo(String token);

    void logout(String token);
}
