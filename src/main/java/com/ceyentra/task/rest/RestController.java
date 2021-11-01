package com.ceyentra.task.rest;


import com.ceyentra.task.entity.Items;
import com.ceyentra.task.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/items")
public class RestController {
    private ItemService itemService;

    @Autowired
    public RestController(ItemService theItemService){

        itemService = theItemService;
    }

    @GetMapping
    public List<Items> findAll() {

        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Items getItem(@PathVariable int id) {

        Items theItem =itemService.findBYId(id);
        if (theItem == null) {
            throw new RuntimeException("ItemCode not found - " + id);
        }

        return theItem;
    }

    @PostMapping()
    public Items addItems(@RequestBody Items theItem){
        theItem.setId(0);
        itemService.save(theItem);
        return theItem;
    }


    // add mapping for PUT /employees - update existing employee

    @PutMapping
    public Items updateItem(@RequestBody Items theItem) {

        itemService.save(theItem);

        return theItem;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/{ItemId}")
    public String deleteItem(@PathVariable int ItemId) {

        Items tempItem = itemService.findBYId(ItemId);

        // throw exception if null

        if (tempItem == null) {
            throw new RuntimeException("Item not found - " + ItemId);
        }

        itemService.deleteById(ItemId);

        return "Deleted item Code : " + ItemId;
    }
}
