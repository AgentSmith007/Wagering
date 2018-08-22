package ru.isys.groupwagering.model.dao;

import ru.isys.groupwagering.model.entity.TypeOfWagering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * dao interface for TypeOfWagering table.
 *
 * @author a.rahmaev
 * @version 2.0
 */
public interface TypeOfWageringDAO extends CrudRepository<TypeOfWagering, Integer> {
    /*
        /**
         * Create the query to database to give all entities what it have.
         * @return list of all entities from the table.
         */
    List<TypeOfWagering> findAll();

    /**
     * Search entity in the table with current id
     *
     * @return entity from table or null if entity with this id doesn't exist.
     */
    TypeOfWagering findById(int id);

    /**
     * Search entity in the table with current name
     *
     * @return entity from table or null if entity with this name doesn't exist.
     */
    TypeOfWagering findByName(String Name);


    /**
     * Update entity in database
     *
     * @return true if update have been finished successful
     */

    long count();

    void delete(TypeOfWagering employee);

}
