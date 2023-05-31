package com.scorefilms.repo;

import com.scorefilms.models.Directors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoDirectors extends JpaRepository<Directors, Long> {
}
