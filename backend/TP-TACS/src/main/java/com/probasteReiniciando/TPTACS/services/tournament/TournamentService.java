package com.probasteReiniciando.TPTACS.services.tournament;

import com.probasteReiniciando.TPTACS.domain.Result;
import com.probasteReiniciando.TPTACS.domain.Tournament;
import com.probasteReiniciando.TPTACS.repositories.ITournamentRepository;
import com.probasteReiniciando.TPTACS.repositories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private ITournamentRepository tournamentRepository;

    public List<Tournament> getPublicTournaments() {
        return tournamentRepository.getPublicTournaments();
    }

    public Tournament getTournamentById(int id){
        return tournamentRepository.getTournament(id);
    }

    public List<Result> getResults(){
        return tournamentRepository.getResults();
    }

}
