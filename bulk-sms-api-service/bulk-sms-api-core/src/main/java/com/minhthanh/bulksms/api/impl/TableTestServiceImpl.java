package com.minhthanh.bulksms.api.impl;

import com.minhthanh.bulk.jpa.entities.TableTest;
import com.minhthanh.bulk.jpa.repositories.TableTestRepos;
import com.minhthanh.bulk.models.TableTestModel;
import com.minhthanh.bulksms.api.core.TableTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyenchu on 7/28/16.
 */
@Service
@Transactional
public class TableTestServiceImpl implements TableTestService {
    @Autowired
    TableTestRepos tableTestRepos;

    @Override
    public List<TableTestModel> getAllModels() {
        Iterable<TableTest> all = tableTestRepos.findAll();
        List<TableTestModel> models = new ArrayList<>();
        all.forEach(element -> {
            TableTestModel model = new TableTestModel();
            model.id = element.id;
            model.column = element.mycolumn;
            models.add(model);
        });
        return models;
    }

    @Override
    public void save(TableTestModel model) {
        TableTest test = new TableTest();
        test.mycolumn = model.column;
        tableTestRepos.save(test);
    }
}
