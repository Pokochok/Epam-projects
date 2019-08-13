package by.epam.tourAgency.logic;

import by.epam.tourAgency.connectionpool.ProxyConnectionPool;
import by.epam.tourAgency.entity.User;
import by.epam.tourAgency.exception.LogicException;
import by.epam.tourAgency.exception.RepositoryException;
import by.epam.tourAgency.repository.impl.UserRepository;
import by.epam.tourAgency.specification.Specification;
import by.epam.tourAgency.specification.impl.admin.FindAdminByLoginPasswordSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginLogicTest {

    @Test
    public void testCheckLoginPasswordExists() throws LogicException, RepositoryException {
        Specification specification = new FindAdminByLoginPasswordSpecification("grekovaAnn",
                "x3Xnt1ft5jDNCqERO9ECZhqziCnKUqZCKreChi8mhkY=");
        User expected = UserRepository.getInstance().query(specification).iterator().next();
        User actual = LoginLogic.checkLoginPassword("grekovaAnn", "1234567890");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCheckLoginPasswordNotExists() throws LogicException, RepositoryException {
        User actual = LoginLogic.checkLoginPassword("grekovaAnn", "123456789340");
        Assert.assertNull(actual);
    }
}