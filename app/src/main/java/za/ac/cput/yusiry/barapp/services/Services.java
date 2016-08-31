package za.ac.cput.yusiry.barapp.services;

import java.util.List;

/**
 * Created by Yusiry on 8/30/2016.
 */
public interface Services<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();
}
