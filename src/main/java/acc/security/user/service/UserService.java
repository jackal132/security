package acc.security.user.service;

import acc.security.user.dao.RoleRepository;
import acc.security.user.dao.UserRepository;
import acc.security.user.dto.UserDto;
import acc.security.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void insert(UserDto userDto){
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        Set<Role> roleSet = new HashSet<Role>();
        roleSet.add(roleRepository.findOneByRoleId(2L)); // 사용자권한 넣어주기 : ROLE_USER
        userRepository.save(userDto.toEntity(roleSet));
    }
}
