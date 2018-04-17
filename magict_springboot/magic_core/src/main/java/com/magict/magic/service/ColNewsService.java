package com.magict.magic.service;

import java.util.List;

public interface ColNewsService {
    /**
     * @param urls 地址数组
     * @return
     */
    List<String> colNews(String[][] urls);
}
