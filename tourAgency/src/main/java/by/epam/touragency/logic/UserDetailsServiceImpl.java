package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.admin.FindAdminByLoginSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

import static by.epam.touragency.util.ParameterConstant.PARAM_NAME_LOGIN;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@RequestParam(value = PARAM_NAME_LOGIN) String userLogin) throws UsernameNotFoundException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!  " + userLogin);
        Specification adminSpecification = new FindAdminByLoginSpecification(userLogin);
        User user = null;
        try {
            Set<User> userSet = userRepository.query(adminSpecification);
            if (!userSet.isEmpty()){
                System.out.println("UserSet is not empty!!!!!!!!!!!!!!!!!");
                user = userSet.iterator().next();
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
