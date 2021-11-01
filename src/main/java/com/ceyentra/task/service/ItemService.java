package com.ceyentra.task.service;

import com.ceyentra.task.entity.Items;

import java.util.List;

public interface ItemService {
    public List<Items> findAll();

    public Items findBYId(int theId);

    public  void save(Items theItem);

    public void deleteById(int theId);
}
