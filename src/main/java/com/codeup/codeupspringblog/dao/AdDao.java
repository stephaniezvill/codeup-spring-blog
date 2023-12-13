package com.codeup.codeupspringblog.dao;

import com.codeup.codeupspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdDao extends JpaRepository<Ad, Long> {

}