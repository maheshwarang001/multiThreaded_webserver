package com.example.recommendationservice.service;


import com.example.recommendationservice.config.ImageService;
import com.example.recommendationservice.config.ProfileService;
import com.example.recommendationservice.model.MetaDataImage;
import com.example.recommendationservice.model.Metadata;
import com.example.recommendationservice.model.UserRecommend;
import com.example.recommendationservice.repository.RecommendationDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@Slf4j
public class RepoService {
    @Autowired
    ImageService imageService;

    @Autowired
    ProfileService profileService;

    @Autowired
    RecommendationDB recommendationDB;

    private volatile List<Metadata> metadata;
    private List<MetaDataImage> metaDataImage;

    public List<UserRecommend> filterUserCityDB(String city,int page, int size) throws InterruptedException {

        List<UUID> uuids = recommendationDB.findByCity(city, PageRequest.of(page,size));

        return provideList(uuids);
    }

    public List<UserRecommend> provideList (List<UUID> uuids) throws InterruptedException {

        metadata = new ArrayList<>();
        metaDataImage = new ArrayList<>();

        Thread thread1 = new Thread(() -> metaDataImage = getMetaDataFromImageService(uuids));
        Thread thread2 = new Thread(() -> metadata = getMetaDataFromProfileService(uuids));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            return iterateLists(metadata,metaDataImage);
        }catch (Exception e){
            throw e;
        }
    }

    private List<UserRecommend> iterateLists(List<Metadata> metadataProfile, List<MetaDataImage> metaDataImages) {

        Map<String, UserRecommend> userMap = new HashMap<>();

        for (Metadata m : metadataProfile) {
            if (m == null) continue;

            UserRecommend userRecommend = new UserRecommend();

            userRecommend.setUuid(m.getUuid());
            userRecommend.setFirstName(m.getFirstname());
            userRecommend.setLastName(m.getLastname());
            userRecommend.setAge(m.getAge());
            userRecommend.setGender(m.getUsersex());
            userRecommend.setPreferenceGender(m.getUserpreference());
            userRecommend.setCity(m.getCity());
            userRecommend.setCountry(m.getCountry());

            userMap.put(String.valueOf(userRecommend.getUuid()), userRecommend);
        }

        // Update UserRecommend objects with images from metaDataImages
        for (MetaDataImage it : metaDataImages) {
            if (it == null) continue;

            String uuid = String.valueOf(it.getUuid());
            if (userMap.containsKey(uuid)) {
                UserRecommend userRecommend = userMap.get(uuid);
                userRecommend.setImage(it.getImages());
            }
        }


        return new ArrayList<>(userMap.values());


    }


    private List<MetaDataImage> getMetaDataFromImageService(List<UUID> uuid) {
        return imageService.imageServiceRequestWebClient(uuid).block();
    }

    private List<Metadata> getMetaDataFromProfileService(List<UUID> uuid) {
        return profileService.imageServiceRequestWebClient(uuid).block();
    }
}
