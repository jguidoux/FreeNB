package com.zenika.training.freenb.reservation.api;

import java.util.Set;

public record OfferPublished(String hostId, String offerId, int capacity, Set<java.time.LocalDate> planning) {
}
