package dev.be.pharmacyfind.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pharmacyfind.pharmacy.entity.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
