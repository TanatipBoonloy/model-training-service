package kmitl.sp.smp.util;

import kmitl.sp.smp.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Created by Jo on 3/27/2017.
 */
public class ThrowExceptionUtil<T> {
    private String persistentClassName;

    public ThrowExceptionUtil(Class<T> persistentClass) {
        this.persistentClassName = persistentClass.getSimpleName();
    }

    public List<T> checkIfListIsNull(List<T> data) {
        if (data.size() < 1) {
            throw new ResourceNotFoundException("" + persistentClassName + " Not Found");
        }
        return data;
    }

    public T checkIfItemIsNull(T data) {
        if (data == null) {
            throw new ResourceNotFoundException("" + persistentClassName + " Not Found");
        }
        return data;
    }
}
