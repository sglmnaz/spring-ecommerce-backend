package com.synclab.ecommerce.service.image;

import com.synclab.ecommerce.model.Image;
import com.synclab.ecommerce.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImplementation implements ImageService {

    @Autowired
    private ImageRepository repository;

    @Override
    public Image insert(Image image) {
        return repository.save(image);
    }

    @Override
    public Image findById(String id) {
        return repository.findById(id).get();
    }

    @Override
    public Image update(Image image) {
        return repository.save(image);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
