package up.value.chefleaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import up.value.chefleaserver.domain.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
