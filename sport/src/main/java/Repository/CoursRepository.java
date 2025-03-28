package Repository;

import Entité.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByCategorie(String categorie);
}
