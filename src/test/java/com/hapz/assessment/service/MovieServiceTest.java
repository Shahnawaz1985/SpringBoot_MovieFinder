package com.hapz.assessment.service;

import com.hapz.assessment.HapzTechnicalAssessmentApplication;
import com.hapz.assessment.model.SearchCache;
import com.hapz.assessment.model.SearchCacheMovie;
import com.hapz.assessment.repository.SearchCacheRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hapz.assessment.service.MovieService;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HapzTechnicalAssessmentApplication.class)
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SearchCacheRepository searchCacheRepository;

    @Before
    public void setUp() {
        searchCacheRepository.deleteAll();
    }

    @Test
    public void testQueryHarryPotter() {
        System.out.println("Test Query: Harry Potter");

        String query = "Harry Potter";
        String[] expResult = {"'Harry Potter': Behind the Magic", "50 Greatest Harry Potter Moments", "Achter de schermen bij 'Harry Potter en de orde van de feniks'", "An Exclusive Inside Look at 'Harry Potter and the Order of the Phoenix'", "Big Movie Premiere: Harry Potter & the Deathly Hallows Pt. 1", "Big Movie Premiere: Harry Potter and the Half Blood Prince", "Creating the World of Harry Potter, Part 1: The Magic Begins", "Creating the World of Harry Potter, Part 2: Characters", "Creating the World of Harry Potter, Part 3: Creatures", "Creating the World of Harry Potter, Part 4: Sound and Music", "Creating the World of Harry Potter, Part 5: Evolution", "Creating the World of Harry Potter, Part 6: Magical Effects", "Creating the World of Harry Potter, Part 7: Story", "Creating the World of Harry Potter, Part 8: Growing Up", "Daniel Radcliffe: Being Harry Potter", "Discovering the Real World of Harry Potter", "Do You Wanna Build a Horcrux?: Harry Potter/Frozen Parody", "Drunk Harry Potter", "Drunk Man Meets Harry Potter Parody", "Harry Potter & the Half Blood Prince: T4 Premiere Special", "Harry Potter Casts a revealing spell", "Harry Potter Parent Teacher Conference", "Harry Potter Spoof Movie", "Harry Potter and the Chamber of Secrets", "Harry Potter and the Deathly Hallows Pt 2 T4 Premiere Special", "Harry Potter and the Deathly Hallows T4 Premiere Special", "Harry Potter and the Deathly Hallows: Part 1", "Harry Potter and the Deathly Hallows: Part 2", "Harry Potter and the Deathly Hallows: Part I", "Harry Potter and the Deathly Hallows: Part II", "Harry Potter and the Escape from Gringotts", "Harry Potter and the Forbidden Journey", "Harry Potter and the Goblet of Fire", "Harry Potter and the Half-Blood Prince", "Harry Potter and the Music Video Parody (About Hermione!)", "Harry Potter and the Order of the Phoenix", "Harry Potter and the Order of the Phoenix: T4 Movie Special", "Harry Potter and the Prisoner of Azkaban", "Harry Potter and the Sorcerer's Stone", "Harry Potter and the Ten Years Later", "Harry Potter and the Unlikely Collaboration", "Harry Potter at the Castle: Magic at Midnight", "Harry Potter for Kinect", "Harry Potter in the Hood", "Harry Potter v. Voldemort", "Harry Potter y los 7 pecados capitales", "Harry Potter és a bölcsek köve", "Harry Potter: Hogwarts Challenge", "Harry Potter: Quidditch World Cup", "Harry Potter: The Magic Lives On", "Harry Potter: The Making of Diagon Alley", "Harry Potter: Witchcraft Repackaged", "Harry Potter: Wizarding World", "Hufflepuff: A Harry Potter Rap Parody", "I Am Harry Potter", "Inside 'Harry Potter and the Goblet of Fire'", "JK Rowling and the Harry Potter Express", "Lego Creator: Harry Potter", "Lego Creator: Harry Potter and the Chamber of Secrets", "Lego Harry Potter: Years 1-4", "Lego Harry Potter: Years 5-7", "Life After Hogwarts: Episode 1 - Harry Potter Goes to Therapy", "Nizard Harry Potter Rap", "R-Rated Harry Potter", "Rufus Beck, die deutsche Hörbuch-Stimme von Harry Potter", "The Hidden Secrets of Harry Potter", "The Magical World of Harry Potter: The Unauthorized Story of J.K. Rowling", "The Seekers Guide to Harry Potter", "Warner Bros. Studio Tour London: The Making of Harry Potter"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQuerySpiderman() {
        System.out.println("Test Query: Spiderman");

        String query = "Spiderman";
        String[] expResult = {"Amazing Spiderman Syndrome", "Fighting, Flying and Driving: The Stunts of Spiderman 3", "Hollywood's Master Storytellers: Spiderman Live", "Italian Spiderman", "Spiderman", "Spiderman 5", "Spiderman and Grandma", "Spiderman in Cannes", "Superman, Spiderman or Batman", "The Amazing Spiderman T4 Premiere Special", "The Death of Spiderman", "They Call Me Spiderman"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQueryAtlantis() {
        System.out.println("Test Query: Atlantis");

        String query = "Atlantis";
        String[] expResult = {"Aquaman: Battle for Atlantis", "Atlantis the City of Sin"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQueryBehindTheScenes() {
        System.out.println("Test Query: Behind the Scenes");

        String query = "Behind the Scenes";
        String[] expResult = {"Behind the Scenes of 'Sin the Movie'", "Behind the Scenes of Walk Tall Stand Strong", "Behind the Scenes of the Most Fascinating Waterworld on Earth: The Great Backwaters, Kerala.", "Walk & Talk - The West Wing Reunion: Behind the Scenes"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQueryCanada() {
        System.out.println("Test Query: Canada");

        String query = "Canada";
        String[] expResult = {"Canada's Walk of Fame", "Canada's Walk of Fame: Celebrating 15 Years"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQueryDialogo() {
        System.out.println("Test Query: Diálogo");

        String query = "Diálogo";
        String[] expResult = {"A - Diálogo sin fronteras"};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    @Test
    public void testQueryNoResults() {
        System.out.println("Test Query: No Results");

        String query = "No Results";
        String[] expResult = {};

        String[] result = movieService.getMovieTitles(query);

        assertNotNull(result);
        assertArrayEquals(expResult, result);

        assertArrayEquals(expResult, getMovieTitleFromCache(query));

        String[] cacheResult = movieService.getMovieTitles(query);

        assertNotNull(cacheResult);
        assertArrayEquals(result, cacheResult);
    }

    private String[] getMovieTitleFromCache(String query) {

        return searchCacheRepository.findAll()
                .stream()
                .filter(searchCache -> query.equals(searchCache.getQuery()))
                .findFirst()
                .map(SearchCache::getSearchResults)
                .map(searchCacheMovies -> searchCacheMovies.stream().map(SearchCacheMovie::getMovieTitle).toArray(String[]::new))
                .orElse(null);

    }
}
