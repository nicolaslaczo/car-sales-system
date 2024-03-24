package com.app.demo.services;

import com.app.demo.entity.Part;
import com.app.demo.handler.PartNotFoundException;
import com.app.demo.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartService {
    @Autowired
    private PartRepository partRepository;


    public List<Part> getAllPartsInStorage() {
        return partRepository.findAll();
    }


    public Part getPartByPartNum(String partNum) {
        Part part = partRepository.findByPartNum(partNum);
        if (part == null) {
            throw new PartNotFoundException(partNum);
        }
        return part;
    }

    public void addPartToStorage(Part part) {
        partRepository.save(part);
    }

    public void deletePartByPartNum(String partNum) {
        Part part = partRepository.findByPartNum(partNum);
        if (part != null) {
            partRepository.deleteByPartNum(partNum);
        } else {
            throw new PartNotFoundException(partNum);
        }
    }

    public void updatePartPrice(String partNum,Part part) {
        Part existingPart = partRepository.findByPartNum(partNum);
        if (existingPart != null) {
            existingPart.setPartPrice(part.getPartPrice());
        } else {
            throw new PartNotFoundException(partNum);
        }
    }

































}
