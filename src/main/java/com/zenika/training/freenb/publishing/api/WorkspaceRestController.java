package com.zenika.training.freenb.publishing.api;

import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
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
        WorkspaceId id = createWorkspaceService.execute(new Workspace(new FreelanceHostId(request.hostId()), new Capacity(request.capacity())));
        return ResponseEntity.created(URI.create("/v1/workspaces/" + id.value())).build();
    }
}
