package ru.isys.groupwagering.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.isys.groupwagering.model.entity.Wagering;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

/**
 * dao for table "Wagering"
 *
 * @author a.rahmaev
 * @version 2.0
 */
public interface WageringDAO extends CrudRepository<Wagering, Integer> {

    List<Wagering> findAll();

   // Wagering findById(int wageringId);

    long count();


    void delete(Wagering wagering);

    Page<Wagering> findAll(Pageable pageable);
}
