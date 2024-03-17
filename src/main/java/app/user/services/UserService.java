package app.user.services;

import app.admin.dto.AdminDto;
import app.admin.vo.AdminVO;
import app.user.dto.UserDto;
import app.user.vo.UserVO;

public interface UserService {
    UserVO login (String email, String password);
    UserVO logout (String email, String password);
    UserVO addAdmin (AdminDto adminDto);

    UserVO signup(UserDto userDto);

    UserVO findById(Integer userId);
}
