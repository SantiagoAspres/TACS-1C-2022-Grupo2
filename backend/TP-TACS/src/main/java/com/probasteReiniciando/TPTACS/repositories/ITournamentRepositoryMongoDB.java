package com.probasteReiniciando.TPTACS.repositories;

import com.probasteReiniciando.TPTACS.domain.Result;
import com.probasteReiniciando.TPTACS.domain.Tournament;
import com.probasteReiniciando.TPTACS.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITournamentRepositoryMongoDB extends MongoRepository<Tournament, String> {

    @Query(value = "{'privacy': 'PUBLIC'}", count = true)
    Integer quantityOfPublicTournaments();

    @Query(value = "{'privacy': 'PRIVATE'}", count = true)
    Integer quantityOfPrivateTournaments(String username);

    @Query(value = "{'id': ?0}")
    Optional<Tournament> obtainTournament(String id);

    @Query(value = "{'id': ?0}", fields = "{ 'results' : 1}")
    List<Result> obtainResults(String id);

    @Query(value = "{'id': ?0}")
        //@Update("{ '$push' : { 'participants' : ?1 } }")
    void addUser(String tournamentId, User user);

    @Query(value = "{'id': ?0}", fields = "{ 'participants' : 1}")
    List<User> obtainParticipants(String tournamentId, Optional<String> orderBy, Optional<String> order);

    //TODO
    //void updateTournament(int tournamentId, Tournament tournament);

    //TODO
    @Query(value = "{'owner': ?0}")
    List<Tournament> findByOwner(String owner, int page, int limit);

    //TODO
    @Query(value = "{'privacy': 'PUBLIC'}")
    List<Tournament> obtainPublicTournaments(int page, int limit);

    //TODO
    @Query(value = "{'privacy': 'PRIVATE'}")
    List<Tournament> obtainPrivateTournaments(int page, int limit, String username);



}
