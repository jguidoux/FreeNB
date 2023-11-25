package com.zenika.training.freenb.publishing.domain.workspace;

import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;

public interface Workspaces {

    Workspace findBy(IdWorkspace idWorkspace);

    IdWorkspace create(Workspace newWorkspace);

}
