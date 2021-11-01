package com.ceyentra.task.service;

import com.ceyentra.task.dao.ItemRepository;
import com.ceyentra.task.entity.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository theItemRepository){
        itemRepository = theItemRepository;
    }

    @Override
    public List<Items> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Items findBYId(int theId) {
        Optional<Items> result = itemRepository.findById(theId);
        Items theItem=null;
        if (result.isPresent()){
            theItem= result.get();

        }
        else {
            throw new RuntimeException("Did not fond the item code "+theId);
        }
        return theItem;

    }


    @Override
    public void save(Items theItem) {

        itemRepository.save(theItem);

    }


    @Override
    public void deleteById(int theId) {
        itemRepository.deleteById(theId);

    }
}
