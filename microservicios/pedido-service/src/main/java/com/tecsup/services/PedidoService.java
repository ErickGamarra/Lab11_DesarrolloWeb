package com.tecsup.services;

import com.tecsup.client.ClienteClient;
import com.tecsup.client.ProductoClient;
import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.DetallePedido;
import com.tecsup.model.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteClient clienteClient;

    @Autowired
    private ProductoClient productoClient;

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {

        clienteClient.verificar(pedido.getIdCliente());

        if (pedido.getDetalles() != null) {
            for (DetallePedido detalle : pedido.getDetalles()) {
                productoClient.verificar(detalle.getIdProducto());
                detalle.setPedido(pedido);
            }
        }

        return repository.save(pedido);
    }

    public Pedido actualizar(Long id, Pedido pedido) {

        Pedido pedidoExistente = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedido no encontrado con id: " + id));

        pedidoExistente.setFecha(pedido.getFecha());
        pedidoExistente.setIdCliente(pedido.getIdCliente());
        pedidoExistente.setTotal(pedido.getTotal());

        return repository.save(pedidoExistente);
    }

    public void eliminar(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Pedido no encontrado con id: " + id));
        repository.delete(pedido);
    }

}
