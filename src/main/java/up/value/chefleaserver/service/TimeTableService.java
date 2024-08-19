package up.value.chefleaserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import up.value.chefleaserver.domain.TimeTable;
import up.value.chefleaserver.repository.TimeTableRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeTableService {

    private final TimeTableRepository timeTableRepository;

    @Transactional(readOnly = true)
    public TimeTable findByIdOrThrow(Long timeTableId) {
        return timeTableRepository.findById(timeTableId).orElseThrow(RuntimeException::new);
    }
}
