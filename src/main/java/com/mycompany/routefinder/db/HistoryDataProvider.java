/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.routefinder.db;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface HistoryDataProvider {
    void saveHistory(String logEntry);
    List<String> loadHistory();
    void clearHistory();
}
