package by.epam.touragency.logic;

import by.epam.touragency.entity.Tour;
import by.epam.touragency.entity.User;
import by.epam.touragency.repository.Repository;
import by.epam.touragency.specification.Specification;
import by.epam.touragency.specification.impl.tour.FindTourByNameSpecification;
import by.epam.touragency.specification.impl.user.FindUserByEmailSpecification;
import by.epam.touragency.specification.impl.user.FindUserByLoginSpecification;
import by.epam.touragency.specification.impl.user.FindUserByPhoneNumberSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * For matching unique fields
 */
@Service
public class MatchOfUniqueFieldsDetector {
    @Autowired
    @Qualifier("tourRepository")
    private Repository<Tour> tourRepository;

    @Autowired
    @Qualifier("userRepository")
    private Repository<User> userRepository;

    /**
     * Checks, if such email exists
     *
     * @param email to check
     * @return true, if email exists, and false - if not
     */
    public boolean isExistsEmail(String email) {
        Specification userSpecification = new FindUserByEmailSpecification(email);
        return tourRepository.isExistsQuery(userSpecification);
    }

    /**
     * Checks, if such phone number exists
     *
     * @param phoneNumber to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsPhoneNumber(String phoneNumber) {
        Specification userSpecification = new FindUserByPhoneNumberSpecification(phoneNumber);
        return tourRepository.isExistsQuery(userSpecification);
    }

    /**
     * Checks, if such login exists
     *
     * @param login to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsLogin(String login) {
        Specification userSpecification = new FindUserByLoginSpecification(login);
        return userRepository.isExistsQuery(userSpecification);
    }

    /**
     * Checks, if such tour name exists
     *
     * @param tourName to check
     * @return true, if phone number exists, and false - if not
     */
    public boolean isExistsTourName(String tourName) {
        Specification specification = new FindTourByNameSpecification(tourName);
        return tourRepository.isExistsQuery(specification);
    }
}
