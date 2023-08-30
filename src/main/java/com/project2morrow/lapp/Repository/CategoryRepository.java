package com.project2morrow.lapp.Repository;

import com.project2morrow.lapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
