package com.df;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author Loser
 * @date 2021年11月26日 11:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticsearchApp.class})
public class IndexSearchTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private SearchRequest searchRequest;
    private SearchResponse searchResponse;

    /**
     * 简单搜索
     * @throws IOException
     */
    @Test
    public void testSearch() throws IOException {
        GetRequest getRequest = new GetRequest("java2104","course","1");
        GetResponse doc = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        //是否存在
        boolean exists = doc.isExists();
        System.out.println(exists);
        //查询内容
        String sourceAsString = doc.getSourceAsString();
        System.out.println(sourceAsString);
    }

    /**
     * match_All搜索
     */
    @Before
    public void testInitSearch(){
        //搜索请求对象
        searchRequest = new SearchRequest("java2104");
        searchRequest.types("course");
    }

    //搜索type下的所有记录
    @Test
    public void testSearchAll() throws IOException {
        //搜索源构建对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索
        restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
    }

    @After
    public void testSearchDoc(){
        //搜索匹配的结果
        SearchHits hits = searchResponse.getHits();
        //搜索总记录数
        long totalHits = hits.totalHits;
        System.out.println("总共搜索到" + totalHits +"条文档");
        //匹配文档
        SearchHit[] hitsHits = hits.getHits();
        //日期格式化对象
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (SearchHit hitsHit : hitsHits) {
            //文档id
            String id = hitsHit.getId();
            System.out.println("id: " + id);
            //源文档内容
            String sourceAsString = hitsHit.getSourceAsString();
            System.out.println(sourceAsString);
            //获取高亮字段
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            if (highlightFields != null){
                HighlightField name = highlightFields.get("name");
                Text[] fragments = name.getFragments();
                System.out.println("高亮字段: " + fragments[0].toString());
            }
        }
    }

    /**
     * 分页查询
     * @throws IOException
     */
    @Test
    public void testSearchPage() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(5);
        searchSourceBuilder.sort("price", SortOrder.DESC);
        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        //执行搜索
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    /**
     * math查询,全文检索
     * @throws IOException
     */
    @Test
    public void testMathQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name","spring开发").operator(Operator.AND));

        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    /**
     * 关键字搜索
     */
    @Test
    public void testMultiMatch() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("开发","name","description"));
        //设置搜索源
        searchRequest.source(searchSourceBuilder);
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    /**
     * 布尔查询
     * @throws IOException
     */
    @Test
    public void testBooleanSearch() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //json条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //设置查询条件
        boolQueryBuilder.must(QueryBuilders.matchQuery("name","开发"));
        boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte("50").lte("100"));
        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    /**
     * filter查询
     * @throws IOException
     */
    @Test
    public void testFilterSearch() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name","开发"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte("50").lte("100"));
        searchSourceBuilder.query(boolQueryBuilder);

        searchRequest.source(searchSourceBuilder);
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }

    /**
     * 高亮查询
     * @throws IOException
     */
    @Test
    public void testHighLightQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("name","spring"));
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color = 'red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        searchSourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(searchSourceBuilder);
        searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.toString());
    }
}
