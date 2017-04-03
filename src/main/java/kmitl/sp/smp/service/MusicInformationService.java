package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.MusicInformation;

import java.util.List;

/**
 * Created by Jo on 4/4/2017.
 */
public interface MusicInformationService {
    List<MusicInformation> getMusicInformationsByIds(List<String> musicIds);
}
