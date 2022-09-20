package com.miguelet.miguelet.persistence.repository;

import com.miguelet.miguelet.persistence.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
