package com.df;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @author Loser
 * @date 2021年11月25日 16:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ElasticsearchApp.class})
public class IndexWriterTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引库
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("java2104");
        //设置索引参数
        createIndexRequest.settings("{\n" +
                "       \"number_of_shards\" : 2,\n" +
                "       \"number_of_replicas\" : 0\n" +
                "  }", XContentType.JSON);
        createIndexRequest.mapping("course", "{\r\n" +
                "  \"_source\": {\r\n" +
                "    \"excludes\":[\"description\"]\r\n" +
                "  }, \r\n" +
                " 	\"properties\": {\r\n" +
                "           \"name\": {\r\n" +
                "              \"type\": \"text\",\r\n" +
                "              \"analyzer\":\"ik_max_word\",\r\n" +
                "              \"search_analyzer\":\"ik_smart\"\r\n" +
                "           },\r\n" +
                "           \"description\": {\r\n" +
                "              \"type\": \"text\",\r\n" +
                "              \"analyzer\":\"ik_max_word\",\r\n" +
                "              \"search_analyzer\":\"ik_smart\"\r\n" +
                "           },\r\n" +
                "           \"studymodel\": {\r\n" +
                "              \"type\": \"keyword\"\r\n" +
                "           },\r\n" +
                "           \"price\": {\r\n" +
                "              \"type\": \"float\"\r\n" +
                "           },\r\n" +
                "           \"timestamp\": {\r\n" +
                "          		\"type\":   \"date\",\r\n" +
                "          		\"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"\r\n" +
                "        	}\r\n" +
                "  }\r\n" +
                "}", XContentType.JSON);
        //创建索引客户端
        IndicesClient indices = restHighLevelClient.indices();
        //创建响应对象
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest, RequestOptions.DEFAULT);
        //得到响应结果
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);
    }

    /**
     * 删除索引库
     * @throws IOException
     */
    @Test
    public void testDelIndex() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("java2104");
        //创建索引操作客户端
        IndicesClient indices = restHighLevelClient.indices();
        DeleteIndexResponse delete = indices.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        //得到响应结果
        boolean acknowledged = delete.isAcknowledged();
        System.out.println(acknowledged);
    }

    /**
     * 添加文档
     * @throws IOException
     */
    @Test
    public void testInsertIndex() throws IOException {
        IndexRequest indexRequest = new IndexRequest("java2014", "course", "1");
        indexRequest.source("{\n" +
                "  \"name\":\"python从入门到放弃\",\n" +
                "  \"description\":\"人生苦短，我用Python\",\n" +
                "  \"pic\":\"250.jpg\",\n" +
                "  \"studymodel\":\"201002\"\n" +
                "}",XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(index.toString());
    }

    /**
     * 批量添加文档
     * @throws IOException
     */
    @Test
    public void testInsertIndex2() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest("java2104", "course").source("{\n" +
                        "  \"name\":\"python从入门到放弃\",\n" +
                        "  \"description\":\"人生苦短，我用Python\",\n" +
                        "  \"pic\":\"250.jpg\",\n" +
                        "  \"studymodel\":\"201002\"\n" +
                        "}",
                XContentType.JSON));
        bulkRequest.add(new IndexRequest("java2104", "course").source("{\n" +
                        "  \"name\":\"java从入门到入土\",\n" +
                        "  \"description\":\"Java天下第一\",\n" +
                        "  \"studymodel\":\"201004\"\n" +
                        "}",
                XContentType.JSON));
        BulkResponse bulkResponse =
                restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());
    }

    /**
     * 修改文档
     * @throws IOException
     */
    @Test
    public void testUpdateDocument() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("java2104","course","2GURWn0BLvxJsoDkEBR6");
        updateRequest.doc("{\n" +
                "  \"pic\":\"girl.jpg\"\n" +
                "}",XContentType.JSON);
        UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.getResult());
    }

    /**
     * 删除文档
     * @throws IOException
     */
    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("java2104","course","2WURWn0BLvxJsoDkEBR6");
        DeleteResponse delete = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(delete.getResult());
    }
}
