package com.example.imageservice.repository;

import com.example.imageservice.model.MetaDataImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MetaDataReposiorty extends MongoRepository<MetaDataImage, UUID> {
    MetaDataImage findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    List<MetaDataImage> findByUuidIn(List<UUID> uuids);

}
