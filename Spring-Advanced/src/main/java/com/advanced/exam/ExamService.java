package com.advanced.exam;

import com.advanced.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    @Trace
    public void result(String itemId) {
        examRepository.save(itemId);
    }

}
