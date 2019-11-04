package by.epam.touragency.logic;

import by.epam.touragency.config.WebAppTestContext;
import by.epam.touragency.entity.User;
import by.epam.touragency.repository.impl.UserRepository;
import by.epam.touragency.specification.Specification;
import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
    MatchOfUniqueFieldsDetector matchOfUniqueFieldsDetector;

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

    private static Flyway flyway;

    @BeforeAll
    public void init() throws IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        EmbeddedPostgres pg = EmbeddedPostgres.builder().setPort(58423).start();
        Connection c = pg.getPostgresDatabase().getConnection();
        String url = pg.getJdbcUrl("postgres", "postgres");
        flyway = Flyway.configure().dataSource(url, "postgres", "").load();
        flyway.migrate();
    }

    @AfterAll
    public static void destroy(){
        flyway.clean();
    }

    @Test
    public void testUpdateEmailTrue(){
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        boolean actual = updateUserLogic.updateEmail("AGENT", "notdefined", "not defined");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateEmailFalse() {
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        boolean actual = updateUserLogic.updateEmail("AGENT", "not defined", "login");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdatePhoneNumberTrue() {
        when(matchOfUniqueFieldsDetector.isExistsPhoneNumber(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "notdefined", "not defined");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdatePhoneNumberFalse()  {
        when(matchOfUniqueFieldsDetector.isExistsPhoneNumber(anyString())).thenReturn(false);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        boolean actual = updateUserLogic.updatePhoneNumber("AGENT", "not defined", "login");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdateLoginTrue() {
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        boolean actual = updateUserLogic.updateLogin("AGENT", "notdefined", "not defined");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateLoginFalse() {
        when(matchOfUniqueFieldsDetector.isExistsEmail(anyString())).thenReturn(true);
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        boolean actual = updateUserLogic.updateLogin("AGENT", "not defined", "email");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdatePassword_Agent_Success()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(true);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "1234567890", "1234567890");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdatePassword_Client_Success()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(true);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("CLIENT", "not defined", "1234567890", "1234567890");
        Assertions.assertTrue(actual);
    }
    @Test
    public void testUpdatePassword_Admin_Success()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(true);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("ADMIN", "not defined", "1234567890", "1234567890");
        Assertions.assertTrue(actual);
    }
    @Test
    public void testUpdatePassword_Agent_Failure()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(false);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("AGENT", "not defined", "1234567890", "1234567890");
        Assertions.assertFalse(actual);
    }
    @Test
    public void testUpdatePassword_Client_Failure()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(false);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("CLIENT", "not defined", "1234567890", "1234567890");
        Assertions.assertFalse(actual);
    }
    @Test
    public void testUpdatePassword_Admin_Failure()  {
        when(bCrypt.encode(anyString())).thenReturn("password");
        when(bCrypt.matches(anyString(), any())).thenReturn(false);
        when(userRepository.query(any(Specification.class))).thenReturn(userHashSet);
        when(userHashSet.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(new User.UserBuilder().build());
        doNothing().when(userRepository).update(any(), any(Specification.class));
        boolean actual = updateUserLogic.updatePassword("ADMIN", "not defined", "1234567890", "1234567890");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdateNameTrue() {
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        boolean actual = updateUserLogic.updateName("AGENT", "not defined", "not defined");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateNameFalse(){
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        boolean actual = updateUserLogic.updateName("AGENT", "login", "not defined");
        Assertions.assertFalse(actual);
    }

    @Test
    public void testUpdateSurnameTrue() {
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(true);
        boolean actual = updateUserLogic.updateSurname("AGENT", "not defined", "not defined");
        Assertions.assertTrue(actual);
    }

    @Test
    public void testUpdateSurnameFalse()  {
        when(matchOfUniqueFieldsDetector.isExistsLogin(anyString())).thenReturn(false);
        boolean actual = updateUserLogic.updateSurname("AGENT", "login", "not defined");
        Assertions.assertFalse(actual);
    }
}