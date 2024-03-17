package app.admin.services;

import app.admin.dto.AdminDto;
import app.admin.vo.AdminVO;

public interface AdminService {
    AdminVO login (String email, String password);
    AdminVO logout (String email, String password);
    AdminVO addAdmin (AdminDto adminDto);

    AdminVO signup(AdminDto adminDto);
}
