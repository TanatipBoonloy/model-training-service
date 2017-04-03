package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.SuggestedMusic;
import kmitl.sp.smp.repository.SuggestedMusicRepository;
import kmitl.sp.smp.service.SuggestedMusicService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class SuggestedMusicServiceImp implements SuggestedMusicService {
    private final SuggestedMusicRepository suggestedMusicRepository;

    @Inject
    public SuggestedMusicServiceImp(SuggestedMusicRepository suggestedMusicRepository) {
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

    @Override
    public List<String> getUnknownMusics(int userId) {
        return suggestedMusicRepository.getUserUnknownMusicIds(userId);
    }

    @Override
    public Iterable<SuggestedMusic> saveSuggestedList(int userId, List<String> musicIds) {
        Date now = new Date();
        List<SuggestedMusic> suggestedMusicList = musicIds.stream()
                .map(id -> {
                    SuggestedMusic suggestedMusic = new SuggestedMusic();
                    suggestedMusic.setUserId(userId);
                    suggestedMusic.setMusicId(id);
                    suggestedMusic.setTimestamp(now);
                    return suggestedMusic;
                })
                .collect(Collectors.toList());

        return suggestedMusicRepository.save(suggestedMusicList);
    }
}
