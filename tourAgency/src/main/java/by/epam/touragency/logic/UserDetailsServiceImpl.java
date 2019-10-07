package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.entity.UserPrincipal;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.impl.admin.FindAdminByLoginSpecification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginSpecification;
import by.epam.touragency.specification.impl.client.FindClientByLoginSpecification;
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
        User user = null;
        try {
            Set<User> adminSet = userRepository.query( new FindAdminByLoginSpecification(userLogin));
            Set<User> clientSet = userRepository.query( new FindClientByLoginSpecification(userLogin));
            Set<User> agentSet = userRepository.query( new FindAgentByLoginSpecification(userLogin));
            if (!adminSet.isEmpty()) {
                user = adminSet.iterator().next();
            }else if (!agentSet.isEmpty()){
                user = agentSet.iterator().next();
            } else if (!clientSet.isEmpty()){
                user = clientSet.iterator().next();
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
