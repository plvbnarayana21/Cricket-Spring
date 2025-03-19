
package com.example.cricket.repository;

import com.example.cricket.Beans.PointsTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointsTableRepository extends MongoRepository<PointsTable, String> {
    PointsTable findByTournamentId(String tournamentId);
}