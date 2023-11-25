package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import com.zenika.training.freenb.publishing.infra.WorkspacesInMemory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateWorkspaceTest {


    @Test
    void should_create_workspace() {

        //given an existing freelance host
        IdFreelanceHost idHost = IdFreelanceHost.create();
        Capacity capacity = new Capacity(5);
        Workspaces workspaceRepo = new WorkspacesInMemory();
        CreateWorkspaceService createWorkspaceService = new CreateWorkspaceService(workspaceRepo);

        // when he want te create wa workspace
        Workspace newWorkspace = new Workspace(idHost, capacity);
        IdWorkspace idWorkspace = createWorkspaceService.execute(newWorkspace);


        // we can retrieve that workspace
        Workspace exsistingWorkspace = workspaceRepo.findBy(idWorkspace);
        assertThat(exsistingWorkspace.getIdHost()).isEqualTo(idHost);
        assertThat(exsistingWorkspace.getCapacity()).isEqualTo(new Capacity(5));

    }
}
