package com.sylleryum.meajudaaajudar.repository;

import com.sylleryum.meajudaaajudar.entity.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Long> {


}
