package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.entity.Model;
import kmitl.sp.smp.repository.ModelRepository;
import kmitl.sp.smp.service.ModelService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

/**
 * Created by Jo on 4/4/2017.
 */
@Service
public class ModelServiceImp implements ModelService {
    private final ModelRepository modelRepository;

    @Inject
    public ModelServiceImp(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model saveNewModel(Model model) {
        return save(model);
    }

    @Override
    public Model saveNewModel(int userId, String weightProfile) {
        return save(userId, weightProfile);
    }

    @Override
    public Model getLatestModel(int userId) {
        return modelRepository.getLatestModelByUserId(userId);
    }

    private Model save(int userId, String weightProfile) {
        Model model = new Model();
        model.setUserId(userId);
        model.setWeightProfile(weightProfile);
        model.setTimestamp(new Date());
        return modelRepository.save(model);
    }

    private Model save(Model model) {
        return modelRepository.save(model);
    }
}
