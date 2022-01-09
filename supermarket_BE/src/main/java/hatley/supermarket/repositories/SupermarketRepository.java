package hatley.supermarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hatley.supermarket.entities.Supermarket;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Integer> {

}
