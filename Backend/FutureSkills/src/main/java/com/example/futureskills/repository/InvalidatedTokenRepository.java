package com.example.futureskills.repository;

import com.example.futureskills.entity.InvalidatedToken;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {

    // Truy vấn các token hết hạn với phân trang
    @Query("SELECT t FROM InvalidatedToken t WHERE t.expiryTime < :now")
    Page<InvalidatedToken> findExpiredTokens(@Param("now") Date now, Pageable pageable);

    // Xóa các token hết hạn
    @Modifying
    @Transactional
    @Query("DELETE FROM InvalidatedToken t WHERE t.expiryTime < :now")
    int deleteExpiredTokens(@Param("now") Date now);

}
