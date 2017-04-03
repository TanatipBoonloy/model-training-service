package kmitl.sp.smp.service.Imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kmitl.sp.smp.entity.Model;
import kmitl.sp.smp.entity.MusicInformation;
import kmitl.sp.smp.entity.User;
import kmitl.sp.smp.model.server.response.base.UserModel;
import kmitl.sp.smp.service.*;
import kmitl.sp.smp.util.ConvertClassUtils;
import kmitl.sp.smp.util.ThrowExceptionUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class ControllerServiceImp implements ControllerService {
    private final UserService userService;
    private final NeuralNetworkService neuralNetworkService;
    private final ModelService modelService;
    private final SuggestedMusicService suggestedMusicService;
    private final MusicInformationService musicInformationService;

    @Inject
    public ControllerServiceImp(UserService userService, NeuralNetworkService neuralNetworkService,
                                ModelService modelService, SuggestedMusicService suggestedMusicService,
                                MusicInformationService musicInformationService) {
        this.userService = userService;
        this.neuralNetworkService = neuralNetworkService;
        this.modelService = modelService;
        this.suggestedMusicService = suggestedMusicService;
        this.musicInformationService = musicInformationService;
    }

    @Override
    public Boolean getUserSuggestedMusic(String userId) {
        User user = userService.getUserByUserId(userId);
        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);

        // get user information (user's model && user's listened songs)
        Model model = modelService.getLatestModel(user.getId());
        List<Object[]> attributes = suggestedMusicService.getAttributeObjects(user.getId());

        // train network
        ObjectMapper objectMapper = new ObjectMapper();
        UserModel userModel;
        if (model != null) {
            UserModel toTrain;
            try {
                toTrain = objectMapper.readValue(model.getWeightProfile(), UserModel.class);
            } catch (IOException e) {
                throw new InternalError("cannot convert json to object");
            }

            userModel = neuralNetworkService.retrainModel(attributes, toTrain);
        } else {
            userModel = neuralNetworkService.trainNewModel(attributes);
        }

        // save network
        String weightProfile;
        try {
            weightProfile = objectMapper.writeValueAsString(userModel);
        } catch (JsonProcessingException e) {
            throw new InternalError("cannot convert object to json string");
        }
        modelService.saveNewModel(user.getId(), weightProfile);

        // get user's unknown musics
        List<String> musicIds = suggestedMusicService.getUnknownMusics(user.getId());
        List<MusicInformation> musicInformationList = musicInformationService.getMusicInformationsByIds(musicIds);
        List<Object[]> suggestAttributes = ConvertClassUtils.converMusicInformationListToAttributesList(musicInformationList);

        List<String> suggestedMusicList = neuralNetworkService.getSuggestedSong(suggestAttributes, userModel, musicIds);
        suggestedMusicService.saveSuggestedList(user.getId(), suggestedMusicList);
        return true;
    }

//    @Override
//    public Boolean trainNewUserModelProfile(String userId) {
//        User user = userService.getUserByUserId(userId);
//        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);
//        List<Object[]> attributes = suggestedMusicService.getAttributeObjects(user.getId());
//        String weightProfile = trainingService.trainNewModel(attributes);
//        modelService.saveNewModel(user.getId(), weightProfile);
//        return true;
//    }
//
//    @Override
//    public Boolean retrainUserModelProfile(String userId) {
//        User user = userService.getUserByUserId(userId);
//        new ThrowExceptionUtil<>(User.class).checkIfItemIsNull(user);
//        Model model = modelService.getLatestModel(user.getId());
//        List<Object[]> attributes = suggestedMusicService.getAttributeObjects(user.getId());
//        String weightProfile = trainingService.retrainModel(attributes, model);
//        modelService.saveNewModel(user.getId(), weightProfile);
//        return true;
//    }
}
