package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.shared.AggregateRoot;

import java.time.LocalDate;
import java.util.Set;


public class AvailableOffer extends AggregateRoot<OfferId> {

    private final HostId host;
    private final Planning planning;

    public AvailableOffer(HostId host, OfferId offerId, Seats seats, Set<LocalDate> days) {
        super(offerId);
        this.host = host;
        this.planning = Planning.fromListOfDays(days, seats);
    }


    public Reservation book(PeriodCriteria period) {
        Reservation reservation = new Reservation(id, host, period);
        planning.decrementSeatsFor(period);
        return reservation;
    }

    public boolean isApproved() {
        return false;
    }

    public void bookRefused(PeriodCriteria periodCriteria) {
        planning.incrementSeatsFor(periodCriteria);
    }


    public Planning getPlanning() {
        return this.planning;
    }

    public boolean containPeriod(PeriodCriteria period) {
        return planning.containPeriod(period);
    }

    public Seats getAvailableSeatsForDay(LocalDate day) {
        return planning.getSeatsOf(day);
    }

    public boolean hasFreeSeatsFor(PeriodCriteria period) {
        return planning.hasFreeSeatsFor(period);
    }
}
