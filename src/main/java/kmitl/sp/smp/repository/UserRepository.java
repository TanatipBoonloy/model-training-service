package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;

/**
 * Created by Jo on 4/3/2017.
 */
@Singleton
@Transactional
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByUserId(String userId);
    User findByFacebookId(String facebookId);
}
