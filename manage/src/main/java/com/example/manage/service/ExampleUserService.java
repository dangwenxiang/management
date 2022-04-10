package com.example.manage.service;

import com.example.manage.elastic.ExampleUser;
import com.example.manage.elastic.ExampleUserElasticsearchRep;
import com.google.common.collect.Lists;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @program: manage
 * @author: dwx
 * @create: 2019-09-06 11:12
 **/
@Service("exampleUserService")
public class ExampleUserService {
    //ElasticsearchRepository的扩展
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private ExampleUserElasticsearchRep exampleUserElasticsearchRep;
    public void foo(){
        BoolQueryBuilder builder = new BoolQueryBuilder();
        builder.must(QueryBuilders.termQuery("exampleUser.userName.keyWord","john"));
        //查询
        Iterable<ExampleUser> search = exampleUserElasticsearchRep.search(builder);
        //新增
        exampleUserElasticsearchRep.save(new ExampleUser());
        //小批量
        exampleUserElasticsearchRep.saveAll(Lists.newArrayList());
        //大批量
        elasticsearchTemplate.bulkIndex(new ArrayList<>());
    }
}
