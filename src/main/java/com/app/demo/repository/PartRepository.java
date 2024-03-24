package com.app.demo.repository;

import com.app.demo.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,Integer> {

    Part findByPartNum(String partNum);

    Part deleteByPartNum(String partNum);
}
