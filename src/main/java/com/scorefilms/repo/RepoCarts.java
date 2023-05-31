package com.scorefilms.repo;

import com.scorefilms.models.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCarts extends JpaRepository<Carts, Long> {
}
