package com.api.servicios;

import com.api.entidades.Chat;
import com.api.repositorios.IRepositorioChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioChat implements IServicioChat{
    @Autowired
    private IRepositorioChat iRepositorioChat;

    @Override
    public List<Chat> listaChat() {
        return this.iRepositorioChat.findAll();
    }

    @Override
    public Chat buscarChatPorId(Long id) {
        return this.iRepositorioChat.findById(id).orElse(null);
    }

    @Override
    public Chat guardarChat(Chat chat) {
        return this.iRepositorioChat.save(chat);
    }

    @Override
    public void eliminarChat(Long id) {
        this.iRepositorioChat.deleteById(id);
    }
}
