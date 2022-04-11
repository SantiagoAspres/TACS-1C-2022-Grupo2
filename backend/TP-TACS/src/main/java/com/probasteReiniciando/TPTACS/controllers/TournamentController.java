package com.probasteReiniciando.TPTACS.controllers;

import com.probasteReiniciando.TPTACS.dto.TournamentDto;
import com.probasteReiniciando.TPTACS.functions.JSONWrapper;
import com.probasteReiniciando.TPTACS.functions.TournamentConverter;
import com.probasteReiniciando.TPTACS.services.tournament.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping({ "/tournaments" })
public class TournamentController {


    @Autowired
    TournamentService tournamentService;

    @Autowired
    TournamentConverter tournamentConverter;

    @GetMapping(produces = "application/json")
    public ResponseEntity<JSONWrapper> publicTournaments() {
        return ResponseEntity.ok(new JSONWrapper<>(tournamentService.getPublicTournaments()));
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<JSONWrapper<TournamentDto>> singleTournaments(@PathVariable int id) {
        //TODO FALTA HACER EL WRAPER

        return ResponseEntity.ok(new JSONWrapper<>(List.of(tournamentConverter.convertTournamentToDto(tournamentService.getTournamentById(id)))));

    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<JSONWrapper<TournamentDto>> createTorneo(@RequestBody TournamentDto tournament){
        // DEBERIA DEVOLVER SOLO EL ID
        return ResponseEntity.ok(new JSONWrapper<>(List.of(tournamentConverter.convertTournamentToDto(tournamentService.postTournament(tournament)))));
    }
}
