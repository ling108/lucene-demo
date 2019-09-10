package com.leon.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/***
 *
 * @Author:Leon
 * @Description:itheima
 * @date: 2019/4/7 10:36
 *
 ****/
public class SearchIndexTest {

    /****
     * 实现索引搜索
     */
    @Test
    public void testSearch() throws Exception{
        //创建分词器
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();

        //创建Query搜索对象   Query:分装搜索条件
        //1.默认搜索域设置
        //2.分词器
        QueryParser queryParser = new QueryParser("desc",analyzer);
        //搜索条件  name:美 AND name:国          name=美 AND name=国
        Query query = queryParser.parse("name:美国");

        //创建Directory流对象,声明索引库位置
        Directory directory = FSDirectory.open(new File("D:/index").toPath());

        //创建索引读取对象IndexReader   IndexReader:负责读取索引库数据
        IndexReader indexReader = DirectoryReader.open(directory);

        //创建索引搜索对象IndexSearcher  IndexSearcher:负责在索引库中实现搜索操作
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //使用索引搜索对象，执行搜索，返回结果集TopDocs     此时是去索引域中搜索数据
        //第1个参数：搜索对象
        //第2个参数：搜索几条记录
        TopDocs topDocs = indexSearcher.search(query, 2);

        //获取scoreDocs
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //解析结果集  参数：对应的下标
            Document document = indexSearcher.doc(scoreDoc.doc);
            System.out.println(document.get("name"));
            System.out.println(document.get("id"));
            System.out.println("===========================");
        }

        //释放资源
        indexReader.close();
    }

}
