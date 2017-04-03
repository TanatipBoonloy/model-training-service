package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Jo on 4/4/2017.
 */
@Singleton
@Transactional
public interface ModelRepository extends CrudRepository<Model, Integer> {
    List<Model> findByUserId(int userId);

    @Query(value = "SELECT * FROM model WHERE user_id = ?1 ORDER BY timestamp LIMIT 1 ", nativeQuery = true)
    Model getLatestModelByUserId(int userId);
}
