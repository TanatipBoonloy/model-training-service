package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.service.UserMusicService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class UserMusicServiceImp implements UserMusicService {
    @Override
    public List<String> getUserNotListenedMusic(int userId) {
        return null;
    }
}
