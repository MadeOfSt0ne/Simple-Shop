package example.Simple.Shop.repository;

import example.Simple.Shop.model.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Keyword getKeywordById(Long keywordId);
}
