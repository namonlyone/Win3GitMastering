package com.aavn.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.jdbc.Driver;
import org.neo4j.jdbc.Neo4jConnection;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.aavn.demo.model.Movie;

/**
 * @author nvhoang2
 * 
 */

@Component("tickerWriter")
public class Neo4jDataWriter implements ItemWriter<Movie> {

	/* Format for query string */
	String FORMAT_QUERY = "CREATE (:Movie {title:\"%s\", released:\"%s\", tagline:\"%s\"})";

	public void write(List<? extends Movie> items) throws Exception {

		// Create connection
		Neo4jConnection connect = new Driver().connect(
				"jdbc:neo4j://192.168.79.32:7474", new Properties());

		// Declare list query string.
		List<String> dataLoad = new ArrayList<String>();
		for (Movie report : items) {
			dataLoad.add(String.format(FORMAT_QUERY, report.getTitle(),
					report.getReleased(), report.getTagline()).replace("\'", "\\'"));
		}

		connect.createStatement()
				.executeQuery(StringUtils.join(dataLoad, "\n"));

	}
}
