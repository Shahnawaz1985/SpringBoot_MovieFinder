package com.hapz.assessment.service.impl;

/* DO NOT CHANGE */
 /* Please do not change the imported functions as you will be assessed based on your usage of the selected libraries, but you can import your own class */
import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.text.*;
import java.math.*;
import java.util.logging.*;
import java.util.regex.*;
import java.net.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import com.hapz.assessment.model.*;
import com.hapz.assessment.repository.*;
import com.hapz.assessment.service.*;
import com.hapz.assessment.utils.ApplicationUtils;

import org.springframework.stereotype.*;

/* Please do not change the imported functions as you will be assessed based on your usage of the selected libraries, but you can import your own class */
 /* DO NOT CHANGE */
@Service
public class MovieServiceImpl implements MovieService {
	
	private static final Logger LOGGER = Logger.getLogger(MovieServiceImpl.class.getName());

    private SearchCacheRepository searchCacheRepository;

    public MovieServiceImpl(SearchCacheRepository searchCacheRepository) {
        this.searchCacheRepository = searchCacheRepository;
    }

    @Override
    public String[] getMovieTitles(String query) {
    	LOGGER.log(Level.INFO, "Entering method getMovieTitles");
        String[] movieTitles = this.getMovieTitlesFromCache(query);
        if (movieTitles == null) {
            movieTitles = this.getMovieTitlesFromApi(query);
            LOGGER.log(Level.INFO, "Movie Titles retrieved from API :" +Arrays.deepToString(movieTitles));
            this.storeMovieTitlesInCache(query, movieTitles);
        }
        LOGGER.log(Level.INFO, "Exiting method getMovieTitles");
        return movieTitles;
    }

    /**
     * Retrieves a unique, sorted list of movie titles from the API using the
     * provided query string
     *
     * @return unique, sorted list of movie titles
     */
    private String[] getMovieTitlesFromApi(String query) {
        // TODO 1: Implement function to retrieve a unique, sorted list of movie titles from the API using the provided query string
    	LOGGER.log(Level.INFO, "Entering method getMovieTitlesFromApi");
    	String response;
    	int startPage = 1;
    	int totalPages = Integer.MAX_VALUE;
    	List<String> titles = new ArrayList<>();
    	String apiURL = ApplicationUtils.API_URL;
		apiURL = apiURL.replaceAll(ApplicationUtils.API_KEY_VAR, ApplicationUtils.API_KEY);
		if(isEligibleForEncoding(query)) {
		try {
			query = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			LOGGER.log(Level.SEVERE, e1.getMessage());
		}
		}
		apiURL = apiURL.replace(" ", "%20");
		apiURL = apiURL.replaceAll(ApplicationUtils.API_QUERY_VAR, query);
		StringBuilder urlBuilder = new StringBuilder(apiURL);
    	while(startPage <= totalPages) {
    	try {
    		urlBuilder = new StringBuilder(apiURL);
    		System.out.println("startpage : "+startPage);
    		urlBuilder = urlBuilder.append("&page="+startPage);  		
    		LOGGER.log(Level.INFO, urlBuilder.toString());
    		URL urlObject = new URL(urlBuilder.toString());
			HttpURLConnection connection = (HttpURLConnection)urlObject.openConnection();
			connection.setRequestMethod(ApplicationUtils.REQUEST_METH_GET);
			connection.setRequestProperty(ApplicationUtils.CONTENT_TYPE, ApplicationUtils.CONTENT_TYPE_VAL);
			connection.setRequestProperty(ApplicationUtils.ACCEPT, ApplicationUtils.CONTENT_TYPE_VAL);
			String redirect = connection.getHeaderField("Location");
			if(null != redirect) {
				connection = (HttpURLConnection) new URL(redirect).openConnection();
			}
			BufferedReader in_reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((response = in_reader.readLine())!=null) {
				LOGGER.log(Level.INFO, response);
				JsonObject targetObject = new Gson().fromJson(response, JsonObject.class);
				totalPages = targetObject.get("total_pages").getAsInt();
				JsonArray data_array = targetObject.getAsJsonArray("data");
				
				for(int i=0; i<data_array.size(); i++) {
					String temp_title = data_array.get(i).getAsJsonObject().get("Title").getAsString();
					titles.add(temp_title);
				}							
			}
			in_reader.close();
			startPage++;
		} catch (MalformedURLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
    	}
    	LOGGER.log(Level.INFO, "Exiting method getMovieTitlesFromApi");
    	return titles.stream()
    		  .distinct()
    		  .sorted()
    		  .collect(Collectors.toList()).toArray(new String[0]);
    }
    
    private boolean isEligibleForEncoding(String queryString) {
    	Pattern p = Pattern.compile("^[a-z A-z]*$");
    	return p.matcher(queryString).find();
    }

    /**
     * Stores the provided list of movie titles a unique, sorted list of movie titles from the database using
     * the provided query string
     *
     * @
     * @return unique, sorted list of movie titles
     */
    private void storeMovieTitlesInCache(String query, String[] movieTitles) {
    	LOGGER.log(Level.INFO, "Entering method storeMovieTitlesInCache");
        SearchCache searchCache = new SearchCache();
        searchCache.setQuery(query);
        List<SearchCacheMovie> searchCacheMovieList = new ArrayList<>();
        searchCache.setSearchResults(searchCacheMovieList);
        SearchCacheMovie tempObj = null;
        for(String movieTitle : movieTitles) {
        	tempObj = new SearchCacheMovie();
        	tempObj.setMovieTitle(movieTitle);
        	searchCacheMovieList.add(tempObj);
        }        
        searchCacheRepository.save(searchCache);
        LOGGER.log(Level.INFO, "Exiting method storeMovieTitlesInCache");
    }

    /**
     * Retrieves a unique, sorted list of movie titles from the database using
     * the provided query string
     *
     * @return unique, sorted list of movie titles
     */
    private String[] getMovieTitlesFromCache(String query) {
        // TODO 3: Implement function to retrieve a unique, sorted list of movie titles from the cache using the provided query string
    	LOGGER.log(Level.INFO, "Entering method getMovieTitlesFromCache");
    	SearchCache searchCacheObj = searchCacheRepository.findByQuery(query);
    	List<String> movieTitles = null;
    	if(null != searchCacheObj) {
    		List<SearchCacheMovie> searchCacheMovieList = searchCacheObj.getSearchResults();
    		if(null != searchCacheMovieList && searchCacheMovieList.size()>0) {
    			movieTitles = new ArrayList<>();
    			for(SearchCacheMovie muv : searchCacheMovieList) {
    				movieTitles.add(muv.getMovieTitle());
    			}
    			return movieTitles.toArray(new String[0]);
    		}
    	}    	
        return null;
    }

}
 