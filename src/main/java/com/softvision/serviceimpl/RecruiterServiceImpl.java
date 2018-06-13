package com.softvision.serviceimpl;

import com.softvision.model.Recruiter;
import com.softvision.repository.RecruiterRepository;
import com.softvision.service.RecruiterService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


@Component
public class RecruiterServiceImpl implements RecruiterService<Recruiter> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RecruiterServiceImpl.class);


    @Inject
    MongoTemplate mongoTemplate;

    @Inject
    RecruiterRepository recruiterRepository;

    @Override
    public List<Recruiter> getAllOnlyFalse() {

        Query query = new Query();
        query.addCriteria(Criteria.where("isDeleted").is(false));

        return mongoTemplate.find(query, Recruiter.class);
    }

    @Override
    public List<Recruiter> getAll() {
        return recruiterRepository.findAll();
    }

    @Override
    public Recruiter getRecruiter(String id) {

        LOGGER.info("RecruiterServiceImpl ID is :{}", id);
        Optional<Recruiter> optRecruiter = recruiterRepository.findById(id);
        if (optRecruiter.isPresent()) {
            return optRecruiter.get();

        }
        return null;
    }

    @Override
    public Recruiter addRecruiter(Recruiter recruiter) {
        if (recruiter != null) {
            LocalDateTime loc = LocalDateTime.now();
            recruiter.setCreatedDate(loc);
            recruiter.setModifiedDate(loc);
        }

        return recruiterRepository.insert(recruiter);
    }

    @Override
    public Recruiter updateRecruiter(Recruiter recruiter, String id) {
        LOGGER.info("RecruiterServiceImpl updateRecruiter()  ID is :{}", id);
        Optional<Recruiter> recruiterDAO = recruiterRepository.findById(id);
        if (recruiterDAO.isPresent()) {
            recruiter.setId(id);
            recruiter.setCreatedDate(recruiterDAO.get().getCreatedDate());
            recruiter.setModifiedDate(LocalDateTime.now());
            return recruiterRepository.save(recruiter);
        }
        LOGGER.info("RecruiterServiceImpl updateRecruiter()  Exit");
        return null;
    }

    @Override
    public void deleteRecruiter(String id) {
        Optional<Recruiter> recruiterDAO = recruiterRepository.findById(id);
        if (recruiterDAO.isPresent()) {
            Recruiter optRecruiter = recruiterDAO.get();
            optRecruiter.setDeleted(true);
            optRecruiter.setModifiedDate(LocalDateTime.now());
            recruiterRepository.save(optRecruiter);

        }

    }

    @Override
    public void deleteAllRecruiter() {

//        UpdateResult updateResult;
//        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
//            updateResult = mongoClient.getDatabase("admin").getCollection("recruiter")
//                    .updateMany(eq("isDeleted", false), new Document("$set", new Document("isDeleted", true)));
//        }

        List<Recruiter> recruiterList = recruiterRepository.findAll();
        recruiterList.forEach(recruiter ->
                recruiter.setDeleted(true)
        );
        recruiterRepository.saveAll(recruiterList);


    }
}
