package kmitl.sp.smp.service;

import java.util.List;

/**
 * Created by Jo on 4/3/2017.
 */
public interface TrainingService {
    String trainNewModel(List<Object[]> attributes);
//    String retrainModel(int userId);
}
