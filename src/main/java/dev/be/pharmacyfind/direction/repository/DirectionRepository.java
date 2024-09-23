package dev.be.pharmacyfind.direction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.pharmacyfind.direction.entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}