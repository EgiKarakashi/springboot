package com.example.springboot_demo.service;

import com.example.springboot_demo.entities.Spid;
import com.example.springboot_demo.entities.Status;
import com.example.springboot_demo.entities.User;
import com.example.springboot_demo.repository.SpidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SpidService {


    private SpidRepository spidRepository;

    public List<Spid> getSpidByUserId(User user) {
        return spidRepository.findSpidById(user);
    }

    @Transactional
    public Spid createSpid(Spid spid) throws Exception {
        List<Spid> spids = spidRepository.findSpidById(spid.getUser());
        if (spids.size() > 0) {
            throw new Exception("User is already created");
        }
        spid.setUser(spid.getId());
        return spidRepository.save(spid);
    }

    public Iterable<Spid> retreiveAllSpids() {
        return spidRepository.findAll();
    }

    public Spid changeSpidStatus(Spid spid) {
        spid.setStatus(Status.READY_FOR_REVIEW);
        return spidRepository.save(spid);
    }

    public Spid searchSpid(User user) throws Exception {
        List<Spid> spid = spidRepository.findSpidById(user);
        if (spid.size() <= 0) {
            throw new Exception("No SPID for the selected user exists");
        }
        return spid.get(0);
    }

    public void deleteSpid(Spid spid) throws Exception {
        if (spid.getStatus() != Status.PENDING) {
            throw new Exception("You cannot delete this SPID");
        }
        spidRepository.delete(spid);
    }

}
