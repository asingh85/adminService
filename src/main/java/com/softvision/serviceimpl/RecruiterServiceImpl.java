package com.softvision.serviceimpl;

import com.softvision.model.Recruiter;
import com.softvision.repository.RecruiterRepository;
import com.softvision.service.RecruiterService;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RecruiterServiceImpl implements RecruiterService<Recruiter> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RecruiterServiceImpl.class);

    @Inject
    RecruiterRepository recruiterRepository;

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

        return recruiterRepository.insert(recruiter);
    }

    @Override
    public Recruiter updateRecruiter(Recruiter recruiter, String id) {
        LOGGER.info("RecruiterServiceImpl updateRecruiter()  ID is :{}", id);
        Optional<Recruiter> recruiterDAO = recruiterRepository.findById(id);
        if (recruiterDAO.isPresent()) {
            recruiter.setId(id);
            return recruiterRepository.save(recruiter);
        }
        LOGGER.info("RecruiterServiceImpl updateRecruiter()  Exit");
        return null;
    }

    @Override
    public void deleteRecruiter(String id) {
        recruiterRepository.deleteById(id);
    }

    @Override
    public void deleteAllRecruiter() {
        recruiterRepository.deleteAll();

    }
}
