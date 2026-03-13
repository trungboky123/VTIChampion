package com.vti.vti_champion.service.classes;

import com.vti.vti_champion.entity.User;
import com.vti.vti_champion.repository.UserRepository;
import com.vti.vti_champion.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy Username"));
    }
}
