package com.hapz.assessment.repository;

/* DO NOT CHANGE */
/* Please do not change the imported functions as you will be assessed based on your usage of the selected libraries, but you can import your own class */
import com.hapz.assessment.model.SearchCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/* Please do not change the imported functions as you will be assessed based on your usage of the selected libraries, but you can import your own class */
/* DO NOT CHANGE */

@Repository
public interface SearchCacheRepository extends JpaRepository<SearchCache, Long> {

    // TODO 4: Create function that retrieves a SearchCache by the query string
	
	
	 @Query("FROM SearchCache WHERE query = ?1") 
	 SearchCache findByQuery(String query);
	 

}
