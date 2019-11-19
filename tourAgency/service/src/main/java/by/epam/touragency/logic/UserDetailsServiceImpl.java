package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.impl.user.FindUserByLoginSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private Repository<User> userRepository;

    @Override
    public UserDetails loadUserByUsername(/*@RequestParam(value = ParameterConstant.PARAM_NAME_LOGIN)*/ String userLogin) throws UsernameNotFoundException {
        User user = null;
        Set<User> userSet = userRepository.query(new FindUserByLoginSpecification(userLogin));
        if (!userSet.isEmpty()) {
            user = userSet.iterator().next();
        }
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
