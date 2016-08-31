package za.ac.cput.yusiry.barapp.services.impl;

import java.util.List;

import za.ac.cput.yusiry.barapp.model.Staff;
import za.ac.cput.yusiry.barapp.repositories.RestAPI;
import za.ac.cput.yusiry.barapp.repositories.rest.RestStaffAPI;
import za.ac.cput.yusiry.barapp.services.StaffService;

/**
 * Created by Yusiry on 8/30/2016.
 */
public class StaffServiceImpl implements StaffService {

    final RestAPI<Staff, Integer> rest = new RestStaffAPI();

    @Override
    public Staff findById(Integer id) {
        return rest.get(id);
    }

    @Override
    public String save(Staff entity) {
        return rest.post(entity);
    }

    @Override
    public String update(Staff entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(Staff entity) {
        return rest.delete(entity);
    }

    @Override
    public List<Staff> findAll() {
        return rest.getAll();
    }
}
