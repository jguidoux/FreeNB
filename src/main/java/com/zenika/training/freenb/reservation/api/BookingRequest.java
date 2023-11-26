package com.zenika.training.freenb.reservation.api;

import java.time.LocalDate;

public record BookingRequest(LocalDate from, LocalDate to) {
}
