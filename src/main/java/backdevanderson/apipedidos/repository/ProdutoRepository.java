package backdevanderson.apipedidos.repository;

import backdevanderson.apipedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {


    Produto findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
}
