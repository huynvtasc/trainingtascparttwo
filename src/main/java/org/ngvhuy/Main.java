package org.ngvhuy;

import org.ngvhuy.service.DatasetService;

public class Main {
    public static void main(String[] args) {
        DatasetService datasetService = new DatasetService();
        datasetService.getDatasetValueThenGetDataset();
    }
}