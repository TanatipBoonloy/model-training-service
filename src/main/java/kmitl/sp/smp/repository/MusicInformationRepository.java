package kmitl.sp.smp.repository;

import kmitl.sp.smp.entity.MusicInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jo on 3/26/2017.
 */
@Singleton
@Transactional
public interface MusicInformationRepository extends CrudRepository<MusicInformation, String> {
    List<MusicInformation> findByIdIn(Collection<String> ids);
}
