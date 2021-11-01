package com.ceyentra.task.dao;

import com.ceyentra.task.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items,Integer> {
}
