package com.kingict.kingictintership2024java.repository;

import com.kingict.kingictintership2024java.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IImageRepository extends JpaRepository<Image, UUID> { }
