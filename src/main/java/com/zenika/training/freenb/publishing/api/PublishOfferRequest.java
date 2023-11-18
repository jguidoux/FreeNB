package com.zenika.training.freenb.publishing.api;

import java.time.LocalDate;

public record PublishOfferRequest(String workspaceId, LocalDate start, LocalDate end, int capacity) {
}
