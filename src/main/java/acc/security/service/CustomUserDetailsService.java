package acc.security.service;

import acc.security.user.dao.UserRepository;
import acc.security.user.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        acc.security.user.model.User user = userRepository.findOneByUserId(userId);

        if(user != null){
            for(Role role: user.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())); // DB에 저장되어 있는 권한을 부여한다
            }
            //grantedAuthorities.add(new SimpleGrantedAuthority("USER")); // USER 라는 역할을 넣어준다
            return new User(user.getUserId(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Can not find User : " + userId);
        }
    }
}
