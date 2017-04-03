package kmitl.sp.smp.service.Imp;

import kmitl.sp.smp.model.server.response.base.UserModel;
import kmitl.sp.smp.service.NeuralNetworkService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Jo on 4/3/2017.
 */
@Service
public class NeuralNetworkServiceImp implements NeuralNetworkService {
    private final int inputNodeSize = 13;
    private final int outputNodeSize = 2;
    private final int hiddenNodeSize = 11;

    private final int epochesSize = 8400;
    private final double learningRate = 0.2;

    @Override
    public List<String> getSuggestedSong(List<Object[]> attributes, UserModel userModel,List<String> musicIds) {
        double[][] w12 = userModel.getW12();
        double[][] w23 = userModel.getW23();

        double[] l1 = new double[hiddenNodeSize];
        double[] l2 = new double[outputNodeSize];

        List<String> suggestedList = new ArrayList<>();
        for(int dataIter = 0 ; dataIter < attributes.size() && suggestedList.size() < 8; dataIter ++) {

            // calculate l1 output
            for (int i = 0; i < hiddenNodeSize; i++) {
                for (int j = 0; j < inputNodeSize; j++) {
                    l1[i] += w12[j][i] * getDoubleFromObject(attributes.get(dataIter)[j]);
                }
                l1[i] = sigmoid(l1[i]);
            }

            // calculate l2 output
            for (int i = 0; i < outputNodeSize; i++) {
                for (int j = 0; j < hiddenNodeSize; j++) {
                    l2[i] += w23[j][i] * l1[j];
                }
                l2[i] = sigmoid(l2[i]);
            }

            if(l2[0] >= l2[1]) {
                suggestedList.add(musicIds.get(dataIter));
            }
        }
        return suggestedList;
    }

    @Override
    public UserModel trainNewModel(List<Object[]> attributes) {
        // initial weight
        double[][] w12 = new double[inputNodeSize][hiddenNodeSize];
        double[][] w23 = new double[hiddenNodeSize][outputNodeSize];

        // random initial weight value
        // random w12
        for (int i = 0; i < inputNodeSize; i++) {
            for (int j = 0; j < hiddenNodeSize; j++) {
                w12[i][j] = ThreadLocalRandom.current().nextDouble();
            }
        }

        // random w23
        for (int i = 0; i < hiddenNodeSize; i++) {
            for (int j = 0; j < outputNodeSize; j++) {
                w23[i][j] = ThreadLocalRandom.current().nextDouble();
            }
        }

        return trainModel(attributes, w12, w23);
    }

    @Override
    public UserModel retrainModel(List<Object[]> attributes, UserModel userModel) {
        double[][] w12 = userModel.getW12();
        double[][] w23 = userModel.getW23();

        return trainModel(attributes, w12, w23);
    }

    private UserModel trainModel(List<Object[]> attributes, double[][] w12, double[][] w23) {

        // initial each layer
        double[] l1 = new double[hiddenNodeSize];
        double[] l2 = new double[outputNodeSize];

        // training Model
        for (int epochIter = 0; epochIter < epochesSize; epochIter++) {
            int dataSize = attributes.get(0).length;
            for (int dataIter = 0; dataIter < attributes.size(); dataIter++) {
                double[] l2Err = new double[outputNodeSize];
                double[] l1Err = new double[hiddenNodeSize];

                // calculate l1 output
                for (int i = 0; i < hiddenNodeSize; i++) {
                    for (int j = 0; j < inputNodeSize; j++) {

//                        l1[i] += w12[j][i] * (double) attributes.get(dataIter)[j];
                        l1[i] += (w12[j][i] * getDoubleFromObject(attributes.get(dataIter)[j]));
                    }
                    l1[i] = sigmoid(l1[i]);
                }

                // calculate l2 output
                for (int i = 0; i < outputNodeSize; i++) {
                    for (int j = 0; j < hiddenNodeSize; j++) {
                        l2[i] += (w23[j][i] * l1[j]);
                    }
                    l2[i] = sigmoid(l2[i]);
                }

                // calculate l2 error
                for (int i = 0; i < outputNodeSize; i++) {
//                    l2Err[i] = derivSigmoid((double) attributes.get(dataIter)[dataSize - (2 - i)] - l2[i]);
                    l2Err[i] = derivSigmoid((getDoubleFromObject(attributes.get(dataIter)[dataSize - (2 - i)]) - l2[i]));
                }

                // calculate l1 error
                for (int i = 0; i < hiddenNodeSize; i++) {
                    for (int j = 0; j < outputNodeSize; j++) {
                        l1Err[i] += (l2Err[j] * w23[i][j]);
                    }
                    l1Err[i] = derivSigmoid(l1Err[i]);
                }

                // train weight w12
                for (int i = 0; i < inputNodeSize; i++) {
                    for (int j = 0; j < hiddenNodeSize; j++) {
//                        w12[i][j] += learningRate * l1Err[j] * (double) attributes.get(dataIter)[i];
                        w12[i][j] += (learningRate * l1Err[j] * getDoubleFromObject(attributes.get(dataIter)[i]));
                    }
                }

                // train weight w23
                for (int i = 0; i < hiddenNodeSize; i++) {
                    for (int j = 0; j < outputNodeSize; j++) {
                        w23[i][j] += (learningRate * l2Err[j] * l1[i]);
                    }
                }
            }
        }

        UserModel userModel = new UserModel();
        userModel.setW12(w12);
        userModel.setW23(w23);
        return userModel;
    }

    private double getDoubleFromObject(Object obj) {
        if (obj instanceof Double) {
            return (double) obj;
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).doubleValue();
        } else if (obj instanceof Integer) {
            return (double) (int) obj;
        } else if (obj instanceof BigInteger) {
            return ((BigInteger) obj).doubleValue();
        } else if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        } else {
            throw new InternalError("cannot cast type");
        }
    }

    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private double derivSigmoid(double x) {
        return x * (1.0 - x);
    }
}
