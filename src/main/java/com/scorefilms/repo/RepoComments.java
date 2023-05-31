package com.scorefilms.repo;

import com.scorefilms.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoComments extends JpaRepository<Comments, Long> {
}
