package ru.isys.groupwagering.component;

import ru.isys.groupwagering.model.dao.TypeOfWageringDAO;
import ru.isys.groupwagering.model.entity.TypeOfWagering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class TypeOfWageringComponent {

    private static Logger logger = Logger.getLogger(TypeOfWageringComponent.class.getName());

    @Autowired
    private TypeOfWageringDAO typeOfWageringDAO;

    public List<TypeOfWagering> getAllTypes() {
        return typeOfWageringDAO.findAll();
    }
}
