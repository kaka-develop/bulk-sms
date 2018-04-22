package com.minhthanh.bulksms.api.core;

import com.minhthanh.bulk.models.TableTestModel;

import java.util.List;

/**
 * Created by luyenchu on 7/28/16.
 */
public interface TableTestService {
    List<TableTestModel> getAllModels();

    void save(TableTestModel model);
}
