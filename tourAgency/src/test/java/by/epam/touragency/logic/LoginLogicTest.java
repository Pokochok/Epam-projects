package by.epam.touragency.logic;

import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.agent.FindAgentByLoginPasswordSpecification;
import by.epam.touragency.util.SHAEncrypting;
import org.junit.Assert;
import org.junit.Test;


public class LoginLogicTest {

    @Test
    public void testCheckLoginPasswordExists() throws LogicException, RepositoryException {
        Specification specification = new FindAgentByLoginPasswordSpecification("not defined",
                SHAEncrypting.hidePassword("1234567890"));
        User expected = UserRepository.getInstance().query(specification).iterator().next();
        User actual = LoginLogic.checkLoginPassword("not defined", "1234567890");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckLoginPasswordNotExists() throws LogicException, RepositoryException {
        User actual = LoginLogic.checkLoginPassword("grekovaAnn", "123456789340");
        Assert.assertNull(actual);
    }
}