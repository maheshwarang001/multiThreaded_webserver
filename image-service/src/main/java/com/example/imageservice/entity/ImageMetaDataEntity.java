package com.example.imageservice.entity;

import com.example.imageservice.dao.MetaDataDao;
import com.example.imageservice.model.MetaDataImage;
import com.example.imageservice.repository.MetaDataReposiorty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@Component
public class ImageMetaDataEntity implements MetaDataDao {

    @Autowired
    MetaDataReposiorty metaDataReposiorty;

    @Override
    public void saveMetaData(MetaDataImage metaDataImage) {

            metaDataReposiorty.save(metaDataImage);
    }

    @Override
    public MetaDataImage getMetaData(UUID uuid) {
        return metaDataReposiorty.findByUuid(uuid);
    }

    @Override
    public List<MetaDataImage> findMetaByUsersID(List<UUID> id) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public boolean existsByUUID(UUID uuid) {

        return metaDataReposiorty.existsByUuid(uuid);
    }

    @Override
    public boolean exisitsByUrl(MetaDataImage metaDataImage) {
        MetaDataImage userData = getMetaData(metaDataImage.getUuid());
        return userData.getImages().equals(metaDataImage.getImages());
    }

    @Override
    public List<MetaDataImage> getAllData(List<UUID> uuids) {
        return metaDataReposiorty.findByUuidIn(uuids);
    }
}
