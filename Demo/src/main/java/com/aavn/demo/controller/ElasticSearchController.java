package com.aavn.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.indices.IndexMissingException;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.aavn.demo.model.Result;
import com.aavn.demo.service.ClientProvider;

@Controller
@RequestMapping("/search")
public class ElasticSearchController extends AbstractController{

	private static final String INDEX_NAME = "neo4jdb";
	private static final String TYPE = "film";
	
	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		ModelAndView model = new ModelAndView("Hello");
		return model;
	}
		
	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Result> search(@PathVariable String name) {

		ArrayList<Result> resultLst = new ArrayList<Result>();
		Result result = null;
		
		try {
			
			ClientProvider clientProvider = ClientProvider.instance();
			clientProvider.prepareClient();
			
			QueryBuilder queryBuilder = QueryBuilders.fuzzyLikeThisQuery("name").likeText(name);
            
            SearchRequestBuilder searchRequestBuilder = clientProvider.getClient().prepareSearch(INDEX_NAME);
            searchRequestBuilder.setTypes(TYPE);
            searchRequestBuilder.setSearchType(SearchType.DEFAULT);
            searchRequestBuilder.setQuery(queryBuilder);
            searchRequestBuilder.setFrom(0).setSize(60).setExplain(true);

            SearchResponse response = searchRequestBuilder.execute().actionGet();

            if (response != null) {

                for (SearchHit hit : response.getHits()) {
                	if (hit.getSource().get("name") != null && hit.getSource().get("born") != null) {
                		result = new Result(hit.getSource().get("name").toString(), hit.getSource().get("born").toString());
                        resultLst.add(result);
					}
                }
            }
            
            clientProvider.getClient().close();

        } catch (IndexMissingException ex){
            System.out.println("IndexMissingException: " + ex.toString());
        }
		
		return resultLst;
	}
}