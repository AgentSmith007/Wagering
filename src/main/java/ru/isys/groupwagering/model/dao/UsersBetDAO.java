package ru.isys.groupwagering.model.dao;

import ru.isys.groupwagering.model.entity.UsersBet;
import org.springframework.data.repository.CrudRepository;
import ru.isys.groupwagering.model.entity.Wagering;


import java.util.List;

/**
 * dao for table "UsersBet"
 *
 * @author a.rahmaev
 * @version 2.0
 */
public interface UsersBetDAO extends CrudRepository<UsersBet, Integer> {
    List<UsersBet> findAll();

    UsersBet findById(int id);

    List<UsersBet> findByUsersAnswer(String Answer);

    List<UsersBet> findAllByWagering(Wagering wagering);

    long count();

    void delete(UsersBet employee);
}
