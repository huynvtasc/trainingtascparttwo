package org.ngvhuy.test;

import org.ngvhuy.service.DatasetService;

public class DatasetServiceTest {
    public static void main(String[] args) {
        DatasetService datasetService = new DatasetService();
        datasetService.deleteNotInTransaction();
    }
}
