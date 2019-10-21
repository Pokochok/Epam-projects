package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.User;
import by.epam.touragency.exception.LogicException;
import by.epam.touragency.exception.RepositoryException;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Iterator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig(WebAppTestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateUserLogicTest {
    @Mock
    Iterator<User> iterator;

    @Mock
    HashSet<User> userHashSet;

    @Mock
    UserRepository  userRepository;

    @Mock
    BCryptPasswordEncoder bCrypt;

    @InjectMocks
    private UpdateUserLogic updateUserLogic;

    @BeforeAll
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateEmailTrue() throws LogicException {
        boolean actual = updateUserLogic.updateEmail("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updateEmail("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateEmailFalse() throws LogicException {
        boolean actual = updateUserLogic.updateEmail("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePhoneNumberTrue() throws LogicException {
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updatePhoneNumber("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdatePhoneNumberFalse() throws LogicException {
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "not defined", "login");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateLoginTrue() throws LogicException {
        boolean actual = updateUserLogic.updateLogin("AGENT", "notdefined", "not defined");
        Assert.assertTrue(actual);
        updateUserLogic.updateLogin("AGENT", "not defined", "not defined");
    }

    @Test
    public void testUpdateLoginFalse() throws LogicException {
        boolean actual = updateUserLogic.updateLogin("AGENT", "not defined", "email");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdatePassword_Agent_Success() throws LogicException, RepositoryException {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(true);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "1234567890", "1234567890");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdatePasswordFalse() throws LogicException {
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "pass", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateNameTrue() throws LogicException {
        boolean actual = updateUserLogic.updateName("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateNameFalse() throws LogicException {
        boolean actual = updateUserLogic.updateName("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }

    @Test
    public void testUpdateSurnameTrue() throws LogicException {
        boolean actual = updateUserLogic.updateSurname("AGENT", "not defined", "not defined");
        Assert.assertTrue(actual);
    }

    @Test
    public void testUpdateSurnameFalse() throws LogicException {
        boolean actual = updateUserLogic.updateSurname("AGENT", "login", "not defined");
        Assert.assertFalse(actual);
    }
}