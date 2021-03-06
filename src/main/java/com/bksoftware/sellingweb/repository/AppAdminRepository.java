package com.bksoftware.sellingweb.repository;

import com.bksoftware.sellingweb.entities.AppAdmin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppAdminRepository extends CrudRepository<AppAdmin,Integer> {
    AppAdmin findByUsername(String username);

    AppAdmin findByUsernameAndPassword(String username, String password);

    List<AppAdmin> findAll();
}
