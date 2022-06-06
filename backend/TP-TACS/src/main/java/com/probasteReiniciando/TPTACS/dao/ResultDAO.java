package com.probasteReiniciando.TPTACS.dao;


import com.probasteReiniciando.TPTACS.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ResultDAO {

    @Id
    private String id;
    private UserDAO user;
    private Integer points;
    private Language language;
    private LocalDate date;

}
