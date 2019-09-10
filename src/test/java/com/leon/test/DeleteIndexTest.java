package com.leon.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/***
 *
 * @Author:Leon
 * @Description:itheima
 * @date: 2019/4/7 12:04
 * 索引删除实现
 ****/
public class DeleteIndexTest {


    /****
     * 删除索引数据
     */
    @Test
    public void testDeleteByTerm() throws Exception{
        //创建分析器（分词器）
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();

        //创建IndexWriterConfig配置信息类
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //创建Directory对象，声明索引库存储位置
        Directory directory = FSDirectory.open(new File("D:/index").toPath());

        //创建IndexWriter写入对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);

        //delete from table where name=?
        //Term:词项
        //indexWriter.deleteDocuments(new Term("name","美国"));
        indexWriter.deleteAll();

        indexWriter.commit();
    }

}
