package com.zenika.training.freenb.publishing.api;

import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.Capacity;
import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.freenb.publishing.domain.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.Workspace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/workspaces")
public class WorkspaceRestController {

    private final CreateWorkspaceService createWorkspaceService;

    public WorkspaceRestController(CreateWorkspaceService createWorkspaceService) {
        this.createWorkspaceService = createWorkspaceService;
    }

    @PostMapping
    public ResponseEntity<String> createNewWorkspace(@RequestBody WorkspaceRequest request) {
        IdWorkspace id = createWorkspaceService.execute(new Workspace(new IdFreelanceHost(request.hostId()), new Capacity(request.capacity())));
        return ResponseEntity.created(URI.create("/v1/workspaces/" + id.value())).build();
    }
}
