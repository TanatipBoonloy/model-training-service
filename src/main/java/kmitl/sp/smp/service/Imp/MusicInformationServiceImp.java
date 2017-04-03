package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.repository.MusicInformationRepository;
import kmitl.sp.smp.service.MusicInformationService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Jo on 4/4/2017.
 */
@Service
public class MusicInformationServiceImp implements MusicInformationService {
    private final MusicInformationRepository musicInformationRepository;

    @Inject
    public MusicInformationServiceImp(MusicInformationRepository musicInformationRepository) {
        this.musicInformationRepository = musicInformationRepository;
    }

    @Override
    public List<MusicInformation> getMusicInformationsByIds(List<String> musicIds) {
        return musicInformationRepository.findByIdIn(musicIds);
    }
}
