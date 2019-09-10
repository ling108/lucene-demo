package com.leon.test;

import com.leon.dao.BookDao;
import com.leon.dao.impl.BookDaoImpl;
import com.leon.domain.Book;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @Author:Leon
 * @Description:itheima
 * @date: 2019/4/7 9:43
 * 创建索引数据
 ****/
public class CreateIndexTest {

    //创建BookDao实例
    private BookDao bookDao = new BookDaoImpl();


    /****
     * 创建索引实现
     */
    @Test
    public void testCreateIndexDemo() throws Exception {
        //采集数据
        List<Book> books = bookDao.queryBookList();

        List<Document> documents = new ArrayList<Document>();
        for (Book book : books) {
            //创建Document文档对象
            Document document = new Document();
            //属性->域
            //不分词   、   根据ID搜索、  存储
            //document.add(new TextField("id",book.getId().toString(), Field.Store.YES));
            document.add(new StringField("id",book.getId().toString(), Field.Store.YES));


            //不分词   、   不搜索   、   存储
            //document.add(new TextField("pic",book.getPic(), Field.Store.YES));
            document.add(new StoredField("pic",book.getPic()));

            //分词    、     搜索     、   不存在
            document.add(new TextField("desc",book.getDesc(), Field.Store.NO));

            //分词、不分词|  搜索     、    存储
            //document.add(new TextField("price",book.getPrice().toString(), Field.Store.YES));
            document.add(new FloatField("price",book.getPrice(), Field.Store.YES));

            //分词   、   搜索    、存储
            document.add(new TextField("name",book.getName(), Field.Store.YES));
            documents.add(document);
        }

        //创建分析器（分词器）
        //Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();

        //创建IndexWriterConfig配置信息类
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //创建Directory对象，声明索引库存储位置
        Directory directory = FSDirectory.open(new File("D:/index").toPath());

        //创建IndexWriter写入对象
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);

        //把Document写入到索引库中
        indexWriter.addDocuments(documents);

        //释放资源
        indexWriter.close();
    }


    /***
     * 创建索引
     */
    @Test
    public void testCreateIndex() throws Exception{
        //1.查询数据(MySQL映射的JavaBean)
        Book book = new Book();
        book.setPic("http://1.jpg");
        book.setDesc("好吃又好玩");
        book.setPrice(998f);
        book.setName("奥利奥");
        book.setId(1);

        //Document相当于Lucene索引库映射的JavaBean
        Document document = new Document();
        document.add(new TextField("id",book.getId().toString(), Field.Store.YES));
        document.add(new TextField("pic",book.getPic(), Field.Store.YES));
        document.add(new TextField("desc",book.getDesc(), Field.Store.YES));
        document.add(new TextField("price",book.getPrice().toString(), Field.Store.YES));
        document.add(new TextField("name",book.getName(), Field.Store.YES));

        //Analyzer:分词器   StandardAnalyzer:标准分词器
        Analyzer analyzer = new StandardAnalyzer();

        //Directory:指定索引库对象（指定索引库的位置）
        File file = new File("D:/index");
        Directory directory = FSDirectory.open(file.toPath());

        //IndexWriterConfig:配置当前往索引库中写的相关参数配置，例如：缓冲区大小
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);

        //2.将数据增加到索引库  IndexWriter:操作索引数据，可以实现索引数据的增加
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexWriter.addDocument(document);

        //关闭资源
        indexWriter.close();


    }
}
