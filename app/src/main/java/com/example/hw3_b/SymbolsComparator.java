package com.example.hw3_b;

import java.util.Comparator;

public class SymbolsComparator implements Comparator<Films> {
    @Override
    public int compare(Films film1, Films film2) {
        return film1.getName().toLowerCase().compareTo(film2.getName().toLowerCase());
    }
}
