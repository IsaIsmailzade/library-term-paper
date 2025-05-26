package library.service;

import library.dao.AdminDao;
import library.dto.AdminDto;
import library.mapper.AdminMapper;
import lombok.Getter;

import java.util.Optional;

public class AdminService {

    @Getter
    private static final AdminService INSTANCE = new AdminService();
    AdminDao adminDao = AdminDao.getINSTANCE();
    AdminMapper adminMapper = AdminMapper.getINSTANCE();

    public Optional<AdminDto> login(String email, String password) {
        return adminDao.findByEmailAndPassword(email, password)
                .map(adminMapper::mapFrom);
    }
}
