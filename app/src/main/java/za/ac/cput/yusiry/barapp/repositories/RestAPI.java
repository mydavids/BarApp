package za.ac.cput.yusiry.barapp.repositories;

import java.util.List;

/**
 * Created by Yusiry on 8/30/2016.
 */
public interface RestAPI<S, ID> {

    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}
