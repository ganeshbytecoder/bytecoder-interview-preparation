package com.bytecoder.DSA.Part_1.SortAndSearchingAlgorithms;

import com.bytecoder.DSA.Part_1.Array_List.SortAndSearchingAlgorithms.SearchAlgorithmAndProblems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchAlgorithmAndProblemsTest {

    private SearchAlgorithmAndProblems searchAlgorithmAndProblems;

    @BeforeEach
    void setUp() {
        searchAlgorithmAndProblems = new SearchAlgorithmAndProblems();
    }

    @Test
    void findElementUsingBST() {
        int [] array = {1,3,4,5,6,8,9, 10};
        boolean result = searchAlgorithmAndProblems.findElementUsingBST(array, 7);
        Assertions.assertEquals(false, result);
    }
}