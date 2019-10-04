package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class LoginLogicTest {

    @Test
    public void testCheckLoginPasswordExists() throws LogicException, RepositoryException {
        Specification specification = new FindAgentByLoginPasswordSpecification("not defined",
                BCrypt.hashpw("1234567890", BCrypt.gensalt()));
        User expected = UserRepository.getInstance().query(specification).iterator().next();
//        User actual = LoginLogic.checkLoginPassword("not defined", "1234567890");
//        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckLoginPasswordNotExists() throws LogicException, RepositoryException {
//        User actual = LoginLogic.checkLoginPassword("grekovaAnn", "123456789340");
//        Assert.assertNull(actual);
    }
}