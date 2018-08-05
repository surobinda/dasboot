package com.boot.controller;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1/")
public class ShipwreckController {

    Logger logger = LoggerFactory.getLogger(ShipwreckController.class);

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @RequestMapping(value="shipwrecks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Shipwreck> list() {
        logger.debug("list method has been invoked");
        return shipwreckRepository.findAll();
        //return ShipwreckStub.list();
    }

    @RequestMapping(value="shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        logger.debug("create method has been invoked");
        return shipwreckRepository.saveAndFlush(shipwreck);
        //return ShipwreckStub.create(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shipwreck get(@PathVariable Long id) {
        logger.debug("update method has been invoked");
        //return ShipwreckStub.update(id, shipwreck);
        return shipwreckRepository.findById(id).get();
    }


    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
        logger.debug("update method has been invoked");
        Shipwreck targetShipwreck = shipwreckRepository.findById(id).get();
        //return ShipwreckStub.update(id, shipwreck);
        BeanUtils.copyProperties(shipwreck, targetShipwreck);
        return shipwreckRepository.saveAndFlush(targetShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Shipwreck delete(@PathVariable Long id) {
        logger.debug("delete method has been invoked");
        //return ShipwreckStub.delete(id);
        Shipwreck shipwreck = shipwreckRepository.findById(id).get();
        shipwreckRepository.delete(shipwreck);
        return shipwreck;
    }

}
