package com.example.imageservice.dao;

import com.example.imageservice.model.MetaDataImage;

import java.util.List;
import java.util.UUID;

public interface MetaDataDao {

    void saveMetaData(MetaDataImage metaDataImage);

    MetaDataImage getMetaData(UUID uuid);

    List<MetaDataImage> findMetaByUsersID(List<UUID> id);

    void delete(UUID uuid);


    boolean existsByUUID(UUID uuid);

    boolean exisitsByUrl(MetaDataImage metaDataImage);


    List<MetaDataImage> getAllData(List<UUID> uuids);

}
