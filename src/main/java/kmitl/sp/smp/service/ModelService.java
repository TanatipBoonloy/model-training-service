package kmitl.sp.smp.service;

import kmitl.sp.smp.entity.Model;

/**
 * Created by Jo on 4/4/2017.
 */
public interface ModelService {
    Model saveNewModel(Model model);

    Model saveNewModel(int userId, String weightProfile);

    Model getLatestModel(int userId);
}
