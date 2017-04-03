package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.SuggestedMusic;
import kmitl.sp.smp.repository.SuggestedMusicRepository;
import kmitl.sp.smp.service.SuggestedMusicService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class SuggestedMusicServiceImp implements SuggestedMusicService{
    private final SuggestedMusicRepository suggestedMusicRepository;

    @Inject
    public SuggestedMusicServiceImp(SuggestedMusicRepository suggestedMusicRepository){
        this.suggestedMusicRepository = suggestedMusicRepository;
    }

    @Override
    public List<SuggestedMusic> getSuggestedMusicByUserId(int userId) {
        return suggestedMusicRepository.findByUserId(userId);
    }

    @Override
    public List<Object[]> getAttributeObjects(int userId) {
        return suggestedMusicRepository.getAttributesObjects(userId);
    }
}
