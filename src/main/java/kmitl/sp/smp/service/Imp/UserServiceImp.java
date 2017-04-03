package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.User;
import kmitl.sp.smp.repository.UserRepository;
import kmitl.sp.smp.service.UserService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by Jo on 4/4/2017.
 */
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Inject
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}
