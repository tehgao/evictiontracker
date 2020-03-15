package org.dsacleveland.evictiontracker.service.util;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.UploadTask;
import org.dsacleveland.evictiontracker.repository.UploadTaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UploadTaskService {
    private UploadTaskRepository uploadTaskRepository;

    public UploadTaskService(UploadTaskRepository uploadTaskRepository) {
        this.uploadTaskRepository = uploadTaskRepository;
    }

    public UUID startTask() {
        return this.uploadTaskRepository
                .save(UploadTask
                        .builder()
                        .startedOn(LocalDateTime.now())
                        .build())
                .getId();
    }

    public void closeTask(UUID id) {
        this.uploadTaskRepository
                .findById(id)
                .ifPresent(uploadTask -> {
                    uploadTask.setFinishedOn(LocalDateTime.now());
                    this.uploadTaskRepository.save(uploadTask);
                });
    }

    public List<UploadTask> getAllTasks() {
        return this.uploadTaskRepository.findAll();
    }

    public Optional<UploadTask> getOneTask(UUID id) {
        return this.uploadTaskRepository.findById(id);
    }
}
