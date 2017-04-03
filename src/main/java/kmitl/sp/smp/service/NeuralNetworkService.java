package kmitl.sp.smp.service;

import kmitl.sp.smp.model.server.response.base.UserModel;

import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
public interface NeuralNetworkService {
    UserModel trainNewModel(List<Object[]> attributes);

    UserModel retrainModel(List<Object[]> attributes, UserModel userModel);

    List<String> getSuggestedSong(List<Object[]> attributes, UserModel userModel, List<String> musicIds);
}
