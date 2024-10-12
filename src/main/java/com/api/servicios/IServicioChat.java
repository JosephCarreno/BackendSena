package com.api.servicios;


import com.api.entidades.Chat;

import java.util.List;

public interface IServicioChat {
    List<Chat> listaChat();

    Chat buscarChatPorId(Long id);

    Chat guardarChat(Chat chat);

    void eliminarChat(Long id);
}
