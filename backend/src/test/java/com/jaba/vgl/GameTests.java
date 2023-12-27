package com.jaba.vgl;

import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.CompanyDto;
import com.jaba.vgl.models.dto.GameDto;
import com.jaba.vgl.models.dto.GameWithCompanyDto;
import com.jaba.vgl.models.entities.Company;
import com.jaba.vgl.resources.DatabaseSetupExtension;
import com.jaba.vgl.resources.TestConfiguration;
import com.jaba.vgl.services.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true"
)
@ContextConfiguration(
        classes = {TestConfiguration.class}
)
@ExtendWith(DatabaseSetupExtension.class)
@ActiveProfiles("dev")
public class GameTests {
    private final static Logger logger = LogManager.getLogger(GameTests.class);

    private final GameServiceImpl gameService;
    private final GameDetailsServiceImpl gameDetailsService;
    private final ReviewServiceImpl reviewService;
    private final CompanyServiceImpl companyService;
    private final UserServiceImpl userService;

    @Autowired
    public GameTests(GameServiceImpl gameService,
                     GameDetailsServiceImpl gameDetailsService,
                     ReviewServiceImpl reviewService,
                     CompanyServiceImpl companyService,
                     UserServiceImpl userService) {
        this.gameService = gameService;
        this.gameDetailsService = gameDetailsService;
        this.reviewService = reviewService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @BeforeEach
    public void setup() {
        CompanyDto companyDto = new CompanyDto(
                1l,
                "Blizzard",
                "",
                Collections.emptyList()
        );

        companyService.saveCompany(companyDto);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Deleting all DB entries...");

        gameService.truncateTable();
        gameDetailsService.truncateTable();
        reviewService.truncateTable();
        companyService.truncateTable();
//        userService.truncateTable();
    }

    @Test
    @Order(1)
    @DisplayName("Add game to DB and fetch from DB.")
    void addGameTest() {
        GameDto gameDto = new GameDto(
                "World of Warcraft - Wrath of Lich King.",
                "best game ever made.",
                5.0f,
                GameGenre.RPG,
                false,
                "02.04.2000"
        );

        CompanyDto companyDto = new CompanyDto(
                1l,
                "Blizzard",
                "",
                Collections.emptyList()
        );

        GameWithCompanyDto gameWithCompanyDto = new GameWithCompanyDto(
                gameDto,
                companyDto
        );

        gameService.createGame(gameWithCompanyDto);

        //Fetch from DB...
        GameWithCompanyDto data = gameService.getGame("World of Warcraft - Wrath of Lich King.");

        logger.info(gameWithCompanyDto.toString());
        assertNotNull(data);
    }
}
