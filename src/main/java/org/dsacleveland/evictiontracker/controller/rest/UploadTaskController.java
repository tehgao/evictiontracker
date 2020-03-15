package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.model.evictiondata.entity.UploadTask;
import org.dsacleveland.evictiontracker.service.util.UploadTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/upload-tasks")
public class UploadTaskController {

    private UploadTaskService uploadTaskService;

    @Autowired
    public UploadTaskController(UploadTaskService uploadTaskService) {
        this.uploadTaskService = uploadTaskService;
    }

    @GetMapping
    public List<UploadTask> getAll() {
        return this.uploadTaskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UploadTask> getOne(@PathVariable UUID id) {
        return ok(this.uploadTaskService
                .getOneTask(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
        );
    }
}
