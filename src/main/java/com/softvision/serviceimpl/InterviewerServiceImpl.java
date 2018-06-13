package com.softvision.serviceimpl;

import com.softvision.model.Interviewer;
import com.softvision.repository.InterviewerRepository;
import com.softvision.service.InterviewerService;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InterviewerServiceImpl implements InterviewerService<Interviewer> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(InterviewerServiceImpl.class);

    @Inject
    private InterviewerRepository interviewerRepository;


    @Override
    public List<Interviewer> getAll() {
        return interviewerRepository.findAll();
    }

    @Override
    public Interviewer getInterviewer(String id) {
        LOGGER.info("InterviewerServiceImpl ID is : {} ", id);
        Optional<Interviewer> optInterviewer = interviewerRepository.findById(id);
        if (optInterviewer.isPresent()) {
            return optInterviewer.get();
        }
        return null;
    }

    @Override
    public Interviewer addInterviewer(Interviewer interviewer) {
        return interviewerRepository.insert(interviewer);
    }

    @Override
    public Interviewer updateInterviewer(Interviewer interviewer, String id) {
        Optional<Interviewer> interviewerDAO = interviewerRepository.findById(id);
        if (interviewerDAO.isPresent()) {
            interviewer.setId(id);
            return interviewerRepository.save(interviewer);
        }
        return null;
    }

    @Override
    public void deleteInterviewer(String id) {
        Optional<Interviewer> interviwerDAO = interviewerRepository.findById(id);
        if (interviwerDAO.isPresent()) {
            Interviewer optInterviwer = interviwerDAO.get();
            optInterviwer.setDeleted(true);
            interviewerRepository.save(optInterviwer);
        }
    }

    @Override
    public void deleteAllInterviewers() {
        List<Interviewer> interviwerList = interviewerRepository.findAll();
        interviwerList.forEach(interviewer ->
                interviewer.setDeleted(true)
        );

        interviewerRepository.saveAll(interviwerList);
    }
}
