package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.UserMusic;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;

/**
 * Created by Jo on 3/26/2017.
 */
@Singleton
@Transactional
public interface UserMusicRepository extends CrudRepository<UserMusic, Integer> {
}
