package ru.isys.groupwagering.model.dao;

import ru.isys.groupwagering.model.entity.TypicalUser;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * dao for table "Typical User"
 *
 * @author a.rahmaev
 * @version 2.0
 */
public interface TypicalUserDAO extends CrudRepository<TypicalUser, Integer> {

    long count();

    void delete(TypicalUser employee);

    TypicalUser findByLogin(String login);
}

