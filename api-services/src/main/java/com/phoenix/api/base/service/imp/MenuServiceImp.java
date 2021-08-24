package com.phoenix.api.base.service.imp;

import com.phoenix.api.base.constant.BeanIds;
import com.phoenix.api.base.entities.ExceptionEntity;
import com.phoenix.api.base.repositories.MenuRepository;
import com.phoenix.api.base.service.MenuService;
import com.phoenix.api.core.service.AbstractBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service(BeanIds.MENU_SERVICES)
@Log4j2
public class MenuServiceImp extends AbstractBaseService implements MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    protected MenuServiceImp(
            @Qualifier(BeanIds.ALL_EXCEPTION) List<ExceptionEntity> exceptionEntities,
            @Qualifier(BeanIds.MENU_REPOSITORY_IMP) MenuRepository menuRepository
    ) {
        super(exceptionEntities);
        this.menuRepository = menuRepository;
    }

    @Override
    public List findAll() {
        HttpSession session = httpSessionFactory.getObject();
        System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));

        return menuRepository.findAll();
    }
}
