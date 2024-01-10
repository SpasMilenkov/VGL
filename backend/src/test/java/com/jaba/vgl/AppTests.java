package com.jaba.vgl;

import com.jaba.vgl.exceptions.CompanyNotFoundException;
import com.jaba.vgl.exceptions.GameNotFoundException;
import com.jaba.vgl.exceptions.ReviewNotFoundException;
import com.jaba.vgl.models.GameGenre;
import com.jaba.vgl.models.dto.*;
import com.jaba.vgl.models.entities.GameDetails;
import com.jaba.vgl.resources.DatabaseSetupExtension;
import com.jaba.vgl.resources.TestConfiguration;
import com.jaba.vgl.services.impl.*;
import com.jaba.vgl.util.MockupDataGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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
public class AppTests {
    private final static Logger logger = LogManager.getLogger(GameTests.class);

    private final GameServiceImpl gameService;
    private final GameDetailsServiceImpl gameDetailsService;
    private final ReviewServiceImpl reviewService;
    private final CompanyServiceImpl companyService;
    private final UserServiceImpl userService;
    private final MockupDataGenerator mockupDataGenerator;

    @Autowired
    public AppTests(GameServiceImpl gameService,
                    GameDetailsServiceImpl gameDetailsService,
                    ReviewServiceImpl reviewService,
                    CompanyServiceImpl companyService,
                    UserServiceImpl userService,
                    MockupDataGenerator mockupDataGenerator) {
        this.gameService = gameService;
        this.gameDetailsService = gameDetailsService;
        this.reviewService = reviewService;
        this.companyService = companyService;
        this.userService = userService;
        this.mockupDataGenerator = mockupDataGenerator;
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

    /**
     * Tests
     */
    @Nested
    class GameTests {
        @BeforeEach
        public void setup() {
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            companyService.saveCompany(companyDto);
        }

        @Test
        @Order(1)
        @DisplayName("Add game to DB and fetch from DB.")
        void addAndSaveGameTest() {

            //Create entry...
            GameWithCompanyDto gameWithCompanyDto = mockupDataGenerator.generateGameWithCompanyDto();

            //Save entry...
            gameService.createGame(gameWithCompanyDto);

            //Fetch from DB...
            GameWithCompanyDto data = gameService.getGame(gameWithCompanyDto.gameDto().name(), gameWithCompanyDto.companyDto());

            logger.info(data.toString());
            assertNotNull(data);
        }

        @Test
        @Order(2)
        @DisplayName("Add game to DB, update it and fetch from DB.")
        void updateGameTest() {

            //Create entry...
            GameWithCompanyDto gameWithCompanyDto = mockupDataGenerator.generateGameWithCompanyDto();
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            //Save entry...
            gameService.createGame(gameWithCompanyDto);

            boolean isFavourite = true;
            Integer rating = 3;

            //Update entry (Name and company shouldn't change to count as the same game)
            GameDto gameDto2 = new GameDto(
                    "World of Warcraft - Wrath of Lich King.",
                    "meh game.",
                    rating,
                    GameGenre.RPG,
                    isFavourite,
                    "02.04.2000"
            );

            GameWithCompanyDto gameWithCompanyDto2 = new GameWithCompanyDto(
                    gameDto2,
                    companyDto
            );

            gameService.updateGame(gameWithCompanyDto2);

            //Fetch from DB...
            GameWithCompanyDto fetchedGameDto = gameService.getGame(gameWithCompanyDto.gameDto().name(), gameWithCompanyDto.companyDto());

            logger.info(fetchedGameDto.toString());

            boolean isGameUpdated = fetchedGameDto.gameDto().isFavourite() == isFavourite &&
                    fetchedGameDto.gameDto().rating().equals(rating);

            //Checking changes in the start of the test are set
            assertTrue(isGameUpdated);
        }

        @Test
        @Order(3)
        @DisplayName("Add game to DB, delete it and try fetch from DB.")
        void deleteGameTest() {

            //Create entry...
            GameWithCompanyDto gameWithCompanyDto = mockupDataGenerator.generateGameWithCompanyDto();

            //Save entry...
            gameService.createGame(gameWithCompanyDto);

            //Delete entry...
            Optional<Long> gameId = gameService.getGameId(gameWithCompanyDto.gameDto().name(), gameWithCompanyDto.companyDto());
            gameId.ifPresent(gameService::deleteGame);

            //Try fetch from DB...
            assertThrows(GameNotFoundException.class, () ->
                    gameService.getGame(gameWithCompanyDto.gameDto().name(), gameWithCompanyDto.companyDto()));
        }
    }

    @Nested
    class GameDetailsTests {
        @BeforeEach
        public void setup() {
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            companyService.saveCompany(companyDto);
        }

        @Test
        @Order(1)
        @DisplayName("Add game details to DB and fetch from DB.")
        void addAndSaveGameDetailsTest() {
            gameDetailsService.deleteGameDetails(1L); //Before each test remove already inserted game details...

            //Create entry...
            GameDetailsDto gameDetailsDto = mockupDataGenerator.generateGameDetailsDto();

            //Save entry...
            gameDetailsService.createGameDetails(gameDetailsDto);

            //Fetch from DB...
            GameDetailsWithReviewsDto fetchedGameDetails = gameDetailsService.getGameDetails(gameDetailsDto.name(), gameDetailsDto.company());

            logger.info(fetchedGameDetails.toString());
            assertNotNull(fetchedGameDetails);
        }

        @Test
        @Order(2)
        @DisplayName("Add game details to DB, update it and fetch from DB.")
        void updateGameTest() {

            //Create entry...
            GameDetailsDto gameDetailsDto = mockupDataGenerator.generateGameDetailsDto();

            //Save entry...
            gameDetailsService.createGameDetails(gameDetailsDto);

            boolean isFavourite = true;
            Integer rating = 3;
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            //Update entry (Name and company shouldn't change to count as the same game)
            GameDetailsDto gameDetailsDto2 = new GameDetailsDto(
                    "World of Warcraft - Wrath of Lich King.",
                    "best game ever made.",
                    rating,
                    GameGenre.RPG,
                    companyDto,
                    isFavourite,
                    "02.04.2000"
            );

            GameDetailsWithReviewsDto gameDetailsWithReviewsDto = new GameDetailsWithReviewsDto(
                    gameDetailsDto2,
                    companyDto,
                    new ArrayList<>()
            );

            gameDetailsService.updateGameDetails(gameDetailsWithReviewsDto);

            //Fetch from DB...
            GameDetailsWithReviewsDto fetchedGameDetails = gameDetailsService.getGameDetails(gameDetailsDto.name(), gameDetailsDto.company());

            boolean isGameUpdated = fetchedGameDetails.gameDetailsDto().isFavourite() == isFavourite &&
                    fetchedGameDetails.gameDetailsDto().rating().equals(rating);

            //Checking changes in the start of the test are set
            logger.info(fetchedGameDetails.toString());
            assertTrue(isGameUpdated);
        }

        @Test
        @Order(3)
        @DisplayName("Add game details to DB, delete it and try fetch from DB.")
        void deleteGameTest() {

            //Create entry...
            GameDetailsDto gameDetailsDto = mockupDataGenerator.generateGameDetailsDto();

            //Save entry...
            gameDetailsService.createGameDetails(gameDetailsDto);

            //Delete entry...
            Optional<Long> gameId = gameService.getGameId(gameDetailsDto.name(), gameDetailsDto.company());
            gameId.ifPresent(gameDetailsService::deleteGameDetails);

            //Try fetch from DB...
            assertThrows(GameNotFoundException.class, () ->
                    gameService.getGame(gameDetailsDto.name(), gameDetailsDto.company()));
        }
    }

    @Nested
    class ReviewTests {
        @BeforeEach
        public void setup() {
            GameDetailsWithReviewsDto gameDetailsDto = mockupDataGenerator.generateGameDetailsWithReviewDto();

            companyService.saveCompany(gameDetailsDto.companyDto());
            gameDetailsService.createGameDetails(gameDetailsDto.gameDetailsDto()); //Fixes foreign key missing for adding review in tests...
        }

        @Test
        @Order(1)
        @DisplayName("Add review to DB and fetch from DB.")
        void addAndSaveReviewTest() {

            //Create entry...
            ReviewDto reviewDto = mockupDataGenerator.generateReviewDto();

            //Save entry...
            reviewService.saveReview(reviewDto);

            //Fetch from DB...
            ReviewDto fetchedReviewDto = reviewService.getReview(reviewDto.id());

            boolean isSameReview = fetchedReviewDto.text().equals(reviewDto.text()) &&
                    fetchedReviewDto.title().equals(reviewDto.title()) &&
                    fetchedReviewDto.rating().equals(reviewDto.rating());

            logger.info(fetchedReviewDto.toString());
            assertTrue(isSameReview);
        }

        @Test
        @Order(2)
        @DisplayName("Add review to DB, update it and fetch from DB.")
        void updateReviewTest() {

            //Create entry...
            ReviewDto reviewDto = mockupDataGenerator.generateReviewDto();

            //Save entry...
            reviewService.saveReview(reviewDto);

            //Update entry (Id and gameId and userId shouldn't change to count as the same review)
            ReviewDto reviewDto2 = new ReviewDto(
                    1L,
                    1L,
                    1L,
                    "Bad game. Would NOT recommend",
                    "Bad graphics.",
                    1.0f
            );

            reviewService.updateReview(reviewDto2);

            //Fetch from DB...
            ReviewDto fetchedReviewDto = reviewService.getReview(reviewDto2.id());

            boolean isSameReview = fetchedReviewDto.text().equals(reviewDto2.text()) &&
                    fetchedReviewDto.title().equals(reviewDto2.title()) &&
                    fetchedReviewDto.rating().equals(reviewDto2.rating());

            logger.info(fetchedReviewDto.toString());
            assertTrue(isSameReview);
        }

        @Test
        @Order(3)
        @DisplayName("Add review to DB, delete it and try fetch from DB.")
        void deleteReviewTest() {

            //Create entry...
            ReviewDto reviewDto = mockupDataGenerator.generateReviewDto();

            //Save entry...
            reviewService.saveReview(reviewDto);

            //Delete entry...
            reviewService.deleteReview(reviewDto.id());

            //Try fetch from DB...
            assertThrows(ReviewNotFoundException.class, () -> reviewService.getReview(reviewDto.id()));
        }
    }

    @Nested
    class CompanyTests {

        @BeforeEach
        public void setup() {
            // Any setup required before each test (if needed)
        }

        @Test
        @Order(1)
        @DisplayName("Add company to DB and fetch from DB.")
        void addAndSaveCompanyTest() {
            // Create entry...
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            // Save entry...
            companyService.saveCompany(companyDto);

            // Fetch from DB...
            CompanyDto fetchedCompanyDto = companyService.getCompany(companyDto.id());

            logger.info(fetchedCompanyDto.toString());
            assertNotNull(fetchedCompanyDto);
            assertEquals(companyDto.name(), fetchedCompanyDto.name());
            assertEquals(companyDto.studio(), fetchedCompanyDto.studio());
        }

        @Test
        @Order(2)
        @DisplayName("Add company to DB, update it and fetch from DB.")
        void updateCompanyTest() {
            // Create entry...
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            // Save entry...
            companyService.saveCompany(companyDto);

            // Update entry...
            CompanyDto updatedCompanyDto = new CompanyDto(
                    companyDto.id(),
                    "Test Company INC",
                    "Test Studio",
                    companyDto.games()
            );

            companyService.updateCompany(updatedCompanyDto);

            // Fetch from DB...
            CompanyDto fetchedCompanyDto = companyService.getCompany(companyDto.id());
            logger.info(fetchedCompanyDto.toString());

            boolean isCompanyUpdated = updatedCompanyDto.name().equals(fetchedCompanyDto.name()) &&
                    updatedCompanyDto.studio().equals(fetchedCompanyDto.studio());

            assertTrue(isCompanyUpdated);
        }

        @Test
        @Order(3)
        @DisplayName("Add company to DB, delete it and try fetch from DB.")
        void deleteCompanyTest() {
            // Create entry...
            CompanyDto companyDto = mockupDataGenerator.generateCompanyDto();

            // Save entry...
            companyService.saveCompany(companyDto);

            // Delete entry...
            companyService.deleteCompany(companyDto.id());

            // Try fetch from DB...
            assertThrows(CompanyNotFoundException.class, () -> companyService.getCompany(companyDto.id()));
        }
    }
}
