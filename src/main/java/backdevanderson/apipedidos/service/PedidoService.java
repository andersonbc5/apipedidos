package backdevanderson.apipedidos.service;

import backdevanderson.apipedidos.dto.ItemPedidoRequestDTO;
import backdevanderson.apipedidos.dto.PedidoRequestDTO;
import backdevanderson.apipedidos.dto.PedidoResponseDTO;
import backdevanderson.apipedidos.exceptions.RecursoNaoEncontradoException;
import backdevanderson.apipedidos.exceptions.RegraDeNegocioException;
import backdevanderson.apipedidos.mapper.PedidoMapper;
import backdevanderson.apipedidos.model.Cliente;
import backdevanderson.apipedidos.model.ItemPedido;
import backdevanderson.apipedidos.model.Pedido;
import backdevanderson.apipedidos.model.Produto;
import backdevanderson.apipedidos.model.enums.StatusPedido;
import backdevanderson.apipedidos.repository.ClienteRepository;
import backdevanderson.apipedidos.repository.PedidoRepository;
import backdevanderson.apipedidos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapper pedidoMapper;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    @Transactional
    public PedidoResponseDTO cadastrarPedido(PedidoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente com id: " + dto.clienteId() + " não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.CRIADO);
        pedido.setValorTotal(BigDecimal.ZERO);

        List<ItemPedido> itensPedido = new ArrayList<>();
        BigDecimal valorTotalPedido = BigDecimal.ZERO;

        for (ItemPedidoRequestDTO itemDto : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDto.produtoId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com id: " + itemDto.produtoId() + " não encontrado"));

            if (produto.getEstoque() < itemDto.quantidade()) {
                throw new RegraDeNegocioException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualiza o estoque do produto
            produto.setEstoque(produto.getEstoque() - itemDto.quantidade());
            produtoRepository.save(produto); // Salva a atualização do estoque

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido); // Associa o item ao pedido
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.quantidade());
            itemPedido.setPrecoUnitario(produto.getPreco());

            itensPedido.add(itemPedido);
            valorTotalPedido = valorTotalPedido.add(itemPedido.getSubTotal());
        }

        pedido.setItens(itensPedido);
        pedido.setValorTotal(valorTotalPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoMapper.toDto(pedidoSalvo);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listarPedidos() {
        return pedidoMapper.toDtoList(pedidoRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscarPedidoPorId(UUID id) {
        return pedidoMapper.toDto(pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido com id: " + id + " não encontrado")));
    }


}
