package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.SuggestedMusic;

import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
public interface SuggestedMusicService {
    List<SuggestedMusic> getSuggestedMusicByUserId(int userId);

    List<Object[]> getAttributeObjects(int userId);
}
