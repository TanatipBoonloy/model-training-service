package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.User;
import kmitl.sp.smp.service.*;
import kmitl.sp.smp.util.ThrowExceptionUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class ControllerServiceImp implements ControllerService {
    private final UserService userService;
    private final TrainingService trainingService;
    private final ModelService modelService;
    private final SuggestedMusicService suggestedMusicService;

    @Inject
    public ControllerServiceImp(UserService userService, TrainingService trainingService, ModelService modelService, SuggestedMusicService suggestedMusicService) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.modelService = modelService;
        this.suggestedMusicService = suggestedMusicService;
    }

    @Override
    public Boolean trainNewUserModelProfile(String userId) {
        User user = userService.getUserByUserId(userId);
        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);
        List<Object[]> attributes = suggestedMusicService.getAttributeObjects(user.getId());
        String weightProfile = trainingService.trainNewModel(attributes);
        modelService.saveNewModel(user.getId(),weightProfile);
        return true;
    }
}
