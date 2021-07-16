package com.phoenix.api.repositories;

import com.phoenix.api.base.repositories.AbstractRepository;
import com.phoenix.api.entities.ProfileEntity;
import com.phoenix.business.domain.Profile;
import com.phoenix.structure.Pair;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

@Repository("ProfileRepositoryImp")
public class ProfileRepositoryImp extends AbstractRepository<ProfileEntity> {
    public ProfileRepositoryImp(EntityManager entityManager) {
        super(entityManager, ProfileEntity.class);
    }

    public Iterable<Profile> findAllProfile() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        String sql = "select p.code, p.gender, p.name, p.type, fu.username, fu.password\n" +
                "from profile p\n" +
                "         left join fw_user fu on p.account_id = fu.id";

        List<Object[]> queryResultList = executeNativeQuery(sql);

        List<Pair<String, Class>> params = new LinkedList<>();
        params.add(new Pair<>("code", String.class));
        params.add(new Pair<>("gender", String.class));
        params.add(new Pair<>("name", String.class));
        params.add(new Pair<>("type", String.class));
        params.add(new Pair<>("account", String.class));
        params.add(new Pair<>("password", String.class));

        return (List<Profile>) parseResult(queryResultList, params, Profile.class);
    }
}
